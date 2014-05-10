package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;

public class KanjiRecognizerResultItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String kanji;
	
	private float score;
	
	public KanjiRecognizerResultItem(String kanji, float score) {
		this.kanji = kanji;
		this.score = score;
	}

	public String getKanji() {
		return kanji;
	}

	public float getScore() {
		return score;
	}		
}
