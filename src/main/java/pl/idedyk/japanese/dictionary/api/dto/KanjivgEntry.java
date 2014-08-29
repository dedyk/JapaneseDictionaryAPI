package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;
import java.util.List;

public class KanjivgEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String kanji;
	
	private List<String> strokePaths;
	
	public KanjivgEntry() {
	}

	public KanjivgEntry(List<String> strokePaths) {
		this.strokePaths = strokePaths;
	}

	public List<String> getStrokePaths() {
		return strokePaths;
	}

	public void setStrokePaths(List<String> strokePaths) {
		this.strokePaths = strokePaths;
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}
}
