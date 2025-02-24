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
import pl.idedyk.japanese.dictionary.api.exception.DictionaryException;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.JMdict;
import pl.idedyk.japanese.dictionary2.kanjidic2.xsd.KanjiCharacterInfo;

public interface IDatabaseConnector {

	public int getDictionaryEntriesSize() throws DictionaryException;
	
	public int getDictionaryEntriesNameSize() throws DictionaryException;
	
	public DictionaryEntry getNthDictionaryEntry(int nth) throws DictionaryException;
	
	public FindWordResult findDictionaryEntries(FindWordRequest findWordRequest) throws DictionaryException;
	
	public void findDictionaryEntriesInGrammaFormAndExamples(FindWordRequest findWordRequest,
			FindWordResult findWordResult) throws DictionaryException;
	
	public DictionaryEntry getDictionaryEntryById(String id) throws DictionaryException;
	public DictionaryEntry getDictionaryEntryByUniqueKey(String uniqueKey) throws DictionaryException;
	
	public JMdict.Entry getDictionaryEntry2ById(Integer entryId) throws DictionaryException;	
	
	public DictionaryEntry getDictionaryEntryNameById(String id) throws DictionaryException;
	public DictionaryEntry getDictionaryEntryNameByUniqueKey(String uniqueKey) throws DictionaryException;
	
	public KanjiCharacterInfo getKanjiCharacterInfoById(String id) throws DictionaryException;
	
	public KanjiCharacterInfo getKanjiEntry(String kanji) throws DictionaryException;
	
	public List<KanjiCharacterInfo> getKanjiEntryList(List<String> kanjiList) throws DictionaryException;
	
	public List<KanjiCharacterInfo> getAllKanjis(boolean onlyUsed) throws DictionaryException;
	
	public List<KanjiCharacterInfo> findKanjiFromRadicals(String[] radicals) throws DictionaryException;
	
	public FindKanjiResult findKanjisFromStrokeCount(int from, int to) throws DictionaryException;
	
	public Set<String> findAllAvailableRadicals(String[] radicals) throws DictionaryException;
	
	public FindKanjiResult findKanji(FindKanjiRequest findKanjiRequest) throws DictionaryException;
	
	public List<GroupEnum> getDictionaryEntryGroupTypes() throws DictionaryException;
	
	public List<DictionaryEntry> getGroupDictionaryEntries(GroupEnum groupName) throws DictionaryException;
	
	public GroupWithTatoebaSentenceList getTatoebaSentenceGroup(String groupId) throws DictionaryException;

	public void findDictionaryEntriesInNames(FindWordRequest findWordRequest, FindWordResult findWordResult) throws DictionaryException;
	
	//
	
	public class FindDictionaryEntriesInGrammaFormAndExamplesWrapper {
		
		private FindWordRequest findWordRequest;
		
		private FindWordResult findWordResult;
		
		public FindDictionaryEntriesInGrammaFormAndExamplesWrapper() {
			// noop
		}

		public FindDictionaryEntriesInGrammaFormAndExamplesWrapper(FindWordRequest findWordRequest, FindWordResult findWordResult) {
			this.findWordRequest = findWordRequest;
			this.findWordResult = findWordResult;
		}

		public FindWordRequest getFindWordRequest() {
			return findWordRequest;
		}

		public void setFindWordRequest(FindWordRequest findWordRequest) {
			this.findWordRequest = findWordRequest;
		}

		public FindWordResult getFindWordResult() {
			return findWordResult;
		}

		public void setFindWordResult(FindWordResult findWordResult) {
			this.findWordResult = findWordResult;
		}
	}
	
	public class GetAllKanjisWrapper {
				
		private boolean onlyUsed;
		
		public GetAllKanjisWrapper() {
			// noop
		}

		public GetAllKanjisWrapper(boolean onlyUsed) {
			this.onlyUsed = onlyUsed;
		}
		
		public boolean isOnlyUsed() {
			return onlyUsed;
		}

		public void setOnlyUsed(boolean onlyUsed) {
			this.onlyUsed = onlyUsed;
		}		
	}
	
    public class FindKanjisFromStrokeCountWrapper {

        private int from;

        private int to;

        public FindKanjisFromStrokeCountWrapper() {
            // noop
        }

        public FindKanjisFromStrokeCountWrapper(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }
    
    public class FindDictionaryEntriesInNamesWrapper {
    	
		private FindWordRequest findWordRequest;
		
		private FindWordResult findWordResult;
		
		public FindDictionaryEntriesInNamesWrapper() {
			// noop
		}

		public FindDictionaryEntriesInNamesWrapper(FindWordRequest findWordRequest, FindWordResult findWordResult) {
			this.findWordRequest = findWordRequest;
			this.findWordResult = findWordResult;
		}

		public FindWordRequest getFindWordRequest() {
			return findWordRequest;
		}

		public void setFindWordRequest(FindWordRequest findWordRequest) {
			this.findWordRequest = findWordRequest;
		}

		public FindWordResult getFindWordResult() {
			return findWordResult;
		}

		public void setFindWordResult(FindWordResult findWordResult) {
			this.findWordResult = findWordResult;
		}
    }
}
