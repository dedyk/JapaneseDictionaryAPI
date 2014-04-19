package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;

public class TransitiveIntransitivePair implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer transitiveId;
	
	private Integer intransitiveId;

	public Integer getTransitiveId() {
		return transitiveId;
	}

	public void setTransitiveId(Integer transitiveId) {
		this.transitiveId = transitiveId;
	}

	public Integer getIntransitiveId() {
		return intransitiveId;
	}

	public void setIntransitiveId(Integer intransitiveId) {
		this.intransitiveId = intransitiveId;
	}
}
