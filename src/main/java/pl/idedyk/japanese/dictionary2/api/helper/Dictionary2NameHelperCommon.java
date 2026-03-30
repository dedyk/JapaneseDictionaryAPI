package pl.idedyk.japanese.dictionary2.api.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pl.idedyk.japanese.dictionary2.jmnedict.xsd.JMnedict;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.KanjiInfo;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.ReadingInfo;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.TranslationalInfo;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.TranslationalInfoNameType;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.TranslationalInfoTransDet;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.TranslationalInfoTransDetAdditionalInfo;

public class Dictionary2NameHelperCommon {
	
	public static List<NameKanjiKanaPair> getNameKanjiKanaPairListStatic(JMnedict.Entry entry) {
		
		List<NameKanjiKanaPair> result = new ArrayList<>();
		
		//
		
		List<KanjiInfo> kanjiInfoList = entry.getKanjiInfoList();
		List<ReadingInfo> readingInfoList = entry.getReadingInfoList();
		
		// jesli nie ma kanji
		if (kanjiInfoList.size() == 0) {
			
			// wszystkie czytania do listy wynikowej
			for (ReadingInfo readingInfo : readingInfoList) {				
				result.add(new NameKanjiKanaPair(null, readingInfo));
			}
			
		} else {	
			// zlaczenie kanji z kana
			
			for (KanjiInfo kanjiInfo : kanjiInfoList) {
				for (ReadingInfo readingInfo : readingInfoList) {
					
					// pobierz kanji
					String kanji = kanjiInfo.getKanji();
															
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
					result.add(new NameKanjiKanaPair(kanjiInfo, readingInfo));					
				}				
			}
		}
				
		// dopasowanie listy translationalInfo do danego kanji i kana
		List<TranslationalInfo> translationalInfoList = entry.getTranslationInfo();
				
		for (NameKanjiKanaPair nameKanjiKanaPair : result) {
						
			// chodzimy po wszystkich translationalInfo i sprawdzamy, czy mozemy je dodac do naszej pary kanji i kana
			for (TranslationalInfo translationalInfo : translationalInfoList) {
								
				// dodajemy ten sens do danej pary				
				nameKanjiKanaPair.getTranslationalInfoList().add(translationalInfo);
			}
		}
		
		return result;
	}
	
