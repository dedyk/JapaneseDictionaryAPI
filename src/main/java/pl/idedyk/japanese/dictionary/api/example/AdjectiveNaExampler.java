package pl.idedyk.japanese.dictionary.api.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResultType;
import pl.idedyk.japanese.dictionary.api.grammaexample.GrammaExampleHelper;

public class AdjectiveNaExampler {

	public static List<ExampleGroupTypeElements> makeAll(DictionaryEntry dictionaryEntry,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache, boolean addVirtual) {

		List<ExampleGroupTypeElements> result = new ArrayList<ExampleGroupTypeElements>();
		
		// motto
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_II_GRADATION, makeMotto(dictionaryEntry));

		// mottomo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_III_GRADATION, makeMottomo(dictionaryEntry));
		
		// ni naru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NARU, makeAdjectiveNaNaru(dictionaryEntry));
		
		// na desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NA_DESU, makeNaDesuExample(dictionaryEntry));
		
		// sugiru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SUGIRU, makeSugiruExample(dictionaryEntry));
		
		// deshou
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_DESHOU, makeDeshouExample(dictionaryEntry));
		
		// sou desu looks like
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SOU_DESU_LOOKS_LIKE, makeSouDesuLooksLikeExample(dictionaryEntry, grammaFormCache));
		
		// kamoshi remasen
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_KAMOSHI_REMASEN, makeKamoshiRemasenExample(dictionaryEntry));
		
		// to ii to others
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TO_II_TO_OTHERS, makeToIIToOthers(dictionaryEntry, grammaFormCache));

		// to ii to me
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TO_II_TO_ME, makeToIIToMe(dictionaryEntry, grammaFormCache));
		
		// toki
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TOKI, makeToki(dictionaryEntry, grammaFormCache));
		
		// sou desu hear
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SOU_DESU_HEAR, makeSouDesuHearExample(dictionaryEntry, grammaFormCache));

		// tte
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TTE, makeTteExample(dictionaryEntry, grammaFormCache));
		
		// tara
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TARA, makeTaraExample(dictionaryEntry, grammaFormCache));
		
		// nakute mo ii desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NAKUTE_MO_II_DESU, makeNakuteMoIiDesu(dictionaryEntry, grammaFormCache));
		
		// mitai desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_MITAI_DESU, makeMitaiDesuExample(dictionaryEntry, grammaFormCache));
		
		// to
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TO, makeToExample(dictionaryEntry, grammaFormCache));
		
		// hazu desu
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_HAZU_DESU, makeHazuDesu(dictionaryEntry, grammaFormCache));
		
		// questions with larger sentences
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_QUESTIONS_WITH_LARGER_SENTENCES, makeQuestionsWithLargerSentences(dictionaryEntry, grammaFormCache));
		
		// ni suru
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_SURU, makeAdjectiveNaSuru(dictionaryEntry));
		
		// noni
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_NONI, makeNoni(dictionaryEntry, grammaFormCache));
		
		// you ni
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_YOUNI, makeYouni(dictionaryEntry, grammaFormCache));
		
		// te mo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TE_MO, makeTeMoExample(dictionaryEntry, grammaFormCache));

		// te mo
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_TE_MO_NEGATIVE, makeNegativeTeMoExample(dictionaryEntry, grammaFormCache));

		// rozkaz
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_IMPERATIVE, makeImperativeExample(dictionaryEntry));

		// zakaz
		GrammaExampleHelper.addExample(result, ExampleGroupType.ADJECTIVE_NA_IMPERATIVE_NOT, makeImperativeNotExample(dictionaryEntry));
		
		return result;
	}
	
	private static ExampleResult makeMotto(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "もっと%s";
		final String templateKana = "もっと%s";
		final String templateRomaji = "motto %s";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}

	private static ExampleResult makeMottomo(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji1 = "最も%s";
		final String templateKana1 = "もっとも%s";
		final String templateRomaji1 = "mottomo %s";
		
		ExampleResult mottomoExample = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji1, templateKana1, templateRomaji1, true);
		
		final String templateKanji2 = "一番%s";
		final String templateKana2 = "いちばん%s";
		final String templateRomaji2 = "ichiban %s";
		
		mottomoExample.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji2, templateKana2, templateRomaji2, true));
		
		return mottomoExample;
	}

	private static ExampleResult makeAdjectiveNaNaru(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sになる";
		final String templateKana = "%sになる";
		final String templateRomaji = "%s ni naru";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeNaDesuExample(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sなんです";
		final String templateKana = "%sなんです";
		final String templateRomaji = "%s nan desu";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeSugiruExample(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sすぎる";
		final String templateKana = "%sすぎる";
		final String templateRomaji = "%s sugiru";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}

	private static ExampleResult makeDeshouExample(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sでしょう";
		final String templateKana = "%sでしょう";
		final String templateRomaji = "%s deshou";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeSouDesuLooksLikeExample(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji1 = "%sそうです";
		String templateKana1 = "%sそうです";
		String templateRomaji1 = "%ssou desu";	
		
		String templateKanji2 = "%sさそうです";
		String templateKana2 = "%sさそうです";
		String templateRomaji2 = "%ssasou desu";

		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji1, templateKana1, templateRomaji1, true);		
		souDesuResult.setInfo("Twierdzenie, zachowuje się jak na-przymiotnik");
		
		//
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE);
		
		ExampleResult souDesuNegativeResult = GrammaExampleHelper.makeSimpleTemplateExampleWithLastCharRemove(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true);	
		souDesuNegativeResult.setInfo("Przeczenie, zachowuje się jak na-przymiotnik");
		
		souDesuResult.setAlternative(souDesuNegativeResult);		
		
		return souDesuResult;
	}
	
	private static ExampleResult makeKamoshiRemasenExample(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sかもしれません";
		final String templateKana = "%sかもしれません";
		final String templateRomaji = "%s kamoshi remasen";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeToIIToOthers(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "%sといいですね";
		final String templateKana = "%sといいですね";
		final String templateRomaji = "%s to ii desu ne";
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE);
		
		exampleResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji, templateKana, templateRomaji, true));
		
		return exampleResult;
	}
	
	private static ExampleResult makeToIIToMe(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sといいんですが";
		final String templateKana1 = "%sといいんですが";
		final String templateRomaji1 = "%s to ii n desu ga";

		final String templateKanji2 = "%sといいんですけど";
		final String templateKana2 = "%sといいんですけど";
		final String templateRomaji2 = "%s to ii n desu kedo";
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji1, templateKana1, templateRomaji1, true);
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji2, templateKana2, templateRomaji2, true);
		
		exampleResult1.setAlternative(exampleResult2);
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE);
		
		exampleResult2.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji1, templateKana1, templateRomaji1, true));
		exampleResult2.getAlternative().setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true));
		
		return exampleResult1;
	}
	
	private static ExampleResult makeToki(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sな時、...";
		final String templateKana1 = "%sなとき、...";
		final String templateRomaji1 = "%s na toki, ...";
		
		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji1, templateKana1, templateRomaji1, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);

		final String templateKanji2 = "%s時、...";
		final String templateKana2 = "%sとき、...";
		final String templateRomaji2 = "%s toki, ...";
		
		exampleResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji2, templateKana2, templateRomaji2, true));
		
		return exampleResult;
	}
	
	private static ExampleResult makeSouDesuHearExample(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sそうです";
		String templateKana = "%sそうです";
		String templateRomaji = "%s sou desu";	
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);
		
		souDesuResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(
				informalPastForm, templateKanji, templateKana, templateRomaji, true));		
		
		return souDesuResult;
	}

	private static ExampleResult makeTteExample(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		String templateKanji = "%sって";
		String templateKana = "%sって";
		String templateRomaji = "%stte";	
		
		GrammaFormConjugateResult informalPresentForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PRESENT);
		
		ExampleResult souDesuResult = GrammaExampleHelper.makeSimpleTemplateExample(informalPresentForm, templateKanji, templateKana, templateRomaji, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);
		
		souDesuResult.setAlternative(GrammaExampleHelper.makeSimpleTemplateExample(
				informalPastForm, templateKanji, templateKana, templateRomaji, true));		
		
		return souDesuResult;
	}
	
	private static ExampleResult makeTaraExample(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
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
		
		exampleResult.setAlternative(alternativeExample);
		
		return exampleResult;
	}
	
	private static ExampleResult makeNakuteMoIiDesu(DictionaryEntry dictionaryEntry,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		GrammaFormConjugateResult informalPresentNegativeForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_TE_NEGATIVE);
		
		final String templateKanji2 = "%sもいいです";
		final String templateKana2 = "%sもいいです";
		final String templateRomaji2 = "%s mo ii desu";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(informalPresentNegativeForm, templateKanji2, templateKana2, templateRomaji2, true);
	}
	
	private static ExampleResult makeMitaiDesuExample(DictionaryEntry dictionaryEntry,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sみたいです";
		final String templateKana1 = "%sみたいです";
		final String templateRomaji1 = "%s mitai desu";

		ExampleResult exampleResult = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji1, templateKana1, templateRomaji1, true);

		final String templateKanji2 = "%sみたいな";
		final String templateKana2 = "%sみたいな";
		final String templateRomaji2 = "%s mitai na";
		
		ExampleResult alternative2 = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji2, templateKana2, templateRomaji2, true);
		
		alternative2.setInfo("Zachowuje się, jak na-przymiotnik");
		
		exampleResult.setAlternative(alternative2);
	
		return exampleResult;
	}
	
	private static ExampleResult makeToExample(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		ExampleResult nounNiNaru = makeAdjectiveNaNaru(dictionaryEntry);
		
		final String templateKanji = "%sと, ...";
		final String templateKana = "%sと, ...";
		final String templateRomaji = "%s to, ...";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(nounNiNaru, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeHazuDesu(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sなはずです";
		final String templateKana1 = "%sなはずです";
		final String templateRomaji1 = "%s na hazu desu";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji1, templateKana1, templateRomaji1, true);
		
		final String templateKanji2 = "%sなはずでした";
		final String templateKana2 = "%sなはずでした";
		final String templateRomaji2 = "%s na hazu deshita";
		
		ExampleResult alternative1 = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji2, templateKana2, templateRomaji2, true);
		
		exampleResult1.setAlternative(alternative1);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeQuestionsWithLargerSentences(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%s か (どうか) 知っています, 覚えていません, わかりません, etc";
		final String templateKana1 = "%s か (どうか) しっています, おぼえていません, わかりません, etc";
		final String templateRomaji1 = "%s ka (douka) shitte imasu, oboete imasen, wakarimasen, etc";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji1, templateKana1, templateRomaji1, false);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeAdjectiveNaSuru(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sにする";
		final String templateKana = "%sにする";
		final String templateRomaji = "%s ni suru";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeNoni(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sなのに, ...";
		final String templateKana1 = "%sなのに, ...";
		final String templateRomaji1 = "%s na noni, ...";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji1, templateKana1, templateRomaji1, true);
		
		GrammaFormConjugateResult informalPastForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_INFORMAL_PAST);
		
		final String templateKanji2 = "%sのに, ...";
		final String templateKana2 = "%sのに, ...";
		final String templateRomaji2 = "%s noni, ...";
		
		ExampleResult exampleResult2 = GrammaExampleHelper.makeSimpleTemplateExample(informalPastForm, templateKanji2, templateKana2, templateRomaji2, true);
		
		exampleResult1.setAlternative(exampleResult2);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeYouni(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji = "[rzeczownik] のように%s";
		final String templateKana = "[rzeczownik] のように%s";
		final String templateRomaji = "[rzeczownik] no you ni %s";
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
		
		return exampleResult1;
	}
	
	private static ExampleResult makeTeMoExample(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sも";
		final String templateKana1 = "%sも";
		final String templateRomaji1 = "%s mo";
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_TE);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(teForm, templateKanji1, templateKana1, templateRomaji1, true);
				
		return exampleResult1;
	}

	private static ExampleResult makeNegativeTeMoExample(DictionaryEntry dictionaryEntry, Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache) {
		
		final String templateKanji1 = "%sも";
		final String templateKana1 = "%sも";
		final String templateRomaji1 = "%s mo";
		
		GrammaFormConjugateResult teForm = grammaFormCache.get(GrammaFormConjugateResultType.ADJECTIVE_NA_TE_NEGATIVE);
		
		ExampleResult exampleResult1 = GrammaExampleHelper.makeSimpleTemplateExample(teForm, templateKanji1, templateKana1, templateRomaji1, true);
				
		return exampleResult1;
	}

	private static ExampleResult makeImperativeExample(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sであれ";
		final String templateKana = "%sであれ";
		final String templateRomaji = "%s de are";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}
	
	private static ExampleResult makeImperativeNotExample(DictionaryEntry dictionaryEntry) {
		
		final String templateKanji = "%sであるな";
		final String templateKana = "%sであるな";
		final String templateRomaji = "%s de aru na";
		
		return GrammaExampleHelper.makeSimpleTemplateExample(dictionaryEntry, templateKanji, templateKana, templateRomaji, true);
	}
}
