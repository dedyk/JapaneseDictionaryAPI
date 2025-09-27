package pl.idedyk.japanese.dictionary2.api.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import pl.idedyk.japanese.dictionary2.jmdict.xsd.DialectEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.FieldEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.GTypeEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.Gloss;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.JMdict;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.KanjiInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.LanguageSource;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.LanguageSourceLsWaseiEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.MiscEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.PartOfSpeechEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingAdditionalInfoEnum;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.ReadingInfoKanaType;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.Sense;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.SenseAdditionalInfo;
import pl.idedyk.japanese.dictionary.api.dto.AttributeList;
import pl.idedyk.japanese.dictionary.api.dto.AttributeType;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.dto.GroupEnum;
import pl.idedyk.japanese.dictionary.api.dto.WordType;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.JMdict.Entry;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.KanjiAdditionalInfoEnum;

public class Dictionary2HelperCommon {
	
	protected List<KanjiKanaPair> getKanjiKanaPairList(Entry entry, boolean filterSearchOnly) {
		return getKanjiKanaPairListStatic(entry, filterSearchOnly);		
	}
	
	public static List<KanjiKanaPair> getKanjiKanaPairListStatic(Entry entry, boolean filterSearchOnly) {
		
		List<KanjiKanaPair> result = new ArrayList<>();
		
		//
		
		List<KanjiInfo> kanjiInfoList = entry.getKanjiInfoList();
		List<ReadingInfo> readingInfoList = entry.getReadingInfoList();
		
		if (filterSearchOnly == true) { // pozbycie sie search only
			
			kanjiInfoList = kanjiInfoList.stream().filter(kanjiInfo -> {
				boolean isKanjiSearchOnly = kanjiInfo != null && kanjiInfo.getKanjiAdditionalInfoList().contains(KanjiAdditionalInfoEnum.SEARCH_ONLY_KANJI_FORM) == true;
				
				return isKanjiSearchOnly == false;
			}).collect(Collectors.toList());
			
			readingInfoList = readingInfoList.stream().filter(readingInfo -> {
				boolean isKanaSearchOnly = readingInfo.getReadingAdditionalInfoList().contains(ReadingAdditionalInfoEnum.SEARCH_ONLY_KANA_FORM) == true;
				
				return isKanaSearchOnly == false;				
			}).collect(Collectors.toList());			
		}
		
		// jesli nie ma kanji
		if (kanjiInfoList.size() == 0) {
			
			// wszystkie czytania do listy wynikowej
			for (ReadingInfo readingInfo : readingInfoList) {
				
				ReadingInfo.ReNokanji noKanji = readingInfo.getNoKanji();
				
				if (noKanji == null) {					
					result.add(new KanjiKanaPair(entry, null, readingInfo));
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
					result.add(new KanjiKanaPair(entry, kanjiInfo, readingInfo));					
				}				
			}
		}
		
		// szukanie kana z no kanji
		for (ReadingInfo readingInfo : readingInfoList) {
			
			ReadingInfo.ReNokanji noKanji = readingInfo.getNoKanji();
			
			if (noKanji != null) {				
				result.add(new KanjiKanaPair(entry, null, readingInfo));
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
			return "dosłownie";
		
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
				
			case NOH:
				result.add("teatr no"); break;
				
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
				
			case PHARMACOLOGY:
				result.add("farmakologia"); break;
				
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
				
			case BIOCHEMISTRY:
				result.add("biochemia"); break;
				
			case GEOMETRY:
				result.add("geometria"); break;
				
			case ORNITHOLOGY:
				result.add("ornitologia"); break;
				
			case FISHING:
				result.add("wędkarstwo"); break;
				
			case PATHOLOGY:
				result.add("patologia"); break;
				
			case GOLF:
				result.add("golf"); break;
				
			case AUDIOVISUAL:
				result.add("audiowizualny"); break;
				
			case ECOLOGY:
				result.add("ekologia"); break;
				
			case EMBRYOLOGY:
				result.add("embriologia"); break;
				
			case GARDENING_HORTICULTURE:
				result.add("ogrodnictwo, sadownictwo"); break;
				
			case GEOGRAPHY:
				result.add("geografia"); break;
				
			case GREEK_MYTHOLOGY:
				result.add("mitologia grecka"); break;
				
			case MECHANICAL_ENGINEERING:
				result.add("inżynieria mechaniczna"); break;
				
			case PSYCHIATRY:
				result.add("psychiatria"); break;
				
			case CARD_GAMES:
				result.add("gra w karty"); break;
				
			case ROMAN_MYTHOLOGY:
				result.add("mitologia rzymska"); break;
				
			case KABUKI:
				result.add("kabuki"); break;
				
			case FILM:
				result.add("film"); break;
				
			case TELEVISION:
				result.add("telewizja"); break;
				
			case POLITICS:
				result.add("polityka"); break;
			
			case DENTISTRY:
				result.add("dentystyka"); break;
				
			case MANGA:
				result.add("manga"); break;
				
			case MINING:
				result.add("górnictwo"); break;
			
			case PSYCHOANALYSIS:
				result.add("psychoanaliza"); break;
				
			case SKIING:
				result.add("narciarstwo"); break;
				
			case STOCK_MARKET:
				result.add("giełda papierów wartościowych"); break;
				
			case INTERNET:
				result.add("internet"); break;
				
			case PROFESSIONAL_WRESTLING:
				result.add("zapasy zawodowe"); break;	
			
			case MOTORSPORT:
				result.add("motorsport"); break;
				
			case BOXING:
				result.add("boks"); break;
				
			case CHINESE_MYTHOLOGY:
				result.add("chińska mitologia"); break;
		
			case CIVIL_ENGINEERING:
				result.add("inżynieria lądowa"); break;
				
			case JAPANESE_MYTHOLOGY:
				result.add("japońska mitologia"); break;
				
			case VETERINARY_TERMS:
				result.add("termin weterynaryjny"); break;
				
			case MINERALOGY:
				result.add("mineralogia"); break;				
				
			case FIGURE_SKATING:
				result.add("łyżwiarstwo figurowe"); break;
				
			case SURGERY:
				result.add("chirurgia"); break;
				
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
							
			case YOJIJUKUGO:
				result.add("słowo składające się z czterech znaków"); break;
				
			case ABBREVIATION:
				result.add("skrót"); break;
				
			case COLLOQUIAL:
				result.add("potocznie"); break;
				
			case DEROGATORY:
				result.add("poniżająco"); break;
				
			case SLANG:
				result.add("slang"); break;
			
			case ONOMATOPOEIC_OR_MIMETIC_WORD:
				result.add("onomatopeiczne lub mimetyczne słowo"); break; 
				
			case IDIOMATIC_EXPRESSION:
				result.add("wyrażenie idiomatyczne"); break;
				
			case ARCHAIC:
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
				
			case LEGEND:
				result.add("legenda"); break;
				
			case WORK_OF_ART_LITERATURE_MUSIC_ETC_NAME:
				result.add("dzieło artystyczne, np. literatura, muzyka i etc"); break;
				
			case RARE_TERM:
				result.add("rzadko używane określenie"); break;
				
			case EUPHEMISTIC:
				result.add("wyrażenie eufemistyczne"); break;
				
			case PRODUCT_NAME:
				result.add("nazwa produktu"); break;
				
			case DEITY:
				result.add("bóstwo"); break;
				
			case MYTHOLOGY:
				result.add("mitologia"); break;
				
			case COMPANY_NAME:
				result.add("nazwa firmy"); break;
				
			case PLACE_NAME:
				result.add("nazwa miejsca"); break;
				
			case FAMILY_OR_SURNAME:
				result.add("rodzina lub nazwisko"); break;
				
			case ORGANIZATION_NAME:
				result.add("nazwa organizacji"); break;
				
			case CHARACTER:
				result.add("postać"); break;
				
			case FICTION:
				result.add("fikcja"); break;
				
			case OBJECT:
				result.add("przedmiot"); break;
				
			case EVENT:
				result.add("wydarzenie"); break;
				
			case CREATURE:
				result.add("stworzenie"); break;
				
			case SERVICE:
				result.add("usługa"); break;
				
			case GIVEN_NAME_OR_FORENAME_GENDER_NOT_SPECIFIED:
				result.add("imię bez określonej płci"); break;
				
			case UNCLASSIFIED_NAME:
				result.add("niesklasyfikowana nazwa"); break;
				
			case SHIP_NAME:
				result.add("nazwa statku"); break;
				
			case GROUP:
				result.add("grupa"); break;
			
			case DOCUMENT:
				result.add("dokument"); break;
				
			case RAILWAY_STATION:
				result.add("stacja kolejowa"); break;
				
			case RELIGION:
				result.add("religia"); break;
				
			case RUDE_OR_X_RATED_TERM_NOT_DISPLAYED_IN_EDUCATIONAL_SOFTWARE:
				result.add("wulgarne lub oznaczone kategorią wiekową X"); break;
				
			case OTHER:
				result.add("inny"); break;
				
			default:
				throw new RuntimeException("Unknown misc enum: " + miscEnum);
			
			}
		}
		
		return result;
	}
	
