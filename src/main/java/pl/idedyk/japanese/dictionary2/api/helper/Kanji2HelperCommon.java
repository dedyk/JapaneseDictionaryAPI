package pl.idedyk.japanese.dictionary2.api.helper;

import pl.idedyk.japanese.dictionary2.kanjidic2.xsd.KenteiLevelType;

public class Kanji2HelperCommon {
	
	public static String translateToPolishGlossType(KenteiLevelType kenteiLevelType) {
		
		if (kenteiLevelType == null) {
			return null;
		}
		
		switch (kenteiLevelType) {
		case LEVEL_10:
			return "poziom 10";
		
		case LEVEL_9:
			return "poziom 9";

		case LEVEL_8:
			return "poziom 8";

		case LEVEL_7:
			return "poziom 7";

		case LEVEL_6:
			return "poziom 6";

		case LEVEL_5:
			return "poziom 5";

		case LEVEL_4:
			return "poziom 4";

		case LEVEL_3:
			return "poziom 3";

		case LEVEL_PRE_2:
			return "poziom 2 (wstępny)";

		case LEVEL_2:
			return "poziom 2";

		case LEVEL_PRE_1:
			return "1 (wstępny)";

		case LEVEL_1:
			return "poziom 1";
			
			default:
				throw new RuntimeException("Unknown kentei level type: " + kenteiLevelType);
		}
	}
}
