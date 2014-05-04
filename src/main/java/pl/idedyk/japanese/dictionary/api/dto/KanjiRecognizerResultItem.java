package pl.idedyk.japanese.dictionary.api.dto;

public class KanjiRecognizerResultItem {
	
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
