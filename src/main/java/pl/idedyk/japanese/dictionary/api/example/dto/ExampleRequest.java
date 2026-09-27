package pl.idedyk.japanese.dictionary.api.example.dto;

import java.io.Serializable;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.grammaexample.dto.GrammaExampleWrapperEntry;
import pl.idedyk.japanese.dictionary2.api.helper.Dictionary2HelperCommon.KanjiKanaPair;

public class ExampleRequest extends GrammaExampleWrapperEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ExampleRequest(DictionaryEntry dictionaryEntry) {
		super(dictionaryEntry);
	}
	
	public ExampleRequest(KanjiKanaPair kanjiKanaPair) {
		super(kanjiKanaPair);		
	}
}
