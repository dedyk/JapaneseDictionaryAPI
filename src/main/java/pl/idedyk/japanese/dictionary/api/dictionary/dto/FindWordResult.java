package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleGroupType;
import pl.idedyk.japanese.dictionary.api.example.dto.ExampleResult;
import pl.idedyk.japanese.dictionary.api.gramma.dto.GrammaFormConjugateResult;

public class FindWordResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public List<ResultItem> result;
	
	public boolean moreElemetsExists = false;
	
	public boolean foundGrammaAndExamples = false;

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

	public static class ResultItem implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private DictionaryEntry dictionaryEntry;
		
		private GrammaFormConjugateResult grammaFormConjugateResult;
		
		private ExampleResult exampleResult;
		private ExampleGroupType exampleGroupType;
		
		private DictionaryEntry relatedDictionaryEntryById;
		
		public ResultItem(DictionaryEntry dictionaryEntry) {
			this.dictionaryEntry = dictionaryEntry;
		}
		
		public ResultItem(GrammaFormConjugateResult grammaFormConjugateResult, DictionaryEntry relatedDictionaryEntryById) {
			this.grammaFormConjugateResult = grammaFormConjugateResult;
			
			this.relatedDictionaryEntryById = relatedDictionaryEntryById;
		}

		public ResultItem(ExampleResult exampleResult, ExampleGroupType exampleGroupType, DictionaryEntry relatedDictionaryEntryById) {
			this.exampleResult = exampleResult;
			this.exampleGroupType = exampleGroupType;
			
			this.relatedDictionaryEntryById = relatedDictionaryEntryById;
		}

		public DictionaryEntry getDictionaryEntry() {
			if (dictionaryEntry != null) {
				return dictionaryEntry;
			} else if (grammaFormConjugateResult != null || exampleResult != null) {
				return relatedDictionaryEntryById;
			}
			
			throw new RuntimeException("getDictionaryEntry");
		}

		public boolean isKanjiExists() {
			
			if (dictionaryEntry != null) {
				return dictionaryEntry.isKanjiExists();
			} else if (grammaFormConjugateResult != null) {
				return grammaFormConjugateResult.isKanjiExists();
			} else if (exampleResult != null) {
				return exampleResult.isKanjiExists();
			}
			
			throw new RuntimeException("isKanjiExists");
		}

		public String getKanji() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getKanji();
			} else if (grammaFormConjugateResult != null) {
				return grammaFormConjugateResult.getKanji();
			} else if (exampleResult != null) {
				return exampleResult.getKanji();
			}
			
			throw new RuntimeException("getKanji");
		}

		public String getPrefixKana() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getPrefixKana();
			} else if (grammaFormConjugateResult != null) {
				return grammaFormConjugateResult.getPrefixKana();
			} else if (exampleResult != null) {
				return exampleResult.getPrefixKana();
			}
			
			throw new RuntimeException("getPrefixKana");
		}

		public List<String> getKanaList() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getKanaList();
			} else if (grammaFormConjugateResult != null) {
				return grammaFormConjugateResult.getKanaList();
			} else if (exampleResult != null) {
				return exampleResult.getKanaList();
			}
			
			throw new RuntimeException("getKanaList");
		}

		public String getPrefixRomaji() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getPrefixRomaji();
			} else if (grammaFormConjugateResult != null) {
				return grammaFormConjugateResult.getPrefixRomaji();
			} else if (exampleResult != null) {
				return exampleResult.getPrefixRomaji();
			}
			
			throw new RuntimeException("getPrefixRomaji");
		}

		public List<String> getRomajiList() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getRomajiList();
			} else if (grammaFormConjugateResult != null) {
				return grammaFormConjugateResult.getRomajiList();
			} else if (exampleResult != null) {
				return exampleResult.getRomajiList();
			}
			
			throw new RuntimeException("getRomajiList");
		}

		public List<String> getTranslates() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getTranslates();
			} else if (grammaFormConjugateResult != null) {
				return relatedDictionaryEntryById.getTranslates();
			} else if (exampleResult != null) {
				return relatedDictionaryEntryById.getTranslates();
			}
			
			throw new RuntimeException("getTranslates");
		}

		public String getInfo() {
			if (dictionaryEntry != null) {
				return dictionaryEntry.getInfo();
			} else if (grammaFormConjugateResult != null) {
				
				String relatedDictionaryEntryByIdInfo = relatedDictionaryEntryById.getInfo();
				
				String result = "";
				
				if (relatedDictionaryEntryByIdInfo != null && relatedDictionaryEntryByIdInfo.equals("") == false) {
					result = relatedDictionaryEntryByIdInfo + ", ";
				}
				
				result = result + grammaFormConjugateResult.getResultType().getName();
				
				return result;
			} else if (exampleResult != null) {
				String relatedDictionaryEntryByIdInfo = relatedDictionaryEntryById.getInfo();
				
				String result = "";
				
				if (relatedDictionaryEntryByIdInfo != null && relatedDictionaryEntryByIdInfo.equals("") == false) {
					result = relatedDictionaryEntryByIdInfo + ", ";
				}
				
				result = result + exampleGroupType.getName() + (exampleGroupType.getInfo() != null ? ", " + exampleGroupType.getInfo() : "");
				
				return result;
				
			}
			
			throw new RuntimeException("getInfo");
		}
	}
}