	public static Collection<MiscEnum> filterPolishMiscEnumList(Collection<MiscEnum> miscEnumList) {
				
		List<MiscEnum> result = new ArrayList<>();
		
		for (MiscEnum miscEnum : miscEnumList) {
			
			if (	miscEnum == MiscEnum.WORD_USUALLY_WRITTEN_USING_KANA_ALONE ||
					miscEnum == MiscEnum.YOJIJUKUGO ||
					miscEnum == MiscEnum.ONOMATOPOEIC_OR_MIMETIC_WORD) {
				continue;
			}
			
			result.add(miscEnum);			
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
				
			case TSUGARU_BEN:
				result.add("dialekt Tsugaru"); break;
				
			case NAGANO_BEN:
				result.add("dialekt Nagano"); break;
				
			case TOSA_BEN:
				result.add("dialekt Tosa"); break;
				
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
			
		case "scr":
			return "chor";
			
		case "hin":
			return "hin";
			
		case "yid":
			return "jid";
			
		case "dan":
			return "duń";
			
		case "pol":
			return "pol";
			
		case "tib":
			return "tib";
			
		case "per":
			return "per";
			
		case "geo":
			return "gru";
			
		case "glg":
			return "gal";
			
		case "fin":
			return "fin";
			
		case "ukr":
			return "ukr";
			
		case "cze":
			return "cze";
			
		case "slo":
			return "slo";
			
		case "urd":
			return "urd";
			
		case "mon":
			return "mon";
			
		case "est":
			return "est";
			
		case "slv":
			return "sło";
			
		case "tam":
			return "tam";
			
		case "nor":
			return "nor";
			
		case "swa":
			return "swa";
			
		case "amh":
			return "amh";
			
		case "mao":
			return "mao";
			
		case "mal":
			return "mal";
			
		case "ind":
			return "indo";
			
		case "arn":
			return "map";
			
		case "bul":
			return "buł";
			
		case "lit":
			return "lit";
			
		case "khm":
			return "khm";
			
		case "rum":
			return "rum";
			
		case "mol":
			return "mol";
			
		case "tgl":
			return "tgl";

		case "uzb":
			return "uzb";
			
		case "bre":
			return "bre";
			
		case "epo":
			return "epo";
			
		case "tah":
			return "tah";
			
		case "alg":
			return "alg";
			
		case "bur":
			return "bir";
			
		case "bnt":
			return "ban";
			
		case "fil":
			return "fil";
			
		case "som":
			return "som";
			
		case "kur":
			return "kur";
						
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

		case "scr":
			return "słowo pochodzenia chorwackiego";
			
		case "hin":
			return "słowo pochodzenia indyjskiego";
			
		case "yid":
			return "słowo pochodzenia żydowskiego";
			
		case "dan":
			return "słowo pochodzenia duńskiego";
			
		case "pol":
			return "słowo pochodzenia polskiego";
			
		case "tib":
			return "słowo pochodzenia tybetańskiego";
			
		case "per":
			return "słowo pochodzenia perskiego";
			
		case "geo":
			return "słowo pochodzenia gruzińskiego";
			
		case "glg":
			return "słowo pochodzenia galicyjskiego";
			
		case "fin":
			return "słowo pochodzenia fińskiego";
			
		case "ukr":
			return "słowo pochodzenia ukraińskiego";
			
		case "cze":
			return "słowo pochodzenia czeskiego";

		case "slo":
			return "słowo pochodzenia słowackiego";

		case "urd":
			return "słowo pochodzi z języka urdu";
			
		case "mon":
			return "słowo pochodzenia mongolskiego";
			
		case "est":
			return "słowo pochodzenia estońskiego";
			
		case "slv":
			return "słowo pochodzenia słoweńskiego";
			
		case "tam":
			return "słowo pochodzi z języka tamilskiego";
			
		case "nor":
			return "słowo pochodzenia norweskiego";
			
		case "swa":
			return "słowo pochodzi z języka suahili (swahili)";
			
		case "amh":
			return "słowo pochodzi z języka amharskiego";
			
		case "mao":
			return "słowo pochodzenia maoryskiego";
			
		case "mal":
			return "słowo pochodzi z języka malajalam";
			
		case "ind":
			return "słowo pochodzenia indonezyjskiego";
			
		case "arn":
			return "słowo pochodzi z języka mapudungun";
			
		case "bul":
			return "słowo pochodzenia bułgarskiego";
			
		case "lit":
			return "słowo pochodzenia litewskiego";
			
		case "khm":
			return "słowo pochodzi z języka khmerskiego";
			
		case "rum":
			return "słowo pochodzenia rumuńskiego";
			
		case "mol":
			return "słowo pochodzenia mołdawskiego";
			
		case "tgl":
			return "słowo pochodzi z języka tagalskiego";
			
		case "uzb":
			return "słowo pochodzi z języka uzbeckiego";
			
		case "bre":
			return "słowo pochodzi z języka bretońskiego";
			
		case "epo":
			return "słowo pochodzi z języka esperanto";
			
		case "tah":
			return "słowo pochodzi z języka tahitańskiego";
			
		case "alg":
			return "słowo pochodzi z języka algonkiańskiego";
			
		case "bur":
			return "słowo pochodzi z języka birmańskiego";
			
		case "bnt":
			return "słowo pochodzi z języka bantu";
			
		case "fil":
			return "słowo pochodzi z języka filipińskiego";
			
		case "som":
			return "słowo pochodzi z języka somalijskiego";
			
		case "kur":
			return "słowo pochodzi z języka kurdyjskiego";
			
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
				result.add("wyrażenie (zwrot, zdanie składowe i etc)"); break;
				
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

			case GODAN_VERB_URU_OLD_CLASS_VERB_OLD_FORM_OF_ERU:
				result.add("czasownik godan (u-czasownik), stary czasownik klasy uru (stara forma eru)"); break;
				
			case GODAN_VERB_WITH_U_ENDING_SPECIAL_CLASS:
				result.add("czasownik godan (u-czasownik) kończący się na u - klasa specjalna"); break;
				
			case ICHIDAN_VERB_ZURU_VERB_ALTERNATIVE_FORM_OF_JIRU_VERBS:
				result.add("czasownik ichidan (ru-czasownik) - czasownik zuru (alternatywna forma czasownika jiru)"); break;
				
			case ICHIDAN_VERB_KURERU_SPECIAL_CLASS:
				result.add("czasownik ichidan (ru-czasownik) - specjalna klasa kureru"); break;
				
			case IRREGULAR_RU_VERB_PLAIN_FORM_ENDS_WITH_RI:
				result.add("nieregularny ru-czasownik z formą prostą kończącą się na ri"); break;
				
			case NIDAN_VERB_WITH_U_ENDING_ARCHAIC:
				result.add("czasownik nidan kończący się na u (archaiczny)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_KU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na ku (archaiczny)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_HU_FU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na hu/fu (archaiczny)"); break;	

			case NIDAN_VERB_LOWER_CLASS_WITH_RU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na ru (archaiczny)"); break;	

			case NIDAN_VERB_LOWER_CLASS_WITH_SU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na su (archaiczny)"); break;	

			case NIDAN_VERB_LOWER_CLASS_WITH_YU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na yu (archaiczny)"); break;	
				
			case NIDAN_VERB_LOWER_CLASS_WITH_MU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na mu (archaiczny)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_BU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na bu (archaiczny)"); break;

			case NIDAN_VERB_LOWER_CLASS_WITH_ZU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na zu (archaiczny)"); break;

			case NIDAN_VERB_LOWER_CLASS_WITH_DZU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na dzu (archaiczny)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_NU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na nu (archaiczny)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_GU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na gu (archaiczny)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_TSU_ENDING_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na tsu (archaiczny)"); break;
				
			case NIDAN_VERB_LOWER_CLASS_WITH_U_ENDING_AND_WE_CONJUGATION_ARCHAIC:
				result.add("czasownik nidan (niższa klasa) kończący się na u i z koniugacją we (archaiczny)"); break;
				
			case NIDAN_VERB_UPPER_CLASS_WITH_TSU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na tsu (archaiczny)"); break;								
				
			case NIDAN_VERB_UPPER_CLASS_WITH_GU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na gu (archaiczny)"); break;				

			case NIDAN_VERB_UPPER_CLASS_WITH_KU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na ku (archaiczny)"); break;				

			case NIDAN_VERB_UPPER_CLASS_WITH_HU_FU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na hu/fu (archaiczny)"); break;
				
			case NIDAN_VERB_UPPER_CLASS_WITH_YU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na yu (archaiczny)"); break;
				
			case NIDAN_VERB_UPPER_CLASS_WITH_BU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na bu (archaiczny)"); break;
				
			case NIDAN_VERB_UPPER_CLASS_WITH_RU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na ru (archaiczny)"); break;
				
			case NIDAN_VERB_UPPER_CLASS_WITH_DZU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na dzu (archaiczny)"); break;

			case NIDAN_VERB_UPPER_CLASS_WITH_MU_ENDING_ARCHAIC:
				result.add("czasownik nidan (wyższa klasa) kończący się na mu (archaiczny)"); break;

			case YODAN_VERB_WITH_KU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na ku (archaiczny)"); break;

			case YODAN_VERB_WITH_RU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na ru (archaiczny)"); break;

			case YODAN_VERB_WITH_SU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na su (archaiczny)"); break;

			case YODAN_VERB_WITH_MU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na mu (archaiczny)"); break;
				
			case YODAN_VERB_WITH_BU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na bu (archaiczny)"); break;
			
			case YODAN_VERB_WITH_HU_FU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na hu/fu (archaiczny)"); break;
				
			case YODAN_VERB_WITH_GU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na gu (archaiczny)"); break;
				
			case YODAN_VERB_WITH_TSU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na tsu (archaiczny)"); break;
				
			case YODAN_VERB_WITH_NU_ENDING_ARCHAIC:
				result.add("czasownik yodan kończący się na nu (archaiczny)"); break;
				
			case SHIKU_ADJECTIVE_ARCHAIC:
				result.add("przymiotnik shiku (archaiczny)"); break;
				
			case VERB_UNSPECIFIED:
				result.add("czasownik nieokreślony"); break;
				
			case UNCLASSIFIED:
				result.add("niesklasyfikowany"); break;
				
			case KARI_ADJECTIVE_ARCHAIC:
				result.add("przymiotnik kari (archaiczny)"); break;
				
			case ADVERBIAL_NOUN_FUKUSHITEKIMEISHI:
				result.add("rzeczownik przysłówkowy (fukushitekimeishi)"); break;
				
			case NOUN_TEMPORAL_JISOUMEISHI:
				result.add("rzeczownik (przejściowy) (jisoumeishi)"); break;
			
			case PROPER_NOUN:
				result.add("nazwa własna"); break;
				
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
				
			case SEARCH_ONLY_KANJI_FORM:
				result.add("forma kanji tylko do wyszukiwania"); break;
			
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
				
			// case WORD_USUALLY_WRITTEN_USING_KANJI_ALONE:
			//	result.add("słowo zazwyczaj zapisywane wyłącznie z użyciem kanji"); break;
				
			case SEARCH_ONLY_KANA_FORM:
				result.add("forma kana tylko do wyszukiwania"); break;
				
			case RARELY_USED_KANA_FORM:
				result.add("rzadko używana forma kana"); break;
				
			default:				
				throw new RuntimeException("Unknown reading additional info enum: " + readingAdditionalInfoEnum);				
			}
		}
		
