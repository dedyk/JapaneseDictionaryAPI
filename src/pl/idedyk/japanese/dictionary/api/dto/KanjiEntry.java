package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;
import java.util.List;

public class KanjiEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String kanji;
	
	private List<String> polishTranslates;
	
	private String info;
	
	private KanjiDic2Entry kanjiDic2Entry;
	
	private KanjivgEntry kanjivgEntry;
		
	private boolean generated;
	
	private List<GroupEnum> groups;

	public int getId() {
		return id;
	}

	public String getKanji() {
		return kanji;
	}

	public List<String> getPolishTranslates() {
		return polishTranslates;
	}

	public String getInfo() {
		return info;
	}

	public KanjiDic2Entry getKanjiDic2Entry() {
		return kanjiDic2Entry;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public void setPolishTranslates(List<String> polishTranslates) {
		this.polishTranslates = polishTranslates;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setKanjiDic2Entry(KanjiDic2Entry kanjiDic2Entry) {
		this.kanjiDic2Entry = kanjiDic2Entry;
	}

	public KanjivgEntry getKanjivgEntry() {
		return kanjivgEntry;
	}

	public void setKanjivgEntry(KanjivgEntry kanjivgEntry) {
		this.kanjivgEntry = kanjivgEntry;
	}

	public boolean isGenerated() {
		return generated;
	}

	public void setGenerated(boolean generated) {
		this.generated = generated;
	}

	public List<GroupEnum> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEnum> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + id;
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		KanjiEntry other = (KanjiEntry) obj;
		
		if (id != other.id)
			return false;
		
		return true;
	}
}
