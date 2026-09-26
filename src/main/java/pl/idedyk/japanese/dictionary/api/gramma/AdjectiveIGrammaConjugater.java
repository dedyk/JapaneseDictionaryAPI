package pl.idedyk.japanese.dictionary.api.gramma;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateGroupType;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;
import pl.idedyk.japanese.dictionary.api.grammaexample.GrammaExampleHelper;
import pl.idedyk.japanese.dictionary.api.grammaexample.dto.GeneratedGrammaExampleWrapperEntry;
import pl.idedyk.japanese.dictionary.api.grammaexample.dto.GrammaExampleType;
import pl.idedyk.japanese.dictionary.api.grammaexample.dto.IGrammaExampleWrapperEntry;

public class AdjectiveIGrammaConjugater {

	public static List<IGrammaExampleWrapperEntry> makeAll(IGrammaExampleWrapperEntry grammaExampleWrapperEntry, 
			Map<GrammaExampleType, IGrammaExampleWrapperEntry> grammaFormCache, boolean addVirtual) {

		if (isKanaI(grammaExampleWrapperEntry) == true) { // nie liczymy dla i dla kany
			return null;
		}
		
		// validate DictionaryEntry
		validateDictionaryEntry(grammaExampleWrapperEntry);
		
		List<IGrammaExampleWrapperEntry> result = new ArrayList<IGrammaExampleWrapperEntry>();

		// forma formalna
		result.add(makeFormalPresentForm(grammaExampleWrapperEntry));
		result.add(makeFormalPresentNegativeForm(grammaExampleWrapperEntry));
		result.add(makeFormalPastForm(grammaExampleWrapperEntry));
		result.add(makeFormalPastNegativeForm(grammaExampleWrapperEntry));
		
		// forma nieformalna (prosta)
		result.add(makeInformalPresentForm(grammaExampleWrapperEntry));
		result.add(makeInformalPresentNegativeForm(grammaExampleWrapperEntry));
		result.add(makeInformalPastForm(grammaExampleWrapperEntry));
		result.add(makeInformalPastNegativeForm(grammaExampleWrapperEntry));

		result.add(informal);
		
		// forma przyslowkowa
		GrammaFormConjugateGroupTypeElements adverbForm = new GrammaFormConjugateGroupTypeElements();
		
		adverbForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_ADVERB);
		
		adverbForm.getGrammaFormConjugateResults().add(makeAdverbForm(grammaExampleWrapperEntry));
		
		result.add(adverbForm);
		
		// forma te
		GrammaFormConjugateGroupTypeElements teForm = new GrammaFormConjugateGroupTypeElements();
		
		teForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_TE);
		
		teForm.getGrammaFormConjugateResults().add(makeTeForm(grammaExampleWrapperEntry));
		teForm.getGrammaFormConjugateResults().add(makeNegativeTeForm(grammaExampleWrapperEntry));
		
		result.add(teForm);
		
		// forma honoryfikatywna
		GrammaFormConjugateGroupTypeElements keigoForm = new GrammaFormConjugateGroupTypeElements();
		
		keigoForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_KEIGO);
		
		keigoForm.getGrammaFormConjugateResults().add(makeKeigoLowForm(grammaExampleWrapperEntry));
		
		result.add(keigoForm);
		
		// caching
		for (GrammaFormConjugateGroupTypeElements grammaFormConjugateGroupTypeElements : result) {
			
			List<GrammaFormConjugateResult> grammaFormConjugateResults = grammaFormConjugateGroupTypeElements.getGrammaFormConjugateResults();
			
			for (GrammaFormConjugateResult grammaFormConjugateResult : grammaFormConjugateResults) {
				grammaFormCache.put(grammaFormConjugateResult.getResultType(), grammaFormConjugateResult);
			}
		}
		
		// virtual
		GrammaFormConjugateResult virtualForm = makeVirtualForm(grammaExampleWrapperEntry);
		
		grammaFormCache.put(virtualForm.getResultType(), virtualForm);

		return result;		
	}
	
	private static GrammaFormConjugateResult makeVirtualForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		
		// wirtualna metoda bez "i" na koncu i ewentualne przerobienie ii na yoi
		
		GrammaFormConjugateResult virtualForm = makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL,
				"", "");
				
		return virtualForm;
	}

	private static IGrammaExampleWrapperEntry makeFormalPresentForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas terazniejszy, twierdzenie, forma formalna, -i desu

		final String postfixKana = "いです";
		final String postfixRomaji = "i desu";

		return makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_FORMAL_PRESENT,
				postfixKana, postfixRomaji);
	}

	private static IGrammaExampleWrapperEntry makeFormalPresentNegativeForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas terazniejszy, przeczenie, forma formalna (prosta), -kunai desu

		final String postfixKana = "くないです";
		final String postfixRomaji = "kunai desu";

		IGrammaExampleWrapperEntry grammaExampleWrapperEntryResult = makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_FORMAL_PRESENT_NEGATIVE,
				postfixKana, postfixRomaji);
		
		// alternative
		grammaExampleWrapperEntryResult.setAlternative(makeFormalPresentNegativeForm2(grammaExampleWrapperEntry));
		
		return grammaExampleWrapperEntryResult;
	}

	private static IGrammaExampleWrapperEntry makeFormalPresentNegativeForm2(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas terazniejszy, przeczenie, forma formalna (prosta), -ku arimasen

		final String postfixKana = "くありません";
		final String postfixRomaji = "ku arimasen";

		return makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_FORMAL_PRESENT_NEGATIVE,
				postfixKana, postfixRomaji);
	}
	
	private static IGrammaExampleWrapperEntry makeFormalPastForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas przesly, twierdzenie, forma formalna, -katta desu

		final String postfixKana = "かったです";
		final String postfixRomaji = "katta desu";

		return makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_FORMAL_PAST,
				postfixKana, postfixRomaji);
	}

	private static IGrammaExampleWrapperEntry makeFormalPastNegativeForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas przesly, przeczenie, forma formalna, -ku nakatta desu

		final String postfixKana = "くなかったです";
		final String postfixRomaji = "kunakatta desu";

		IGrammaExampleWrapperEntry grammaExampleWrapperEntryResult = makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_FORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
		
		// alternative
		grammaExampleWrapperEntryResult.setAlternative(makeFormalPastNegativeForm2(grammaExampleWrapperEntry));
		
		return grammaExampleWrapperEntryResult;
	}

	private static IGrammaExampleWrapperEntry makeFormalPastNegativeForm2(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas przesly, przeczenie, forma formalna, -ku arimasen deshita
		
		final String postfixKana = "くありませんでした";
		final String postfixRomaji = "ku arimasen deshita";

		return makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_FORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
	}
	
	private static IGrammaExampleWrapperEntry makeInformalPresentForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas terazniejszy, twierdzenie, forma nieformalna (prosta), -i

		final String postfixKana = "い";
		final String postfixRomaji = "i";

		return makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_INFORMAL_PRESENT,
				postfixKana, postfixRomaji);
	}

	private static IGrammaExampleWrapperEntry makeInformalPresentNegativeForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		
		// czas terazniejszy, przeczenie, forma nieformalna (prosta), -kunai

		final String postfixKana = "くない";
		final String postfixRomaji = "kunai";

		IGrammaExampleWrapperEntry grammaExampleWrapperEntryResult = makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE,
				postfixKana, postfixRomaji);
				
		return grammaExampleWrapperEntryResult;
	}

	private static IGrammaExampleWrapperEntry makeInformalPastForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		
		// czas przesly, twierdzenie, forma nieformalna (prosta), -katta

		final String postfixKana = "かった";
		final String postfixRomaji = "katta";

		IGrammaExampleWrapperEntry grammaExampleWrapperEntryResult = makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_INFORMAL_PAST,
				postfixKana, postfixRomaji);
				
		return grammaExampleWrapperEntryResult;
	}

	private static IGrammaExampleWrapperEntry makeInformalPastNegativeForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// czas przesly, przeczenie, forma nieformalna (prosta), -ku nakatta

		final String postfixKana = "くなかった";
		final String postfixRomaji = "kunakatta";

		return makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaExampleType.ADJECTIVE_I_INFORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
	}

	private static IGrammaExampleWrapperEntry makeAdjectiveGrammaConjugateForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry, 
			GrammaExampleType grammaExampleType, String postfixKana, String postfixRomaji) {

		// make common
		GeneratedGrammaExampleWrapperEntry result = makeCommon(grammaExampleWrapperEntry);

		result.addStackGrammaExampleType(grammaExampleType);

		String kanji = grammaExampleWrapperEntry.getKanji();

		if (kanji != null) {
			kanji = getKanaToConjugate(kanji, grammaExampleWrapperEntry.getRomaji(), grammaExampleType);
			
			result.setKanji(removeLastChar(kanji) + postfixKana);
		}

		List<String> kanaList = grammaExampleWrapperEntry.getKanaList();
		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			currentKana = getKanaToConjugate(currentKana, grammaExampleWrapperEntry.getRomaji(), grammaExampleType);

			kanaListResult.add(removeLastChar(currentKana) + postfixKana);
		}

		result.setKanaList(kanaListResult);		

		List<String> romajiList = grammaExampleWrapperEntry.getRomajiList();
		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			currentRomaji = getRomajiToConjugate(currentRomaji, grammaExampleType);

			romajiListResult.add(removeLastChar(currentRomaji) + postfixRomaji);
		}

		result.setRomajiList(romajiListResult);

		return result; 
	}

	private static String getKanaToConjugate(String kana, String romaji, GrammaExampleType grammaExampleType) {

		if (grammaExampleType != GrammaExampleType.ADJECTIVE_I_FORMAL_PRESENT && 
				grammaExampleType != GrammaExampleType.ADJECTIVE_I_INFORMAL_PRESENT) {

			if (kana.endsWith("いい") == true) {
				
				if (romaji.equals("ii") == true || romaji.endsWith(" ii") == true) {
					return kana.substring(0, kana.length() - 2) + "よい";
				}
			}
		}

		return kana;
	}

	private static String getRomajiToConjugate(String romaji, GrammaExampleType grammaExampleType) {

		if (grammaExampleType != GrammaExampleType.ADJECTIVE_I_FORMAL_PRESENT && 
				grammaExampleType != GrammaExampleType.ADJECTIVE_I_INFORMAL_PRESENT) {

			if (romaji.equals("ii") == true || romaji.endsWith(" ii") == true) {
				return romaji.substring(0, romaji.length() - 2) + "yoi";
			}
		}

		return romaji;
	}

	private static GeneratedGrammaExampleWrapperEntry makeCommon(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {

		// create result
		GeneratedGrammaExampleWrapperEntry result = new GeneratedGrammaExampleWrapperEntry();

		result.setPrefixKana(grammaExampleWrapperEntry.getPrefixKana());
		result.setPrefixRomaji(grammaExampleWrapperEntry.getPrefixRomaji());
		
		// stos poprzednich typow
		grammaExampleWrapperEntry.getStackGrammaExampleTypeList().forEach(s -> result.addStackGrammaExampleType(s));
				
		return result;
	}

	private static String removeLastChar(String text) {
		return text.substring(0, text.length() - 1);
	}

	private static void validateDictionaryEntry(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		
		List<DictionaryEntryType> dictionaryEntryTypeList = grammaExampleWrapperEntry.getDictionaryEntryTypeList();

		if (dictionaryEntryTypeList.contains(DictionaryEntryType.WORD_ADJECTIVE_I) == false) {
			throw new RuntimeException("dictionaryEntryType != DictionaryEntryType.WORD_ADJECTIVE_I: " + dictionaryEntryTypeList);
		}

		String kanji = grammaExampleWrapperEntry.getKanji();

		if (kanji != null && kanji.endsWith("い") == false) {
			throw new RuntimeException("kanji.endsWith(い) == false: " + kanji);
		}

		List<String> kanaList = grammaExampleWrapperEntry.getKanaList();

		for (String currentKana : kanaList) {
			if (currentKana.endsWith("い") == false) {
				throw new RuntimeException("currentKana.endsWith(い) == false: " + currentKana);
			}			
		}

		List<String> romajiList = grammaExampleWrapperEntry.getRomajiList();

		for (String currentRomaji : romajiList) {
			if (currentRomaji.endsWith("i") == false) {
				throw new RuntimeException("currentRomaji.endsWith(i) == false: " + currentRomaji);
			}
		}		
	}
	
	private static boolean isKanaI(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		
		String kanji = grammaExampleWrapperEntry.getKanji();

		if (kanji != null && kanji.endsWith("イ") == true) {
			return true;
		}

		List<String> kanaList = grammaExampleWrapperEntry.getKanaList();

		for (String currentKana : kanaList) {
			if (currentKana.endsWith("イ") == true) {
				return true;
			}			
		}		
		
		return false;
	}
	
	private static GrammaFormConjugateResult makeAdverbForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
				
		GrammaFormConjugateResult virtualForm = makeAdjectiveGrammaConjugateForm(grammaExampleWrapperEntry, GrammaFormConjugateResultType.ADJECTIVE_I_ADVERB,
				"く", "ku");
				
		return virtualForm;
	}

	
	private static GrammaFormConjugateResult makeTeForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// forma te
		
		String postfixKana = "くて";
		String postfixRomaji = "kute";
		
		// make common
		GrammaFormConjugateResult result = makeCommon(grammaExampleWrapperEntry);
		
		result.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_TE);
		
		String kanji = grammaExampleWrapperEntry.getKanji();

		if (kanji != null) {
			kanji = getKanaToConjugate(kanji, grammaExampleWrapperEntry.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE);
			
			result.setKanji(removeLastChar(kanji) + postfixKana);
		}

		List<String> kanaList = grammaExampleWrapperEntry.getKanaList();

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			currentKana = getKanaToConjugate(currentKana, grammaExampleWrapperEntry.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE);

			kanaListResult.add(removeLastChar(currentKana) + postfixKana);
		}

		result.setKanaList(kanaListResult);		

		List<String> romajiList = grammaExampleWrapperEntry.getRomajiList();

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			currentRomaji = getRomajiToConjugate(currentRomaji, GrammaFormConjugateResultType.ADJECTIVE_I_TE);

			romajiListResult.add(removeLastChar(currentRomaji) + postfixRomaji);
		}

		result.setRomajiList(romajiListResult);		
		
		return result;
	}
	
	private static GrammaFormConjugateResult makeNegativeTeForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		// forma te
		
		String postfixKana = "くなくて";
		String postfixRomaji = "kunakute";
		
		// make common
		GrammaFormConjugateResult result = makeCommon(grammaExampleWrapperEntry);
		
		result.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
		
		String kanji = grammaExampleWrapperEntry.getKanji();

		if (kanji != null) {
			kanji = getKanaToConjugate(kanji, grammaExampleWrapperEntry.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
			
			result.setKanji(removeLastChar(kanji) + postfixKana);
		}

		List<String> kanaList = grammaExampleWrapperEntry.getKanaList();

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			currentKana = getKanaToConjugate(currentKana, grammaExampleWrapperEntry.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);

			kanaListResult.add(removeLastChar(currentKana) + postfixKana);
		}

		result.setKanaList(kanaListResult);		

		List<String> romajiList = grammaExampleWrapperEntry.getRomajiList();

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			currentRomaji = getRomajiToConjugate(currentRomaji, GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);

			romajiListResult.add(removeLastChar(currentRomaji) + postfixRomaji);
		}

		result.setRomajiList(romajiListResult);		
		
		return result;
	}
	
	private static GrammaFormConjugateResult makeKeigoLowForm(IGrammaExampleWrapperEntry grammaExampleWrapperEntry) {
		
		// keigo low
		
		final String templateKanji1 = "%sでございます";
		final String templateKana1 = "%sでございます";
		final String templateRomaji1 = "%s de gozaimasu";
		
		GrammaFormConjugateResult result = GrammaExampleHelper.makeSimpleTemplateGrammaFormConjugateResult(grammaExampleWrapperEntry, templateKanji1, templateKana1, templateRomaji1, true);
		
		result.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_KEIGO_LOW);
		
		final String templateKanji2 = "%sでござる";
		final String templateKana2 = "%sでござる";
		final String templateRomaji2 = "%s de gozaru";
		
		GrammaFormConjugateResult alternative = GrammaExampleHelper.makeSimpleTemplateGrammaFormConjugateResult(grammaExampleWrapperEntry, templateKanji2, templateKana2, templateRomaji2, true);

		alternative.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_KEIGO_LOW);
		
		result.setAlternative(alternative);
		
		return result;
	}
}
