package pl.idedyk.japanese.dictionary.api.grammaexample;

import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleRequest;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateRequest;
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
		String kana = grammaFormConjugateResult.getKana();		
		String romaji = grammaFormConjugateResult.getRomaji();
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();
		
		result.setPrefixKana(prefixKana);
		result.setPrefixRomaji(prefixRomaji);
		
		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, kanji));
		}
		
		result.setKana(String.format(templateKana, kana));
		result.setRomaji(String.format(templateRomaji, romaji));
		
		return result;
	}
	
	public static ExampleResult makeSimpleTemplateExample(ExampleRequest exampleRequest,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleRequest.getPrefixKana();
		String kanji = exampleRequest.getKanji();		
		String kana = exampleRequest.getKana();
		
		String prefixRomaji = exampleRequest.getPrefixRomaji();		
		String romaji = exampleRequest.getRomaji();
		
		return makeSimpleTemplateExample(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static GrammaFormConjugateResult makeSimpleTemplateGrammaFormConjugateResult(GrammaFormConjugateRequest grammaFormConjugateRequest,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = grammaFormConjugateRequest.getPrefixKana();
		String kanji = grammaFormConjugateRequest.getKanji();		
		String kana = grammaFormConjugateRequest.getKana();
		
		String prefixRomaji = grammaFormConjugateRequest.getPrefixRomaji();
		String romaji = grammaFormConjugateRequest.getRomaji();
		
		return makeSimpleTemplateGrammaFormConjugateResult(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExample(GrammaFormConjugateResult grammaFormConjugateResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();
		String kanji = grammaFormConjugateResult.getKanji();
		
		String kana = grammaFormConjugateResult.getKana();
		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();		
		String romaji = grammaFormConjugateResult.getRomaji();
		
		return makeSimpleTemplateExample(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static ExampleResult makeSimpleTemplateExample(ExampleResult exampleResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleResult.getPrefixKana();
		String kanji = exampleResult.getKanji();
		String kana = exampleResult.getKana();
		String prefixRomaji = exampleResult.getPrefixRomaji();
		String romaji = exampleResult.getRomaji();
		
		return makeSimpleTemplateExample(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(ExampleResult exampleResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleResult.getPrefixKana();
		String kanji = exampleResult.getKanji();
		
		String kana = exampleResult.getKana();		
		String prefixRomaji = exampleResult.getPrefixRomaji();
		String romaji = exampleResult.getRomaji();
		
		return makeSimpleTemplateExampleWithLastCharRemove(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(ExampleRequest exampleRequest,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleRequest.getPrefixKana();
		String kanji = exampleRequest.getKanji();		
		String kana = exampleRequest.getKana();
		
		String prefixRomaji = exampleRequest.getPrefixRomaji();		
		String romaji = exampleRequest.getRomaji();
		
		return makeSimpleTemplateExampleWithLastCharRemove(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(GrammaFormConjugateResult grammaFormConjugateResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();
		String kanji = grammaFormConjugateResult.getKanji();		
		String kana = grammaFormConjugateResult.getKana();
		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();
		String romaji = grammaFormConjugateResult.getRomaji();
		
		return makeSimpleTemplateExampleWithLastCharRemove(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(ExampleRequest exampleRequest,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleRequest.getPrefixKana();
		String kanji = exampleRequest.getKanji();		
		String kana = exampleRequest.getKana();
		
		String prefixRomaji = exampleRequest.getPrefixRomaji();		
		String romaji = exampleRequest.getRomaji();
		
		return makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}

	public static ExampleResult makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(GrammaFormConjugateResult grammaFormConjugateResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = grammaFormConjugateResult.getPrefixKana();
		String kanji = grammaFormConjugateResult.getKanji();
		String kana = grammaFormConjugateResult.getKana();
		
		String prefixRomaji = grammaFormConjugateResult.getPrefixRomaji();		
		String romaji = grammaFormConjugateResult.getRomaji();
		
		return makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static ExampleResult makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(ExampleResult exampleResult,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		String prefixKana = exampleResult.getPrefixKana();
		String kanji = exampleResult.getKanji();		
		String kana = exampleResult.getKana();
		
		String prefixRomaji = exampleResult.getPrefixRomaji();		
		String romaji = exampleResult.getRomaji();
		
		return makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(prefixKana, kanji, kana, prefixRomaji, romaji, templateKanji, templateKana, templateRomaji, canAddPrefix);
	}
	
	public static ExampleResult makeSimpleTemplateExample(String prefixKana, String kanji, String kana, String prefixRomaji, String romaji,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		ExampleResult result = new ExampleResult();
				
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}
		
		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, kanji));
		}

		result.setKana(String.format(templateKana, kana));
		result.setRomaji(String.format(templateRomaji, romaji));
		
		return result;		
	}
	
	private static GrammaFormConjugateResult makeSimpleTemplateGrammaFormConjugateResult(String prefixKana, String kanji, String kana, String prefixRomaji, String romaji,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		GrammaFormConjugateResult result = new GrammaFormConjugateResult();
				
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}
		
		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, kanji));
		}
		
		result.setKana(String.format(templateKana, kana));
		result.setRomaji(String.format(templateRomaji, romaji));
		
		return result;		
	}

	private static ExampleResult makeSimpleTemplateExampleWithLastCharRemove(String prefixKana, String kanji, String kana, String prefixRomaji, String romaji,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		ExampleResult result = new ExampleResult();
		
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}

		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, removeLastChar(kanji)));
		}
		
		result.setKana(String.format(templateKana, removeLastChar(kana)));
		result.setRomaji(String.format(templateRomaji, removeLastChar(romaji)));
		
		return result;		
	}

	private static ExampleResult makeSimpleTemplateExampleWithKanaLastCharAndRomajiTwoCharsRemove(String prefixKana, String kanji, String kana, String prefixRomaji, String romaji,
			String templateKanji, String templateKana, String templateRomaji, boolean canAddPrefix) {
		
		ExampleResult result = new ExampleResult();
		
		if (canAddPrefix == true) {
			result.setPrefixKana(prefixKana);
			result.setPrefixRomaji(prefixRomaji);
		}

		if (kanji != null) {		
			result.setKanji(String.format(templateKanji, removeLastChar(kanji)));
		}
		
		result.setKana(String.format(templateKana, removeLastChar(kana)));
		result.setRomaji(String.format(templateRomaji, removeTwoChars(romaji)));
		
		return result;		
	}
	
	private static String removeLastChar(String text) {
		return text.substring(0, text.length() - 1);
	}

	private static String removeTwoChars(String text) {
		return text.substring(0, text.length() - 2);
	}
	
	public static GrammaFormConjugateRequest convertToVirtualDictionaryEntry(GrammaFormConjugateResult grammaFormConjugateResult, DictionaryEntryType dictionaryEntryType) {
		
		DictionaryEntry virtualDictionaryEntry = new DictionaryEntry();
		
		virtualDictionaryEntry.setDictionaryEntryType(dictionaryEntryType);
		
		virtualDictionaryEntry.setPrefixKana(grammaFormConjugateResult.getPrefixKana());
		virtualDictionaryEntry.setKanji(grammaFormConjugateResult.getKanji());
		
		virtualDictionaryEntry.setKana(grammaFormConjugateResult.getKana());
		
		virtualDictionaryEntry.setPrefixRomaji(grammaFormConjugateResult.getPrefixRomaji());
		virtualDictionaryEntry.setRomaji(grammaFormConjugateResult.getRomaji());
		
		return new GrammaFormConjugateRequest(virtualDictionaryEntry);
	}
}
