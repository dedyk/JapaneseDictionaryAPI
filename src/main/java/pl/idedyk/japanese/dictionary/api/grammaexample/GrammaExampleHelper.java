package pl.idedyk.japanese.dictionary.api.grammaexample;

import java.util.ArrayList;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;

public class GrammaExampleHelper {
	
	public static void addExample(List<ExampleGroupTypeElements> result, ExampleGroupType exampleGroupType, ExampleResult exampleResult) {
		
		if (exampleResult == null) {
			return;
		}
		
		ExampleGroupTypeElements exampleGroup = new ExampleGroupTypeElements();
		
		exampleGroup.setExampleGroupType(exampleGroupType);
		
		exampleGroup.getExampleResults().add(exampleResult);
		
		result.add(exampleGroup);
	}
	
	public static GrammaFormConjugateResult makeSimpleTemplateGrammaFormConjugateResult(GrammaFormConjugateResult grammaFormConjugateResult,
			String templateKanji, String templateKana, String templateRomaji) {
		
		GrammaFormConjugateResult result = new GrammaFormConjugateResult();
		
		String kanji = grammaFormConjugateResult.getKanji();
		
		List<String> kanaList = grammaFormConjugateResult.getKanaList();
		
		List<String> romajiList = grammaFormConjugateResult.getRomajiList();
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();
		
		result.setPrefixKana(prefixKana);
		result.setPrefixRomaji(prefixRomaji);
		
		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, kanji));
		}

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			kanaListResult.add(String.format(templateKana, currentKana));
		}

		result.setKanaList(kanaListResult);

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			romajiListResult.add(String.format(templateRomaji, currentRomaji));
		}

		result.setRomajiList(romajiListResult);
		
		return result;
	}
	
	public static ExampleResult makeSimpleTemplateExample(DictionaryEntry dictionaryEntry,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = dictionaryEntry.getPrefixKana();
		String kanji = dictionaryEntry.getKanji();
		
		@SuppressWarnings("deprecation")
		List<String> kanaList = dictionaryEntry.getKanaList();
		
		String prefixRomaji = dictionaryEntry.getPrefixRomaji();
		
		@SuppressWarnings("deprecation")
		List<String> romajiList = dictionaryEntry.getRomajiList();
		
		return makeSimpleTemplateExample(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static GrammaFormConjugateResult makeSimpleTemplateGrammaFormConjugateResult(DictionaryEntry dictionaryEntry,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = dictionaryEntry.getPrefixKana();
		String kanji = dictionaryEntry.getKanji();
		
		@SuppressWarnings("deprecation")
		List<String> kanaList = dictionaryEntry.getKanaList();
		
		String prefixRomaji = dictionaryEntry.getPrefixRomaji();
		
		@SuppressWarnings("deprecation")
		List<String> romajiList = dictionaryEntry.getRomajiList();
		
		return makeSimpleTemplateGrammaFormConjugateResult(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExample(GrammaFormConjugateResult grammaFormConjugateResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();
		String kanji = grammaFormConjugateResult.getKanji();
		
		List<String> kanaList = grammaFormConjugateResult.getKanaList();
		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();
		
		List<String> romajiList = grammaFormConjugateResult.getRomajiList();
		
		return makeSimpleTemplateExample(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static ExampleResult makeSimpleTemplateExample(ExampleResult exampleResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleResult.getPrefixKana();
		String kanji = exampleResult.getKanji();
		List<String> kanaList = exampleResult.getKanaList();
		String prefixRomaji = exampleResult.getPrefixRomaji();
		List<String> romajiList = exampleResult.getRomajiList();
		
		return makeSimpleTemplateExample(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(ExampleResult exampleResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleResult.getPrefixKana();
		String kanji = exampleResult.getKanji();
		
		List<String> kanaList = exampleResult.getKanaList();		
		String prefixRomaji = exampleResult.getPrefixRomaji();
		List<String> romajiList = exampleResult.getRomajiList();
		
		return makeSimpleTemplateExampleWithLastCharRemove(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(DictionaryEntry dictionaryEntry,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = dictionaryEntry.getPrefixKana();
		String kanji = dictionaryEntry.getKanji();
		
		@SuppressWarnings("deprecation")
		List<String> kanaList = dictionaryEntry.getKanaList();
		
		String prefixRomaji = dictionaryEntry.getPrefixRomaji();
		
		@SuppressWarnings("deprecation")
		List<String> romajiList = dictionaryEntry.getRomajiList();
		
		return makeSimpleTemplateExampleWithLastCharRemove(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}


	public static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(GrammaFormConjugateResult grammaFormConjugateResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();
		String kanji = grammaFormConjugateResult.getKanji();
		
		List<String> kanaList = grammaFormConjugateResult.getKanaList();
		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();
		
		List<String> romajiList = grammaFormConjugateResult.getRomajiList();
		
		return makeSimpleTemplateExampleWithLastCharRemove(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(DictionaryEntry dictionaryEntry,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = dictionaryEntry.getPrefixKana();
		String kanji = dictionaryEntry.getKanji();
		
		@SuppressWarnings("deprecation")
		List<String> kanaList = dictionaryEntry.getKanaList();
		
		String prefixRomaji = dictionaryEntry.getPrefixRomaji();
		
		@SuppressWarnings("deprecation")
		List<String> romajiList = dictionaryEntry.getRomajiList();
		
		return makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(GrammaFormConjugateResult grammaFormConjugateResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();
		String kanji = grammaFormConjugateResult.getKanji();
		
		List<String> kanaList = grammaFormConjugateResult.getKanaList();
		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();
		
		List<String> romajiList = grammaFormConjugateResult.getRomajiList();
		
		return makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(prefixKana, kanji, kanaList, prefixRomaji, romajiList, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static ExampleResult makeSimpleTemplateExample(String prefixKana, String kanji, List<String> kanaList, String prefixRomaji, List<String> romajiList,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		ExampleResult result = new ExampleResult();
				
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}
		
		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, kanji));
		}

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			kanaListResult.add(String.format(templateKana, currentKana));
		}

		result.setKanaList(kanaListResult);

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			romajiListResult.add(String.format(templateRomaji, currentRomaji));
		}

		result.setRomajiList(romajiListResult);
		
		return result;		
	}
	
	private static GrammaFormConjugateResult makeSimpleTemplateGrammaFormConjugateResult(String prefixKana, String kanji, List<String> kanaList, String prefixRomaji, List<String> romajiList,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		GrammaFormConjugateResult result = new GrammaFormConjugateResult();
				
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}
		
		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, kanji));
		}

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			kanaListResult.add(String.format(templateKana, currentKana));
		}

		result.setKanaList(kanaListResult);

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			romajiListResult.add(String.format(templateRomaji, currentRomaji));
		}

		result.setRomajiList(romajiListResult);
		
		return result;		
	}

	private static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(String prefixKana, String kanji, List<String> kanaList, String prefixRomaji, List<String> romajiList,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		ExampleResult result = new ExampleResult();
		
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}

		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, removeLastChar(kanji)));
		}

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			kanaListResult.add(String.format(templateKana, removeLastChar(currentKana)));
		}

		result.setKanaList(kanaListResult);

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			romajiListResult.add(String.format(templateRomaji, removeLastChar(currentRomaji)));
		}

		result.setRomajiList(romajiListResult);
		
		return result;		
	}

	private static ExampleResult makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(String prefixKana, String kanji, List<String> kanaList, String prefixRomaji, List<String> romajiList,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		ExampleResult result = new ExampleResult();
		
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}

		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, removeLastChar(kanji)));
		}

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			kanaListResult.add(String.format(templateKana, removeLastChar(currentKana)));
		}

		result.setKanaList(kanaListResult);

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			romajiListResult.add(String.format(templateRomaji, removeTwoChars(currentRomaji)));
		}

		result.setRomajiList(romajiListResult);
		
		return result;		
	}
	
	private static String removeLastChar(String text) {
		return text.substring(0, text.length() - 1);
	}

	private static String removeTwoChars(String text) {
		return text.substring(0, text.length() - 2);
	}
	
	public static DictionaryEntry convertToVirtualDictionaryEntry(GrammaFormConjugateResult grammaFormConjugateResult, DictionaryEntryType dictionaryEntryType) {
		
		DictionaryEntry virtualDictionaryEntry = new DictionaryEntry();
		
		virtualDictionaryEntry.setDictionaryEntryType(dictionaryEntryType);
		
		virtualDictionaryEntry.setPrefixKana(grammaFormConjugateResult.getPrefixKana());
		virtualDictionaryEntry.setKanji(grammaFormConjugateResult.getKanji());
		
		virtualDictionaryEntry.setKana(grammaFormConjugateResult.getKanaList().get(0));
		
		virtualDictionaryEntry.setPrefixRomaji(grammaFormConjugateResult.getPrefixRomaji());
		virtualDictionaryEntry.setRomaji(grammaFormConjugateResult.getRomajiList().get(0));
		
		return virtualDictionaryEntry;
	}
}
