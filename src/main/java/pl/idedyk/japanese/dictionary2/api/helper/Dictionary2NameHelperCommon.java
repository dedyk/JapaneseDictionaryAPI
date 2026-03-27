package pl.idedyk.japanese.dictionary2.api.helper;

import java.util.ArrayList;
import java.util.List;

import pl.idedyk.japanese.dictionary2.jmnedict.xsd.TranslationalInfoTransDet;
import pl.idedyk.japanese.dictionary2.jmnedict.xsd.TranslationalInfoTransDetAdditionalInfo;

public class Dictionary2NameHelperCommon {
	
	public static List<TranslationalInfoTransDet> getEnglishOrPolishTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList) {
		
		// pobieramy polskie (moze nie byc)
		List<TranslationalInfoTransDet> polishTranslationalInfoTransDet = getPolishTranslationalInfoTransDet(translationalInfoTransDetList);
		
		if (polishTranslationalInfoTransDet != null && polishTranslationalInfoTransDet.size() > 0) {
			return polishTranslationalInfoTransDet;
		}
		
		// pobieramy angielskie znaczenie (zawsze musi istniec
		return getEnglishTranslationalInfoTransDet(translationalInfoTransDetList);
	}
	
	public static List<TranslationalInfoTransDet> getPolishTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList) {
		return getLangTranslationalInfoTransDet(translationalInfoTransDetList, "pol");		
	}

	public static List<TranslationalInfoTransDet> getEnglishTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList) {
		return getLangTranslationalInfoTransDet(translationalInfoTransDetList, "eng");		
	}
	
	private static List<TranslationalInfoTransDet> getLangTranslationalInfoTransDet(List<TranslationalInfoTransDet> translationalInfoTransDetList, String lang) {
		
		List<TranslationalInfoTransDet> result = new ArrayList<>();
		
		for (TranslationalInfoTransDet translationalInfoTransDet : translationalInfoTransDetList) {
			if (translationalInfoTransDet.getLang().equals(lang) == true) {
				result.add(translationalInfoTransDet);
			}
		}
		
		return result;
	}

	public static TranslationalInfoTransDetAdditionalInfo getFirstEnglishOrPolishTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList) {
		TranslationalInfoTransDetAdditionalInfo firstPolishTranslationalInfoTransDetAdditionalInfo = getFirstPolishTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList);
		
		if (firstPolishTranslationalInfoTransDetAdditionalInfo != null) {
			return firstPolishTranslationalInfoTransDetAdditionalInfo;
		}
		
		return getFirstEngTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList);
	}
	
	public static TranslationalInfoTransDetAdditionalInfo getFirstPolishTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList) {
		return getFirstLangTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList, "pol");
	}

	public static TranslationalInfoTransDetAdditionalInfo getFirstEngTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList) {
		return getFirstLangTranslationalInfoTransDetAdditionalInfo(translationalInfoTransDetAdditionalInfoList, "eng");
	}
	
	private static TranslationalInfoTransDetAdditionalInfo getFirstLangTranslationalInfoTransDetAdditionalInfo(List<TranslationalInfoTransDetAdditionalInfo> translationalInfoTransDetAdditionalInfoList, String lang) {
		
		for (TranslationalInfoTransDetAdditionalInfo translationalInfoTransDetAdditionalInfo : translationalInfoTransDetAdditionalInfoList) {
			if (translationalInfoTransDetAdditionalInfo.getLang().equals(lang) == true) {
				return translationalInfoTransDetAdditionalInfo;
			}
		}
		
		return null;
	}
}