	public static List<TranslationalInfoTransDet> getEnglishOrPolishTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList) {
		
		// pobieramy polskie (moze nie byc)
		List<TranslationalInfoTransDet> polishTranslationalInfoTransDet = getPolishTranslationalInfoTransDet(translationalInfoTransDetList);
		
		if (polishTranslationalInfoTransDet != null && polishTranslationalInfoTransDet.size() > 0) {
			return polishTranslationalInfoTransDet;
		}
		
		// pobieramy angielskie znaczenie (zawsze musi istniec
		return getEnglishTranslationalInfoTransDet(translationalInfoTransDetList);
	}
	
	public static List<TranslationalInfoTransDet> getPolishTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList) {
		return getLangTranslationalInfoTransDet(translationalInfoTransDetList, "pol");		
	}

	public static List<TranslationalInfoTransDet> getEnglishTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList) {
		return getLangTranslationalInfoTransDet(translationalInfoTransDetList, "eng");		
	}
	
	private static List<TranslationalInfoTransDet> getLangTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList, String lang) {
		
		List<TranslationalInfoTransDet> result = new ArrayList<>();
		
		for (TranslationalInfoTransDet translationalInfoTransDet : translationalInfoTransDetList) {
			if (translationalInfoTransDet.getLang().equals(lang) == true) {
				result.add(translationalInfoTransDet);
			}
		}
		
		return result;
	}

	public static TranslationalInfoTransDetAdditionalInfo getFirstEnglishOrPolishTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList) {
		TranslationalInfoTransDetAdditionalInfo firstPolishTranslationalInfoTransDetAdditionalInfo = getFirstPolishTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList);
		
		if (firstPolishTranslationalInfoTransDetAdditionalInfo != null) {
			return firstPolishTranslationalInfoTransDetAdditionalInfo;
		}
		
		return getFirstEngTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList);
	}
	
	public static TranslationalInfoTransDetAdditionalInfo getFirstPolishTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList) {
		return getFirstLangTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList, "pol");
	}

	public static TranslationalInfoTransDetAdditionalInfo getFirstEngTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList) {
		return getFirstLangTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList, "eng");
	}
	
	private static TranslationalInfoTransDetAdditionalInfo getFirstLangTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList, String lang) {
		
		for (TranslationalInfoTransDetAdditionalInfo translationalInfoTransDetAdditionalInfo : translationalInfoTransDetAdditionalInfoList) {
			if (translationalInfoTransDetAdditionalInfo.getLang().equals(lang) == true) {
				return translationalInfoTransDetAdditionalInfo;
			}
		}
		
		return null;
	}
	
	public static String[] getUniqueKanjiKanaRomajiSetWithoutSearchOnly(JMnedict.Entry entry) {
		
		Set<String> kanjiUniqueSet = new LinkedHashSet<>();
		Set<String> kanaUniqueSet = new LinkedHashSet<>();
		Set<String> romajiUniqueSet = new LinkedHashSet<>();
		
		// kanji
		entry.getKanjiInfoList().stream().filter(kanjiInfo -> {
			boolean isKanjiSearchOnly = kanjiInfo != null;
			
			return isKanjiSearchOnly == false;
		}).forEach(kanjiInfo -> kanjiUniqueSet.add(kanjiInfo.getKanji()));
		
		// kana i romaji
		entry.getReadingInfoList().stream().forEach(readingInfo -> {
			
			kanaUniqueSet.add(readingInfo.getKana());
			romajiUniqueSet.add(readingInfo.getRomaji());
		});	
		
		if (kanjiUniqueSet.size() == 0) {
			kanjiUniqueSet.add("-");
		}
		
		if (kanaUniqueSet.size() == 0) {
			kanaUniqueSet.add("-");
		}
		
		if (romajiUniqueSet.size() == 0) {
			romajiUniqueSet.add("-");
		}
		
		return new String[] { String.join(",", kanjiUniqueSet), String.join(",", kanaUniqueSet), String.join(",", romajiUniqueSet) };
	}
	
	public static List<String> translateToPolishTranslationalInfoNameTypeList(Collection<TranslationalInfoNameType> translationalInfoNameTypeList) {
		
		// FM_FIXME: sprawdzic, czy to dziala
		
		List<String> result = new ArrayList<>();

		for (TranslationalInfoNameType translationalInfoNameType : translationalInfoNameTypeList) {
			
			switch (translationalInfoNameType) {
			
			case CHARACTER:
				result.add("postać"); break;
				
			case COMPANY_NAME:
				result.add("nazwa firmy"); break;

			case CREATURE:
				result.add("stworzenie"); break;

			case DEITY:
				result.add("bóstwo"); break;

			case DOCUMENT:
				result.add("dokument"); break;

			case EVENT:
				result.add("wydarzenie"); break;

			case FEMALE_GIVEN_NAME_OR_FORENAME:
				result.add("imię żeńskie"); break;

			case FICTION:
				result.add("fikcja"); break;

			case GIVEN_NAME_OR_FORENAME_GENDER_NOT_SPECIFIED:
				result.add("imię (bez określenia płci)"); break;

			case GROUP:
				result.add("grupa"); break;

			case LEGEND:
				result.add("legenda"); break;

			case MALE_GIVEN_NAME_OR_FORENAME:
				result.add("imię męskie"); break;

			case MYTHOLOGY:
				result.add("mitologia"); break;

			case OBJECT:
				result.add("obiekt"); break;

			case ORGANIZATION_NAME:
				result.add("nazwa organizacji"); break;

			case OTHER:
				result.add("inny"); break;

			case FULL_NAME_OF_A_PARTICULAR_PERSON:
				result.add("imię i nazwisko osoby"); break;

			case PLACE_NAME:
				result.add("nazwa miejsca"); break;

			case PRODUCT_NAME:
				result.add("nazwa produktu"); break;

			case RELIGION:
				result.add("religia"); break;

			case SERVICE:
				result.add("usługa"); break;

			case SHIP_NAME:
				result.add("nazwa statku"); break;

			case RAILWAY_STATION:
				result.add("nazwa stacji"); break;

			case FAMILY_OR_SURNAME:
				result.add("nazwisko"); break;

			case UNCLASSIFIED_NAME:
				result.add("niesklasyfikowana nazwa"); break;

			case WORK_OF_ART_LITERATURE_MUSIC_ETC_NAME:
				result.add("dzieło sztuki, literatury, muzyki i etc"); break;
				
			default:				
				throw new RuntimeException("Unknown name type enum: " + translationalInfoNameTypeList);				
			}
		}
		
		return result;
	}

	
	//
	
	public static class NameKanjiKanaPair {
		
		private KanjiInfo kanjiInfo;		
		private ReadingInfo readingInfo;
				
		private List<TranslationalInfo> translationalInfoList = new ArrayList<>();

		public NameKanjiKanaPair(KanjiInfo kanjiInfo, ReadingInfo readingInfo) {
			this.kanjiInfo = kanjiInfo;
			this.readingInfo = readingInfo;
		}

		public KanjiInfo getKanjiInfo() {
			return kanjiInfo;
		}

		public ReadingInfo getReadingInfo() {
			return readingInfo;
		}

		public List<TranslationalInfo> getTranslationalInfoList() {
			return translationalInfoList;
		}
		
		public String getKanji() {
			
			if (kanjiInfo == null) {
				return null;
			}
			
			return kanjiInfo.getKanji();			
		}
				
		public String getKana() {
			
			if (readingInfo == null) {
				return null;
			}

			return readingInfo.getKana();
		}
		
		public String getRomaji() {
			
			if (readingInfo == null) {
				return null;
			}

			return readingInfo.getRomaji();
		}

				
		@Override
		public String toString() {
			return "KanjiKanaPair [kanji=" + kanjiInfo + ", kana=" + readingInfo + "]";
		}
	}
}
