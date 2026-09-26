package pl.idedyk.japanese.dictionary.api.grammaexample.dto;

import java.io.Serializable;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;

public class GeneratedGrammaExampleWrapperEntry implements IGrammaExampleWrapperEntry, Serializable {
		
	private String prefixKana;
	
	private String kanji;	
	private List<String> kanaList;
	
	private String prefixRomaji;	
	private List<String> romajiList;
	
	@Deprecated
	public GeneratedGrammaExampleWrapperEntry(int sprawdzic_czy_byl_uzywany_add_stack_a_pozniej_usunac_ten_konstruktor) { }
	
	@Override
	public String getPrefixKana() {
		return prefixKana;
	}
	
	public void setPrefixKana(String prefixKana) {
		this.prefixKana = prefixKana;
	}
	
	@Override
	public String getKanji() {
		return kanji;
	}
	
	public void setKanji(String kanji) {
		this.kanji = kanji;
	}
	
	@Override
	public List<String> getKanaList() {
		return kanaList;
	}
	
	public void setKanaList(List<String> kanaList) {
		this.kanaList = kanaList;
	}
	
	@Override
	public String getPrefixRomaji() {
		return prefixRomaji;
	}
	
	public void setPrefixRomaji(String prefixRomaji) {
		this.prefixRomaji = prefixRomaji;
	}
	
	@Override
	public List<String> getRomajiList() {
		return romajiList;
	}
	
	public void setRomajiList(List<String> romajiList) {
		this.romajiList = romajiList;
	}

	
	/*
	@Override
	public List<DictionaryEntryType> getDictionaryEntryTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DictionaryEntryType getDictionaryEntryType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrammaExampleType> getGrammaExampleTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	*/
}
