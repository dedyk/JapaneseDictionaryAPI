package pl.idedyk.japanese.dictionary.api.dto;

import java.io.Serializable;
import java.util.List;

public class KanaEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String kanaJapanese;
	
	private String kanaDisplay;
	
	private String kana;
	
	private KanaType kanaType;
	
	private KanaGroup kanaGroup;
	
	private List<KanjivgEntry> strokePaths;
	
	private String image;
	
	private boolean use = true;

	public KanaEntry(String kanaJapanese, String kana, KanaType kanaType, KanaGroup kanaGroup) {
		this.kanaJapanese = kanaJapanese;
		this.kana = kana;
		this.kanaType = kanaType;
		this.kanaGroup = kanaGroup;
	}

	public KanaEntry(String kanaJapanese, String kana, KanaType kanaType, KanaGroup kanaGroup, boolean use) {
		this.kanaJapanese = kanaJapanese;
		this.kana = kana;
		this.kanaType = kanaType;
		this.kanaGroup = kanaGroup;
		this.use = use;
	}
	
	public KanaEntry(String kanaJapanese, String kana, String kanaDisplay, KanaType kanaType, KanaGroup kanaGroup) {
		this.kanaJapanese = kanaJapanese;
		this.kana = kana;
		this.kanaDisplay = kanaDisplay;
		this.kanaType = kanaType;
		this.kanaGroup = kanaGroup;
	}

	public String getKanaJapanese() {
		return kanaJapanese;
	}

	public String getKana() {
		return kana;
	}
	
	public String getKanaDisplay() {
		
		if (kanaDisplay != null) {
			return kanaDisplay;
		}
		
		return kana;
	}
	
	public KanaType getKanaType() {
		return kanaType;
	}

	public KanaGroup getKanaGroup() {
		return kanaGroup;
	}
	
	public List<KanjivgEntry> getStrokePaths() {
		return strokePaths;
	}

	public void setStrokePaths(List<KanjivgEntry> strokePaths) {
		
		if (this.strokePaths != null) {
			throw new RuntimeException("this.strokePaths != null: " + strokePaths.toString());
		}
		
		this.strokePaths = strokePaths;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public static enum KanaType {
		HIRAGANA,
		
		KATAKANA;
	}

	public static enum KanaGroup {
		
		GOJUUON,
		
		DAKUTEN,
		
		HANDAKUTEN,
		
		YOUON,
		
		SOKUON,
		
		OTHER;
	}
}