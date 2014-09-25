package pl.idedyk.japanese.dictionary.api.dto;

public class TatoebaSentence {
	
	private String id;
	
	private String lang;
	
	private String sentence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
}
