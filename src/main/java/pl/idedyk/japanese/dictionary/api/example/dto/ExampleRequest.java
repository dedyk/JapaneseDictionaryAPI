package pl.idedyk.japanese.dictionary.api.example.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import pl.idedyk.japanese.dictionary.api.dto.AttributeList;
import pl.idedyk.japanese.dictionary.api.dto.AttributeType;
import pl.idedyk.japanese.dictionary.api.dto.DictionaryEntry;
import pl.idedyk.japanese.dictionary2.api.helper.Dictionary2HelperCommon.KanjiKanaPair;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.MiscInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfo;
import pl.idedyk.japanese.dictionary2.jmdict.xsd.OldPolishJapaneseDictionaryInfoAttributeListInfo;

public class ExampleRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// stary slownik
	private DictionaryEntry dictionaryEntry;
	
	// nowy format
	private KanjiKanaPair kanjiKanaPair;

	public ExampleRequest(DictionaryEntry dictionaryEntry) {
		this.dictionaryEntry = dictionaryEntry;
	}
	
	public ExampleRequest(KanjiKanaPair kanjiKanaPair) {
		this.kanjiKanaPair = kanjiKanaPair;		
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
