package pl.idedyk.japanese.dictionary2.api.helper;

import java.util.ArrayList;
import java.util.List;

import pl.idedyk.japanese.dictionary2.jmdict.xsd.GTypeEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.KanjiInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.LanguageSourceLsWaseiEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingInfoKanaType;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.Sense;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.JMdict.Entry;

public class Dictionary2HelperCommon {
	
	protected List<KanjiKanaPair> getKanjiKanaPairList(Entry entry) {
		return getKanjiKanaPairListStatic(entry);		
	}
	
	public static List<KanjiKanaPair> getKanjiKanaPairListStatic(Entry entry) {
		
		List<KanjiKanaPair> result = new ArrayList<>();
		
		//
		
		List<KanjiInfo> kanjiInfoList = entry.getKanjiInfoList();
		List<ReadingInfo> readingInfoList = entry.getReadingInfoList();
		
		// jesli nie ma kanji
		if (kanjiInfoList.size() == 0) {
			
			// wszystkie czytania do listy wynikowej
			for (ReadingInfo readingInfo : readingInfoList) {
				
				ReadingInfo.ReNokanji noKanji = readingInfo.getNoKanji();
				
				if (noKanji == null) {
										
					String kana = readingInfo.getKana().getValue();
					String romaji = readingInfo.getKana().getRomaji();
					
					ReadingInfoKanaType kanaType = readingInfo.getKana().getKanaType();
					
					//
					
					result.add(new KanjiKanaPair(null, kana, kanaType, romaji));
				}
			}
			
		} else {			
			// zlaczenie kanji z kana
			
			for (KanjiInfo kanjiInfo : kanjiInfoList) {
				for (ReadingInfo readingInfo : readingInfoList) {
					
					// pobierz kanji
					String kanji = kanjiInfo.getKanji();
											
					ReadingInfo.ReNokanji noKanji = readingInfo.getNoKanji();
					
					// jest pozycja kana nie laczy sie ze znakiem kanji
					if (noKanji != null) { 
						continue;
					}
					
					// pobierz kana
					String kana = readingInfo.getKana().getValue();
					String romaji = readingInfo.getKana().getRomaji();
					
					ReadingInfoKanaType kanaType = readingInfo.getKana().getKanaType();
					
					List<String> kanjiRestrictedListForKana = readingInfo.getKanjiRestrictionList();
					
					boolean isRestricted = true;
					
					// sprawdzanie, czy dany kana laczy sie z kanji
					if (kanjiRestrictedListForKana.size() == 0) { // brak restrykcji						
						isRestricted = false;
						
					} else { // sa jakies restrykcje, sprawdzamy, czy kanji znajduje sie na tej liscie					
						if (kanjiRestrictedListForKana.contains(kanji) == true) {
							isRestricted = false;
						}							
					}
					
					// to zlaczenie nie znajduje sie na liscie, omijamy je
					if (isRestricted == true) {
						continue; // omijamy to zlaczenie
					}
					
					// mamy pare
					result.add(new KanjiKanaPair(kanji, kana, kanaType, romaji));					
				}				
			}
		}
		
		// szukanie kana z no kanji
		for (ReadingInfo readingInfo : readingInfoList) {
			
			ReadingInfo.ReNokanji noKanji = readingInfo.getNoKanji();
			
			if (noKanji != null) {
								
				String kana = readingInfo.getKana().getValue();
				String romaji = readingInfo.getKana().getRomaji();
				
				ReadingInfoKanaType kanaType = readingInfo.getKana().getKanaType();
				
				//
				
				result.add(new KanjiKanaPair(null, kana, kanaType, romaji));
			}
		}
		
		// dopasowanie listy sense do danego kanji i kana
		List<Sense> entrySenseList = entry.getSenseList();
				
		for (KanjiKanaPair kanjiKanaPair : result) {
			
			String kanji = kanjiKanaPair.kanji;
			String kana = kanjiKanaPair.kana;
			
			// chodzimy po wszystkich sense i sprawdzamy, czy mozemy je dodac do naszej pary kanji i kana
			for (Sense sense : entrySenseList) {
				
				boolean isKanjiRestricted = true;
				
				if (sense.getRestrictedToKanjiList().size() == 0) {
					isKanjiRestricted = false;
					
				} else {
					if (sense.getRestrictedToKanjiList().contains(kanji) == true) {
						isKanjiRestricted = false;
					}
				}
				
				if (isKanjiRestricted == true) {
					continue; // ten sens nie bedzie wchodzil w sklad tej pary
				}	
				
				boolean isKanaRestricted = true;
				
				if (sense.getRestrictedToKanaList().size() == 0) {
					isKanaRestricted = false;
					
				} else {
					if (sense.getRestrictedToKanaList().contains(kana) == true) {
						isKanaRestricted = false;
					}
				}
				
				if (isKanaRestricted == true) {
					continue; // ten sens nie bedzie wchodzil w sklad tej pary
				}
				
				// dodajemy ten sens do danej pary				
				kanjiKanaPair.getSenseList().add(sense);
			}
		}
		
		return result;
	}
	
