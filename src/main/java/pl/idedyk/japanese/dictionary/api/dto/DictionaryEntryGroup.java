package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;
import java.util.List;

public class DictionaryEntryGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private List<DictionaryEntry> dictionaryEntryList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<DictionaryEntry> getDictionaryEntryList() {
		return dictionaryEntryList;
	}

	public void setDictionaryEntryList(List<DictionaryEntry> dictionaryEntryList) {
		this.dictionaryEntryList = dictionaryEntryList;
	}
}
