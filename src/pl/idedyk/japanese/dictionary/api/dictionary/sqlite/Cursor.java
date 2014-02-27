package pl.idedyk.japanese.dictionary.api.dictionary.sqlite;

public interface Cursor {

	public boolean moveToFirst();
	public boolean moveToNext();
	
	public boolean isAfterLast();
	
	public String getString(int idx);
	public int getInt(int idx);

	public void close();
}
