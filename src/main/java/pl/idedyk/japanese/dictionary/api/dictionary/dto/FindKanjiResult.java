package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;
import java.util.List;

import pl.idedyk.japanese.dictionary2.kanjidic2.xsd.CharacterInfo;

public class FindKanjiResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<CharacterInfo> result;
	
	public boolean moreElemetsExists = false;

	public List<CharacterInfo> getResult() {
		return result;
	}

	public boolean isMoreElemetsExists() {
		return moreElemetsExists;
	}

	public void setResult(List<CharacterInfo> result) {
		this.result = result;
	}

	public void setMoreElemetsExists(boolean moreElemetsExists) {
		this.moreElemetsExists = moreElemetsExists;
	}
}
