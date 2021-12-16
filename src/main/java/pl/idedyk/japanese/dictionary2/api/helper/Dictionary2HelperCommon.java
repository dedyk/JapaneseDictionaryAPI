package pl.idedyk.japanese.dictionary2.api.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.idedyk.japanese.dictionary2.jmdict.xsd.DialectEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.FieldEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.GTypeEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.KanjiInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.LanguageSourceLsWaseiEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.MiscEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.PartOfSpeechEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingAdditionalInfoEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingInfoKanaType;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.Sense;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.JMdict.Entry;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.KanjiAdditionalInfoEnum;

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
					result.add(new KanjiKanaPair(null, readingInfo));
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
					result.add(new KanjiKanaPair(kanjiInfo, readingInfo));					
				}				
			}
		}
		
		// szukanie kana z no kanji
		for (ReadingInfo readingInfo : readingInfoList) {
			
			ReadingInfo.ReNokanji noKanji = readingInfo.getNoKanji();
			
			if (noKanji != null) {				
				result.add(new KanjiKanaPair(null, readingInfo));
			}
		}
		
		// dopasowanie listy sense do danego kanji i kana
		List<Sense> entrySenseList = entry.getSenseList();
				
		for (KanjiKanaPair kanjiKanaPair : result) {
			
			String kanji = kanjiKanaPair.getKanji();
			String kana = kanjiKanaPair.getKana();
			
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
			
			String kanjiKanaPair$Kanji = kanjiKanaPair.getKanji() != null ? kanjiKanaPair.getKanji() : "";
			String kanjiKanaPair$Kana = kanjiKanaPair.getKana() != null ? kanjiKanaPair.getKana() : "";
			
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
	
	public static List<String> translateToPolishFieldEnumList(Collection<FieldEnum> fieldEnumList) {
		
		List<String> result = new ArrayList<>();

		for (FieldEnum fieldEnum : fieldEnumList) {
			
			switch (fieldEnum) {
			
			case COMPUTING:
				result.add("informatyka"); break;
			
			case SUMO:
				result.add("sumo"); break;
				
			case SPORTS:
				result.add("sport"); break;
				
			case LINGUISTICS:
				result.add("lingwistyka"); break;
				
			case ASTRONOMY:
				result.add("astronomia"); break;
				
			case BUDDHISM:
				result.add("buddyzm"); break;
				
			case MATHEMATICS:
				result.add("matematyka"); break;
				
			case MUSIC:
				result.add("muzyka"); break;
				
			case MARTIAL_ARTS:
				result.add("sztuki walki"); break;
				
			case MEDICINE:
				result.add("medycyna"); break;
				
			case BASEBALL:
				result.add("baseball"); break;
				
			case PHYSICS:
				result.add("fizyka"); break;
				
			case BIOLOGY:
				result.add("biologia"); break;
				
			case FOOD_COOKING:
				result.add("jedzenie, gotowanie"); break;
				
			case ARCHEOLOGY:
				result.add("archeologia"); break;
				
			case CHRISTIANITY:
				result.add("chrześcijaństwo"); break;
				
			case CHEMISTRY:
				result.add("chemia"); break;
			
			case GEOLOGY:
				result.add("geologia"); break;
				
			case ANATOMY:
				result.add("anatomia"); break;
				
			case BOTANY:
				result.add("botanika"); break;
				
			case MAHJONG:
				result.add("mahjong"); break;
				
			case MILITARY:
				result.add("wojskowość"); break;
				
			case LAW:
				result.add("prawo"); break;
				
			case FINANCE:
				result.add("finanse"); break;
				
			case ZOOLOGY:
				result.add("zoologia"); break;
				
			case ECONOMICS:
				result.add("ekonomia"); break;
				
			case SHOGI:
				result.add("shogi"); break;
				
			case PHARMACY:
				result.add("farmacja"); break;
				
			case STATISTICS:
				result.add("statystyka"); break;
				
			case BUSINESS:
				result.add("biznes"); break;
				
			case PHOTOGRAPHY:
				result.add("fotografia"); break;
			
			case PHILOSOPHY:
				result.add("filozofia"); break;
				
			case ARCHITECTURE:
				result.add("architektura"); break;
				
			case HORSE_RACING:
				result.add("wyścigi konne"); break;
			
			case SHINTO:
				result.add("shinto"); break;
				
			case ENGINEERING:
				result.add("inżynieria"); break;
				
			case TELECOMMUNICATIONS:
				result.add("telekomunikacja"); break;
				
			case HANAFUDA:
				result.add("hanafuda"); break;
				
			case TRADEMARK:
				result.add("znak towarowy"); break;
				
			case CLOTHING:
				result.add("odzież"); break;
				
			case GO_GAME:
				result.add("go (gra)"); break;
				
			case LOGIC:
				result.add("logika"); break;
				
			case ELECTRICITY_ELEC_ENG:
				result.add("elektryczność"); break;
			
			case METEOROLOGY:
				result.add("meteorologia"); break;
				
			case GENETICS:
				result.add("genetyka"); break;
			
			case PSYCHOLOGY:
				result.add("psychologia"); break;
				
			case VIDEO_GAMES:
				result.add("gry wideo"); break;
				
			case PRINTING:
				result.add("drukarstwo"); break;
				
			case ELECTRONICS:
				result.add("elektronika"); break;
				
			case PALEONTOLOGY:
				result.add("paleontologia"); break;				
				
			case CRYSTALLOGRAPHY:
				result.add("krystalografia"); break;
				
			case ENTOMOLOGY:
				result.add("entomologia"); break;

			case ART_AESTHETICS:
				result.add("estetyka sztuki"); break;
				
			case AGRICULTURE:
				result.add("rolnictwo"); break;
				
			case RAILWAY:
				result.add("kolejnictwo"); break;

			case AVIATION:
				result.add("lotnictwo"); break;

			case GRAMMAR:
				result.add("gramatyka"); break;
				
			case PHYSIOLOGY:
				result.add("fizjologia"); break;
				
			default:
				throw new RuntimeException("Unknown field enum: " + fieldEnum);
			
			}
		}
		
		return result;
	}

	public static List<String> translateToPolishMiscEnumList(Collection<MiscEnum> miscEnumList) {
		
		List<String> result = new ArrayList<>();

		for (MiscEnum miscEnum : miscEnumList) {
			
			switch (miscEnum) {
			
			case WORD_USUALLY_WRITTEN_USING_KANA_ALONE:
				result.add("pisanie zwykle z użyciem kana"); break;			
			
			case OBSCURE_TERM:
				result.add("mało znane słowo"); break;
				
			case YOJIJUKUGO:
				result.add("słowo składające się z czterech znaków"); break;
				
			case ABBREVIATION:
				result.add("skrót"); break;
				
			case COLLOQUIALISM:
				result.add("kolokwializm"); break;
				
			case DEROGATORY:
				result.add("poniżająco"); break;
				
			case SLANG:
				result.add("slang"); break;
			
			case ONOMATOPOEIC_OR_MIMETIC_WORD:
				result.add("onomatopeiczne lub mimetyczne słowo"); break; 
				
			case IDIOMATIC_EXPRESSION:
				result.add("wyrażenie idiomatyczne"); break;
				
			case ARCHAISM:
				result.add("archaizm"); break;
				
			case HONORIFIC_OR_RESPECTFUL_SONKEIGO_LANGUAGE:
				result.add("honoryfikatywnie"); break;
				
			case OBSOLETE_TERM:
				result.add("przestarzałe słowo"); break;
				
			case JOCULAR_HUMOROUS_TERM:
				result.add("żartobliwie"); break;
				
			case PROVERB:
				result.add("przysłowie"); break;
				
			case VULGAR_EXPRESSION_OR_WORD:
				result.add("wulgarnie"); break;
				
			case MANGA_SLANG:
				result.add("slang mangowy"); break;
				
			case FORMAL_OR_LITERARY_TERM:
				result.add("formalny lub literacki termin"); break;
				
			case HISTORICAL_TERM:
				result.add("termin historyczny"); break;
				
			case CHILDREN_S_LANGUAGE:
				result.add("język dzieci"); break;
				
			case POLITE_TEINEIGO_LANGUAGE:
				result.add("uprzejmie"); break;
				
			case FAMILIAR_LANGUAGE:
				result.add("język poufały"); break;
				
			case MALE_TERM_OR_LANGUAGE:
				result.add("język męski"); break;
				
			case DATED_TERM:
				result.add("przestarzałe słowo"); break;
				
			case INTERNET_SLANG:
				result.add("slang internetowy"); break;
				
			case FEMALE_TERM_OR_LANGUAGE:
				result.add("kobiecy termin lub język"); break;
				
			case SENSITIVE:
				result.add("wrażliwie"); break;
				
			case HUMBLE_KENJOUGO_LANGUAGE:
				result.add("pokornie"); break;
				
			case FULL_NAME_OF_A_PARTICULAR_PERSON:
				result.add("imię i nazwisko osoby"); break;	

			case QUOTATION:
				result.add("cytat"); break;
				
			case POETICAL_TERM:
				result.add("poetycki termin"); break;
				
			default:
				throw new RuntimeException("Unknown misc enum: " + miscEnum);
			
			}
		}
		
		return result;
	}
	
	public static List<String> translateToPolishDialectEnumList(Collection<DialectEnum> dialectEnumList) {
		
		List<String> result = new ArrayList<>();

		for (DialectEnum dialectEnum : dialectEnumList) {
			
			switch (dialectEnum) {
			
			case KANSAI_BEN:
				result.add("dialekt Kansai"); break;
				
			case HOKKAIDO_BEN:
				result.add("dialekt Hokkaido"); break;
				
			case RYUUKYUU_BEN:
				result.add("dialekt Ryuukyuu"); break;
				
			case KYUUSHUU_BEN:
				result.add("dialekt Kyuushuu"); break;

			case TOUHOKU_BEN:
				result.add("dialekt Touhoku"); break;
				
			case OSAKA_BEN:
				result.add("dialekt Osaka"); break;
				
			case BRAZILIAN:
				result.add("dialekt brazyliński"); break;
			
			case KANTOU_BEN:
				result.add("dialekt Kantou"); break;
				
			case KYOTO_BEN:
				result.add("dialekt Kyoto"); break;
				
			default:
				throw new RuntimeException("Unknown dialect enum: " + dialectEnum);
				
			}
		}
		
		return result;
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
	
	public static List<String> translateToPolishPartOfSpeechEnum(Collection<PartOfSpeechEnum> partOfSpeechEnumList) {
		
		List<String> result = new ArrayList<>();

		for (PartOfSpeechEnum partOfSpeechEnum : partOfSpeechEnumList) {
			
			switch (partOfSpeechEnum) {
			
			case NOUN_COMMON_FUTSUUMEISHI:
				result.add("rzeczownik (powszechny) (futsuumeishi)"); break;
			
			case NOUN_USED_AS_A_PREFIX:
				result.add("rzeczownik, używany jako przedrostek"); break;
				
			case NOUN_USED_AS_A_SUFFIX:
				result.add("rzeczownik, używany jako przyrostek"); break;
				
			case ADJECTIVAL_NOUNS_OR_QUASI_ADJECTIVES_KEIYODOSHI:
				result.add("rzeczownik przymiotnikowy lub quasi-przymiotnik (keiyodoshi)"); break;
				
			case NOUN_OR_PARTICIPLE_WHICH_TAKES_THE_AUX_VERB_SURU:
				result.add("rzeczownik lub imiesłów, który przyjmuje czasownik posiłkowy suru"); break;
				
			case NOUN_OR_VERB_ACTING_PRENOMINALLY:
				result.add("rzeczownik lub czasownik działający prenominalnie"); break;
				
			case NOUNS_WHICH_MAY_TAKE_THE_GENITIVE_CASE_PARTICLE_NO:
				result.add("rzeczownik, który przyjmuje partykułę 'no'"); break;
				
			case PRE_NOUN_ADJECTIVAL_RENTAISHI:
				result.add("pre-rzeczownik przymiotnikowy (rentaishi)"); break;
				
			case PRONOUN:
				result.add("zaimek"); break;
				
			case NUMERIC:
				result.add("liczba"); break;
				
			case PARTICLE:
				result.add("partykuła"); break;
				
			case PREFIX:
				result.add("przedrostek"); break;
				
			case SUFFIX:
				result.add("przyrostek"); break;
				
			case COPULA:
				result.add("kopula"); break;
				
			case COUNTER:
				result.add("klasyfikator"); break;
				
			case CONJUNCTION:
				result.add("spójnik"); break;
				
			case ADJECTIVE_KEIYOUSHI:
				result.add("przymiotnik (keiyoushi)"); break;
				
			case ADJECTIVE_KEIYOUSHI_YOI_II_CLASS:
				result.add("przymiotnik (keiyoushi) - klasa yoi/ii"); break;
				
			case ADVERB_FUKUSHI:
				result.add("przysłówek (fukushi)"); break;	
			
			case ADVERB_TAKING_THE_TO_PARTICLE:
				result.add("przysłówek przyjmujący partykułę 'to'"); break;
				
			case AUXILIARY:
				result.add("posiłkowy (pomocniczy)"); break;
				
			case AUXILIARY_ADJECTIVE:
				result.add("przymiotnik posiłkowy (pomocniczy)"); break;
				
			case AUXILIARY_VERB:
				result.add("czasownik posiłkowy (pomocniczy)"); break;
				
			case ARCHAIC_FORMAL_FORM_OF_NA_ADJECTIVE:
				result.add("archaiczna/formalna forma na-przymiotnika"); break;
				
			case EXPRESSIONS_PHRASES_CLAUSES_ETC:
				result.add("wyrażenie (zwrot, zdanie składowe i etc) "); break;
				
			case ICHIDAN_VERB:
				result.add("czasownik ichidan (ru-czasownik)"); break;
				
			case TARU_ADJECTIVE:
				result.add("przymiotnik taru"); break;
				
			case TRANSITIVE_VERB:
				result.add("czasownik przechodni"); break;
				
			case INTRANSITIVE_VERB:
				result.add("czasownik nieprzechodni"); break;
				
			case IRREGULAR_NU_VERB:
				result.add("nieregularny nu-czasownik"); break;
				
			case INTERJECTION_KANDOUSHI:
				result.add("wykrzyknik (kandoushi)"); break;
				
			case KU_ADJECTIVE_ARCHAIC:
				result.add("ku-przymiotnik (archaiczny)"); break;
				
			case KURU_VERB_SPECIAL_CLASS:
				result.add("czasownik kuru - klasa specjalna"); break;
				
			case SURU_VERB_INCLUDED:
				result.add("czasownik suru"); break;
				
			case SURU_VERB_SPECIAL_CLASS:
				result.add("czasownik suru - klasa specjalna"); break;
				
			case SU_VERB_PRECURSOR_TO_THE_MODERN_SURU:
				result.add("czasownik su - prekursor współczesnego czasownika suru"); break;
				
			case GODAN_VERB_ARU_SPECIAL_CLASS:
				result.add("czasownik godan (u-czasownik) - klasa specjalna aru"); break;
				
			case GODAN_VERB_IKU_YUKU_SPECIAL_CLASS:
				result.add("czasownik godan (u-czasownik) - klasa specjalna iku/yuku"); break;
				
			case GODAN_VERB_WITH_BU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na bu"); break;

			case GODAN_VERB_WITH_GU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na gu"); break;

			case GODAN_VERB_WITH_KU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na ku"); break;

			case GODAN_VERB_WITH_MU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na mu"); break;

			case GODAN_VERB_WITH_NU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na nu"); break;

			case GODAN_VERB_WITH_RU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na ru"); break;

			case GODAN_VERB_WITH_RU_ENDING_IRREGULAR_VERB:
				result.add("czasownik godan (u-czasownik) kończący się na ru, czasownik nieregularny"); break;

			case GODAN_VERB_WITH_SU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na su"); break;

			case GODAN_VERB_WITH_TSU_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na tsu"); break;

			case GODAN_VERB_WITH_U_ENDING:
				result.add("czasownik godan (u-czasownik) kończący się na u"); break;

			case ICHIDAN_VERB_ZURU_VERB_ALTERNATIVE_FORM_OF_JIRU_VERBS:
				result.add("czasownik ichidan (ru-czasownik) - czasownik zuru (alternatywna forma czasownika jiru)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_HU_FU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na hu/fu (archaiczny)"); break;	

			case NIDAN_VERB_LOWER_CLASS_WITH_RU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na ru (archaiczny)"); break;	

			case NIDAN_VERB_LOWER_CLASS_WITH_SU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na su (archaiczny)"); break;	

			case NIDAN_VERB_LOWER_CLASS_WITH_YU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na yu (archaiczny)"); break;	

			case YODAN_VERB_WITH_KU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na ku (archaiczny)"); break;

			case YODAN_VERB_WITH_RU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na ru (archaiczny)"); break;

			case YODAN_VERB_WITH_SU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na su (archaiczny)"); break;
			
			default:				
				throw new RuntimeException("Unknown part of speech enum: " + partOfSpeechEnum);				
			}
		}
		
		return result;
	}

	public static List<String> translateToPolishKanjiAdditionalInfoEnum(Collection<KanjiAdditionalInfoEnum> kanjiAdditionalInfoEnumList) {
		
		List<String> result = new ArrayList<>();

		for (KanjiAdditionalInfoEnum kanjiAdditionalInfoEnum : kanjiAdditionalInfoEnumList) {
			
			switch (kanjiAdditionalInfoEnum) {
			
			case ATEJI_PHONETIC_READING:
				result.add("czytanie ateji (fonetyczne)"); break;
			
			case WORD_CONTAINING_OUT_DATED_KANJI_OR_KANJI_USAGE:
				result.add("słowo zawierające przestarzałe kanji"); break;
			
			case IRREGULAR_OKURIGANA_USAGE:
				result.add("nieregularne użycie okurigana"); break;
				
			case WORD_CONTAINING_IRREGULAR_KANA_USAGE:
				result.add("słowo zawierające nieregularne użycie kana"); break;
				
			case WORD_CONTAINING_IRREGULAR_KANJI_USAGE:
				result.add("słowo zawierające nieregularne użycie kanji"); break;
				
			case RARELY_USED_KANJI_FORM:
				result.add("rzadko używana forma kanji"); break;
			
			default:				
				throw new RuntimeException("Unknown kanji additional info enum: " + kanjiAdditionalInfoEnum);				
			}
		}
		
		return result;
	}

	public static List<String> translateToPolishReadingAdditionalInfoEnum(Collection<ReadingAdditionalInfoEnum> readingAdditionalInfoEnumList) {
		
		List<String> result = new ArrayList<>();

		for (ReadingAdditionalInfoEnum readingAdditionalInfoEnum : readingAdditionalInfoEnumList) {
			
			switch (readingAdditionalInfoEnum) {
			
			case WORD_CONTAINING_IRREGULAR_KANA_USAGE:
				result.add("słowo zawierające nieregularne użycie kana"); break;

			case OUT_DATED_OR_OBSOLETE_KANA_USAGE:
				result.add("przestarzałe lub nieużywane użycie kana"); break;
			
			case GIKUN_MEANING_AS_READING_OR_JUKUJIKUN_SPECIAL_KANJI_READING:
				result.add("gikun (znaczenie jako czytanie) lub jukujikun (specjalne czytanie kanji)"); break;
				
			case WORD_USUALLY_WRITTEN_USING_KANJI_ALONE:
				result.add("słowo zazwyczaj zapisywane wyłącznie z użyciem kanji"); break;
			
			default:				
				throw new RuntimeException("Unknown reading additional info enum: " + readingAdditionalInfoEnum);				
			}
		}
		
		return result;
	}
	
	public static class KanjiKanaPair {
		
		private KanjiInfo kanjiInfo;		
		private ReadingInfo readingInfo;
				
		private List<Sense> senseList = new ArrayList<>();

		public KanjiKanaPair(KanjiInfo kanjiInfo, ReadingInfo readingInfo) {
			this.kanjiInfo = kanjiInfo;
			this.readingInfo = readingInfo;
		}

		public KanjiInfo getKanjiInfo() {
			return kanjiInfo;
		}

		public ReadingInfo getReadingInfo() {
			return readingInfo;
		}

		public List<Sense> getSenseList() {
			return senseList;
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

			return readingInfo.getKana().getValue();
		}
		
		public String romaji() {

			if (readingInfo == null) {
				return null;
			}

			return readingInfo.getKana().getRomaji();
		}
		
		public ReadingInfoKanaType getKanaType() {
			
			if (readingInfo == null) {
				return null;
			}

			return readingInfo.getKana().getKanaType();
		}

		@Override
		public String toString() {
			return "KanjiKanaPair [kanji=" + kanjiInfo + ", kana=" + readingInfo + "]";
		}
	}
}
