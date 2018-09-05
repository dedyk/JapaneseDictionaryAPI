package pl.idedyk.japanese.dictionary.api.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindKanjiRequest;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindKanjiResult;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindWordRequest;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindWordResult;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.FindWordResult.ResultItem;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.TranslateJapaneseSentenceResult;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.TranslateJapaneseSentenceResult.TokenType;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.WordPlaceSearch;
import pl.idedyk.japanese.dictionary.api.dictionary.dto.WordPowerList;
import pl.idedyk.japanese.dictionary.api.dto.AttributeType;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.FuriganaEntry;
import pl.idedyk.japanese.dictionary.api.dto.GroupEnum;
import pl.idedyk.japanese.dictionary.api.dto.GroupWithTatoebaSentenceList;
import pl.idedyk.japanese.dictionary.api.dto.KanaEntry;
import pl.idedyk.japanese.dictionary.api.dto.KanjiDic2Entry;
import pl.idedyk.japanese.dictionary.api.dto.KanjiEntry;
import pl.idedyk.japanese.dictionary.api.dto.KanjivgEntry;
import pl.idedyk.japanese.dictionary.api.dto.RadicalInfo;
import pl.idedyk.japanese.dictionary.api.dto.TransitiveIntransitivePair;
import pl.idedyk.japanese.dictionary.api.exception.DictionaryException;
import pl.idedyk.japanese.dictionary.api.keigo.KeigoHelper;
import pl.idedyk.japanese.dictionary.api.tools.KanaHelper;

public abstract class DictionaryManagerAbstract {
	
	protected IDatabaseConnector databaseConnector;
		
	public abstract KanaHelper getKanaHelper();
	
	public abstract KeigoHelper getKeigoHelper();
	
	public abstract List<TransitiveIntransitivePair> getTransitiveIntransitivePairsList() throws DictionaryException;
	
	public abstract void waitForDatabaseReady() throws DictionaryException;

	public int getWordGroupsNo(int groupSize) throws DictionaryException {
		
		waitForDatabaseReady();

		int dictionaryEntriesSize = databaseConnector.getDictionaryEntriesSize();

		int result = dictionaryEntriesSize / groupSize;

		if (dictionaryEntriesSize % groupSize > 0) {
			result++;
		}

		return result;
	}
	
	public List<DictionaryEntry> getWordsGroup(int groupSize, int groupNo) throws DictionaryException {
		
		waitForDatabaseReady();

		int dictionaryEntriesSize = databaseConnector.getDictionaryEntriesSize();

		List<DictionaryEntry> result = new ArrayList<DictionaryEntry>();

		for (int idx = groupNo * groupSize; idx < (groupNo + 1) * groupSize && idx < dictionaryEntriesSize; ++idx) {
			DictionaryEntry currentDictionaryEntry = databaseConnector.getDictionaryEntryById(String.valueOf(idx + 1));

			result.add(currentDictionaryEntry);
		}

		return result;
		
	}

	public List<DictionaryEntry> getWordsNameGroup(int groupSize, int groupNo) throws DictionaryException {
		
		waitForDatabaseReady();

		int dictionaryEntriesSize = databaseConnector.getDictionaryEntriesNameSize();

		List<DictionaryEntry> result = new ArrayList<DictionaryEntry>();

		for (int idx = groupNo * groupSize; idx < (groupNo + 1) * groupSize && idx < dictionaryEntriesSize; ++idx) {
			DictionaryEntry currentDictionaryEntry = databaseConnector.getDictionaryEntryNameById(String.valueOf(idx + 1));

			result.add(currentDictionaryEntry);
		}

		return result;
		
	}
	
