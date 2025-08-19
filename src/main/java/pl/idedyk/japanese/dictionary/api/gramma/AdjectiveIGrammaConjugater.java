package pl.idedyk.japanese.dictionary.api.gramma;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateGroupType;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateRequest;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResultType;
import pl.idedyk.japanese.dictionary.api.grammaexample.GrammaExampleHelper;

public class AdjectiveIGrammaConjugater {

	public static List<GrammaFormConjugateGroupTypeElements> makeAll(GrammaFormConjugateRequest grammaFormConjugateRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache, boolean addVirtual) {

		if (isKanaIorEorDash(grammaFormConjugateRequest) == true) { // nie liczymy dla i lub e lub ー dla kany
			return null;
		}
		
		// validate DictionaryEntry
		validateDictionaryEntry(grammaFormConjugateRequest);
		
		List<GrammaFormConjugateGroupTypeElements> result = new ArrayList<GrammaFormConjugateGroupTypeElements>();

		// forma formalna
		GrammaFormConjugateGroupTypeElements formal = new GrammaFormConjugateGroupTypeElements();

		formal.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_FORMAL);

		formal.getGrammaFormConjugateResults().add(makeFormalPresentForm(grammaFormConjugateRequest));
		formal.getGrammaFormConjugateResults().add(makeFormalPresentNegativeForm(grammaFormConjugateRequest));
		formal.getGrammaFormConjugateResults().add(makeFormalPastForm(grammaFormConjugateRequest));
		formal.getGrammaFormConjugateResults().add(makeFormalPastNegativeForm(grammaFormConjugateRequest));

		result.add(formal);

		// forma nieformalna (prosta)
		GrammaFormConjugateGroupTypeElements informal = new GrammaFormConjugateGroupTypeElements();

