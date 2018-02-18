package pl.idedyk.japanese.dictionary.api.exception;

public class DictionaryException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DictionaryException(Exception e) {
		super(e);
	}

	public DictionaryException(String detailMessage) {
		super(detailMessage);
	}
}
