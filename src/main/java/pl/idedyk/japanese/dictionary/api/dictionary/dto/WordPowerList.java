package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordPowerList {
	
	private Map<Integer, List<Integer>> wordPowerMap;
	
	public void addPower(int power, int dictionaryEntryId) {
		
		if (wordPowerMap == null) {
			wordPowerMap = new TreeMap<Integer, List<Integer>>();
		}
		
		List<Integer> listForPower = wordPowerMap.get(power);
		
		if (listForPower == null) {
			listForPower = new ArrayList<Integer>();
			
			wordPowerMap.put(power, listForPower);
		}
		
		listForPower.add(dictionaryEntryId);
	}

	public Map<Integer, List<Integer>> getWordPowerMap() {
		return wordPowerMap;
	}

	public void setWordPowerMap(Map<Integer, List<Integer>> wordPowerMap) {
		this.wordPowerMap = wordPowerMap;
	}
}
