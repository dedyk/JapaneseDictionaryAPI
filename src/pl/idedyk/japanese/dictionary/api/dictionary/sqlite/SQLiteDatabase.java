package pl.idedyk.japanese.dictionary.api.dictionary.sqlite;

import java.util.Map;

public interface SQLiteDatabase {
	
	public void open();	
	public void close();

	public void beginTransaction();

	public void setTransactionSuccessful();

	public void endTransaction();

	public Cursor rawQuery(String query, String[] params);

	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);
	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
	public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
	
	public void execSQL(String sql);

	public long insertOrThrow(String table, String nullColumnHack, Map<String, Object> values);
	
}
