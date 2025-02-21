package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;
import java.util.List;

import pl.idedyk.japanese.dictionary2.kanjidic2.xsd.KanjiCharacterInfo;

public class FindKanjiResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<KanjiCharacterInfo> result;
	
	public boolean moreElemetsExists = false;

	public List<KanjiCharacterInfo> getResult() {
		return result;
	}

	public boolean isMoreElemetsExists() {
		return moreElemetsExists;
	}

	public void setResult(List<KanjiCharacterInfo> result) {
		this.result = result;
	}

	public void setMoreElemetsExists(boolean moreElemetsExists) {
		this.moreElemetsExists = moreElemetsExists;
	}
}
