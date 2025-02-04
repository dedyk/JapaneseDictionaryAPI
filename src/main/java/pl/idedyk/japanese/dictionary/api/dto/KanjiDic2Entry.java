package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;
import java.util.List;

public class KanjiDic2Entry implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String kanji;
	
	private int strokeCount;
	
	private List<String> onReading;
	private List<String> kunReading;
	private List<String> nanoriReading;
	
	private List<String> radicals;
	
	private List<String> engMeaning;
	
    private Integer jlpt;

    private Integer freq;
    
    private Integer jouyouGrade;

	public String getKanji() {
		return kanji;
	}

	public int getStrokeCount() {
		return strokeCount;
	}

	public List<String> getOnReading() {
		return onReading;
	}

	public List<String> getKunReading() {
		return kunReading;
	}
	
	public List<String> getNanoriReading() {
		return nanoriReading;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public void setStrokeCount(int strokeCount) {
		this.strokeCount = strokeCount;
	}

	public void setOnReading(List<String> onReading) {
		this.onReading = onReading;
	}

	public void setKunReading(List<String> kunReading) {
		this.kunReading = kunReading;
	}
	
	public void setNanoriReading(List<String> nanoriReading) {
		this.nanoriReading = nanoriReading;
	}

	public List<String> getRadicals() {
		return radicals;
	}

	public void setRadicals(List<String> radicals) {
		this.radicals = radicals;
	}

	public List<String> getEngMeaning() {
		return engMeaning;
	}

	public void setEngMeaning(List<String> engMeaning) {
		this.engMeaning = engMeaning;
	}

	public Integer getJlpt() {
		return jlpt;
	}

	public void setJlpt(Integer jlpt) {
		this.jlpt = jlpt;
	}

	public Integer getFreq() {
		return freq;
	}

	public void setFreq(Integer freq) {
		this.freq = freq;
	}
	
	public Integer getJouyouGrade() {
		return jouyouGrade;
	}

	public void setJouyouGrade(Integer jouyouGrade) {
		this.jouyouGrade = jouyouGrade;
	}

	@Override
	public String toString() {
		return "KanjiDic2Entry [kanji=" + kanji + ", strokeCount=" + strokeCount + ", onReading=" + onReading
				+ ", kunReading=" + kunReading + ", radicals=" + radicals + ", engMeaning=" + engMeaning + ", jlpt="
				+ jlpt + ", freq=" + freq + "]";
	}
}
