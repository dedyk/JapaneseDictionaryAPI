package pl.idedyk.japanese.dictionary.api.example.dto;

import java.io.Serializable;

public class ExampleResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String prefixKana;
	
	private String kanji;
	
	private String kana;
	
	private String prefixRomaji;
	
	private String romaji;
	
	private String info;
		
	private ExampleResult alternative;
	
	public String getKanji() {
		return kanji;
	}

	public String getKana() {
		return kana;
	}

	public String getRomaji() {
		return romaji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public void setRomaji(String romaji) {
		this.romaji = romaji;
	}

	public ExampleResult getAlternative() {
		return alternative;
	}

	public void setAlternative(ExampleResult alternative) {
		this.alternative = alternative;
	}
	
	public String getPrefixKana() {
		return prefixKana;
	}

	public String getPrefixRomaji() {
		return prefixRomaji;
	}

	public void setPrefixKana(String prefixKana) {
		this.prefixKana = prefixKana;
	}

	public void setPrefixRomaji(String prefixRomaji) {
		this.prefixRomaji = prefixRomaji;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isKanjiExists() {
		if (kanji != null && kanji.equals("-") == false) {
			return true;
		} else {
			return false;
		}
	}
}
