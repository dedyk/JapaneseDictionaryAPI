package pl.idedyk.japanese.dictionary.api.grammaexample.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pl.idedyk.japanese.dictionary.api.dto.AttributeList;
import pl.idedyk.japanese.dictionary.api.dto.AttributeType;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntryType;
import pl.idedyk.japanese.dictionary2.api.helper.Dictionary2HelperCommon.KanjiKanaPair;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.MiscInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfoAttributeListInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfoEntriesInfo;

public class KanjiKanaPairGrammaExampleWrapperEntry implements IGrammaExampleWrapperEntry, Serializable {

	private static final long serialVersionUID = 1L;
		
	// nowy format
	private KanjiKanaPair kanjiKanaPair;
		
	protected KanjiKanaPairGrammaExampleWrapperEntry(KanjiKanaPair kanjiKanaPair) {
		this.kanjiKanaPair = kanjiKanaPair;		
	}
	
	@Override
	public List<GrammaExampleType> getGrammaExampleTypeList() {
		return new ArrayList<>(); // to jest glowny poziom, wiec nie ma zadnego typu
	}

	@Override
	public List<DictionaryEntryType> getDictionaryEntryTypeList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
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
				
		// to nigdy nie powinno zdarzyc sie, a jesli zdarzylo sie, to znaczy, ze slownik nie zostal w pelni zsynchronizowany
		return Arrays.asList(DictionaryEntryType.UNKNOWN);
	}
	
	@Override
	public DictionaryEntryType getDictionaryEntryType() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
		return getDictionaryEntryTypeList().get(0);
	}

	@Override
	public String getPrefixKana() {
		return null;
	}

	@Override
	public String getPrefixRomaji() {
		return null;
	}

	@Override
	public String getKanji() {
		return kanjiKanaPair.getKanji();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> getKanaList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
		return Arrays.asList(kanjiKanaPair.getKana());
	}
	
	@Override
	public String getKana() {
		return getKanaList().get(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> getRomajiList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
		
		return Arrays.asList(kanjiKanaPair.getRomaji());
	}

	@Override
	public String getRomaji() {
		return getRomajiList().get(0);
	}

	public AttributeList getAttributeList() {
		int fixme = 1; // FM_FIXME: do zastanowienia sie co z tym
				
		// musza byc zapisane do Entry dane ze starego slownika, aby to dzialalo; standardowy Entry nie zadziala
		AttributeList attributeList = new AttributeList();
		
		MiscInfo misc = kanjiKanaPair.getEntry().getMisc();
		
		if (misc != null) {
			OldPolishJapaneseDictionaryInfo oldPolishJapaneseDictionary = misc.getOldPolishJapaneseDictionary();
			
			if (oldPolishJapaneseDictionary != null) {
				List<OldPolishJapaneseDictionaryInfoAttributeListInfo> oldPolishJapaneseDictionaryAttributeList = oldPolishJapaneseDictionary.getAttributeList();
				
				for (OldPolishJapaneseDictionaryInfoAttributeListInfo oldPolishJapaneseDictionaryInfoAttributeListInfo : oldPolishJapaneseDictionaryAttributeList) {
					attributeList.addAttributeValue(AttributeType.valueOf(oldPolishJapaneseDictionaryInfoAttributeListInfo.getType()), oldPolishJapaneseDictionaryInfoAttributeListInfo.getValue());
				}					
			}
		}
		
		return attributeList;
	}
}
