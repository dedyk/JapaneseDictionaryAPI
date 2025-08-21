package pl.idedyk.japanese.dictionary.api.gramma.dto;

import java.io.Serializable;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.grammaexample.dto.GrammaExampleWrapperEntry;
import pl.idedyk.japanese.dictionary2.api.helper.Dictionary2HelperCommon.KanjiKanaPair;

public class GrammaFormConjugateRequest extends GrammaExampleWrapperEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	public GrammaFormConjugateRequest(DictionaryEntry dictionaryEntry) {
		super(dictionaryEntry);
	}
	
	public GrammaFormConjugateRequest(KanjiKanaPair kanjiKanaPair) {
		super(kanjiKanaPair);		
	}
}


