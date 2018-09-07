package pl.idedyk.japanese.dictionary.api.dto;

public class TransitiveIntransitivePairWithDictionaryEntry extends TransitiveIntransitivePair {

	private static final long serialVersionUID = 1L;
	
	private DictionaryEntry transitiveDictionaryEntry;
	
	private DictionaryEntry intransitiveDictionaryEntry;

	public DictionaryEntry getTransitiveDictionaryEntry() {
		return transitiveDictionaryEntry;
	}

	public void setTransitiveDictionaryEntry(DictionaryEntry transitiveDictionaryEntry) {
		this.transitiveDictionaryEntry = transitiveDictionaryEntry;
	}

	public DictionaryEntry getIntransitiveDictionaryEntry() {
		return intransitiveDictionaryEntry;
	}

	public void setIntransitiveDictionaryEntry(DictionaryEntry intransitiveDictionaryEntry) {
		this.intransitiveDictionaryEntry = intransitiveDictionaryEntry;
	}
}
