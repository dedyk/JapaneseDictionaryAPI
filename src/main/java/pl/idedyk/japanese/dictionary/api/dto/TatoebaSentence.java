package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;
import java.util.List;

public class TatoebaSentence implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
		
	private String lang;
	
	private String sentence;
	
	private List<String> sentenceToken;

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

	public List<String> getSentenceToken() {
		return sentenceToken;
	}

	public void setSentenceToken(List<String> sentenceToken) {
		this.sentenceToken = sentenceToken;
	}
}
