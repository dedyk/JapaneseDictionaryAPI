package pl.idedyk.japanese.dictionary.api.gramma.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GrammaFormConjugateResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private GrammaFormConjugateResultType resultType;
	
	private String prefixKana;
	
	private String kanji;
	
	private List<String> kanaList;
	
	private String prefixRomaji;
	
	private List<String> romajiList;
	
	private String info;
	
	private GrammaFormConjugateResult alternative;

	public String getKanji() {
		return kanji;
	}

	public List<String> getKanaList() {
		return kanaList;
	}

	public List<String> getRomajiList() {
		return romajiList;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public void setKanaList(List<String> kanaList) {
		this.kanaList = kanaList;
	}

	public void setRomajiList(List<String> romajiList) {
		this.romajiList = romajiList;
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
		
		if (kanaList != null) {
			copy.setKanaList(new ArrayList<>());
			copy.getKanaList().addAll(kanaList);
		}
		
		copy.setPrefixRomaji(prefixRomaji);
		
		if (romajiList != null) {
			copy.setRomajiList(new ArrayList<>());
			copy.getRomajiList().addAll(romajiList);
		}
		
		copy.setInfo(info);
		
		if (alternative != null) {
			copy.setAlternative(alternative.createCopy());
		}
		
		return copy;
	}
}
