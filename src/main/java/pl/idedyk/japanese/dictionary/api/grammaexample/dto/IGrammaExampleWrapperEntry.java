package pl.idedyk.japanese.dictionary.api.grammaexample.dto;

import java.io.Serializable;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;

public interface IGrammaExampleWrapperEntry extends Serializable {
	
	public List<DictionaryEntryType> getDictionaryEntryTypeList();
	public DictionaryEntryType getDictionaryEntryType();
	
	public List<GrammaExampleType> getStackGrammaExampleTypeList();
	public void addStackGrammaExampleType(GrammaExampleType grammaExampleType);
	
	public String getPrefixKana();
	public String getPrefixRomaji();
	
	public String getKanji();
	
	public List<String> getKanaList();
	public String getKana();
	
	public List<String> getRomajiList();
	public String getRomaji();
	
	public IGrammaExampleWrapperEntry setAlternative(IGrammaExampleWrapperEntry alternative);
	public IGrammaExampleWrapperEntry getAlternative();
}
