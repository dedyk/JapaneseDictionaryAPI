package pl.idedyk.japanese.dictionary.api.grammaexample.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pl.idedyk.japanese.dictionary.api.dto.AttributeList;
import pl.idedyk.japanese.dictionary.api.dto.AttributeType;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary2.api.helper.Dictionary2HelperCommon.KanjiKanaPair;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.MiscInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfoAttributeListInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfoEntriesInfo;

public abstract class GrammaExampleWrapperEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// stary slownik
	private DictionaryEntry dictionaryEntry;
	
	// nowy format
	private KanjiKanaPair kanjiKanaPair;
	
	protected GrammaExampleWrapperEntry(DictionaryEntry dictionaryEntry) {
		this.dictionaryEntry = dictionaryEntry;
	}
	
	protected GrammaExampleWrapperEntry(KanjiKanaPair kanjiKanaPair) {
		this.kanjiKanaPair = kanjiKanaPair;		
	}

	public List<DictionaryEntryType> getDictionaryEntryTypeList() {
		
		if (dictionaryEntry != null) {
			return dictionaryEntry.getDictionaryEntryTypeList();
		}
		
		if (kanjiKanaPair != null) { // musza byc zapisane do Entry dane ze starego slownika, aby to dzialalo; standardowy Entry nie zadziala
			MiscInfo misc = kanjiKanaPair.getEntry().getMisc();
			
			if (misc != null) {
				OldPolishJapaneseDictionaryInfo oldPolishJapaneseDictionary = misc.getOldPolishJapaneseDictionary();
				
				if (oldPolishJapaneseDictionary != null) {
					
					// szukamy wpisu w stary slowniku
					List<OldPolishJapaneseDictionaryInfoEntriesInfo> oldPolishJapaneseDictionaryInfoEntries = oldPolishJapaneseDictionary.getEntries();
					
					for (OldPolishJapaneseDictionaryInfoEntriesInfo oldPolishJapaneseDictionaryInfoEntry : oldPolishJapaneseDictionaryInfoEntries) {
						
						String kanjiKanaPairKanji = kanjiKanaPair.getKanji() != null ? kanjiKanaPair.getKanji() : "";
						String kanjiKanaPairKana = kanjiKanaPair.getKana() != null ? kanjiKanaPair.getKana() : "";
						
						String oldPolishJapaneseDictionaryInfoEntryKanji = oldPolishJapaneseDictionaryInfoEntry.getKanji() != null ? oldPolishJapaneseDictionaryInfoEntry.getKanji() : "";
						String oldPolishJapaneseDictionaryInfoEntryKana = oldPolishJapaneseDictionaryInfoEntry.getKana() != null ? oldPolishJapaneseDictionaryInfoEntry.getKana() : "";
						
						if (kanjiKanaPairKanji.equals(oldPolishJapaneseDictionaryInfoEntryKanji) == true && kanjiKanaPairKana.equals(oldPolishJapaneseDictionaryInfoEntryKana) == true) { // mamy odpowiedni wpis
							
							// pobieramy sklejona liste typow
							String dictionaryEntryTypeAsStringList = oldPolishJapaneseDictionaryInfoEntry.getDictionaryEntryTypeList();
							
							// rozdzielamy i zamieniamy na liste enum-ow
							return Arrays.asList(dictionaryEntryTypeAsStringList.split(",")).stream().map(m -> DictionaryEntryType.valueOf(m)).collect(Collectors.toList());
						}
					}
				}				
			}
		}		
		
		// to nigdy nie powinno zdarzyc sie, a jesli zdarzylo sie, to znaczy, ze slownik nie zostal w pelni zsynchronizowany
		return Arrays.asList(DictionaryEntryType.UNKNOWN);
	}
	
	public DictionaryEntryType getDictionaryEntryType() {
		// FM_FIXME: byc moze to jeszcze bedzie do zmiany - do sprawdzenia - byc moze do usuniecia
		return getDictionaryEntryTypeList().get(0);
	}

	public String getPrefixKana() {
		
		if (dictionaryEntry != null) {
			return dictionaryEntry.getPrefixKana();
		}
		
		if (kanjiKanaPair != null) {
			return null;
		}
		
		throw new RuntimeException();
	}

	public String getPrefixRomaji() {
		
		if (dictionaryEntry != null) {
			return dictionaryEntry.getPrefixRomaji();
		}
		
		if (kanjiKanaPair != null) {
			return null;
		}
		
		throw new RuntimeException();
	}

	public String getKanji() {
		
		if (dictionaryEntry != null) {
			return dictionaryEntry.getKanji();
		}
		
		if (kanjiKanaPair != null) {
			return kanjiKanaPair.getKanji();
		}
		
		throw new RuntimeException();
	}

	@SuppressWarnings("deprecation")
	public List<String> getKanaList() {
		
		if (dictionaryEntry != null) {
			return dictionaryEntry.getKanaList();
		}
		
		if (kanjiKanaPair != null) {
			return Arrays.asList(kanjiKanaPair.getKana());
		}
		
		throw new RuntimeException();
	}
	
	public String getKana() {
		return getKanaList().get(0);
	}

	@SuppressWarnings("deprecation")
	public List<String> getRomajiList() {
		
		if (dictionaryEntry != null) {
			return dictionaryEntry.getRomajiList();
		}
		
		if (kanjiKanaPair != null) {
			return Arrays.asList(kanjiKanaPair.getRomaji());
		}
		
		throw new RuntimeException();
	}

	public String getRomaji() {
		return getRomajiList().get(0);
	}

	public AttributeList getAttributeList() {
		if (dictionaryEntry != null) {
			return dictionaryEntry.getAttributeList();
		}
		
		if (kanjiKanaPair != null) { // musza byc zapisane do Entry dane ze starego slownika, aby to dzialalo; standardowy Entry nie zadziala
			MiscInfo misc = kanjiKanaPair.getEntry().getMisc();
			
			if (misc != null) {
				OldPolishJapaneseDictionaryInfo oldPolishJapaneseDictionary = misc.getOldPolishJapaneseDictionary();
				
				if (oldPolishJapaneseDictionary != null) {
					List<OldPolishJapaneseDictionaryInfoAttributeListInfo> oldPolishJapaneseDictionaryAttributeList = oldPolishJapaneseDictionary.getAttributeList();
					
					AttributeList attributeList = new AttributeList();
					
					for (OldPolishJapaneseDictionaryInfoAttributeListInfo oldPolishJapaneseDictionaryInfoAttributeListInfo : oldPolishJapaneseDictionaryAttributeList) {
						attributeList.addAttributeValue(AttributeType.valueOf(oldPolishJapaneseDictionaryInfoAttributeListInfo.getType()), oldPolishJapaneseDictionaryInfoAttributeListInfo.getValue());
					}					
					
					return attributeList;
				}
			}
		}
		
		throw new RuntimeException();
	}
}