	public FindWordResult findWord(final FindWordRequest findWordRequest) throws DictionaryException {
		
		waitForDatabaseReady();

		FindWordResult findWordResult = null;

		findWordResult = databaseConnector.findDictionaryEntries(findWordRequest);

		databaseConnector.findDictionaryEntriesInGrammaFormAndExamples(findWordRequest, findWordResult);		
		databaseConnector.findDictionaryEntriesInNames(findWordRequest, findWordResult);

		//

		final Map<String, KanaEntry> kanaCache = getKanaHelper().getKanaCache();
		
		final String findWordWithoutPolishChars = Utils.removePolishChars(findWordRequest.word);

		try {
			Collections.sort(findWordResult.result, new Comparator<ResultItem>() {
	
				@Override
				public int compare(ResultItem lhs, ResultItem rhs) {
					
					boolean lhsIsName = lhs.getDictionaryEntry().isName();
					boolean rhsIsName = rhs.getDictionaryEntry().isName();
					
					if (lhsIsName == false && rhsIsName == true) {
						return -1;
						
					} else if (lhsIsName == true && rhsIsName == false) {
						return 1;
					}
										
					String findWord = findWordRequest.word;
									
					String lhsKanji = lhs.getKanji();
					String rhsKanji = rhs.getKanji();
	
					if (lhsKanji != null && lhsKanji.equals(findWord) == true && rhsKanji != null && rhsKanji.equals(findWord) == false) {
						return -1;
					} else if (lhsKanji != null && lhsKanji.equals(findWord) == false && rhsKanji != null && rhsKanji.equals(findWord) == true) {
						return 1;
					}
					
					List<String> lhsKanaList = lhs.getKanaList();
					List<String> rhsKanaList = rhs.getKanaList();
	
					if (lhsKanaList.contains(findWord) == true && rhsKanaList.contains(findWord) == false) {
						return -1;
					} else if (lhsKanaList.contains(findWord) == false && rhsKanaList.contains(findWord) == true) {
						return 1;
					}
	
					List<String> lhsRomajiList = lhs.getRomajiList();
					
					boolean isInLhsRomajiList = false;
					
					for (String currentLhsRomajiList : lhsRomajiList) {
						if (currentLhsRomajiList.equalsIgnoreCase(findWord) == true) {
							isInLhsRomajiList = true;
							
							continue;
						}
					}
									
					List<String> rhsRomajiList = rhs.getRomajiList();
					
					boolean isInRhsRomajiList = false;
					
					for (String currentRhsRomajiList : rhsRomajiList) {
						if (currentRhsRomajiList.equalsIgnoreCase(findWord) == true) {
							isInRhsRomajiList = true;
							
							continue;
						}
					}
					
					if (isInLhsRomajiList == true && isInRhsRomajiList == false) {
						return -1;
					} else if (isInLhsRomajiList == false && isInRhsRomajiList == true) {
						return 1;
					}
					
					List<String> lhsTranslates = lhs.getTranslates();
	
					boolean islhsTranslates = false;
					
					for (String currentLhsTranslates : lhsTranslates) {
						if (Utils.removePolishChars(currentLhsTranslates).equalsIgnoreCase(findWordWithoutPolishChars) == true) {
							islhsTranslates = true;
							
							continue;
						}
					}
					
					List<String> rhsTranslates = rhs.getTranslates();
	
					boolean isRhsTranslates = false;
					
					for (String currentRhsTranslates : rhsTranslates) {
						if (Utils.removePolishChars(currentRhsTranslates).equalsIgnoreCase(findWordWithoutPolishChars) == true) {
							isRhsTranslates = true;
							
							continue;
						}
					}
					
					if (islhsTranslates == true && isRhsTranslates == false) {
						return -1;
					} else if (islhsTranslates == false && isRhsTranslates == true) {
						return 1;
					}
					
					boolean lhsContaintsCommon = lhs.getDictionaryEntry().getAttributeList().contains(AttributeType.COMMON_WORD);
					boolean rhsContaintsCommon = rhs.getDictionaryEntry().getAttributeList().contains(AttributeType.COMMON_WORD);
					
					if (lhsContaintsCommon == true && rhsContaintsCommon == false) {
						return -1;
						
					} else if (lhsContaintsCommon == false && rhsContaintsCommon == true) {
						return 1;
					}
					
					int maxKanaListArraySize = lhsKanaList.size();
	
					if (maxKanaListArraySize < rhsKanaList.size()) {
						maxKanaListArraySize = rhsKanaList.size();
					}
	
					for (int idx = 0; idx < maxKanaListArraySize; ++idx) {
						int compareResult = compare(lhsKanaList, rhsKanaList, idx);
	
						if (compareResult != 0) {
							return compareResult;
						}
					}
	
					return 0;
				}
	
				private int compare(List<String> lhsKanaList, List<String> rhsKanaList, int idx) {
					
					KanaHelper kanaHelper = getKanaHelper();
	
					String lhsString = getString(lhsKanaList, idx);
	
					String rhsString = getString(rhsKanaList, idx);
	
					if (lhsString == null && rhsString == null) {
						return 0;
					} else if (lhsString != null && rhsString == null) {
						return -1;
					} else if (lhsString == null && rhsString != null) {
						return 1;
					} else {
						String lhsRomaji = kanaHelper.createRomajiString(kanaHelper.convertKanaStringIntoKanaWord(
								lhsString, kanaCache, true));
						String rhsRomaji = kanaHelper.createRomajiString(kanaHelper.convertKanaStringIntoKanaWord(
								rhsString, kanaCache, true));
	
						return lhsRomaji.compareToIgnoreCase(rhsRomaji);
					}
				}
	
				private String getString(List<String> kanaList, int kanaListIdx) {
					if (kanaListIdx < kanaList.size()) {
						return kanaList.get(kanaListIdx);
					} else {
						return null;
					}
				}
			});

		} catch (IllegalArgumentException e) {
						
			StringBuffer errorMessage = new StringBuffer();
			
			errorMessage.append("Error during sort words. Debug info:\n\n");
			errorMessage.append("findWordRequest.word = ").append(findWordRequest.word).append("\n");
			errorMessage.append("findWordRequest.searchKanji = ").append(findWordRequest.searchKanji).append("\n");
			errorMessage.append("findWordRequest.searchKana = ").append(findWordRequest.searchKana).append("\n");
			errorMessage.append("findWordRequest.searchRomaji = ").append(findWordRequest.searchRomaji).append("\n");
			errorMessage.append("findWordRequest.searchTranslate = ").append(findWordRequest.searchTranslate).append("\n");
			errorMessage.append("findWordRequest.searchInfo = ").append(findWordRequest.searchInfo).append("\n");
			errorMessage.append("findWordRequest.searchMainDictionary = ").append(findWordRequest.searchMainDictionary).append("\n");
			errorMessage.append("findWordRequest.searchGrammaFormAndExamples = ").append(findWordRequest.searchGrammaFormAndExamples).append("\n");
			errorMessage.append("findWordRequest.wordPlaceSearch = ").append(findWordRequest.wordPlaceSearch).append("\n");
			errorMessage.append("findWordRequest.dictionaryEntryTypeList = ").append(findWordRequest.dictionaryEntryTypeList).append("\n");
			
			throw new IllegalArgumentException(errorMessage.toString(), e);
		}
			
		return findWordResult;
	}
	
