package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.KanjiEntry;

public class FindKanjiResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<KanjiEntry> result;
	
	public boolean moreElemetsExists = false;

	public List<KanjiEntry> getResult() {
		return result;
	}

	public boolean isMoreElemetsExists() {
		return moreElemetsExists;
	}

	public void setResult(List<KanjiEntry> result) {
		this.result = result;
	}

	public void setMoreElemetsExists(boolean moreElemetsExists) {
		this.moreElemetsExists = moreElemetsExists;
	}
}
