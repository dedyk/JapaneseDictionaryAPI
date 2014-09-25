package pl.idedyk.japanese.dictionary.api.dto;

import java.util.List;

public class GroupWithTatoebaSentenceList {
	
	private String groupId;
	
	private List<TatoebaSentence> tatoebaSentenceList;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<TatoebaSentence> getTatoebaSentenceList() {
		return tatoebaSentenceList;
	}

	public void setTatoebaSentenceList(List<TatoebaSentence> tatoebaSentenceList) {
		this.tatoebaSentenceList = tatoebaSentenceList;
	}
}
