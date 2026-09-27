package pl.idedyk.japanese.dictionary.api.gramma.dto;

import java.io.Serializable;

public class GrammaFormConjugateResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private GrammaFormConjugateResultType resultType;
	
	private String prefixKana;
	
	private String kanji;
	
	private String kana;
	
	private String prefixRomaji;
	
	private String romaji;
	
	private String info;
	
	private GrammaFormConjugateResult alternative;

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

	public GrammaFormConjugateResultType getResultType() {
		return resultType;
	}

	public void setResultType(GrammaFormConjugateResultType resultType) {
		this.resultType = resultType;
	}

	public GrammaFormConjugateResult getAlternative() {
		return alternative;
	}

	public void setAlternative(GrammaFormConjugateResult alternative) {
		this.alternative = alternative;
	}
	
	public boolean isKanjiExists() {
		if (kanji != null && kanji.equals("-") == false) {
			return true;
		} else {
			return false;
		}
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
	
	public GrammaFormConjugateResult createCopy() {
		/*
		private GrammaFormConjugateResultType resultType;
		
		private String prefixKana;
		
		private String kanji;
		
		private List<String> kanaList;
		
		private String prefixRomaji;
		
		private List<String> romajiList;
		
		private String info;
		
		private GrammaFormConjugateResult alternative;
		*/
		
		GrammaFormConjugateResult copy = new GrammaFormConjugateResult();
		
		copy.setResultType(getResultType());
		copy.setPrefixKana(prefixKana);
		copy.setKanji(kanji);
		copy.setKana(kana);
				
		copy.setPrefixRomaji(prefixRomaji);
		copy.setRomaji(romaji);
				
		copy.setInfo(info);
		
		if (alternative != null) {
			copy.setAlternative(alternative.createCopy());
		}
		
		return copy;
	}
}
