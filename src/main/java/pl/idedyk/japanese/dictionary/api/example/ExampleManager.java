package pl.idedyk.japanese.dictionary.api.example;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupTypeElements;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleRequest;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResultType;
import pl.idedyk.japanese.dictionary.api.keigo.KeigoHelper;

public class ExampleManager {

	public static List<ExampleGroupTypeElements> getExamples(KeigoHelper keigoHelper, ExampleRequest exampleRequest,
			Map<GrammaFormConjugateResultType, GrammaFormConjugateResult> grammaFormCache,
			DictionaryEntryType forceDictionaryEntryType, boolean addVirtual) {

		DictionaryEntryType dictionaryEntryType = null;

		if (forceDictionaryEntryType == null) {
			dictionaryEntryType = exampleRequest.getDictionaryEntryType();
		} else {
			dictionaryEntryType = forceDictionaryEntryType;
		}

		List<ExampleGroupTypeElements> result = null;

		if (dictionaryEntryType == DictionaryEntryType.WORD_ADJECTIVE_I) {
			result = AdjectiveIExampler.makeAll(exampleRequest, grammaFormCache, addVirtual);

		} else if (dictionaryEntryType == DictionaryEntryType.WORD_ADJECTIVE_NA) {
			result = AdjectiveNaExampler.makeAll(exampleRequest, grammaFormCache, addVirtual);

		} else if (dictionaryEntryType == DictionaryEntryType.WORD_NOUN || 
				dictionaryEntryType == DictionaryEntryType.WORD_ADJECTIVE_NO ||
				dictionaryEntryType == DictionaryEntryType.WORD_TEMPORAL_NOUN ||
				dictionaryEntryType == DictionaryEntryType.WORD_ADVERBIAL_NOUN ||
				dictionaryEntryType == DictionaryEntryType.WORD_PROPER_NOUN) {
			
			result = NounExampler.makeAll(exampleRequest, grammaFormCache, addVirtual);

		} else if (dictionaryEntryType == DictionaryEntryType.WORD_VERB_U
				|| dictionaryEntryType == DictionaryEntryType.WORD_VERB_RU
				|| dictionaryEntryType == DictionaryEntryType.WORD_VERB_IRREGULAR) {

			result = VerbExampler.makeAll(keigoHelper, exampleRequest, grammaFormCache, addVirtual);
		}

		if (result != null) {

			Collections.sort(result, new Comparator<ExampleGroupTypeElements>() {

				private final Collator collator = Collator.getInstance(Locale.getDefault());

				@Override
				public int compare(ExampleGroupTypeElements lhs, ExampleGroupTypeElements rhs) {
					return collator.compare(lhs.getExampleGroupType().getName(), rhs.getExampleGroupType().getName());
				}
			});
		}

		return result;
	}
}