		return result;
	}
	
	public static PrintableSense getPrintableSense(KanjiKanaPair kanjiKanaPair) {
		
		PrintableSense printableSense = new PrintableSense();
		
		for (int senseIdx = 0; senseIdx < kanjiKanaPair.getSenseList().size(); ++senseIdx) {
			
			PrintableSenseEntry printableSenseEntry = new PrintableSenseEntry();
			
			Sense sense = kanjiKanaPair.getSenseList().get(senseIdx);
			
			List<Gloss> glossList = sense.getGlossList();
			List<SenseAdditionalInfo> senseAdditionalInfoList = sense.getAdditionalInfoList();
			List<LanguageSource> senseLanguageSourceList = sense.getLanguageSourceList();
			List<FieldEnum> senseFieldList = sense.getFieldList();
			List<MiscEnum> senseMiscList = sense.getMiscList();
			List<DialectEnum> senseDialectList = sense.getDialectList();
			List<PartOfSpeechEnum> partOfSpeechList = sense.getPartOfSpeechList();
										
			// pobieramy polskie tlumaczenia
			List<Gloss> glossPolList = getPolishGlossList(glossList);
			
			// i informacje dodatkowe
			SenseAdditionalInfo senseAdditionalPol = findFirstPolishAdditionalInfo(senseAdditionalInfoList);				
						
			// czesci mowy
			if (partOfSpeechList.size() > 0) {				
				List<String> translateToPolishPartOfSpeechEnum = Dictionary2HelperCommon.translateToPolishPartOfSpeechEnum(partOfSpeechList);
				
				printableSenseEntry.setPolishPartOfSpeechValue(pl.idedyk.japanese.dictionary.api.dictionary.Utils.convertListToString(translateToPolishPartOfSpeechEnum, "; "));
			}				
			
			for (Gloss currentGlossPol : glossPolList) {
				
				PrintableSenseEntryGloss printableSenseEntryGloss = new PrintableSenseEntryGloss();
				
				// dodanie pojedynczego znaczenia
				printableSenseEntryGloss.setGlossValue(currentGlossPol.getValue());
								
				// sprawdzenie, czy wystepuje dodatkowy typ znaczenia
				if (currentGlossPol.getGType() != null) {
					printableSenseEntryGloss.setGlossValueGType(Dictionary2HelperCommon.translateToPolishGlossType(currentGlossPol.getGType()));					
				}
				
				printableSenseEntry.getGlossList().add(printableSenseEntryGloss);
			}
			
			// informacje dodatkowe
			List<String> additionalInfoToAddList = new ArrayList<>();
			
			// dziedzina
			if (senseFieldList.size() > 0) {
				additionalInfoToAddList.addAll(Dictionary2HelperCommon.translateToPolishFieldEnumList(senseFieldList));						
			}
			
			// rozne informacje
			if (senseMiscList.size() > 0) {
				additionalInfoToAddList.addAll(Dictionary2HelperCommon.translateToPolishMiscEnumList(senseMiscList));
			}
			
			// dialekt
			if (senseDialectList.size() > 0) {
				additionalInfoToAddList.addAll(Dictionary2HelperCommon.translateToPolishDialectEnumList(senseDialectList));
			}
			
			if (senseAdditionalPol != null) { // czy informacje dodatkowe istnieja				
				String senseAdditionalPolValue = senseAdditionalPol.getValue();
				
				additionalInfoToAddList.add(senseAdditionalPolValue);
			}
			
			// czy sa informacje o zagranicznym pochodzeniu slow
			if (senseLanguageSourceList != null && senseLanguageSourceList.size() > 0) {
				
				for (LanguageSource languageSource : senseLanguageSourceList) {
											
					String languageCodeInPolish = Dictionary2HelperCommon.translateToPolishLanguageCode(languageSource.getLang());
					String languageValue = languageSource.getValue();
					String languageLsWasei = Dictionary2HelperCommon.translateToPolishLanguageSourceLsWaseiEnum(languageSource.getLsWasei());
					
					if (languageValue != null && languageValue.trim().equals("") == false) {
						additionalInfoToAddList.add(languageCodeInPolish + ": " + languageValue);
						
					} else {
						additionalInfoToAddList.add(Dictionary2HelperCommon.translateToPolishLanguageCodeWithoutValue(languageSource.getLang()));
					}
					
					if (languageLsWasei != null) {
						additionalInfoToAddList.add(languageLsWasei);
					}
				}
			}
							
			if (additionalInfoToAddList.size() > 0) {
				printableSenseEntry.setAdditionalInfoValue(pl.idedyk.japanese.dictionary.api.dictionary.Utils.convertListToString(additionalInfoToAddList, "; "));
			}
			
			//
			
			printableSense.getSenseEntryList().add(printableSenseEntry);
		}
				
		return printableSense;
	}
	
	public static List<Gloss> getPolishGlossList(List<Gloss> glossPolList) {
				
		List<Gloss> result = new ArrayList<>();
		
		for (Gloss gloss : glossPolList) {
			if (gloss.getLang().equals("pol") == true) {
				result.add(gloss);
			}
		}
		
		return result;
	}
	
	public static SenseAdditionalInfo findFirstPolishAdditionalInfo(List<SenseAdditionalInfo> additionalInfo) {
		
		for (SenseAdditionalInfo senseAdditionalInfo : additionalInfo) {
			if (senseAdditionalInfo.getLang().equals("pol") == true) {
				return senseAdditionalInfo;
			}
		}
		
		return null;
	}
	
	public static String[] getUniqueKanjiKanaRomajiSetWithoutSearchOnly(Entry entry) {
		
		Set<String> kanjiUniqueSet = new LinkedHashSet<>();
		Set<String> kanaUniqueSet = new LinkedHashSet<>();
		Set<String> romajiUniqueSet = new LinkedHashSet<>();
		
		// kanji
		entry.getKanjiInfoList().stream().filter(kanjiInfo -> {
			boolean isKanjiSearchOnly = kanjiInfo != null && kanjiInfo.getKanjiAdditionalInfoList().contains(KanjiAdditionalInfoEnum.SEARCH_ONLY_KANJI_FORM) == true;
			
			return isKanjiSearchOnly == false;
		}).forEach(kanjiInfo -> kanjiUniqueSet.add(kanjiInfo.getKanji()));
		
		// kana i romaji
		entry.getReadingInfoList().stream().filter(readingInfo -> {
			boolean isKanaSearchOnly = readingInfo.getReadingAdditionalInfoList().contains(ReadingAdditionalInfoEnum.SEARCH_ONLY_KANA_FORM) == true;
			
			return isKanaSearchOnly == false;				
		}).forEach(readingInfo -> {
			
			kanaUniqueSet.add(readingInfo.getKana().getValue());
			romajiUniqueSet.add(readingInfo.getKana().getRomaji());
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
	
	public static DictionaryEntry convertKanjiKanaPairToOldDictionaryEntry(KanjiKanaPair kanjiKanaPair) {
		
		final String kanjiKanaPairKanji = kanjiKanaPair.getKanji() != null ? kanjiKanaPair.getKanji() : "-";
		final String kanjiKanaPairKana = kanjiKanaPair.getKana();
						
		// szukamy starego elementu
		JMdict.Entry dictionaryEntry2 = kanjiKanaPair.getEntry();
		
		if (dictionaryEntry2 == null || dictionaryEntry2.getMisc() == null || dictionaryEntry2.getMisc().getOldPolishJapaneseDictionary() == null) {
			return null;
		}
				
		DictionaryEntry oldDictionaryEntry = dictionaryEntry2.getMisc().getOldPolishJapaneseDictionary().getEntries().stream().filter(oldPolishJapaneseDictionary -> {
			
			String oldPolishJapaneseDictionaryKanji = oldPolishJapaneseDictionary.getKanji();
			String oldPolishJapaneseDictionaryKana = oldPolishJapaneseDictionary.getKana();
			
			if (oldPolishJapaneseDictionaryKanji == null) {
				oldPolishJapaneseDictionaryKanji = "-";
			}
			
			return 	kanjiKanaPairKanji.equals(oldPolishJapaneseDictionaryKanji) == true &&
					kanjiKanaPairKana.equals(oldPolishJapaneseDictionaryKana) == true;
			
		}).map(oldPolishJapaneseDictionary -> {
			DictionaryEntry oldVirtualDictionaryEntry = new DictionaryEntry();
			
			// id
			oldVirtualDictionaryEntry.setId((int)oldPolishJapaneseDictionary.getId());
			
			// dictionaryEntryTypeList
			oldVirtualDictionaryEntry.setDictionaryEntryTypeList(Arrays.asList(oldPolishJapaneseDictionary.getDictionaryEntryTypeList().split(",")).stream(). 
				map(m -> DictionaryEntryType.getDictionaryEntryType(m)).collect(Collectors.toList()));

			// attributeList
			dictionaryEntry2.getMisc().getOldPolishJapaneseDictionary().getAttributeList().stream().forEach(attr -> {
				if (oldVirtualDictionaryEntry.getAttributeList() == null) {
					oldVirtualDictionaryEntry.setAttributeList(new AttributeList());
				}
				
				oldVirtualDictionaryEntry.getAttributeList().addAttributeValue(AttributeType.valueOf(attr.getType()), attr.getValue());
			});
								
			// wordType
			oldVirtualDictionaryEntry.setWordType(WordType.valueOf(kanjiKanaPair.getKanaType().value()));
			
			// groups
			oldVirtualDictionaryEntry.setGroups(dictionaryEntry2.getMisc().getOldPolishJapaneseDictionary().getGroupsList().stream().
				map(grr -> GroupEnum.valueOf(grr)).collect(Collectors.toList()));
				
			// prefixKana, kanji, kana, prefixRomaji, romaji
			oldVirtualDictionaryEntry.setPrefixKana(oldPolishJapaneseDictionary.getPrefixKana());
			oldVirtualDictionaryEntry.setPrefixRomaji(oldPolishJapaneseDictionary.getPrefixRomaji());
			
			oldVirtualDictionaryEntry.setKanji(oldPolishJapaneseDictionary.getKanji());
			oldVirtualDictionaryEntry.setKana(oldPolishJapaneseDictionary.getKana());
			oldVirtualDictionaryEntry.setRomaji(oldPolishJapaneseDictionary.getRomaji());
			
			// translates, info, exampleSentenceGroupIdsList, name
			// tych elementow nie mapujemy
			
			//
			
			return oldVirtualDictionaryEntry;
							
		}).findFirst().orElse(null);

		return oldDictionaryEntry;		
	}
	
	public static CreateTranslatesAndAdditionalInfoForOldDictionaryEntryResult createTranslatesAndAdditionalInfoForOldDictionaryEntry(KanjiKanaPair kanjiKanaPair) {
		
		// pobieranie wszystkich znaczen
		List<Sense> kanjiKanaPairSenseList = kanjiKanaPair.getSenseList();
		
		// czesc wspolna	
		
		Collection<FieldEnum> fieldCommonList = null;
		Collection<MiscEnum> miscCommonList = null;
		Collection<DialectEnum> dialectCommonList = null;
		Collection<String> languageSourceCommonList = null;
		Collection<String> additionalInfoCommonList = null;
		
		// generowanie wspolnej czesci dla wszystkich znaczen
		for (Sense currentSense : kanjiKanaPairSenseList) {

			if (fieldCommonList == null) {
				fieldCommonList = currentSense.getFieldList();
				
			} else {
				fieldCommonList = CollectionUtils.intersection(fieldCommonList, currentSense.getFieldList());
			}
			
			//
			
			if (miscCommonList == null) {
				miscCommonList = currentSense.getMiscList();
				
			} else {
				miscCommonList = CollectionUtils.intersection(miscCommonList, currentSense.getMiscList());
			}
			
			//
			
			if (dialectCommonList == null) {
				dialectCommonList = currentSense.getDialectList();
				
			} else {
				dialectCommonList = CollectionUtils.intersection(dialectCommonList, currentSense.getDialectList());
			}	
			
			//
			
			if (languageSourceCommonList == null) {
				languageSourceCommonList = translateToPolishLanguageSourceList(currentSense.getLanguageSourceList());
				
			} else {
				languageSourceCommonList = CollectionUtils.intersection(languageSourceCommonList, translateToPolishLanguageSourceList(currentSense.getLanguageSourceList()));
			}
			
			//
			
			List<SenseAdditionalInfo> additionalPolInfoList = currentSense.getAdditionalInfoList().stream().filter(senseAdditionalInfo -> (senseAdditionalInfo.getLang().equals("pol") == true)).collect(Collectors.toList());
			
			if (additionalInfoCommonList == null) {
				additionalInfoCommonList = translateToPolishSenseAdditionalInfoList(additionalPolInfoList);
				
			} else {
				additionalInfoCommonList = CollectionUtils.intersection(additionalInfoCommonList, translateToPolishSenseAdditionalInfoList(additionalPolInfoList));
			}
		}

		
		List<String> newPolishTranslateList = new ArrayList<>();			
		List<String> newPolishAdditionalInfoList = new ArrayList<>();
		
		for (Sense currentSense : kanjiKanaPairSenseList) {
			
			List<SenseAdditionalInfo> additionalPolInfoList = currentSense.getAdditionalInfoList().stream().filter(senseAdditionalInfo -> (senseAdditionalInfo.getLang().equals("pol") == true)).collect(Collectors.toList());
			
			//

			List<FieldEnum> currentSenseFieldList = currentSense.getFieldList();
			List<MiscEnum> currentSenseMiscList = currentSense.getMiscList();
			List<DialectEnum> currentSenseDialectList = currentSense.getDialectList();
			List<String> currentSenseLanguageSourceList = translateToPolishLanguageSourceList(currentSense.getLanguageSourceList());
			List<String> currentSenseAdditionalInfoList = translateToPolishSenseAdditionalInfoList(additionalPolInfoList);
			List<PartOfSpeechEnum> partOfSpeechList = currentSense.getPartOfSpeechList();
			
			
			// wyliczenie roznic miedzy obecnym znaczeniem, a czescia wspolna dla wszystkich znaczen
			Collection<FieldEnum> fieldEnumListUniqueForCurrentSense = CollectionUtils.subtract(currentSenseFieldList, fieldCommonList);
			Collection<MiscEnum> miscEnumListUniqueForCurrentSense = CollectionUtils.subtract(currentSenseMiscList, miscCommonList);
			Collection<DialectEnum> dialectEnumListUniqueForCurrentSense = CollectionUtils.subtract(currentSenseDialectList, dialectCommonList);
			Collection<String> languageSourceListUniqueForCurrentSense = CollectionUtils.subtract(currentSenseLanguageSourceList, languageSourceCommonList);
			Collection<String> senseAdditionalInfoListUniqueForCurrentSense = CollectionUtils.subtract(currentSenseAdditionalInfoList, additionalInfoCommonList);
			
			//
							
			List<Gloss> glossPolList = currentSense.getGlossList().stream().filter(gloss -> (gloss.getLang().equals("pol") == true)).collect(Collectors.toList());
			
			for (Gloss currentPolGloss : glossPolList) {
				
				String currentPolGlossType = translateToPolishGlossType(currentPolGloss.getGType());
				String currentPolGlossValue = currentPolGloss.getValue();
				
				//
				
				List<String> currentPolGlossPolishTranslate = new ArrayList<>();
				
				// dodajemy tlumaczenie
				currentPolGlossPolishTranslate.add(currentPolGlossValue);
				
				// podtyp tlumaczenia
				if (currentPolGlossType != null) {
					currentPolGlossPolishTranslate.add(currentPolGlossType);
				}
				
				// dziedzina
				if (fieldEnumListUniqueForCurrentSense.size() > 0) {
					currentPolGlossPolishTranslate.addAll(translateToPolishFieldEnumList(fieldEnumListUniqueForCurrentSense));						
				}
				
				// rozne informacje
				if (miscEnumListUniqueForCurrentSense.size() > 0) {
					currentPolGlossPolishTranslate.addAll(translateToPolishMiscEnumList(filterPolishMiscEnumList(miscEnumListUniqueForCurrentSense)));
				}
				
				// dialekt
				if (dialectEnumListUniqueForCurrentSense.size() > 0) {
					currentPolGlossPolishTranslate.addAll(translateToPolishDialectEnumList(dialectEnumListUniqueForCurrentSense));
				}
				
				// informacje dodatkowe dla znaczenia
				if (senseAdditionalInfoListUniqueForCurrentSense.size() > 0) {
					currentPolGlossPolishTranslate.addAll(senseAdditionalInfoListUniqueForCurrentSense);
				}
				
				// jezyk zrodlowy
				if (languageSourceListUniqueForCurrentSense.size() > 0) {
					currentPolGlossPolishTranslate.addAll(languageSourceListUniqueForCurrentSense);
				}
									
				// generowanie tlumaczenia dla slowka
				
				// nowa pozycja w tlumaczeniu
				newPolishTranslateList.add(joinStringForOldPolishJapaneseEntry(currentPolGlossPolishTranslate, true));
			}
			
			// czesc mowy - tylko sprawdzenie
			if (partOfSpeechList.size() > 0) {
				translateToPolishPartOfSpeechEnum(partOfSpeechList);
			}
		}
		
		// informacje dodatkowe
					
		// dziedzina
		if (fieldCommonList != null && fieldCommonList.size() > 0) {
			newPolishAdditionalInfoList.addAll(translateToPolishFieldEnumList(fieldCommonList));						
		}
		
		// rozne informacje
		if (miscCommonList != null && miscCommonList.size() > 0) {
			newPolishAdditionalInfoList.addAll(translateToPolishMiscEnumList(miscCommonList));
		}
		
		// dialekt
		if (dialectCommonList != null && dialectCommonList.size() > 0) {
			newPolishAdditionalInfoList.addAll(translateToPolishDialectEnumList(dialectCommonList));
		}
		
		// informacje dodatkowe dla znaczenia
		if (additionalInfoCommonList != null && additionalInfoCommonList.size() > 0) {
			newPolishAdditionalInfoList.addAll(additionalInfoCommonList);
		}
		
		// jezyk zrodlowy
		if (languageSourceCommonList != null && languageSourceCommonList.size() > 0) {
			newPolishAdditionalInfoList.addAll(languageSourceCommonList);
		}
		
		String newPolishAdditionalInfo = joinStringForOldPolishJapaneseEntry(newPolishAdditionalInfoList, false);
		
		// utworzenie wyniku
		return new CreateTranslatesAndAdditionalInfoForOldDictionaryEntryResult(newPolishTranslateList, newPolishAdditionalInfo);
		
	}
	
	private static List<String> translateToPolishLanguageSourceList(Collection<LanguageSource> languageSourceList) {
		
		List<String> result = new ArrayList<>();
		
		for (LanguageSource languageSource : languageSourceList) {
			
			String languageCodeInPolish = translateToPolishLanguageCode(languageSource.getLang());
			String languageValue = languageSource.getValue();
			
			if (languageValue != null && languageValue.trim().equals("") == false) {
				result.add(languageCodeInPolish + ": " + languageValue);
				
			} else {
				result.add(translateToPolishLanguageCodeWithoutValue(languageSource.getLang()));
			}
		}
		
		return result;
	}
	
	private static List<String> translateToPolishSenseAdditionalInfoList(List<SenseAdditionalInfo> additionalPolInfoList) {
				
		List<String> result = new ArrayList<>();
		
		for (SenseAdditionalInfo senseAdditionalInfo : additionalPolInfoList) {
			result.add(senseAdditionalInfo.getValue());
		}
		
		return result;
	}
		
	private static String joinStringForOldPolishJapaneseEntry(List<String> list, boolean addBracket) {
		
		StringBuffer result = new StringBuffer();
		
		for (int idx = 0; idx < list.size(); ++idx) {
			
			String currentListElement = list.get(idx);
			
			if (idx == 0) {
				result.append(currentListElement);
				
			} else if (idx == 1 && addBracket == true) {
				result.append(" (");
				result.append(currentListElement);
				
			} else if (idx >= 2 || addBracket == false) {
				result.append("; ");
				result.append(currentListElement);
				
			} else {
				throw new RuntimeException();
			}
			
			if (idx != 0 && idx == list.size() - 1 && addBracket == true) {
				result.append(")");
			}
		}

		return result.toString();
	}
	
	public static class KanjiKanaPair {
		
		private Entry entry;
		
		private KanjiInfo kanjiInfo;		
		private ReadingInfo readingInfo;
				
		private List<Sense> senseList = new ArrayList<>();

		public KanjiKanaPair(Entry entry, KanjiInfo kanjiInfo, ReadingInfo readingInfo) {
			this.entry = entry;
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
		
		public String getRomaji() {

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

		public Entry getEntry() {
			return entry;
		}

		@Override
		public String toString() {
			return "KanjiKanaPair [entryId=" + entry.getEntryId() + ", kanji=" + kanjiInfo + ", kana=" + readingInfo + "]";
		}
	}
	
	public static class PrintableSense {
		
		private List<PrintableSenseEntry> senseEntryList = new ArrayList<>();
		
		public List<PrintableSenseEntry> getSenseEntryList() {
			return senseEntryList;
		}
		
		public void addSenseEntry(PrintableSenseEntry senseEntry) {
			senseEntryList.add(senseEntry);
		}
	}
	
	public static class PrintableSenseEntry {
		
		private String polishPartOfSpeechValue;
		
		private List<PrintableSenseEntryGloss> glossList = new ArrayList<>();
		
		private String additionalInfoValue;
				
		public String getPolishPartOfSpeechValue() {
			return polishPartOfSpeechValue;
		}

		public void setPolishPartOfSpeechValue(String polishPartOfSpeechValue) {
			this.polishPartOfSpeechValue = polishPartOfSpeechValue;
		}

		public List<PrintableSenseEntryGloss> getGlossList() {
			return glossList;
		}

		public void setGlossList(List<PrintableSenseEntryGloss> glossList) {
			this.glossList = glossList;
		}

		public String getAdditionalInfoValue() {
			return additionalInfoValue;
		}

		public void setAdditionalInfoValue(String additionalInfoValue) {
			this.additionalInfoValue = additionalInfoValue;
		}
	}
	
	public static class PrintableSenseEntryGloss {
		
		private String glossValue;
		
		private String glossValueGType;

		public String getGlossValue() {
			return glossValue;
		}

		public void setGlossValue(String glossValue) {
			this.glossValue = glossValue;
		}

		public String getGlossValueGType() {
			return glossValueGType;
		}

		public void setGlossValueGType(String glossValueGType) {
			this.glossValueGType = glossValueGType;
		}
	}
	
	public static class CreateTranslatesAndAdditionalInfoForOldDictionaryEntryResult {
		private List<String> newPolishTranslateList;
		private String newPolishAdditionalInfo;
		
		public CreateTranslatesAndAdditionalInfoForOldDictionaryEntryResult(List<String> newPolishTranslateList, String newPolishAdditionalInfo) {
			this.newPolishTranslateList = newPolishTranslateList;
			this.newPolishAdditionalInfo = newPolishAdditionalInfo;
		}

		public List<String> getNewPolishTranslateList() {
			return newPolishTranslateList;
		}

		public String getNewPolishAdditionalInfo() {
			return newPolishAdditionalInfo;
		}		
	}
}
