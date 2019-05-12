package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dictionary.dto.WordPlaceSearch;

public class WordDictionaryMissingWordEvent extends QueueEventCommon {

    private String word;

    private WordPlaceSearch wordPlaceSearch;

    public WordDictionaryMissingWordEvent(String userId, String word, WordPlaceSearch wordPlaceSearch) {

        super(userId);

        this.word = word;
        this.wordPlaceSearch = wordPlaceSearch;
    }

    public WordDictionaryMissingWordEvent(Long id, String userId, Date createDate, Map<String, String> paramsMap) {

        super(id, userId, createDate);

        this.word = paramsMap.get("word");
        this.wordPlaceSearch = WordPlaceSearch.valueOf(paramsMap.get("wordPlaceSearch"));
    }

    @Override
    public QueueEventOperation getQueryEventOperation() {
        return QueueEventOperation.WORD_DICTIONARY_MISSING_WORD_EVENT;
    }

    @Override
    public Map<String, String> getParams() {

        Map<String, String> result = new HashMap<>();

        result.put("word", word);
        result.put("wordPlaceSearch", wordPlaceSearch.toString());

        return result;
    }

    public String getWord() {
        return word;
    }

    public WordPlaceSearch getWordPlaceSearch() {
        return wordPlaceSearch;
    }
}
