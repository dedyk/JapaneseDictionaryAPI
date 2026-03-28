package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pl.idedyk.japanese.dictionary2.jmdict.xsd.JMdict;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.Sense;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.JMnedict;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.TranslationalInfo;

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

		private JMdict.Entry wordEntry;
		private JMnedict.Entry nameEntry;
		
		private boolean isName;
		private boolean isGrammaFormOrExamples;
		
		public ResultItem(JMdict.Entry wordEntry, boolean isName, boolean isGrammaFormOrExamples) {
			this.wordEntry = wordEntry;
			this.isName = isName;
			this.isGrammaFormOrExamples = isGrammaFormOrExamples;
		}
		
		public ResultItem(JMnedict.Entry nameEntry, boolean isName, boolean isGrammaFormOrExamples) {
			this.nameEntry = nameEntry;
			this.isName = isName;
			this.isGrammaFormOrExamples = isGrammaFormOrExamples;
		}
				
		public JMdict.Entry getWordEntry() {
			return wordEntry;
		}

		public JMnedict.Entry getNameEntry() {
			return nameEntry;
		}
		
		public boolean isName() {
			return isName;
		}

		public boolean isGrammaFormOrExamples() {
			return isGrammaFormOrExamples;
		}		
		
		public List<String> getKanjiList() {			
			if (wordEntry != null) {
				return wordEntry.getKanjiInfoList().stream().filter(f -> f.getKanji() != null).map(f -> f.getKanji()).collect(Collectors.toList());
			}
			
			if (nameEntry != null) {
				return nameEntry.getKanjiInfoList().stream().filter(f -> f.getKanji() != null).map(f -> f.getKanji()).collect(Collectors.toList());
			}
						
			throw new RuntimeException("getKanji");
		}
		
		public List<String> getKanaList() {			
			if (wordEntry != null) {
				return wordEntry.getReadingInfoList().stream().filter(f -> f.getKana() != null).map(f -> f.getKana().getValue()).collect(Collectors.toList());
			}

			if (nameEntry != null) {
				return nameEntry.getReadingInfoList().stream().filter(f -> f.getKana() != null).map(f -> f.getKana()).collect(Collectors.toList());
			}
			
			throw new RuntimeException("getKanaList");
		}
		
		public List<String> getRomajiList() {			
			if (wordEntry != null) {
				return wordEntry.getReadingInfoList().stream().filter(f -> f.getKana() != null).map(f -> f.getKana().getRomaji()).collect(Collectors.toList());
			}
			
			if (nameEntry != null) {
				return nameEntry.getReadingInfoList().stream().filter(f -> f.getRomaji() != null).map(f -> f.getRomaji()).collect(Collectors.toList());
			}
			
			throw new RuntimeException("getRomajiList");
		}
		
		public List<String> getTranslates() {			
			if (wordEntry != null) {
				List<String> result = new ArrayList<>();
				
				for (Sense sense : wordEntry.getSenseList()) {
					sense.getGlossList().stream().filter(f -> f.getLang().equals("pol") == true).forEach(c -> result.add(c.getValue()));					
				}
				
				return result;
			}
			
			if (nameEntry != null) {
				List<String> result = new ArrayList<>();
				
				// najpierw proba pobrania jezyka polskiego (moze nie byc)
				for (TranslationalInfo translationalInfo : nameEntry.getTranslationInfo()) {
					translationalInfo.getTransDet().stream().filter(f -> f.getLang().equals("pol") == true).forEach(c -> result.add(c.getValue()));					
				}
				
				// jezeli nic nie ma to pobieramy jezyk angielski
				if (result.size() == 0) {
					for (TranslationalInfo translationalInfo : nameEntry.getTranslationInfo()) {
						translationalInfo.getTransDet().stream().filter(f -> f.getLang().equals("eng") == true).forEach(c -> result.add(c.getValue()));					
					}					
				}
				
				return result;
			}
						
			throw new RuntimeException("getTranslates");
		}


		// stary kod		
		/*
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
		*/
	}
}
