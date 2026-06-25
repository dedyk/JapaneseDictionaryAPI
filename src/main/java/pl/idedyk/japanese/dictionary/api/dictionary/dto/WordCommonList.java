package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.util.ArrayList;
import java.util.List;

public class WordCommonList {
	
	private List<Integer> wordCommonList;
	
	public void addDictionaryEntryId(int dictionaryEntryId) {
		
		if (wordCommonList == null) {
			wordCommonList = new ArrayList<>();
		}
				
		wordCommonList.add(dictionaryEntryId);
	}

	public List<Integer> getWordCommonList() {
		return wordCommonList;
	}

	public void setWordCommonList(List<Integer> wordCommonList) {
		this.wordCommonList = wordCommonList;
	}
}
