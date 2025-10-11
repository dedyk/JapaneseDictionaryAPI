package pl.idedyk.japanese.dictionary.api.gramma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateGroupType;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateRequest;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResultType;

public class NounGrammaConjugater {

	public static List<GrammaFormConjugateGroupTypeElements> makeAll(GrammaFormConjugateRequest grammaFormConjugateRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache,
			DictionaryEntryType forceDictionaryEntryType, boolean addVirtual) {

		// validate DictionaryEntry
		validateDictionaryEntry(grammaFormConjugateRequest, forceDictionaryEntryType);

		List<GrammaFormConjugateGroupTypeElements> result = new ArrayList<GrammaFormConjugateGroupTypeElements>();

		// forma formalna
		GrammaFormConjugateGroupTypeElements formal = new GrammaFormConjugateGroupTypeElements();

		formal.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.NOUN_FORMAL);

		formal.getGrammaFormConjugateResults().add(makeFormalPresentForm(grammaFormConjugateRequest));
		formal.getGrammaFormConjugateResults().add(makeFormalPresentNegativeForm(grammaFormConjugateRequest));
		formal.getGrammaFormConjugateResults().add(makeFormalPastForm(grammaFormConjugateRequest));
		formal.getGrammaFormConjugateResults().add(makeFormalPastNegativeForm(grammaFormConjugateRequest));

		result.add(formal);

		// forma nieformalna (prosta)
		GrammaFormConjugateGroupTypeElements informal = new GrammaFormConjugateGroupTypeElements();

