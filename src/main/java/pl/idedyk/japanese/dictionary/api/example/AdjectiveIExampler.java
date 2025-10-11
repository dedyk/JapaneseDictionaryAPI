package pl.idedyk.japanese.dictionary.api.example;

import java.util.ArrayList;
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
		
		// deshou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_I_DESHOU, makeDeshouExample(exampleRequest));
		
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
		
		return result;
	}
	
	private static boolean isKanaI(ExampleRequest exampleRequest) {
		
		String kanji = exampleRequest.getKanji();

		if (kanji != null && kanji.endsWith("イ") == true) {
			return true;
		}

		List<String> kanaList = exampleRequest.getKanaList();

		for (String currentKana : kanaList) {
			if (currentKana.endsWith("イ") == true) {
				return true;
			}			
		}		
		
		return false;
	}
	
	private static ExampleResult makeMotto(ExampleRequest exampleRequest) {
		
		final String templateKanji = "もっと%s";
		final String templateKana = "もっと%s";
		final String templateRomaji = "motto %s";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
	}

	private static ExampleResult makeMottomo(ExampleRequest exampleRequest) {
		
		final String templateKanji1 = "最も%s";
		final String templateKana1 = "もっとも%s";
		final String templateRomaji1 = "mottomo %s";
		
		ExampleResult mottomoExample = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		final String templateKanji2 = "一番%s";
		final String templateKana2 = "いちばん%s";
		final String templateRomaji2 = "ichiban %s";
		
		mottomoExample.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true));
		
		return mottomoExample;
	}
	
	private static ExampleResult makeAdjectiveINaru(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sくなる";
		final String templateKana = "%sくなる";
		final String templateRomaji = "%sku naru";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true);
	}

	private static ExampleResult makeNDesuExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sんです";
		final String templateKana = "%sんです";
		final String templateRomaji = "%sn desu";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeSugiruExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sすぎる";
		final String templateKana = "%sすぎる";
		final String templateRomaji = "%s sugiru";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true);
	}

	private static ExampleResult makeDeshouExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sでしょう";
		final String templateKana = "%sでしょう";
		final String templateRomaji = "%s deshou";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeSouDesuLooksLikeExample(ExampleRequest exampleRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		boolean isIiAdjective = false;
		
		List<String> kanaList = exampleRequest.getKanaList();
		
		for (String currentKana : kanaList) {
			if (currentKana.endsWith("いい") == true) {
				isIiAdjective = true;
				
				break;
			}
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
		
		souDesuResult.setAlternative(souDesuNegativeResult);
		
		return souDesuResult;
	}
	
	private static ExampleResult makeKamoshiRemasenExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sかもしれません";
		final String templateKana = "%sかもしれません";
		final String templateRomaji = "%s kamoshi remasen";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeToIIToOthers(ExampleRequest exampleRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%sといいですね";
		final String templateKana = "%sといいですね";
		final String templateRomaji = "%s to ii desu ne";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		exampleResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
		
		return exampleResult;
	}
	
	private static ExampleResult makeToIIToMe(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sといいんですが";
		final String templateKana1 = "%sといいんですが";
		final String templateRomaji1 = "%s to ii n desu ga";

		final String templateKanji2 = "%sといいんですけど";
		final String templateKana2 = "%sといいんですけど";
		final String templateRomaji2 = "%s to ii n desu kedo";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true);
		
		exampleResult1.setAlternative(exampleResult2);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		exampleResult2.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji1, templateKana1, templateRomaji1, true));
		exampleResult2.getAlternative().setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true));
		
		return exampleResult1;
	}
	
	private static ExampleResult makeToki(ExampleRequest exampleRequest, 
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%s時、...";
		final String templateKana = "%sとき、...";
		final String templateRomaji = "%s toki, ...";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PAST);
		
		exampleResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji, templateKana, templateRomaji, true));
		
		return exampleResult;
	}
	
	private static ExampleResult makeSouDesuHearExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sそうです";
		String templateKana = "%sそうです";
		String templateRomaji = "%s sou desu";	
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		souDesuResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(
				informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
		
		return souDesuResult;
	}	

	private static ExampleResult makeTteExample(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sって";
		String templateKana = "%sって";
		String templateRomaji = "%stte";	
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE);
		
		souDesuResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(
				informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
		
		return souDesuResult;
	}	
	
	private static ExampleResult makeTaraExample(ExampleRequest exampleRequest,
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
		
		exampleResult.setAlternative(alternativeExample);
		
		return exampleResult;
	}
	
	private static ExampleResult makeNakuteMoIiDesu(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
		
		final String templateKanji2 = "%sもいいです";
		final String templateKana2 = "%sもいいです";
		final String templateRomaji2 = "%s mo ii desu";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true);
	}
	
	private static ExampleResult makeMitaiDesuExample(ExampleRequest exampleRequest,
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
		
		exampleResult.setAlternative(alternative2);
	
		return exampleResult;
	}
	
	private static ExampleResult makeToExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		ExampleResult nounNiNaru = makeAdjectiveINaru(exampleRequest, grammaFormCache);
		
		final String templateKanji = "%sと, ...";
		final String templateKana = "%sと, ...";
		final String templateRomaji = "%s to, ...";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(nounNiNaru, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeHazuDesu(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
	
		final String templateKanji1 = "%sはずです";
		final String templateKana1 = "%sはずです";
		final String templateRomaji1 = "%s hazu desu";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, true);
		
		final String templateKanji2 = "%sはずでした";
		final String templateKana2 = "%sはずでした";
		final String templateRomaji2 = "%s hazu deshita";
		
		ExampleResult alternative1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji2, templateKana2, templateRomaji2, true);
		
		exampleResult1.setAlternative(alternative1);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeQuestionsWithLargerSentences(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
				
		final String templateKanji1 = "%s か (どうか) 知っています, 覚えていません, わかりません, etc";
		final String templateKana1 = "%s か (どうか) しっています, おぼえていません, わかりません, etc";
		final String templateRomaji1 = "%s ka (douka) shitte imasu, oboete imasen, wakarimasen, etc";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji1, templateKana1, templateRomaji1, false);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeAdjectiveISuru(ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult virtualForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_VIRTUAL);
		
		final String templateKanji = "%sくする";
		final String templateKana = "%sくする";
		final String templateRomaji = "%sku suru";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(virtualForm, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeNoni(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%sのに, ...";
		final String templateKana = "%sのに, ...";
		final String templateRomaji = "%s noni, ...";
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_INFORMAL_PAST);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji, templateKana, templateRomaji, true);
		
		exampleResult1.setAlternative(exampleResult2);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeYouni(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
				
		final String templateKanji = "[rzeczownik] のように%s";
		final String templateKana = "[rzeczownik] のように%s";
		final String templateRomaji = "[rzeczownik] no you ni %s";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeTeMoExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sも";
		final String templateKana1 = "%sも";
		final String templateRomaji1 = "%s mo";
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_TE);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(teForm, templateKanji1, templateKana1, templateRomaji1, true);
				
		return exampleResult1;
	}

	private static ExampleResult makeNegativeTeMoExample(ExampleRequest exampleRequest, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sも";
		final String templateKana1 = "%sも";
		final String templateRomaji1 = "%s mo";
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_I_TE_NEGATIVE);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(teForm, templateKanji1, templateKana1, templateRomaji1, true);
				
		return exampleResult1;
	}

	private static ExampleResult makeImperativeExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sであれ";
		final String templateKana = "%sであれ";
		final String templateRomaji = "%s de are";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeImperativeNotExample(ExampleRequest exampleRequest) {
		
		final String templateKanji = "%sであるな";
		final String templateKana = "%sであるな";
		final String templateRomaji = "%s de aru na";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(exampleRequest, templateKanji, templateKana, templateRomaji, true);
	}
}
