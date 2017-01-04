package pl.idedyk.japanese.dictionary.api.dictionary;

import java.util.List;
import java.util.Set;

import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindKanjiRequest;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindKanjiResult;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindWordRequest;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindWordResult;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.GroupEnum;
import pl.idedyk.japanese.dictionary.api.dto.GroupWithTatoebaSentenceList;
import pl.idedyk.japanese.dictionary.api.dto.KanjiEntry;
import pl.idedyk.japanese.dictionary.api.exception.DictionaryException;

public interface IDatabaseConnector {

	public int getDictionaryEntriesSize();
	
	public int getDictionaryEntriesNameSize();
	
	public DictionaryEntry getNthDictionaryEntry(int nth) throws DictionaryException;
	
	public FindWordResult findDictionaryEntries(FindWordRequest findWordRequest) throws DictionaryException;
	public FindWordResult findDictionaryGroupEntries(FindWordRequest findWordRequest) throws DictionaryException;
	
	public void findDictionaryEntriesInGrammaFormAndExamples(FindWordRequest findWordRequest,
			FindWordResult findWordResult) throws DictionaryException;
	
	public DictionaryEntry getDictionaryEntryById(String id) throws DictionaryException;
	
	public DictionaryEntry getDictionaryEntryNameById(String id) throws DictionaryException;
	
	public KanjiEntry getKanjiEntryById(String id) throws DictionaryException;
	
	public KanjiEntry getKanjiEntry(String kanji) throws DictionaryException;
	
	public List<KanjiEntry> getAllKanjis(boolean withDetails, boolean onlyUsed) throws DictionaryException;
	
	public List<KanjiEntry> findKanjiFromRadicals(String[] radicals) throws DictionaryException;
	
	public FindKanjiResult findKanjisFromStrokeCount(int from, int to) throws DictionaryException;
	
	public Set<String> findAllAvailableRadicals(String[] radicals) throws DictionaryException;
	
	public FindKanjiResult findKanji(FindKanjiRequest findKanjiRequest) throws DictionaryException;
	
	public List<GroupEnum> getDictionaryEntryGroupTypes();
	
	public List<DictionaryEntry> getGroupDictionaryEntries(GroupEnum groupName) throws DictionaryException;
	
	public GroupWithTatoebaSentenceList getTatoebaSentenceGroup(String groupId) throws DictionaryException;

	public void findDictionaryEntriesInNames(FindWordRequest findWordRequest, FindWordResult findWordResult) throws DictionaryException;
}