	public static KanjiKanaPair findKanjiKanaPair(List<KanjiKanaPair> kanjiKanaPairList, DictionaryEntry dictionaryEntry) {
		
		String dictionaryEntry$Kanji = dictionaryEntry.isKanjiExists() == true ? dictionaryEntry.getKanji() : "";
		String dictionaryEntry$Kana = dictionaryEntry.getKana() != null ? dictionaryEntry.getKana() : "";
		
		for (KanjiKanaPair kanjiKanaPair : kanjiKanaPairList) {
			
			String kanjiKanaPair$Kanji = kanjiKanaPair.kanji != null ? kanjiKanaPair.kanji : "";
			String kanjiKanaPair$Kana = kanjiKanaPair.kana != null ? kanjiKanaPair.kana : "";
			
			if (dictionaryEntry$Kanji.equals(kanjiKanaPair$Kanji) == true && dictionaryEntry$Kana.equals(kanjiKanaPair$Kana) == true) {
				return kanjiKanaPair;
			}			
		}

		return null;
	}
	
	public static String translateToPolishGlossType(GTypeEnum glossType) {
		
		if (glossType == null) {
			return null;
		}
		
		switch (glossType) {
		
		case EXPL:
			return "wyjaśnienie";
			
		case FIG:
			return "przenośna";
			
		case LIT:
			return "literacko";
		
		case TM:
			return "znak towarowy";
			
			default:
				throw new RuntimeException("Unknown gloss type: " + glossType);
		}
	}
	
	public static String translateToPolishLanguageSourceLsWaseiEnum(LanguageSourceLsWaseiEnum languageSource) {
		
		if (languageSource == null) {
			return null;
		}
		
		if (languageSource == LanguageSourceLsWaseiEnum.Y) {
			return "wasei (słowo utworzone w Japonii)";
			
		} else {
			return null;
		}
	}
	
	public static String translateToPolishLanguageCode(String language) {
		
		switch (language) {
		
		case "eng":
			return "ang";
			
		case "ita":
			return "wło";
			
		case "ger":
			return "niem";
			
		case "por":
			return "por";
			
		case "rus":
			return "ros";
			
		case "fre":
			return "fra";
			
		case "lat":
			return "łać";
			
		case "dut":
			return "hol";
			
		case "tur":
			return "tur";
			
		case "gre":
			return "gre";
			
		case "chi":
			return "chin";

		case "ara":
			return "ara";
			
		case "ain":
			return "ainu";
			
		case "spa":
			return "hisz";
			
		case "kor":
			return "kor";
			
		case "may":
			return "mal";
			
		case "tha":
			return "taj";
		
		case "san":
			return "san";
			
		case "chn":
			return "chin";
			
		case "vie":
			return "wiet";

		case "hun":
			return "węg";
			
		case "heb":
			return "heb";
			
		case "haw":
			return "haw";
			
		case "afr":
			return "afr";
			
		case "grc":
			return "grk";

		case "mnc":
			return "mar";
			
		case "swe":
			return "swe";
			
		case "ice":
			return "isl";
			
			default:
				throw new RuntimeException("Unknown language: " + language);
		}
	}
	
	public static String translateToPolishLanguageCodeWithoutValue(String language) {
		
		switch (language) {
		
		case "eng":
			return "słowo pochodzenia angielskiego";
			
		case "ita":
			return "słowo pochodzenia włoskiego";
			
		case "ger":
			return "słowo pochodzenia niemieckiego";
			
		case "por":
			return "słowo pochodzenia portugalskiego";
			
		case "rus":
			return "słowo pochodzenia rosyjskiego";
			
		case "fre":
			return "słowo pochodzenia francuskiego";
			
		case "lat":
			return "słowo pochodzenia łacińskiego";
			
		case "dut":
			return "słowo pochodzenia holenderskiego";
			
		case "tur":
			return "słowo pochodzenia tureckiego";
			
		case "gre":
			return "słowo pochodzenia greckiego";
			
		case "chi":
			return "słowo pochodzenia chińskiego";
			
		case "ara":
			return "słowo pochodzenia arabskiego";
			
		case "ain":
			return "słowo pochodzenia ainu";
			
		case "spa":
			return "słowo pochodzenia hiszpańskiego";
			
		case "kor":
			return "słowo pochodzenia koreańskiego";
			
		case "may":
			return "słowo pochodzenia malajskiego";
			
		case "tha":
			return "słowo pochodzenia tajskiego";
			
		case "san":
			return "sanskryt";
			
		case "chn":
			return "słowo pochodzenia chińskiego";
			
		case "vie":
			return "słowo pochodzenia wietnamskiego";

		case "hun":
			return "słowo pochodzenia węgierskiego";
			
		case "heb":
			return "słowo pochodzenia hebrajskiego";
			
		case "haw":
			return "słowo pochodzenia hawajskiego";
			
		case "afr":
			return "słowo pochodzenia afrykanerski";
			
		case "grc":
			return "słowo pochodzenia greckiego";
			
		case "mnc":
			return "słowo pochodzenia mandżurskiego";
			
		case "swe":
			return "słowo pochodzenia szwedzkiego";
			
		case "ice":
			return "słowo pochodzenia islandzkiego";
			
			default:
				throw new RuntimeException("Unknown language: " + language);
		}
	}

	public static class KanjiKanaPair {
		
		public String kanji;
		
		public String kana;
		public ReadingInfoKanaType kanaType;
		
		public String romaji;
		
		public List<Sense> senseList = new ArrayList<>();

		public KanjiKanaPair(String kanji, String kana, ReadingInfoKanaType kanaType, String romaji) {
			this.kanji = kanji;
			this.kana = kana;
			this.kanaType = kanaType;
			this.romaji = romaji;
		}

		public List<Sense> getSenseList() {
			return senseList;
		}

		@Override
		public String toString() {
			return "KanjiKanaPair [kanji=" + kanji + ", kana=" + kana + "]";
		}
	}
}
