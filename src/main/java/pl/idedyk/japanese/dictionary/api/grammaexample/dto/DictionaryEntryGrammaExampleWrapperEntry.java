package pl.idedyk.japanese.dictionary.api.grammaexample.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.AttributeList;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;

public class DictionaryEntryGrammaExampleWrapperEntry implements IGrammaExampleWrapperEntry, Serializable {

	private static final long serialVersionUID = 1L;
	
	// stary slownik
	private DictionaryEntry dictionaryEntry;
		
	public DictionaryEntryGrammaExampleWrapperEntry(DictionaryEntry dictionaryEntry) {
		this.dictionaryEntry = dictionaryEntry;
	}
	
	@Override
	public List<GrammaExampleType> getGrammaExampleTypeList() {
		return new ArrayList<>(); // to jest glowny poziom, wiec nie ma zadnego typu
	}
	
	@Override
	public List<DictionaryEntryType> getDictionaryEntryTypeList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
				
		return dictionaryEntry.getDictionaryEntryTypeList();
	}
	
	@Override
	public DictionaryEntryType getDictionaryEntryType() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
		return getDictionaryEntryTypeList().get(0);
	}

	@Override
	public String getPrefixKana() {
		return dictionaryEntry.getPrefixKana();
	}

	@Override
	public String getPrefixRomaji() {
		return dictionaryEntry.getPrefixRomaji();
	}

	@Override
	public String getKanji() {
		return dictionaryEntry.getKanji();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> getKanaList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
		return dictionaryEntry.getKanaList();
	}
	
	@Override
	public String getKana() {
		return getKanaList().get(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> getRomajiList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
		return dictionaryEntry.getRomajiList();
	}

	@Override
	public String getRomaji() {
		return getRomajiList().get(0);
	}

	public AttributeList getAttributeList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
		return dictionaryEntry.getAttributeList();
	}
}
