package pl.idedyk.japanese.dictionary.api.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum DictionaryEntryType {

	WORD_NUMBER("liczba"),

	WORD_NOUN("rzeczownik"),
	
	WORD_NOUN_PREFIX("rzeczownik (prefiks)"),
	
	WORD_NOUN_SUFFIX("rzeczownik (przyrostek)"),	
	
	WORD_PRE_NOUN_ADJECTIVAL("pre rzeczownik przysłówkowy"),
	
	WORD_PROPER_NOUN("nazwa własna"),
	
	WORD_ADJECTIVE_F("rzeczownik, bądź czasownik pełniący rolę przymiotnika"),

	WORD_TEMPORAL_NOUN("rzeczownik czasowy"),

	WORD_VERB_U("u-czasownik"),

	WORD_VERB_RU("ru-czasownik"),

	WORD_VERB_TE("te-czasownik"),

	WORD_VERB_IRREGULAR("czasownik nieregularny"),

	WORD_VERB_ZURU("czasownik zuru"),

	WORD_VERB_AUX("czasownik pomocniczy"),
	
	WORD_NIDAN_VERB("???"),

	WORD_ADJECTIVE_I("i-przymiotnik"),

	WORD_AUX_ADJECTIVE_I("i-przymiotnik (pomocniczy)"),

	WORD_ADJECTIVE_NA("na-przymiotnik"),
	
	WORD_ADJECTIVE_NO("rzeczownik z partykułą no pełniący rolę przymiotnika"),

	WORD_ADJECTIVE_TARU("taru-przymiotnik"),

	WORD_ADJECTIVE_NARI("archaiczna/dawna forma na-przymiotnika"),
	
	WORD_ADJECTIVE_KU("ku-przymiotnik"),
	
	WORD_EXPRESSION("wyrażenie"),

	WORD_ADVERB("przysłówek"),

	WORD_ADVERB_TO("przysłówek z partykułą to"),

	WORD_ADVERBIAL_NOUN("rzeczownik przysłówkowy"),

	WORD_COUNTER("klasyfikatory"),

	WORD_PRONOUN("zaimki"),

	WORD_NAME("imię"),

	WORD_MALE_NAME("imię męskie"),

	WORD_FEMALE_NAME("imię żeńskie"),

	WORD_SURNAME_NAME("nazwisko"),
	
	WORD_UNCLASS_NAME("bezklasowa nazwa"),

	WORD_PERSON("osoba"),
	
	WORD_STATION_NAME("nazwa stacji"),
	
	WORD_PLACE("nazwa miejsca"),
	
	WORD_COMPANY_NAME("nazwa firmy"),
	
	WORD_PRODUCT_NAME("nazwa produktu"),
	
	WORD_ORGANIZATION_NAME("nazwa organizacji"),
	
	WORD_CONJUNCTION("spójnik"),

	WORD_PARTICULE("partykuła"),

	WORD_INTERJECTION("wykrzyknik"),

	WORD_AUX("słówko pomocnicze"),
	
	WORD_PREFIX("prefiks"),
	
	WORD_SUFFIX("przyrostek"),
	
	WORD_EMPTY("pusty"),

	UNKNOWN("nieznany");

	private String name;

	DictionaryEntryType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// static

	private static List<DictionaryEntryType> addableDictionaryEntryList;

	static {
		addableDictionaryEntryList = new ArrayList<DictionaryEntryType>();

		addableDictionaryEntryList.add(WORD_PARTICULE);
		addableDictionaryEntryList.add(WORD_COUNTER);
		addableDictionaryEntryList.add(WORD_NOUN);
		addableDictionaryEntryList.add(WORD_NOUN_PREFIX);
		addableDictionaryEntryList.add(WORD_NOUN_SUFFIX);
		addableDictionaryEntryList.add(WORD_TEMPORAL_NOUN);
		addableDictionaryEntryList.add(WORD_PROPER_NOUN);
		addableDictionaryEntryList.add(WORD_ADJECTIVE_I);
		addableDictionaryEntryList.add(WORD_AUX_ADJECTIVE_I);
		addableDictionaryEntryList.add(WORD_ADJECTIVE_NA);
		addableDictionaryEntryList.add(WORD_ADJECTIVE_NO);
		addableDictionaryEntryList.add(WORD_ADJECTIVE_TARU);
		addableDictionaryEntryList.add(WORD_ADJECTIVE_F);
		addableDictionaryEntryList.add(WORD_VERB_U);
		addableDictionaryEntryList.add(WORD_VERB_RU);
		addableDictionaryEntryList.add(WORD_VERB_IRREGULAR);
		addableDictionaryEntryList.add(WORD_VERB_ZURU);
		addableDictionaryEntryList.add(WORD_VERB_TE);
		addableDictionaryEntryList.add(WORD_VERB_AUX);
		addableDictionaryEntryList.add(WORD_ADVERB);
		addableDictionaryEntryList.add(WORD_ADVERB_TO);
		addableDictionaryEntryList.add(WORD_ADVERBIAL_NOUN);
		addableDictionaryEntryList.add(WORD_PRONOUN);
		addableDictionaryEntryList.add(WORD_CONJUNCTION);
		addableDictionaryEntryList.add(WORD_INTERJECTION);
		addableDictionaryEntryList.add(WORD_AUX);
		addableDictionaryEntryList.add(WORD_EXPRESSION);
		addableDictionaryEntryList.add(WORD_PREFIX);
		addableDictionaryEntryList.add(WORD_SUFFIX);
		addableDictionaryEntryList.add(WORD_NAME);
		addableDictionaryEntryList.add(WORD_MALE_NAME);
		addableDictionaryEntryList.add(WORD_FEMALE_NAME);
		addableDictionaryEntryList.add(WORD_SURNAME_NAME);
		addableDictionaryEntryList.add(WORD_PERSON);
		addableDictionaryEntryList.add(WORD_STATION_NAME);
		addableDictionaryEntryList.add(WORD_PLACE);		
		addableDictionaryEntryList.add(WORD_COMPANY_NAME);
		addableDictionaryEntryList.add(WORD_PRODUCT_NAME);
		addableDictionaryEntryList.add(WORD_ORGANIZATION_NAME);
		
		Collections.sort(addableDictionaryEntryList, new Comparator<DictionaryEntryType>() {

			@Override
			public int compare(DictionaryEntryType o1, DictionaryEntryType o2) {
				
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	public static boolean isAddableDictionaryEntryTypeInfo(DictionaryEntryType dictionaryEntryType) {
		return addableDictionaryEntryList.contains(dictionaryEntryType);
	}

	public static List<DictionaryEntryType> getAddableDictionaryEntryList() {
		return addableDictionaryEntryList;
	}

	public static List<DictionaryEntryType> getOtherDictionaryEntryList() {

		List<DictionaryEntryType> otherDictionaryEntryList = new ArrayList<DictionaryEntryType>();

		DictionaryEntryType[] allValues = values();

		for (DictionaryEntryType currentValues : allValues) {

			if (addableDictionaryEntryList.contains(currentValues) == false) {
				otherDictionaryEntryList.add(currentValues);
			}
		}

		return otherDictionaryEntryList;
	}

	public static List<DictionaryEntryType> convertToListDictionaryEntryType(List<String> values) {

		if (values == null) {
			return null;
		}

		List<DictionaryEntryType> dictionaryEntryTypeList = new ArrayList<DictionaryEntryType>();

		for (String currentValue : values) {

			DictionaryEntryType dictionaryEntryType = getDictionaryEntryType(currentValue);

			if (dictionaryEntryType != null) {
				dictionaryEntryTypeList.add(dictionaryEntryType);
			}
		}

		return dictionaryEntryTypeList;
	}

	public static DictionaryEntryType getDictionaryEntryType(String value) {

		if (value == null || value.equals("") == true) {
			return null;
		}

		return DictionaryEntryType.valueOf(value);
	}

	public static List<String> convertToValues(List<DictionaryEntryType> dictionaryEntryTypeList) {

		if (dictionaryEntryTypeList == null) {
			return null;
		}

		List<String> values = new ArrayList<String>();

		for (DictionaryEntryType currentDictionaryEntryTypeList : dictionaryEntryTypeList) {
			values.add(currentDictionaryEntryTypeList.toString());
		}

		return values;
	}
}