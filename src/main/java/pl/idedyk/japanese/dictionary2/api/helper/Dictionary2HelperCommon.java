package pl.idedyk.japanese.dictionary2.api.helper;

import java.util.ArrayList;
import java.util.List;

import pl.idedyk.japanese.dictionary2.jmdict.xsd.KanjiInfo;
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
	
	public KanjiKanaPair findKanjiKanaPair(List<KanjiKanaPair> kanjiKanaPairList, DictionaryEntry dictionaryEntry) {
		
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
