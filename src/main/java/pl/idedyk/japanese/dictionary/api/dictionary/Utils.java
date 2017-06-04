package pl.idedyk.japanese.dictionary.api.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import pl.idedyk.japanese.dictionary.api.dto.AttributeList;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary.api.dto.GroupEnum;
import pl.idedyk.japanese.dictionary.api.dto.KanjiDic2Entry;
import pl.idedyk.japanese.dictionary.api.dto.KanjiEntry;
import pl.idedyk.japanese.dictionary.api.dto.KanjivgEntry;
import pl.idedyk.japanese.dictionary.api.exception.DictionaryException;

public class Utils {

	public static int MAX_LIST_SIZE = 30;

	public static List<String> parseStringIntoList(String text, boolean limitSize) {

		if (text == null) {
			return null;
		}
		
		List<String> result = new ArrayList<String>();

		if (text.equals("") == true) {
			return result;
		}
		
		String[] splitedText = text.split("\n");

		for (String currentSplitedText : splitedText) {
			result.add(currentSplitedText);
		}

		if (limitSize == true && result.size() > MAX_LIST_SIZE) {
			throw new RuntimeException("parseStringIntoList max list size: " + text);
		}

		return result;
	}

	public static String convertListToString(List<String> list) {
		
		if (list == null) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();

		for (int idx = 0; idx < list.size(); ++idx) {
			sb.append(list.get(idx));

			if (idx != list.size() - 1) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	public static DictionaryEntry parseDictionaryEntry(String idString, Object dictionaryEntryTypeObject,
			Object attributesObject, Object groupsObject, String prefixKanaString, String kanjiString,
			String kanaString, String prefixRomajiString, String romajiString, Object translateListObject,
			String infoString, Object exampleSentenceGroupIdsListObject) throws DictionaryException {

		if (kanjiString.equals("") == true || kanjiString.equals("-") == true) {
			kanjiString = null;
		}

		if (prefixRomajiString.equals("") == true || prefixRomajiString.equals("-") == true) {
			prefixRomajiString = null;
		}

		//DictionaryEntryType dictionaryEntryType = DictionaryEntryType.valueOf(dictionaryEntryTypeString);

		DictionaryEntry entry = new DictionaryEntry();

		entry.setId(Integer.parseInt(idString));

		if (dictionaryEntryTypeObject instanceof String) {
			entry.setDictionaryEntryTypeList(DictionaryEntryType.convertToListDictionaryEntryType(parseStringIntoList(
					(String) dictionaryEntryTypeObject, false)));
		} else {
			entry.setDictionaryEntryTypeList(DictionaryEntryType
					.convertToListDictionaryEntryType(convertToListString(dictionaryEntryTypeObject)));
		}

		if (attributesObject instanceof String) {
			entry.setAttributeList(AttributeList.parseAttributesStringList(parseStringIntoList(
					(String) attributesObject, false)));
		} else {
			entry.setAttributeList(AttributeList.parseAttributesStringList((convertToListString(attributesObject))));
		}

		if (groupsObject instanceof String) {
			entry.setGroups(GroupEnum.sortGroups(GroupEnum.convertToListGroupEnum(parseStringIntoList(
					(String) groupsObject, false))));
		} else {
			entry.setGroups(GroupEnum.sortGroups(GroupEnum.convertToListGroupEnum(convertToListString(groupsObject))));
		}

		entry.setPrefixKana(prefixKanaString);
		entry.setKanji(kanjiString);
		entry.setPrefixRomaji(prefixRomajiString);

		entry.setKana(kanaString);
		
		entry.setRomaji(romajiString);

		if (translateListObject instanceof String) {
			entry.setTranslates(parseStringIntoList((String) translateListObject, true));
		} else {
			entry.setTranslates(convertToListString(translateListObject));
		}

		entry.setInfo(infoString);
		
		if (exampleSentenceGroupIdsListObject instanceof String) {
			entry.setExampleSentenceGroupIdsList(parseStringIntoList((String) exampleSentenceGroupIdsListObject, false));			
		} else {
			entry.setExampleSentenceGroupIdsList(convertToListString(exampleSentenceGroupIdsListObject));
		}
				
		return entry;
	}

	private static List<String> convertToListString(Object object) {

		if (object == null) {
			return new ArrayList<String>();
		}

		List<?> listObject = (List<?>) object;

		List<String> result = new ArrayList<String>();

		for (Object currentListObject : listObject) {
			result.add((String) currentListObject);
		}

		return result;
	}

	public static KanjiEntry parseKanjiEntry(String idString, String kanjiString, String strokeCountString,
			List<String> radicalsList, List<String> onReadingList, List<String> kunReadingList,
			Object strokePathObject, List<String> polishTranslateList, String infoString, String usedString,
			List<String> groupsList) throws DictionaryException {

		int id = Integer.parseInt(idString);

		if (kanjiString.equals("") == true) {
			throw new DictionaryException("Empty kanji: " + idString);
		}

		KanjiDic2Entry kanjiDic2Entry = null;

		if (strokeCountString != null && strokeCountString.equals("") == false) {

			kanjiDic2Entry = new KanjiDic2Entry();

			int strokeCount = Integer.parseInt(strokeCountString);

			kanjiDic2Entry.setKanji(kanjiString);
			kanjiDic2Entry.setStrokeCount(strokeCount);
			kanjiDic2Entry.setRadicals(radicalsList);
			kanjiDic2Entry.setKunReading(kunReadingList);
			kanjiDic2Entry.setOnReading(onReadingList);
		}

		KanjiEntry entry = new KanjiEntry();

		entry.setId(id);
		entry.setKanji(kanjiString);
		
		KanjivgEntry kanjivgEntry = new KanjivgEntry();
		
		if (strokePathObject instanceof String) {
			kanjivgEntry.setStrokePaths(parseStringIntoList((String)strokePathObject, false));
			
		} else {
			kanjivgEntry.setStrokePaths(convertToListString(strokePathObject));
		}		
		
		entry.setKanjivgEntry(kanjivgEntry);
		entry.setPolishTranslates(polishTranslateList);
		entry.setInfo(infoString);

		entry.setUsed(Boolean.parseBoolean(usedString));

		entry.setGroups(GroupEnum.convertToListGroupEnum(groupsList));

		entry.setKanjiDic2Entry(kanjiDic2Entry);

		return entry;
	}

	public static String removePolishChars(String text) {

		text = text.replace('Ę', 'E');
		text = text.replace('ę', 'e');

		text = text.replace('Ó', 'O');
		text = text.replace('ó', 'o');

		text = text.replace('Ą', 'A');
		text = text.replace('ą', 'a');

		text = text.replace('Ś', 'S');
		text = text.replace('ś', 's');

		text = text.replace('Ł', 'L');
		text = text.replace('ł', 'l');

		text = text.replace('Ż', 'Z');
		text = text.replace('ż', 'z');

		text = text.replace('Ź', 'z');
		text = text.replace('ź', 'z');

		text = text.replace('Ć', 'C');
		text = text.replace('ć', 'c');

		text = text.replace('Ń', 'N');
		text = text.replace('ń', 'n');

		return text;
	}
	
	public static char removePolishChar(char c) {
		
		switch (c) {
			
			case 'Ę':
				return 'E';
			
			case 'ę':
				return 'e';
			
			case 'Ó':
				return 'O';
				
			case 'ó':
				return 'o';
				
			case 'Ą':
				return 'A';
			
			case 'ą':
				return 'a';
				
			case 'Ś':
				return 'S';
				
			case 'ś':
				return 's';
				
			case 'Ł':
				return 'L';
				
			case 'ł':
				return 'l';
				
			case 'Ż':
				return 'Z';
				
			case 'ż':
				return 'z';
				
			case 'Ź':
				return 'Z';
				
			case 'ź':
				return 'z';
				
			case 'Ć':
				return 'C';
				
			case 'ć':
				return 'c';
			
			case 'Ń':
				return 'N';
				
			case 'ń':
				return 'n';
			
			default:
				return c;		
		}
	}

	public static boolean containsPolishChars(String text) {

		Pattern pattern = Pattern.compile("(Ę|ę|Ó|ó|Ą|ą|Ś|ś|Ł|ł|Ż|ż|Ź|ź|Ć|ć|Ń|ń)");

		return pattern.matcher(text).find();
	}
	
	public static boolean isKanji(char c) {
		return (c >= 0x4e00 && c < 0xa000) || c == '\u3005';
	}

	public static boolean isKatakana(char c) {
		return (c >= 0x30a0 && c < 0x3100);
	}

	public static boolean isHiragana(char c) {
		return (c >= 0x3040 && c < 0x30a0);
	}

	public static boolean isKana(char c) {
		return (c >= 0x3040 && c < 0x3100);
	}
	
	public static boolean isJapanaseChar(char c) {
		return isHiragana(c) || isKatakana(c) || isKana(c) || isKanji(c);
	}
	
	public static boolean isAllKanjiChars(String text) {
		
		boolean isAllKanjiChars = true;

		for (int textIdx = 0; textIdx < text.length(); ++textIdx) {
			
			if (isKanji(text.charAt(textIdx)) == false) {
				
				isAllKanjiChars = false;
				
				break;
			}									
		}

		return isAllKanjiChars;
	}

	public static boolean isAllKanaChars(String text) {
		
		boolean isAllKanaChars = true;

		for (int textIdx = 0; textIdx < text.length(); ++textIdx) {
			
			if (isKana(text.charAt(textIdx)) == false) {
				
				isAllKanaChars = false;
				
				break;
			}									
		}

		return isAllKanaChars;
	}

	public static boolean isAllJapaneseChars(String text) {
		
		boolean isAllJapaneseChars = true;

		for (int textIdx = 0; textIdx < text.length(); ++textIdx) {
			
			if (isJapanaseChar(text.charAt(textIdx)) == false) {
				
				isAllJapaneseChars = false;
				
				break;
			}									
		}

		return isAllJapaneseChars;
	}
}