		informal.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_INFORMAL);

		informal.getGrammaFormConjugateResults().add(makeInformalPresentForm(grammaFormConjugateRequest));
		informal.getGrammaFormConjugateResults().add(makeInformalPresentNegativeForm(grammaFormConjugateRequest));
		informal.getGrammaFormConjugateResults().add(makeInformalPastForm(grammaFormConjugateRequest));
		informal.getGrammaFormConjugateResults().add(makeInformalPastNegativeForm(grammaFormConjugateRequest));

		result.add(informal);
		
		// forma przyslowkowa
		GrammaFormConjugateGroupTypeElements adverbForm = new GrammaFormConjugateGroupTypeElements();
		
		adverbForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_ADVERB);
		
		adverbForm.getGrammaFormConjugateResults().add(makeAdverbForm(grammaFormConjugateRequest));
		
		result.add(adverbForm);
		
		// forma te
		GrammaFormConjugateGroupTypeElements teForm = new GrammaFormConjugateGroupTypeElements();
		
		teForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_TE);
		
		teForm.getGrammaFormConjugateResults().add(makeTeForm(grammaFormConjugateRequest));
		teForm.getGrammaFormConjugateResults().add(makeNegativeTeForm(grammaFormConjugateRequest));
		
		result.add(teForm);
		
		// forma honoryfikatywna
		GrammaFormConjugateGroupTypeElements keigoForm = new GrammaFormConjugateGroupTypeElements();
		
		keigoForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.ADJECTIVE_I_KEIGO);
		
		keigoForm.getGrammaFormConjugateResults().add(makeKeigoLowForm(grammaFormConjugateRequest));
		
		result.add(keigoForm);
		
		// caching
		for (GrammaFormConjugateGroupTypeElements grammaFormConjugateGroupTypeElements : result) {
			
			List<GrammaFormConjugateResult> grammaFormConjugateResults = grammaFormConjugateGroupTypeElements.getGrammaFormConjugateResults();
			
			for (GrammaFormConjugateResult grammaFormConjugateResult : grammaFormConjugateResults) {
				grammaFormCache.put(grammaFormConjugateResult.getResultType(), grammaFormConjugateResult);
			}
		}
		
		// virtual
		GrammaFormConjugateResult virtualForm = makeVirtualForm(grammaFormConjugateRequest);
		
		grammaFormCache.put(virtualForm.getResultType(), virtualForm);

		return result;		
	}
	
	private static GrammaFormConjugateResult makeVirtualForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		
		// wirtualna metoda bez "i" na koncu i ewentualne przerobienie ii na yoi
		
		GrammaFormConjugateResult virtualForm = makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL,
				"", "");
				
		return virtualForm;
	}

	private static GrammaFormConjugateResult makeFormalPresentForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, twierdzenie, forma formalna, -i desu

		final String postfixKana = "いです";
		final String postfixRomaji = "i desu";

		return makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PRESENT,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeFormalPresentNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, przeczenie, forma formalna (prosta), -kunai desu

		final String postfixKana = "くないです";
		final String postfixRomaji = "kunai desu";

		GrammaFormConjugateResult grammaFormConjugateResult = makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PRESENT_NEGATIVE,
				postfixKana, postfixRomaji);
		
		// alternative
		grammaFormConjugateResult.setAlternative(makeFormalPresentNegativeForm2(grammaFormConjugateRequest));
		
		return grammaFormConjugateResult;
	}

	private static GrammaFormConjugateResult makeFormalPresentNegativeForm2(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, przeczenie, forma formalna (prosta), -ku arimasen

		final String postfixKana = "くありません";
		final String postfixRomaji = "ku arimasen";

		return makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PRESENT_NEGATIVE,
				postfixKana, postfixRomaji);
	}
	
	private static GrammaFormConjugateResult makeFormalPastForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, twierdzenie, forma formalna, -katta desu

		final String postfixKana = "かったです";
		final String postfixRomaji = "katta desu";

		return makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PAST,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeFormalPastNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, przeczenie, forma formalna, -ku nakatta desu

		final String postfixKana = "くなかったです";
		final String postfixRomaji = "kunakatta desu";

		GrammaFormConjugateResult grammaFormConjugateResult = makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
		
		// alternative
		grammaFormConjugateResult.setAlternative(makeFormalPastNegativeForm2(grammaFormConjugateRequest));
		
		return grammaFormConjugateResult;
	}

	private static GrammaFormConjugateResult makeFormalPastNegativeForm2(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, przeczenie, forma formalna, -ku arimasen deshita
		
		final String postfixKana = "くありませんでした";
		final String postfixRomaji = "ku arimasen deshita";

		return makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
	}
	
	private static GrammaFormConjugateResult makeInformalPresentForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, twierdzenie, forma nieformalna (prosta), -i

		final String postfixKana = "い";
		final String postfixRomaji = "i";

		return makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeInformalPresentNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		
		// czas terazniejszy, przeczenie, forma nieformalna (prosta), -kunai

		final String postfixKana = "くない";
		final String postfixRomaji = "kunai";

		GrammaFormConjugateResult grammaFormConjugateResult = makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE,
				postfixKana, postfixRomaji);
				
		return grammaFormConjugateResult;
	}

	private static GrammaFormConjugateResult makeInformalPastForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		
		// czas przesly, twierdzenie, forma nieformalna (prosta), -katta

		final String postfixKana = "かった";
		final String postfixRomaji = "katta";

		GrammaFormConjugateResult grammaFormConjugateResult = makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PAST,
				postfixKana, postfixRomaji);
				
		return grammaFormConjugateResult;
	}

	private static GrammaFormConjugateResult makeInformalPastNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, przeczenie, forma nieformalna (prosta), -ku nakatta

		final String postfixKana = "くなかった";
		final String postfixRomaji = "kunakatta";

		return makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeAdjectiveGrammaConjugateForm(GrammaFormConjugateRequest grammaFormConjugateRequest, 
			GrammaFormConjugateResultType grammaFormConjugateResultType, String postfixKana, String postfixRomaji) {

		// make common
		GrammaFormConjugateResult result = makeCommon(grammaFormConjugateRequest);

		result.setResultType(grammaFormConjugateResultType);

		String kanji = grammaFormConjugateRequest.getKanji();

		if (kanji != null) {
			kanji = getKanaToConjugate(kanji, grammaFormConjugateRequest.getRomaji(), grammaFormConjugateResultType);
			
			result.setKanji(removeLastChar(kanji) + postfixKana);
		}

		List<String> kanaList = grammaFormConjugateRequest.getKanaList();

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			currentKana = getKanaToConjugate(currentKana, grammaFormConjugateRequest.getRomaji(), grammaFormConjugateResultType);

			kanaListResult.add(removeLastChar(currentKana) + postfixKana);
		}

		result.setKanaList(kanaListResult);		

		List<String> romajiList = grammaFormConjugateRequest.getRomajiList();

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			currentRomaji = getRomajiToConjugate(currentRomaji, grammaFormConjugateResultType);

			romajiListResult.add(removeLastChar(currentRomaji) + postfixRomaji);
		}

		result.setRomajiList(romajiListResult);

		return result; 
	}

	private static String getKanaToConjugate(String kana, String romaji, GrammaFormConjugateResultType grammaFormConjugateResultType) {

		if (grammaFormConjugateResultType != GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PRESENT && 
				grammaFormConjugateResultType != GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT) {

			if (kana.endsWith("いい") == true) {
				
				if (romaji.equals("ii") == true || romaji.endsWith(" ii") == true) {
					return kana.substring(0, kana.length() - 2) + "よい";
				}
			}
		}

		return kana;
	}

	private static String getRomajiToConjugate(String romaji, GrammaFormConjugateResultType grammaFormConjugateResultType) {

		if (grammaFormConjugateResultType != GrammaFormConjugateResultType.ADJECTIVE_I_FORMAL_PRESENT && 
				grammaFormConjugateResultType != GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT) {

			if (romaji.equals("ii") == true || romaji.endsWith(" ii") == true) {
				return romaji.substring(0, romaji.length() - 2) + "yoi";
			}
		}

		return romaji;
	}

	private static GrammaFormConjugateResult makeCommon(GrammaFormConjugateRequest grammaFormConjugateRequest) {

		// create result
		GrammaFormConjugateResult result = new GrammaFormConjugateResult();

		result.setPrefixKana(grammaFormConjugateRequest.getPrefixKana());
		result.setPrefixRomaji(grammaFormConjugateRequest.getPrefixRomaji());
		
		return result;
	}

	private static String removeLastChar(String text) {
		return text.substring(0, text.length() - 1);
	}

	private static void validateDictionaryEntry(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		
		List<DictionaryEntryType> dictionaryEntryTypeList = grammaFormConjugateRequest.getDictionaryEntryTypeList();

		if (dictionaryEntryTypeList.contains(DictionaryEntryType.WORD_ADJECTIVE_I) == false) {
			throw new RuntimeException("dictionaryEntryType != DictionaryEntryType.WORD_ADJECTIVE_I: " + dictionaryEntryTypeList);
		}

		String kanji = grammaFormConjugateRequest.getKanji();

		if (kanji != null && kanji.endsWith("い") == false) {
			throw new RuntimeException("kanji.endsWith(い) == false: " + kanji);
		}

		List<String> kanaList = grammaFormConjugateRequest.getKanaList();

		for (String currentKana : kanaList) {
			if (currentKana.endsWith("い") == false) {
				throw new RuntimeException("currentKana.endsWith(い) == false: " + currentKana);
			}			
		}

		List<String> romajiList = grammaFormConjugateRequest.getRomajiList();

		for (String currentRomaji : romajiList) {
			if (currentRomaji.endsWith("i") == false) {
				throw new RuntimeException("currentRomaji.endsWith(i) == false: " + currentRomaji);
			}
		}		
	}
	
	private static boolean isKanaIorEorDash(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		
		// FM_FIXME: zrobic to inaczej
		
		String kanji = grammaFormConjugateRequest.getKanji();

		if (kanji != null && kanji.endsWith("イ") == true) {
			return true;
		}

		List<String> kanaList = grammaFormConjugateRequest.getKanaList();

		for (String currentKana : kanaList) {
			if (currentKana.endsWith("イ") == true || currentKana.endsWith("え") == true || currentKana.endsWith("ぇ") == true || currentKana.endsWith("ー") == true) {
				return true;
			}			
		}		
		
		return false;
	}
	
	private static GrammaFormConjugateResult makeAdverbForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
				
		GrammaFormConjugateResult virtualForm = makeAdjectiveGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.ADJECTIVE_I_ADVERB,
				"く", "ku");
				
		return virtualForm;
	}

	
	private static GrammaFormConjugateResult makeTeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// forma te
		
		String postfixKana = "くて";
		String postfixRomaji = "kute";
		
		// make common
		GrammaFormConjugateResult result = makeCommon(grammaFormConjugateRequest);
		
		result.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_TE);
		
		String kanji = grammaFormConjugateRequest.getKanji();

		if (kanji != null) {
			kanji = getKanaToConjugate(kanji, grammaFormConjugateRequest.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE);
			
			result.setKanji(removeLastChar(kanji) + postfixKana);
		}

		List<String> kanaList = grammaFormConjugateRequest.getKanaList();

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			currentKana = getKanaToConjugate(currentKana, grammaFormConjugateRequest.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE);

			kanaListResult.add(removeLastChar(currentKana) + postfixKana);
		}

		result.setKanaList(kanaListResult);		

		List<String> romajiList = grammaFormConjugateRequest.getRomajiList();

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			currentRomaji = getRomajiToConjugate(currentRomaji, GrammaFormConjugateResultType.ADJECTIVE_I_TE);

			romajiListResult.add(removeLastChar(currentRomaji) + postfixRomaji);
		}

		result.setRomajiList(romajiListResult);		
		
		return result;
	}
	
	private static GrammaFormConjugateResult makeNegativeTeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// forma te
		
		String postfixKana = "くなくて";
		String postfixRomaji = "kunakute";
		
		// make common
		GrammaFormConjugateResult result = makeCommon(grammaFormConjugateRequest);
		
		result.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
		
		String kanji = grammaFormConjugateRequest.getKanji();

		if (kanji != null) {
			kanji = getKanaToConjugate(kanji, grammaFormConjugateRequest.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
			
			result.setKanji(removeLastChar(kanji) + postfixKana);
		}

		List<String> kanaList = grammaFormConjugateRequest.getKanaList();

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {			
			currentKana = getKanaToConjugate(currentKana, grammaFormConjugateRequest.getRomaji(), GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);

			kanaListResult.add(removeLastChar(currentKana) + postfixKana);
		}

		result.setKanaList(kanaListResult);		

		List<String> romajiList = grammaFormConjugateRequest.getRomajiList();

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			currentRomaji = getRomajiToConjugate(currentRomaji, GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);

			romajiListResult.add(removeLastChar(currentRomaji) + postfixRomaji);
		}

		result.setRomajiList(romajiListResult);		
		
		return result;
	}
	
	private static GrammaFormConjugateResult makeKeigoLowForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		
		// keigo low
		
		final String templateKanji1 = "%sでございます";
		final String templateKana1 = "%sでございます";
		final String templateRomaji1 = "%s de gozaimasu";
		
		GrammaFormConjugateResult result = GrammaExampleHelper.makeSimpleTemplateGrammaFormConjugateResult(grammaFormConjugateRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		result.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_KEIGO_LOW);
		
		final String templateKanji2 = "%sでござる";
		final String templateKana2 = "%sでござる";
		final String templateRomaji2 = "%s de gozaru";
		
		GrammaFormConjugateResult alternative = GrammaExampleHelper.makeSimpleTemplateGrammaFormConjugateResult(grammaFormConjugateRequest, templateKanji2, templateKana2, templateRomaji2, true);

		alternative.setResultType(GrammaFormConjugateResultType.ADJECTIVE_I_KEIGO_LOW);
		
		result.setAlternative(alternative);
		
		return result;
	}
}