	public int getDictionaryEntriesSize() throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getDictionaryEntriesSize();
	}

	public int getDictionaryEntriesNameSize() throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getDictionaryEntriesNameSize();
	}
	
	public DictionaryEntry getDictionaryEntryById(int id) throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getDictionaryEntryById(String.valueOf(id));		
	}
	
	public DictionaryEntry getDictionaryEntryNameById(int id) throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getDictionaryEntryNameById(String.valueOf(id));
	}
	
	public List<KanjiEntry> findKnownKanji(String text) throws DictionaryException {
		
		waitForDatabaseReady();

		List<KanjiEntry> result = new ArrayList<KanjiEntry>();

		for (int idx = 0; idx < text.length(); ++idx) {

			String currentChar = String.valueOf(text.charAt(idx));

			KanjiEntry kanjiEntry = databaseConnector.getKanjiEntry(currentChar);

			if (kanjiEntry != null) {
				result.add(kanjiEntry);
			}
		}

		return result;
	}

	public KanjiEntry findKanji(String kanji) throws DictionaryException {
		
		waitForDatabaseReady();

		KanjiEntry kanjiEntry = databaseConnector.getKanjiEntry(kanji);		

		return kanjiEntry;
	}

	public List<KanjiEntry> getAllKanjis(boolean withDetails, boolean onlyUsed) throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getAllKanjis(withDetails, onlyUsed);
	}
	
	public KanjiEntry getKanjiEntryById(int id) throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getKanjiEntryById(String.valueOf(id));
	}
	
	public abstract List<RadicalInfo> getRadicalList();
	
	public List<KanjiEntry> findKnownKanjiFromRadicals(String[] radicals) throws DictionaryException {
		
		waitForDatabaseReady();

		List<KanjiEntry> result = databaseConnector.findKanjiFromRadicals(radicals);

		Collections.sort(result, new Comparator<KanjiEntry>() {

			@Override
			public int compare(KanjiEntry lhs, KanjiEntry rhs) {

				int lhsId = lhs.getId();
				int rhsId = rhs.getId();

				if (lhsId < rhsId) {
					return -1;
				} else if (lhsId > rhsId) {
					return 1;
				} else {
					String lhsKanji = lhs.getKanji();
					String rhsKanji = rhs.getKanji();

					return lhsKanji.compareTo(rhsKanji);
				}
			}
		});

		return result;
	}

	public FindKanjiResult findKanjisFromStrokeCount(int from, int to) throws DictionaryException {
		
		waitForDatabaseReady();

		FindKanjiResult result = databaseConnector.findKanjisFromStrokeCount(from, to);

		Collections.sort(result.getResult(), new Comparator<KanjiEntry>() {

			@Override
			public int compare(KanjiEntry lhs, KanjiEntry rhs) {

				int lhsId = lhs.getId();
				int rhsId = rhs.getId();

				if (lhsId < rhsId) {
					return -1;
				} else if (lhsId > rhsId) {
					return 1;
				} else {
					String lhsKanji = lhs.getKanji();
					String rhsKanji = rhs.getKanji();

					return lhsKanji.compareTo(rhsKanji);
				}
			}
		});

		return result;
	}

	public Set<String> findAllAvailableRadicals(String[] radicals) throws DictionaryException {
		
		waitForDatabaseReady();

		return databaseConnector.findAllAvailableRadicals(radicals);
	}

	public FindKanjiResult findKanji(final FindKanjiRequest findKanjiRequest) throws DictionaryException {

		waitForDatabaseReady();
		
		FindKanjiResult findKanjiResult = databaseConnector.findKanji(findKanjiRequest);
		
		Collections.sort(findKanjiResult.getResult(), new Comparator<KanjiEntry>() {

			@Override
			public int compare(KanjiEntry lhs, KanjiEntry rhs) {
				
				String findWord = findKanjiRequest.word;
				
				String lhsKanji = lhs.getKanji();
				String rhsKanji = rhs.getKanji();

				if (lhsKanji != null && lhsKanji.equals(findWord) == true && rhsKanji != null && rhsKanji.equals(findWord) == false) {
					return -1;
				} else if (lhsKanji != null && lhsKanji.equals(findWord) == false && rhsKanji != null && rhsKanji.equals(findWord) == true) {
					return 1;
				}
									
				List<String> lhsPolishTranslates = lhs.getPolishTranslates();

				boolean islhsPolishTranslates = false;
				
				for (String currentLhsPolishTranslates : lhsPolishTranslates) {
					if (Utils.removePolishChars(currentLhsPolishTranslates).equalsIgnoreCase(findWord) == true) {
						islhsPolishTranslates = true;
						
						continue;
					}
				}
				
				List<String> rhsPolishTranslates = rhs.getPolishTranslates();

				boolean isRhsPolishTranslates = false;
				
				for (String currentRhsPolishTranslates : rhsPolishTranslates) {
					if (Utils.removePolishChars(currentRhsPolishTranslates).equalsIgnoreCase(findWord) == true) {
						isRhsPolishTranslates = true;
						
						continue;
					}
				}
				
				if (islhsPolishTranslates == true && isRhsPolishTranslates == false) {
					return -1;
				} else if (islhsPolishTranslates == false && isRhsPolishTranslates == true) {
					return 1;
				}

				int lhsId = lhs.getId();
				int rhsId = rhs.getId();

				if (lhsId < rhsId) {
					return -1;
				} else if (lhsId > rhsId) {
					return 1;
				} else {
					return lhsKanji.compareTo(rhsKanji);
				}
			}				
		});
		
		return findKanjiResult;		
	}
	
	public List<KanjivgEntry> getStrokePathsForWord(String word) throws DictionaryException {
		
		waitForDatabaseReady();

		List<KanjivgEntry> result = new ArrayList<KanjivgEntry>();

		if (word == null) {
			return result;
		}

		Map<String, KanaEntry> kanaCache = getKanaHelper().getKanaCache();

		for (int idx = 0; idx < word.length(); ++idx) {

			String currentChar = String.valueOf(word.charAt(idx));

			KanjiEntry kanjiEntry = databaseConnector.getKanjiEntry(currentChar);

			if (kanjiEntry != null) {
				result.add(kanjiEntry.getKanjivgEntry());
			} else {
				KanaEntry kanaEntry = kanaCache.get(currentChar);

				if (kanaEntry != null) {
					List<KanjivgEntry> strokePaths = kanaEntry.getStrokePaths();
					
					for (KanjivgEntry currentStrokePaths : strokePaths) {
						result.add(currentStrokePaths);
					}
				}
			}
		}

		return result;
	}

	public List<FuriganaEntry> getFurigana(DictionaryEntry dictionaryEntry) throws DictionaryException {
		
		waitForDatabaseReady();

		if (dictionaryEntry == null) {
			return null;
		}

		String kanji = dictionaryEntry.getKanji();

		if (kanji == null) {
			return null;
		}
		
		@SuppressWarnings("deprecation")
		List<String> kana = dictionaryEntry.getKanaList();

		List<FuriganaEntry> result = new ArrayList<FuriganaEntry>();

		for (String currentKana : kana) {
			List<FuriganaEntry> currentFurigana = getFurigana(kanji, currentKana);

			if (currentFurigana != null) {
				result.addAll(currentFurigana);
			} else {
				// Log.d("FuriganaError", kanji + " - " + currentKana);
			}
		}

		List<FuriganaEntry> newResult = new ArrayList<FuriganaEntry>();

		for (FuriganaEntry currentFuriganaEntry : result) {
			if (currentFuriganaEntry.matchKanaWithKanaPart() == true) {
				newResult.add(currentFuriganaEntry);
			}
		}

		result = newResult;

		if (result.size() == 0) {
			return null;
		}

		return result;
	}

	/*
	private void getFuriganaForAll() throws DictionaryException {

		int dictionaryEntriesSize = databaseConnector.getDictionaryEntriesSize();

		for (int dictionaryEntriesSizeIdx = 0; dictionaryEntriesSizeIdx < dictionaryEntriesSize; ++dictionaryEntriesSizeIdx) {

			DictionaryEntry nthDictionaryEntry = databaseConnector.getNthDictionaryEntry(dictionaryEntriesSizeIdx);

			getFurigana(nthDictionaryEntry);
		}
	}
	*/

	private List<FuriganaEntry> getFurigana(String kanji, String kana) throws DictionaryException {

		List<FuriganaEntry> furiganaEntries = new ArrayList<FuriganaEntry>();

		// start furigana
		FuriganaEntry startFuriganaEntry = new FuriganaEntry(kanji, kana);

		furiganaEntries.add(startFuriganaEntry);

		for (int idx = 0; idx < kanji.length(); ++idx) {

			String currentChar = String.valueOf(kanji.charAt(idx));

			KanjiEntry kanjiEntry = databaseConnector.getKanjiEntry(currentChar);

			if (kanjiEntry == null) { // if hiragana

				List<FuriganaEntry> newFuriganaEntries = new ArrayList<FuriganaEntry>();

				for (FuriganaEntry furiganaEntry : furiganaEntries) {

					FuriganaEntry newFuriganaEntry = furiganaEntry.createCopy();

					boolean removeCharsFromCurrentKanaStateResult = newFuriganaEntry.removeCharsFromCurrentKanaState(1);

					if (removeCharsFromCurrentKanaStateResult == true) {
						newFuriganaEntry.addHiraganaChar(currentChar);

						newFuriganaEntries.add(newFuriganaEntry);
					}
				}

				furiganaEntries = newFuriganaEntries;

				continue;
			}

			List<String> kanjiReading = normalizeKanjiReading(kanjiEntry);

			List<FuriganaEntry> newFuriganaEntries = new ArrayList<FuriganaEntry>();

			for (String currentKanjiReading : kanjiReading) {

				for (FuriganaEntry currentFuriganaEntry : furiganaEntries) {

					String currentKanaState = currentFuriganaEntry.getCurrentKanaState();

					if (currentKanaState.startsWith(currentKanjiReading) == true) { // match

						FuriganaEntry newCurrentFuriganaEntry = currentFuriganaEntry.createCopy();

						boolean removeCharsFromCurrentKanaStateResult = newCurrentFuriganaEntry
								.removeCharsFromCurrentKanaState(currentKanjiReading.length());

						if (removeCharsFromCurrentKanaStateResult == true) {
							newCurrentFuriganaEntry.addReading(kanjiEntry.getKanji(), currentKanjiReading);

							newFuriganaEntries.add(newCurrentFuriganaEntry);
						}
					}
				}
			}

			furiganaEntries = newFuriganaEntries;
		}

		if (furiganaEntries.size() == 0) {

			FuriganaEntry furiganaEntry = new FuriganaEntry(kanji, kana);

			furiganaEntry.addReading(kanji, kana);

			furiganaEntries.add(furiganaEntry);
		}

		return furiganaEntries;
	}

	private List<String> normalizeKanjiReading(KanjiEntry kanjiEntry) {

		List<String> result = new ArrayList<String>();

		KanjiDic2Entry kanjiDic2Entry = kanjiEntry.getKanjiDic2Entry();

		if (kanjiDic2Entry == null) {
			return result;
		}

		List<String> kunReading = kanjiDic2Entry.getKunReading();

		if (kunReading != null && kunReading.size() > 0) {

			for (String currentKunReading : kunReading) {

				List<String> readingVariantList = getReadingVariant(currentKunReading);

				if (readingVariantList != null) {
					result.addAll(readingVariantList);
				}
			}
		}

		List<String> onReading = kanjiDic2Entry.getOnReading();

		if (onReading != null && onReading.size() > 0) {

			for (String currentOnReading : onReading) {

				List<String> readingVariantList = getReadingVariant(currentOnReading);

				List<String> readingVariantList2 = new ArrayList<String>();

				if (readingVariantList != null) {
					for (String currentReadingVariant : readingVariantList) {
						String hiraganaString = getKanaHelper().convertKatakanaToHiragana(currentReadingVariant);

						if (hiraganaString != null) {
							readingVariantList2.add(hiraganaString);
						}
					}
				}

				if (readingVariantList2 != null) {
					result.addAll(readingVariantList2);
				}
			}
		}

		// generate additional

		Map<String, String[]> generateAdditionalMap = new HashMap<String, String[]>();

		generateAdditionalMap.put("か", new String[] { "が" });
		generateAdditionalMap.put("き", new String[] { "ぎ" });
		generateAdditionalMap.put("く", new String[] { "ぐ" });
		generateAdditionalMap.put("け", new String[] { "げ" });
		generateAdditionalMap.put("こ", new String[] { "ご" });

		generateAdditionalMap.put("さ", new String[] { "ざ" });
		generateAdditionalMap.put("し", new String[] { "じ" });
		generateAdditionalMap.put("す", new String[] { "ず" });
		generateAdditionalMap.put("せ", new String[] { "ぜ" });
		generateAdditionalMap.put("そ", new String[] { "ぞ" });

		generateAdditionalMap.put("た", new String[] { "だ" });
		generateAdditionalMap.put("ち", new String[] { "ぢ" });
		generateAdditionalMap.put("つ", new String[] { "づ" });
		generateAdditionalMap.put("て", new String[] { "で" });
		generateAdditionalMap.put("と", new String[] { "ど" });

		generateAdditionalMap.put("は", new String[] { "ば", "ぱ" });
		generateAdditionalMap.put("ひ", new String[] { "び", "ぴ" });
		generateAdditionalMap.put("ふ", new String[] { "ぶ", "ぷ" });
		generateAdditionalMap.put("へ", new String[] { "べ", "ぺ" });
		generateAdditionalMap.put("ほ", new String[] { "ぼ", "ぽ" });

		List<String> newResult = new ArrayList<String>();

		for (String currentResult : result) {

			if (currentResult.length() == 0) {
				continue;
			}

			String currentResultFirstCgar = String.valueOf(currentResult.charAt(0));

			String[] additionalValues = generateAdditionalMap.get(currentResultFirstCgar);

			if (additionalValues == null) {
				newResult.add(currentResult);
			} else {
				newResult.add(currentResult);

				for (String currentAdditionalValue : additionalValues) {
					newResult.add(currentAdditionalValue + currentResult.substring(1));
				}
			}
		}

		TreeSet<String> treeSet = new TreeSet<String>(newResult);

		return new ArrayList<String>(treeSet);
	}

	private List<String> getReadingVariant(String reading) {

		if (reading == null) {
			return null;
		}

		boolean nextStep = true;

		List<String> result = new ArrayList<String>();

		{
			String reading1 = reading.trim();

			if (reading1.length() == 0) {
				nextStep = false;
			}

			if (nextStep == true) {

				int dotIdx = reading1.indexOf(".");

				if (dotIdx != -1) {
					reading1 = reading1.substring(0, dotIdx);
				}

				reading1 = reading1.trim();

				if (reading1.startsWith("-") == true) {
					reading1 = reading1.substring(1);
				}

				reading1 = reading1.trim();

				if (reading1.endsWith("-") == true) {
					reading1 = reading1.substring(0, reading1.length() - 1);
				}

				reading1 = reading1.trim();

				if (reading1.length() == 0) {
					nextStep = false;
				}

				if (nextStep == true) {
					result.add(reading1);
				}
			}
		}

		{
			String reading2 = reading.trim();

			reading2 = reading2.replaceAll("\\.", "");

			reading2 = reading2.trim();

			if (reading2.startsWith("-") == true) {
				reading2 = reading2.substring(1);
			}

			reading2 = reading2.trim();

			result.add(reading2);
		}

		return result;
	}
	
	public List<GroupEnum> getDictionaryEntryGroupTypes() throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getDictionaryEntryGroupTypes();
	}

	public List<DictionaryEntry> getGroupDictionaryEntries(GroupEnum groupName) throws DictionaryException {
		
		waitForDatabaseReady();

		return databaseConnector.getGroupDictionaryEntries(groupName);
	}
	
	public GroupWithTatoebaSentenceList getTatoebaSentenceGroup(String groupId) throws DictionaryException {
		
		waitForDatabaseReady();
		
		return databaseConnector.getTatoebaSentenceGroup(groupId);
	}
	
	public TranslateJapaneseSentenceResult translateJapaneseSentenceTEST(String sentence) throws DictionaryException {
		
		TranslateJapaneseSentenceResult result = new TranslateJapaneseSentenceResult();
		
		//
		
		final int maxStartIdx = 0;
		final int maxEndIdx = sentence.length();
		
		int currentStartIdx = maxStartIdx;
		int currentEndIdx = maxEndIdx;
		
		int currentUnknownStartIdx = -1;
		int currentUnknownEndIdx = -1;
		
		while (true) {
			
			if (currentStartIdx == maxEndIdx) {
				break;
			}
			
			// robimy ciecie zdania
			String substringToCheck = substringSentence(sentence, currentStartIdx, currentEndIdx);
						
			if (substringToCheck.equals("") == true) { // nie udalo sie nic znalesc
				
				// ustawiamy, ze ten znak bedzie poczatkiem nieznanego fragmentu				
				if (currentUnknownStartIdx == -1) {
					currentUnknownStartIdx = currentStartIdx;
				}
								
				// przesuwamy poczatek o jeden, moze uda sie cos innego znalezc				
				currentStartIdx++;
				currentEndIdx = maxEndIdx;
				
				continue;
			}
						
			// wyszukujemy, czy ten uciety kawalek znajduje sie w slowniku
			FindWordRequest findWordRequest = createFindWordRequestForTranslateJapaneseSentence(substringToCheck);
			
			// wyszukujemy
			FindWordResult findWordResult = findWord(findWordRequest);
			
			// sprawdzamy odpowiedz
			if (findWordResult.result.size() != 0) { // mamy cos
				
				// czy wczesniej byly jakiej nieznane znaki, jesli tak to dodajemy je do listy
				if (currentUnknownStartIdx != -1) {
										
					currentUnknownEndIdx = currentStartIdx;
					
					//
					
					String unknownWord = substringSentence(sentence, currentUnknownStartIdx, currentUnknownEndIdx);
										
					result.addToken(TokenType.UNKNOWN, unknownWord, currentUnknownStartIdx, currentUnknownEndIdx, null);
					
					currentUnknownStartIdx = -1;
					currentUnknownEndIdx = -1;
				}
								
				// wstawic znaleziony kawalek do wyniku				
				result.addToken(TokenType.FOUND, substringToCheck, currentStartIdx, currentEndIdx, findWordResult);

				//
				
				currentStartIdx = currentEndIdx;
				currentEndIdx = maxEndIdx;				
				
			} else { // nic nie znalezlismy				
				currentEndIdx--;
			}			
		}
		
		if (currentUnknownStartIdx != -1) {
			
			currentUnknownEndIdx = currentStartIdx;
			
			// czy wczesniej byly jakiej nieznane znaki, jesli tak to dodajemy je do listy			
			String unknownWord = substringSentence(sentence, currentUnknownStartIdx, currentUnknownEndIdx);
						
			result.addToken(TokenType.UNKNOWN, unknownWord, currentUnknownStartIdx, currentUnknownEndIdx, null);

			currentUnknownStartIdx = -1;
			currentUnknownEndIdx = -1;
		}
		
		return result;
	}
	
	public abstract WordPowerList getWordPowerList() throws DictionaryException;
	
	private String substringSentence(String sentence, int startIdx, int endIdx) {		
		return sentence.substring(startIdx, endIdx);
	}
	
	private FindWordRequest createFindWordRequestForTranslateJapaneseSentence(String word) {
		
		FindWordRequest findWordRequest = new FindWordRequest();
		
		findWordRequest.word = word;
		
		findWordRequest.searchKanji = true;		
		findWordRequest.searchKana = true;		
		findWordRequest.searchRomaji = true;
		
		findWordRequest.searchTranslate = false;		
		findWordRequest.searchInfo = false;
		
		findWordRequest.searchOnlyCommonWord = false;
		
		findWordRequest.searchMainDictionary = true;
		findWordRequest.searchGrammaFormAndExamples = true;
		findWordRequest.searchName = true;
		
		findWordRequest.wordPlaceSearch = WordPlaceSearch.EXACT;
		
		return findWordRequest;
	}
}
