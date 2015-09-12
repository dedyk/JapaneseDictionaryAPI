package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;

public class FindKanjiRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String word;
		
	public WordPlaceSearch wordPlaceSearch = WordPlaceSearch.START_WITH;
	
	public Integer strokeCountFrom = null;
	
	public Integer strokeCountTo = null;
	
	public boolean searchOnlyTop2500 = false;
	
	@Override
	public String toString() {
		return "FindKanjiRequest [word=" + word + ", wordPlaceSearch=" + wordPlaceSearch + ", strokeCountFrom="
				+ strokeCountFrom + ", strokeCountTo=" + strokeCountTo + ", searchOnlyTop2500=" + searchOnlyTop2500
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + (searchOnlyTop2500 ? 1231 : 1237);
		result = prime * result + ((strokeCountFrom == null) ? 0 : strokeCountFrom.hashCode());
		result = prime * result + ((strokeCountTo == null) ? 0 : strokeCountTo.hashCode());
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
		
		FindKanjiRequest other = (FindKanjiRequest) obj;
		
		if (searchOnlyTop2500 != other.searchOnlyTop2500)
			return false;
		
		if (strokeCountFrom == null) {
			if (other.strokeCountFrom != null)
				return false;
		
		} else if (!strokeCountFrom.equals(other.strokeCountFrom))
			return false;
		
		if (strokeCountTo == null) {
			if (other.strokeCountTo != null)
				return false;
		} else if (!strokeCountTo.equals(other.strokeCountTo))
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
}
