package pl.idedyk.japanese.dictionary.api.gramma;

import java.util.List;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateRequest;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResultType;
import pl.idedyk.japanese.dictionary.api.keigo.KeigoHelper;

public class GrammaConjugaterManager {

	public static List<GrammaFormConjugateGroupTypeElements> getGrammaConjufateResult(KeigoHelper keigoHelper,
			GrammaFormConjugateRequest grammaFormConjugateRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache,
			DictionaryEntryType forceDictionaryEntryType, boolean addVirtual) {

		DictionaryEntryType dictionaryEntryType = null;

		if (forceDictionaryEntryType == null) {
			dictionaryEntryType = grammaFormConjugateRequest.getDictionaryEntryType();
		} else {
			dictionaryEntryType = forceDictionaryEntryType;
		}

		if (dictionaryEntryType == DictionaryEntryType.WORD_ADJECTIVE_I) {
			return AdjectiveIGrammaConjugater.makeAll(grammaFormConjugateRequest, grammaFormCache, addVirtual);

		} else if (dictionaryEntryType == DictionaryEntryType.WORD_ADJECTIVE_NA) {
			return AdjectiveNaGrammaConjugater.makeAll(grammaFormConjugateRequest, grammaFormCache, forceDictionaryEntryType, addVirtual);

		} else if (dictionaryEntryType == DictionaryEntryType.WORD_NOUN || 
				dictionaryEntryType == DictionaryEntryType.WORD_ADJECTIVE_NO ||
				dictionaryEntryType == DictionaryEntryType.WORD_TEMPORAL_NOUN ||
				dictionaryEntryType == DictionaryEntryType.WORD_ADVERBIAL_NOUN ||
				dictionaryEntryType == DictionaryEntryType.WORD_PROPER_NOUN) {
			
			return NounGrammaConjugater.makeAll(grammaFormConjugateRequest, grammaFormCache, forceDictionaryEntryType, addVirtual);

		} else if (dictionaryEntryType == DictionaryEntryType.WORD_VERB_U
				|| dictionaryEntryType == DictionaryEntryType.WORD_VERB_RU
				|| dictionaryEntryType == DictionaryEntryType.WORD_VERB_IRREGULAR) {

			return VerbGrammaConjugater.makeAll(keigoHelper, grammaFormConjugateRequest, grammaFormCache, addVirtual);
		}

		return null;
	}
}
