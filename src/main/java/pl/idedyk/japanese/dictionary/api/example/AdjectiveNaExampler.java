package pl.idedyk.japanese.dictionary.api.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleRequest;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResultType;
import pl.idedyk.japanese.dictionary.api.grammaexample.GrammaExampleHelper;

public class AdjectiveNaExampler {

	public static List<ExampleGroupTypeElements> makeAll(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache, boolean addVirtual) {

		List<ExampleGroupTypeElements> result = new ArrayList<ExampleGroupTypeElements>();
		
		// motto
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_II_GRADATION, makeMotto(exampleRequest));

		// mottomo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_III_GRADATION, makeMottomo(exampleRequest));
		
		// ni naru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NARU, makeAdjectiveNaNaru(exampleRequest));
		
		// na desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NA_DESU, makeNaDesuExample(exampleRequest));
		
		// sugiru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SUGIRU, makeSugiruExample(exampleRequest));		
		
		// darou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_DAROU, makeDarouExample(exampleRequest));		
		
		// deshou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_DESHOU, makeDeshouExample(exampleRequest));

		// de arou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_DE_AROU, makeDeArouExample(exampleRequest));
		
		// sou desu looks like
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SOU_DESU_LOOKS_LIKE, makeSouDesuLooksLikeExample(exampleRequest, grammaFormCache));
		
		// kamoshi remasen
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_KAMOSHI_REMASEN, makeKamoshiRemasenExample(exampleRequest));
		
		// to ii to others
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TO_II_TO_OTHERS, makeToIIToOthers(exampleRequest, grammaFormCache));

		// to ii to me
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TO_II_TO_ME, makeToIIToMe(exampleRequest, grammaFormCache));
		
		// toki
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TOKI, makeToki(exampleRequest, grammaFormCache));
		
		// sou desu hear
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SOU_DESU_HEAR, makeSouDesuHearExample(exampleRequest, grammaFormCache));

		// tte
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TTE, makeTteExample(exampleRequest, grammaFormCache));
		
		// tara
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TARA, makeTaraExample(exampleRequest, grammaFormCache));
		
		// nakute mo ii desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NAKUTE_MO_II_DESU, makeNakuteMoIiDesu(exampleRequest, grammaFormCache));
		
		// mitai desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_MITAI_DESU, makeMitaiDesuExample(exampleRequest, grammaFormCache));
		
		// to
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TO, makeToExample(exampleRequest, grammaFormCache));
		
		// hazu desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_HAZU_DESU, makeHazuDesu(exampleRequest, grammaFormCache));
		
		// questions with larger sentences
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_QUESTIONS_WITH_LARGER_SENTENCES, makeQuestionsWithLargerSentences(exampleRequest, grammaFormCache));
		
		// ni suru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SURU, makeAdjectiveNaSuru(exampleRequest));
		
		// noni
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NONI, makeNoni(exampleRequest, grammaFormCache));
		
		// you ni
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_YOUNI, makeYouni(exampleRequest, grammaFormCache));
		
		// te mo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TE_MO, makeTeMoExample(exampleRequest, grammaFormCache));

		// te mo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TE_MO_NEGATIVE, makeNegativeTeMoExample(exampleRequest, grammaFormCache));

		// rozkaz
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_IMPERATIVE, makeImperativeExample(exampleRequest));

		// zakaz
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_IMPERATIVE_NOT, makeImperativeNotExample(exampleRequest));
		
		return result;
	}
	
	private static List<ExampleResult> makeMotto(ExampleRequest exampleRequest) {
		
		final String templateKanji = "もっと%s";
		final String templateKana = "もっと%s";
		final String templateRomaji = "motto %s";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}

	private static List<ExampleResult> makeMottomo(ExampleRequest exampleRequest) {
		
		final String templateKanji1 = "最も%s";
		final String templateKana1 = "もっとも%s";
		final String templateRomaji1 = "mottomo %s";
		
		ExampleResult mottomoExample = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		final String templateKanji2 = "一番%s";
		final String templateKana2 = "いちばん%s";
		final String templateRomaji2 = "ichiban %s";
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(mottomoExample);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true));
		
		return result;
	}

	private static List<ExampleResult> makeAdjectiveNaNaru(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sになる";
		final String templateKana = "%sになる";
		final String templateRomaji = "%s ni naru";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeNaDesuExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sなんです";
		final String templateKana = "%sなんです";
		final String templateRomaji = "%s nan desu";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeSugiruExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sすぎる";
		final String templateKana = "%sすぎる";
		final String templateRomaji = "%s sugiru";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}

	private static List<ExampleResult> makeDarouExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sだろう";
		final String templateKana = "%sだろう";
		final String templateRomaji = "%s darou";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeDeshouExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sでしょう";
		final String templateKana = "%sでしょう";
		final String templateRomaji = "%s deshou";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeDeArouExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sであろう";
		final String templateKana = "%sであろう";
		final String templateRomaji = "%s de arou";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeSouDesuLooksLikeExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji1 = "%sそうです";
		String templateKana1 = "%sそうです";
		String templateRomaji1 = "%ssou desu";	
		
		String templateKanji2 = "%sさそうです";
		String templateKana2 = "%sさそうです";
		String templateRomaji2 = "%ssasou desu";

		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);		
		souDesuResult.setInfo("Twierdzenie, zachowuje się jak na-przymiotnik");
		
		//
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE);
		
		ExampleResult souDesuNegativeResult = GrammaExampleHelper.makeSimpleTemplateExampleWithLastCharRemove(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true);	
		souDesuNegativeResult.setInfo("Przeczenie, zachowuje się jak na-przymiotnik");
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(souDesuResult);
		result.add(souDesuNegativeResult);
				
		return result;
	}
	
	private static List<ExampleResult> makeKamoshiRemasenExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sかもしれません";
		final String templateKana = "%sかもしれません";
		final String templateRomaji = "%s kamoshi remasen";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeToIIToOthers(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%sといいですね";
		final String templateKana = "%sといいですね";
		final String templateRomaji = "%s to ii desu ne";
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
		
		return result;
	}
	
	private static List<ExampleResult> makeToIIToMe(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		List<ExampleResult> result = new ArrayList<>();
		
		final String templateKanji1 = "%sといいんですが";
		final String templateKana1 = "%sといいんですが";
		final String templateRomaji1 = "%s to ii n desu ga";

		final String templateKanji2 = "%sといいんですけど";
		final String templateKana2 = "%sといいんですけど";
		final String templateRomaji2 = "%s to ii n desu kedo";
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji1, templateKana1, templateRomaji1, true);
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji2, templateKana2, templateRomaji2, true);
		
		result.add(exampleResult1);
		result.add(exampleResult2);
				
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE);
		
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji1, templateKana1, templateRomaji1, true));
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true));
		
		return result;
	}
	
	private static List<ExampleResult> makeToki(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sな時、...";
		final String templateKana1 = "%sなとき、...";
		final String templateRomaji1 = "%s na toki, ...";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);

		final String templateKanji2 = "%s時、...";
		final String templateKana2 = "%sとき、...";
		final String templateRomaji2 = "%s toki, ...";
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji2, templateKana2, templateRomaji2, true));
				
		return result;
	}
	
	private static List<ExampleResult> makeSouDesuHearExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sそうです";
		String templateKana = "%sそうです";
		String templateRomaji = "%s sou desu";	
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(souDesuResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji, templateKana, templateRomaji, true));		
		
		return result;
	}

	private static List<ExampleResult> makeTteExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sって";
		String templateKana = "%sって";
		String templateRomaji = "%stte";	
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(souDesuResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji, templateKana, templateRomaji, true));		
		
		return result;
	}
	
	private static List<ExampleResult> makeTaraExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);

		final String templateKanji1 = "%sら、...";
		final String templateKana1 = "%sら、...";
		final String templateRomaji1 = "%sra, ...";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji1, templateKana1, templateRomaji1, true);
		
		exampleResult.setInfo("twierdzenie");
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE);
		
		final String templateKanji2 = "%sかったら、...";
		final String templateKana2 = "%sかったら、...";
		final String templateRomaji2 = "%skattara, ...";		
		
		ExampleResult alternativeExample = GrammaExampleHelper.makeSimpleTemplateExampleWithLastCharRemove(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true);
		
		alternativeExample.setInfo("przeczenie");
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult);
		result.add(alternativeExample);
		
		return result;
	}
	
	private static List<ExampleResult> makeNakuteMoIiDesu(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_TE_NEGATIVE);
		
		final String templateKanji2 = "%sもいいです";
		final String templateKana2 = "%sもいいです";
		final String templateRomaji2 = "%s mo ii desu";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true));
	}
	
	private static List<ExampleResult> makeMitaiDesuExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sみたいです";
		final String templateKana1 = "%sみたいです";
		final String templateRomaji1 = "%s mitai desu";

		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);

		final String templateKanji2 = "%sみたいな";
		final String templateKana2 = "%sみたいな";
		final String templateRomaji2 = "%s mitai na";
		
		ExampleResult alternative2 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true);
		
		alternative2.setInfo("Zachowuje się, jak na-przymiotnik");
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult);
		result.add(alternative2);
	
		return result;
	}
	
	private static List<ExampleResult> makeToExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
				
		List<ExampleResult> nounNiNaruList = makeAdjectiveNaNaru(exampleRequest);
		
		final String templateKanji = "%sと, ...";
		final String templateKana = "%sと, ...";
		final String templateRomaji = "%s to, ...";
		
		List<ExampleResult> result = new ArrayList<>();
		
		for (ExampleResult currentNounNiNaru : nounNiNaruList) {
			result.add(GrammaExampleHelper.makeSimpleTemplateExample(currentNounNiNaru, templateKanji, templateKana, templateRomaji, true));
		}
		
		return result;
	}
	
	private static List<ExampleResult> makeHazuDesu(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sなはずです";
		final String templateKana1 = "%sなはずです";
		final String templateRomaji1 = "%s na hazu desu";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		final String templateKanji2 = "%sなはずでした";
		final String templateKana2 = "%sなはずでした";
		final String templateRomaji2 = "%s na hazu deshita";
		
		ExampleResult alternative1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult1);
		result.add(alternative1);
				
		return result;
	}
	
	private static List<ExampleResult> makeQuestionsWithLargerSentences(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%s か (どうか) 知っています, 覚えていません, わかりません, etc";
		final String templateKana1 = "%s か (どうか) しっています, おぼえていません, わかりません, etc";
		final String templateRomaji1 = "%s ka (douka) shitte imasu, oboete imasen, wakarimasen, etc";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, false);
		
		return Arrays.asList(exampleResult1);
	}
	
	private static List<ExampleResult> makeAdjectiveNaSuru(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sにする";
		final String templateKana = "%sにする";
		final String templateRomaji = "%s ni suru";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeNoni(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sなのに, ...";
		final String templateKana1 = "%sなのに, ...";
		final String templateRomaji1 = "%s na noni, ...";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);
		
		final String templateKanji2 = "%sのに, ...";
		final String templateKana2 = "%sのに, ...";
		final String templateRomaji2 = "%s noni, ...";
		
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji2, templateKana2, templateRomaji2, true);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult1);
		result.add(exampleResult2);
				
		return result;
	}
	
	private static List<ExampleResult> makeYouni(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "[rzeczownik] のように%s";
		final String templateKana = "[rzeczownik] のように%s";
		final String templateRomaji = "[rzeczownik] no you ni %s";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		return Arrays.asList(exampleResult1);
	}
	
	private static List<ExampleResult> makeTeMoExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sも";
		final String templateKana1 = "%sも";
		final String templateRomaji1 = "%s mo";
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_TE);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(teForm, templateKanji1, templateKana1, templateRomaji1, true);
				
		return Arrays.asList(exampleResult1);
	}

	private static List<ExampleResult> makeNegativeTeMoExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sも";
		final String templateKana1 = "%sも";
		final String templateRomaji1 = "%s mo";
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_TE_NEGATIVE);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(teForm, templateKanji1, templateKana1, templateRomaji1, true);
				
		return Arrays.asList(exampleResult1);
	}

	private static List<ExampleResult> makeImperativeExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sであれ";
		final String templateKana = "%sであれ";
		final String templateRomaji = "%s de are";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeImperativeNotExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sであるな";
		final String templateKana = "%sであるな";
		final String templateRomaji = "%s de aru na";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
}
