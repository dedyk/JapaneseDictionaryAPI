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

public class AdjectiveIExampler {

	public static List<ExampleGroupTypeElements> makeAll(ExampleRequest exampleRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache, boolean addVirtual) {

		if (isKanaI(exampleRequest) == true) { // nie liczymy dla i dla kany
			return null;
		}
		
		List<ExampleGroupTypeElements> result = new ArrayList<ExampleGroupTypeElements>();
		
		// motto
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_II_GRADATION, makeMotto(exampleRequest));

		// mottomo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_III_GRADATION, makeMottomo(exampleRequest));
		
		// ku naru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_NARU, makeAdjectiveINaru(exampleRequest, grammaFormCache));
		
		// n desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_N_DESU, makeNDesuExample(exampleRequest));
		
		// sugiru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_SUGIRU, makeSugiruExample(exampleRequest, grammaFormCache));
		
		// darou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_DAROU, makeDarouExample(exampleRequest));		
		
		// deshou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_DESHOU, makeDeshouExample(exampleRequest));
		
		// de arou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_DE_AROU, makeDeArouExample(exampleRequest));

		// karou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_KAROU, makeKarouExample(exampleRequest, grammaFormCache));		
		
		// sou desu look like
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_SOU_DESU_LOOKS_LIKE, makeSouDesuLooksLikeExample(exampleRequest, grammaFormCache));		
		
		// kamoshi remasen
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_KAMOSHI_REMASEN, makeKamoshiRemasenExample(exampleRequest));
		
		// to ii to others
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TO_II_TO_OTHERS, makeToIIToOthers(exampleRequest, grammaFormCache));

		// to ii to me
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TO_II_TO_ME, makeToIIToMe(exampleRequest, grammaFormCache));
		
		// toki
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TOKI, makeToki(exampleRequest, grammaFormCache));
		
		// sou desu hear
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_SOU_DESU_HEAR, makeSouDesuHearExample(exampleRequest, grammaFormCache));		

		// tte
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TTE, makeTteExample(exampleRequest, grammaFormCache));		
		
		// tara
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TARA, makeTaraExample(exampleRequest, grammaFormCache));
		
		// nakute mo ii desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_NAKUTE_MO_II_DESU, makeNakuteMoIiDesu(exampleRequest, grammaFormCache));

		// mitai desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_MITAI_DESU, makeMitaiDesuExample(exampleRequest, grammaFormCache));

		// to
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TO, makeToExample(exampleRequest, grammaFormCache));
		
		// hazu desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_HAZU_DESU, makeHazuDesu(exampleRequest, grammaFormCache));
		
		// questions with larger sentences
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_QUESTIONS_WITH_LARGER_SENTENCES, makeQuestionsWithLargerSentences(exampleRequest, grammaFormCache));
		
		// ku suru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_SURU, makeAdjectiveISuru(exampleRequest, grammaFormCache));
		
		// noni
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_NONI, makeNoni(exampleRequest, grammaFormCache));
		
		// you ni
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_YOUNI, makeYouni(exampleRequest, grammaFormCache));
		
		// te mo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TE_MO, makeTeMoExample(exampleRequest, grammaFormCache));

		// te mo, negative
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_TE_MO_NEGATIVE, makeNegativeTeMoExample(exampleRequest, grammaFormCache));

		// rozkaz
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_IMPERATIVE, makeImperativeExample(exampleRequest));
		
		// zakaz
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_IMPERATIVE_NOT, makeImperativeNotExample(exampleRequest));
		
		// kare
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_KARE, makeKareExample(exampleRequest, grammaFormCache));		

		// ki
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_KI, makeKiExample(exampleRequest, grammaFormCache));		

		// shi
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_SHI, makeShiExample(exampleRequest, grammaFormCache));		

		return result;
	}
	
	private static boolean isKanaI(ExampleRequest exampleRequest) {
		
		String kanji = exampleRequest.getKanji();

		if (kanji != null && kanji.endsWith("イ") == true) {
			return true;
		}

		String kana = exampleRequest.getKana();
		
		if (kana.endsWith("イ") == true) {
			return true;
		}			
		
		return false;
	}
	
	private static List<ExampleResult> makeMotto(ExampleRequest exampleRequest) {
		
		final String templateKanji = "もっと%s";
		final String templateKana = "もっと%s";
		final String templateRomaji = "motto %s";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}

	private static List<ExampleResult> makeMottomo(ExampleRequest exampleRequest) {
		
		List<ExampleResult> result = new ArrayList<>();
		
		final String templateKanji1 = "最も%s";
		final String templateKana1 = "もっとも%s";
		final String templateRomaji1 = "mottomo %s";
		
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true));
		
		final String templateKanji2 = "一番%s";
		final String templateKana2 = "いちばん%s";
		final String templateRomaji2 = "ichiban %s";
		
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true));
		
		return result;
	}
	
	private static List<ExampleResult> makeAdjectiveINaru(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sくなる";
		final String templateKana = "%sくなる";
		final String templateRomaji = "%sku naru";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true));
	}

	private static List<ExampleResult> makeNDesuExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sんです";
		final String templateKana = "%sんです";
		final String templateRomaji = "%sn desu";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeSugiruExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sすぎる";
		final String templateKana = "%sすぎる";
		final String templateRomaji = "%s sugiru";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true));
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
	
	private static List<ExampleResult> makeSouDesuLooksLikeExample(ExampleRequest exampleRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		boolean isIiAdjective = false;
		
		String kana = exampleRequest.getKana();
				
		if (kana.endsWith("いい") == true) {
			isIiAdjective = true;
		}
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		String templateKanji1 = "%sそうです";
		String templateKana1 = "%sそうです";
		String templateRomaji1 = "%ssou desu";	
		
		String templateKanji2 = "%sさそうです";
		String templateKana2 = "%sさそうです";
		String templateRomaji2 = "%ssasou desu";
		
		ExampleResult souDesuResult = null;
		
		if (isIiAdjective == false) {
			souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji1, templateKana1, templateRomaji1, true);		
		} else {
			souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji2, templateKana2, templateRomaji2, true);
		}
		
		souDesuResult.setInfo("Twierdzenie, zachowuje się jak na-przymiotnik");
		
		//
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
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
	
	private static List<ExampleResult> makeToIIToOthers(ExampleRequest exampleRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%sといいですね";
		final String templateKana = "%sといいですね";
		final String templateRomaji = "%s to ii desu ne";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
				
		return result;
	}
	
	private static List<ExampleResult> makeToIIToMe(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		List<ExampleResult> result = new ArrayList<>();
		
		final String templateKanji1 = "%sといいんですが";
		final String templateKana1 = "%sといいんですが";
		final String templateRomaji1 = "%s to ii n desu ga";

		final String templateKanji2 = "%sといいんですけど";
		final String templateKana2 = "%sといいんですけど";
		final String templateRomaji2 = "%s to ii n desu kedo";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true);
				
		result.add(exampleResult1);
		result.add(exampleResult2);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji1, templateKana1, templateRomaji1, true));
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true));
		
		return result;
	}
	
	private static List<ExampleResult> makeToki(ExampleRequest exampleRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%s時、...";
		final String templateKana = "%sとき、...";
		final String templateRomaji = "%s toki, ...";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PAST);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji, templateKana, templateRomaji, true));
		
		return result;
	}
	
	private static List<ExampleResult> makeSouDesuHearExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sそうです";
		String templateKana = "%sそうです";
		String templateRomaji = "%s sou desu";	
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(souDesuResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
		
		return result;
	}	

	private static List<ExampleResult> makeTteExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sって";
		String templateKana = "%sって";
		String templateRomaji = "%stte";	
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(souDesuResult);
		result.add(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
		
		return result;
	}	
	
	private static List<ExampleResult> makeTaraExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PAST);

		final String templateKanji1 = "%sら、...";
		final String templateKana1 = "%sら、...";
		final String templateRomaji1 = "%sra, ...";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji1, templateKana1, templateRomaji1, true);
		
		exampleResult.setInfo("twierdzenie");
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		final String templateKanji2 = "%sかったら、...";
		final String templateKana2 = "%sかったら、...";
		final String templateRomaji2 = "%skattara, ...";		
		
		ExampleResult alternativeExample = GrammaExampleHelper.makeSimpleTemplateExampleWithLastCharRemove(
				informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true);
		
		alternativeExample.setInfo("przeczenie");
		
		List<ExampleResult> result = new ArrayList<>();
		
		result.add(exampleResult);
		result.add(alternativeExample);
				
		return result;
	}
	
	private static List<ExampleResult> makeNakuteMoIiDesu(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
		
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
		
		List<ExampleResult> nounNiNaruList = makeAdjectiveINaru(exampleRequest, grammaFormCache);
		
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
	
		final String templateKanji1 = "%sはずです";
		final String templateKana1 = "%sはずです";
		final String templateRomaji1 = "%s hazu desu";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		final String templateKanji2 = "%sはずでした";
		final String templateKana2 = "%sはずでした";
		final String templateRomaji2 = "%s hazu deshita";
		
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
	
	private static List<ExampleResult> makeAdjectiveISuru(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sくする";
		final String templateKana = "%sくする";
		final String templateRomaji = "%sku suru";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeNoni(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%sのに, ...";
		final String templateKana = "%sのに, ...";
		final String templateRomaji = "%s noni, ...";
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PAST);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji, templateKana, templateRomaji, true);
		
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
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_TE);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(teForm, templateKanji1, templateKana1, templateRomaji1, true);
				
		return Arrays.asList(exampleResult1);
	}

	private static List<ExampleResult> makeNegativeTeMoExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sも";
		final String templateKana1 = "%sも";
		final String templateRomaji1 = "%s mo";
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
		
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
	
	private static List<ExampleResult> makeKareExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sかれ";
		final String templateKana = "%sかれ";
		final String templateRomaji = "%s kare";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeKarouExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sかろう";
		final String templateKana = "%sかろう";
		final String templateRomaji = "%s karou";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeKiExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sき";
		final String templateKana = "%sき";
		final String templateRomaji = "%ski";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true));
	}
	
	private static List<ExampleResult> makeShiExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL).createCopy();
		
		if (virtualForm.getKanji() != null && virtualForm.getKanji().endsWith("し") == true) {
			virtualForm.setKanji(virtualForm.getKanji().substring(0, virtualForm.getKanji().length() - 1));
		}
		
		if (virtualForm.getKana() != null && virtualForm.getKana().endsWith("し") == true) {
			virtualForm.setKana(virtualForm.getKana().subSequence(0, virtualForm.getKana().length() - 1).toString());
						
		} else {
			virtualForm.setKana(virtualForm.getKana());
		}
		
		if (virtualForm.getRomaji() != null && virtualForm.getRomaji().endsWith("shi") == true) {
			virtualForm.setRomaji(virtualForm.getRomaji().subSequence(0, virtualForm.getRomaji().length() - 3).toString());
			
		} else {
			virtualForm.setRomaji(virtualForm.getRomaji());
		}
		
		final String templateKanji = "%sし";
		final String templateKana = "%sし";
		final String templateRomaji = "%sshi";
		
		return Arrays.asList(GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true));
	}
}
