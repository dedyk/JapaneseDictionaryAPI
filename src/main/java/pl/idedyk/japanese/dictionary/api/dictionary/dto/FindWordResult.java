package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryGroup;

public class FindWordResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public List<ResultItem> result;
	
	public boolean moreElemetsExists = false;
	
	public boolean foundGrammaAndExamples = false;
	
	public boolean foundNames = false;

	public List<ResultItem> getResult() {
		return result;
	}

	public void setResult(List<ResultItem> result) {
		this.result = result;
	}

	public boolean isMoreElemetsExists() {
		return moreElemetsExists;
	}

	public void setMoreElemetsExists(boolean moreElemetsExists) {
		this.moreElemetsExists = moreElemetsExists;
	}

	public boolean isFoundGrammaAndExamples() {
		return foundGrammaAndExamples;
	}

	public void setFoundGrammaAndExamples(boolean foundGrammaAndExamples) {
		this.foundGrammaAndExamples = foundGrammaAndExamples;
	}

	public boolean isFoundNames() {
		return foundNames;
	}

	public void setFoundNames(boolean foundNames) {
		this.foundNames = foundNames;
	}

	public static class ResultItem implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private DictionaryEntry dictionaryEntry;
		
		private DictionaryEntryGroup dictionaryEntryGroup;
				
		public ResultItem(DictionaryEntry dictionaryEntry) {
			this.dictionaryEntry = dictionaryEntry;
		}
		
		@Deprecated
		public DictionaryEntry getDictionaryEntry() {
			
			if (dictionaryEntry != null) {
				return dictionaryEntry;
				
			}
			
			throw new RuntimeException("getDictionaryEntry");
		}
		
		public List<Integer> getDictionaryEntryIdList() {
			
			if (dictionaryEntry != null) {
				return Arrays.asList(dictionaryEntry.getId());
					
			} else if (dictionaryEntryGroup != null) {
				
				List<Integer> result = new ArrayList<Integer>();
				
				for (DictionaryEntry dictionaryEntry : dictionaryEntryGroup.getDictionaryEntryList()) {
					result.add(dictionaryEntry.getId());
				}
				
				return result;
			}
			
			throw new RuntimeException("getDictionaryEntryIdList");
		}

		public boolean isKanjiExists() {
			
			if (dictionaryEntry != null) {
				return dictionaryEntry.isKanjiExists();
			}
			
			throw new RuntimeException("isKanjiExists");
		}

		public String getKanji() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getKanji();
			}
			
			throw new RuntimeException("getKanji");
		}

		public String getPrefixKana() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getPrefixKana();
			}
			
			throw new RuntimeException("getPrefixKana");
		}

		@SuppressWarnings("deprecation")
		public List<String> getKanaList() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getKanaList();
			}
			
			throw new RuntimeException("getKanaList");
		}

		public String getPrefixRomaji() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getPrefixRomaji();
			}
			
			throw new RuntimeException("getPrefixRomaji");
		}

		@SuppressWarnings("deprecation")
		public List<String> getRomajiList() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getRomajiList();
			}
			
			throw new RuntimeException("getRomajiList");
		}

		public List<String> getTranslates() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getTranslates();
			}
			
			throw new RuntimeException("getTranslates");
		}

		public String getInfo() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getInfo();
			}
			
			throw new RuntimeException("getInfo");
		}
	}
}
