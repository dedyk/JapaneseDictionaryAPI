package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;

public class RadicalInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String radical;
	
	private int strokeCount;
	
	public int getId() {
		return id;
	}

	public String getRadical() {
		return radical;
	}

	public int getStrokeCount() {
		return strokeCount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRadical(String radical) {
		this.radical = radical;
	}

	public void setStrokeCount(int strokeCount) {
		this.strokeCount = strokeCount;
	}
}
