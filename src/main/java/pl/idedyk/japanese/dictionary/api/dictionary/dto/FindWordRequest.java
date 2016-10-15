package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;

public class FindWordRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String word;
	
	public boolean searchKanji = true;
	
	public boolean searchKana = true;
	
	public boolean searchRomaji = true;
	
	public boolean searchTranslate = true;
	
	public boolean searchInfo = true;
	
	public boolean searchOnlyCommonWord = false;
	
	public boolean searchMainDictionary = true;
	public boolean searchGrammaFormAndExamples = false;
	public boolean searchName = false;
	
	public WordPlaceSearch wordPlaceSearch = WordPlaceSearch.START_WITH;
		
	public List<DictionaryEntryType> dictionaryEntryTypeList = null;

	@Override
	public String toString() {
		return "FindWordRequest [word=" + word + ", searchKanji=" + searchKanji + ", searchKana=" + searchKana
				+ ", searchRomaji=" + searchRomaji + ", searchTranslate=" + searchTranslate + ", searchInfo="
				+ searchInfo + ", searchOnlyCommonWord=" + searchOnlyCommonWord + ", searchMainDictionary="
				+ searchMainDictionary + ", searchGrammaFormAndExamples=" + searchGrammaFormAndExamples
				+ ", searchName=" + searchName + ", wordPlaceSearch=" + wordPlaceSearch + ", dictionaryEntryTypeList="
				+ dictionaryEntryTypeList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((dictionaryEntryTypeList == null) ? 0 : dictionaryEntryTypeList.hashCode());
		result = prime * result + (searchMainDictionary ? 1231 : 1237);
		result = prime * result + (searchGrammaFormAndExamples ? 1231 : 1237);
		result = prime * result + (searchOnlyCommonWord ? 1231 : 1237);
		result = prime * result + (searchName ? 1231 : 1237);
		result = prime * result + (searchInfo ? 1231 : 1237);
		result = prime * result + (searchKana ? 1231 : 1237);
		result = prime * result + (searchKanji ? 1231 : 1237);
		result = prime * result + (searchRomaji ? 1231 : 1237);
		result = prime * result + (searchTranslate ? 1231 : 1237);
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + ((wordPlaceSearch == null) ? 0 : wordPlaceSearch.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		FindWordRequest other = (FindWordRequest) obj;
		
		if (dictionaryEntryTypeList == null) {
			if (other.dictionaryEntryTypeList != null)
				return false;
		
		} else if (!dictionaryEntryTypeList.equals(other.dictionaryEntryTypeList))
			return false;

		if (searchMainDictionary != other.searchMainDictionary)
			return false;
		
		if (searchGrammaFormAndExamples != other.searchGrammaFormAndExamples)
			return false;

		if (searchName != other.searchName)
			return false;
		
		if (searchOnlyCommonWord != other.searchOnlyCommonWord)
			return false;
		
		if (searchInfo != other.searchInfo)
			return false;
		
		if (searchKana != other.searchKana)
			return false;
		
		if (searchKanji != other.searchKanji)
			return false;
		
		if (searchRomaji != other.searchRomaji)
			return false;
		
		if (searchTranslate != other.searchTranslate)
			return false;
		
		if (word == null) {
			if (other.word != null)
				return false;
		
		} else if (!word.equals(other.word))
			return false;
		
		if (wordPlaceSearch != other.wordPlaceSearch)
			return false;
		
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWord() {
		return word;
	}

	public boolean isSearchKanji() {
		return searchKanji;
	}

	public boolean isSearchKana() {
		return searchKana;
	}

	public boolean isSearchRomaji() {
		return searchRomaji;
	}

	public boolean isSearchTranslate() {
		return searchTranslate;
	}

	public boolean isSearchInfo() {
		return searchInfo;
	}

	public boolean isSearchOnlyCommonWord() {
		return searchOnlyCommonWord;
	}

	public boolean isSearchGrammaFormAndExamples() {
		return searchGrammaFormAndExamples;
	}

	public WordPlaceSearch getWordPlaceSearch() {
		return wordPlaceSearch;
	}

	public List<DictionaryEntryType> getDictionaryEntryTypeList() {
		return dictionaryEntryTypeList;
	}
}