		informal.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.NOUN_INFORMAL);

		informal.getGrammaFormConjugateResults().add(makeInformalPresentForm(grammaFormConjugateRequest));
		informal.getGrammaFormConjugateResults().add(makeInformalPresentNegativeForm(grammaFormConjugateRequest));
		informal.getGrammaFormConjugateResults().add(makeInformalPastForm(grammaFormConjugateRequest));
		informal.getGrammaFormConjugateResults().add(makeInformalPastNegativeForm(grammaFormConjugateRequest));

		result.add(informal);

		// forma te
		GrammaFormConjugateGroupTypeElements teForm = new GrammaFormConjugateGroupTypeElements();

		teForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.NOUN_TE);

		teForm.getGrammaFormConjugateResults().add(makeTeForm(grammaFormConjugateRequest));
		teForm.getGrammaFormConjugateResults().add(makeNegativeTeForm(grammaFormConjugateRequest));

		result.add(teForm);

		// forma honoryfikatywna
		GrammaFormConjugateGroupTypeElements keigoForm = new GrammaFormConjugateGroupTypeElements();

		keigoForm.setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType.NOUN_KEIGO);

		keigoForm.getGrammaFormConjugateResults().add(makeKeigoLowForm(grammaFormConjugateRequest));

		result.add(keigoForm);

		// caching
		for (GrammaFormConjugateGroupTypeElements grammaFormConjugateGroupTypeElements : result) {

			List<GrammaFormConjugateResult> grammaFormConjugateResults = grammaFormConjugateGroupTypeElements
					.getGrammaFormConjugateResults();

			for (GrammaFormConjugateResult grammaFormConjugateResult : grammaFormConjugateResults) {
				grammaFormCache.put(grammaFormConjugateResult.getResultType(), grammaFormConjugateResult);
			}
		}

		return result;
	}

	private static GrammaFormConjugateResult makeFormalPresentForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, twierdzenie, forma formalna, -desu

		final String postfixKana = "です";
		final String postfixRomaji = " desu";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_FORMAL_PRESENT,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeFormalPresentNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, przeczenie, forma formalna (prosta), -dewa arimasen

		final String postfixKana = "ではありません";
		final String postfixRomaji = " dewa arimasen";

		GrammaFormConjugateResult grammaFormConjugateResult = makeNounGrammaConjugateForm(grammaFormConjugateRequest,
				GrammaFormConjugateResultType.NOUN_FORMAL_PRESENT_NEGATIVE, postfixKana, postfixRomaji);

		// alternative
		grammaFormConjugateResult.setAlternative(makeFormalPresentNegativeForm2(grammaFormConjugateRequest));

		return grammaFormConjugateResult;
	}

	private static GrammaFormConjugateResult makeFormalPresentNegativeForm2(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, przeczenie, forma formalna (prosta), -ja arimasen

		final String postfixKana = "じゃありません";
		final String postfixRomaji = " ja arimasen";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_FORMAL_PRESENT_NEGATIVE,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeFormalPastForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, twierdzenie, forma formalna, -deshita

		final String postfixKana = "でした";
		final String postfixRomaji = " deshita";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_FORMAL_PAST,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeFormalPastNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, przeczenie, forma formalna, -dewa arimasen deshita

		final String postfixKana = "ではありませんでした";
		final String postfixRomaji = " dewa arimasen deshita";

		GrammaFormConjugateResult grammaFormConjugateResult = makeNounGrammaConjugateForm(grammaFormConjugateRequest,
				GrammaFormConjugateResultType.NOUN_FORMAL_PAST_NEGATIVE, postfixKana, postfixRomaji);

		// alternative
		grammaFormConjugateResult.setAlternative(makeFormalPastNegativeForm2(grammaFormConjugateRequest));

		return grammaFormConjugateResult;
	}

	private static GrammaFormConjugateResult makeFormalPastNegativeForm2(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, przeczenie, forma formalna, -ja arimasen deshita

		final String postfixKana = "じゃありませんでした";
		final String postfixRomaji = " ja arimasen deshita";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_FORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeInformalPresentForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, twierdzenie, forma nieformalna (prosta), -da

		final String postfixKana = "だ";
		final String postfixRomaji = " da";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_INFORMAL_PRESENT,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeInformalPresentNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas terazniejszy, przeczenie, forma nieformalna (prosta), -ja nai

		final String postfixKana = "じゃない";
		final String postfixRomaji = " ja nai";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest,
				GrammaFormConjugateResultType.NOUN_INFORMAL_PRESENT_NEGATIVE, postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeInformalPastForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, twierdzenie, forma nieformalna (prosta), -datta

		final String postfixKana = "だった";
		final String postfixRomaji = " datta";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_INFORMAL_PAST,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeInformalPastNegativeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// czas przesly, przeczenie, forma nieformalna (prosta), -ja nakatta

		final String postfixKana = "じゃなかった";
		final String postfixRomaji = " ja nakatta";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_INFORMAL_PAST_NEGATIVE,
				postfixKana, postfixRomaji);
	}

	private static GrammaFormConjugateResult makeNounGrammaConjugateForm(GrammaFormConjugateRequest grammaFormConjugateRequest,
			GrammaFormConjugateResultType grammaFormConjugateResultType, String postfixKana, String postfixRomaji) {

		// make common
		GrammaFormConjugateResult result = makeCommon(grammaFormConjugateRequest);

		result.setResultType(grammaFormConjugateResultType);

		String kanji = grammaFormConjugateRequest.getKanji();

		if (kanji != null) {
			result.setKanji(kanji + postfixKana);
		}

		List<String> kanaList = grammaFormConjugateRequest.getKanaList();

		List<String> kanaListResult = new ArrayList<String>();

		for (String currentKana : kanaList) {
			kanaListResult.add(currentKana + postfixKana);
		}

		result.setKanaList(kanaListResult);

		List<String> romajiList = grammaFormConjugateRequest.getRomajiList();

		List<String> romajiListResult = new ArrayList<String>();

		for (String currentRomaji : romajiList) {
			romajiListResult.add(currentRomaji + postfixRomaji);
		}

		result.setRomajiList(romajiListResult);

		return result;
	}

	private static GrammaFormConjugateResult makeCommon(GrammaFormConjugateRequest grammaFormConjugateRequest) {

		// create result
		GrammaFormConjugateResult result = new GrammaFormConjugateResult();

		result.setPrefixKana(grammaFormConjugateRequest.getPrefixKana());
		result.setPrefixRomaji(grammaFormConjugateRequest.getPrefixRomaji());

		return result;
	}

	private static void validateDictionaryEntry(GrammaFormConjugateRequest grammaFormConjugateRequest,
			DictionaryEntryType forceDictionaryEntryType) {

		List<DictionaryEntryType> dictionaryEntryTypeList = null;

		if (forceDictionaryEntryType == null) {
			dictionaryEntryTypeList = grammaFormConjugateRequest.getDictionaryEntryTypeList();
		} else {
			dictionaryEntryTypeList = Arrays.asList(forceDictionaryEntryType);
		}

		if (	dictionaryEntryTypeList.contains(DictionaryEntryType.WORD_NOUN) == false && 
				dictionaryEntryTypeList.contains(DictionaryEntryType.WORD_ADJECTIVE_NO) == false &&
				dictionaryEntryTypeList.contains(DictionaryEntryType.WORD_TEMPORAL_NOUN) == false &&
				dictionaryEntryTypeList.contains(DictionaryEntryType.WORD_ADVERBIAL_NOUN) == false &&
				dictionaryEntryTypeList.contains(DictionaryEntryType.WORD_PROPER_NOUN) == false) {
			
			throw new RuntimeException("dictionaryEntryType != DictionaryEntryType.WORD_NOUN && "
					+ "dictionaryEntryType != DictionaryEntryType.WORD_ADJECTIVE_NO && "
					+ "dictionaryEntryType != DictionaryEntryType.WORD_TEMPORAL_NOUN && "
					+ "dictionaryEntryType != DictionaryEntryType.WORD_ADVERBIAL_NOUN && "
					+ "dictionaryEntryType != DictionaryEntryType.WORD_PROPER_NOUN: " + dictionaryEntryTypeList);
		}
	}

	private static GrammaFormConjugateResult makeTeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {

		// forma te

		final String postfixKana = "で";
		final String postfixRomaji = " de";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_TE, postfixKana,
				postfixRomaji);
	}

	private static GrammaFormConjugateResult makeNegativeTeForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {
		// forma te

		final String postfixKana = "じゃなくて";
		final String postfixRomaji = " ja nakute";

		return makeNounGrammaConjugateForm(grammaFormConjugateRequest, GrammaFormConjugateResultType.NOUN_TE_NEGATIVE,
				postfixKana, postfixRomaji);
	}
	
	private static GrammaFormConjugateResult makeKeigoLowForm(GrammaFormConjugateRequest grammaFormConjugateRequest) {

		// keigo low

		final String postfixKana1 = "でございます";
		final String postfixRomaji1 = " de gozaimasu";

		GrammaFormConjugateResult result = makeNounGrammaConjugateForm(grammaFormConjugateRequest,
				GrammaFormConjugateResultType.NOUN_KEIGO_LOW, postfixKana1, postfixRomaji1);

		final String postfixKana2 = "でござる";
		final String postfixRomaji2 = " de gozaru";

		result.setAlternative(makeNounGrammaConjugateForm(grammaFormConjugateRequest,
				GrammaFormConjugateResultType.NOUN_KEIGO_LOW, postfixKana2, postfixRomaji2));

		return result;
	}
}
