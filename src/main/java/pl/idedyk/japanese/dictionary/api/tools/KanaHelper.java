package pl.idedyk.japanese.dictionary.api.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.dto.KanaEntry;
import pl.idedyk.japanese.dictionary.api.dto.KanaEntry.KanaGroup;
import pl.idedyk.japanese.dictionary.api.dto.KanaEntry.KanaType;
import pl.idedyk.japanese.dictionary.api.dto.KanjivgEntry;
import pl.idedyk.japanese.dictionary.api.exception.DictionaryException;

public class KanaHelper {

	private List<KanaEntry> hiraganaEntries = null;

	private List<KanaEntry> katakanaEntries = null;

	private List<KanaEntry> additionalEntries = null;

	public KanaHelper() {
		
		getAllHiraganaKanaEntries();
		getAllKatakanaKanaEntries();
		getAllAdditionalKanaEntries();		
	}
	
	public KanaHelper(Map<String, List<KanjivgEntry>> kanaAndStrokePaths) {

		getAllHiraganaKanaEntries();
		setHiraganaStrokePaths(kanaAndStrokePaths);

		getAllKatakanaKanaEntries();
		setKatakanaStrokePaths(kanaAndStrokePaths);

		getAllAdditionalKanaEntries();
		setAdditionalStrokePaths(kanaAndStrokePaths);
	}

	public String createKanaString(KanaWord kanaWord) {

		StringBuffer sb = new StringBuffer();

		for (KanaEntry kanaEntry : kanaWord.kanaEntries) {
			sb.append(kanaEntry.getKanaJapanese());
		}

		return sb.toString();
	}

	public String createRomajiString(KanaWord kanaWord) {

		StringBuffer sb = new StringBuffer();

		boolean repeat = false;
		boolean lastN = false;

		for (KanaEntry kanaEntry : kanaWord.kanaEntries) {
			String kana = kanaEntry.getKana();

			if (lastN == true && kana.startsWith("e") == true) {
				sb.append("'");

				lastN = false;

			} else if (lastN == true
					&& (kana.startsWith("y") == true || kana.equals("i") == true || kana.equals("a") == true
							|| kana.equals("u") == true || kana.equals("o") == true)) {
				sb.append("'");

				lastN = true;
			} else {
				lastN = false;
			}

			if (kana.equals("ttsu") == true) {
				repeat = true;

				continue;
			}

			if (kana.equals("ttsu2") == true && sb.length() > 0) {
				sb.append(sb.charAt(sb.length() - 1));

				continue;
				
			} else if (kana.equals("ttsu2") == true && sb.length() == 0) {
				sb.append("ー");
				
				continue;
			}

			if (repeat == true) {
				repeat = false;

				sb.append(kana.charAt(0));
			}

			sb.append(kana);

			if (kana.equals("n") == true) {
				lastN = true;
			} else {
				lastN = false;
			}
		}

		return sb.toString();
	}

	public List<KanaEntry> getAllHiraganaKanaEntries() {

		if (hiraganaEntries != null) {
			return hiraganaEntries;
		}

		hiraganaEntries = new ArrayList<KanaEntry>();

		hiraganaEntries.add(new KanaEntry("あ", "a", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("い", "i", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("う", "u", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("え", "e", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("お", "o", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("か", "ka", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("き", "ki", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("く", "ku", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("け", "ke", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("こ", "ko", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("さ", "sa", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("し", "shi", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("す", "su", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("せ", "se", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("そ", "so", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("た", "ta", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ち", "chi", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("つ", "tsu", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("て", "te", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("と", "to", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("な", "na", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("に", "ni", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ぬ", "nu", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ね", "ne", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("の", "no", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("は", "ha", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ひ", "hi", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ふ", "fu", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("へ", "he", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ほ", "ho", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("ま", "ma", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("み", "mi", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("む", "mu", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("め", "me", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("も", "mo", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("や", "ya", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("𛀆", "yi", KanaType.HIRAGANA, KanaGroup.GOJUUON, false));
		hiraganaEntries.add(new KanaEntry("ゆ", "yu", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("𛀁", "ye", KanaType.HIRAGANA, KanaGroup.GOJUUON, false));
		hiraganaEntries.add(new KanaEntry("よ", "yo", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("ら", "ra", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("り", "ri", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("る", "ru", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("れ", "re", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ろ", "ro", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("わ", "wa", KanaType.HIRAGANA, KanaGroup.GOJUUON));
		hiraganaEntries.add(new KanaEntry("ゐ", "wi", KanaType.HIRAGANA, KanaGroup.GOJUUON, false));
		hiraganaEntries.add(new KanaEntry("ゑ", "we", KanaType.HIRAGANA, KanaGroup.GOJUUON, false));
		hiraganaEntries.add(new KanaEntry("を", "wo", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("ん", "n", KanaType.HIRAGANA, KanaGroup.GOJUUON));

		hiraganaEntries.add(new KanaEntry("が", "ga", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぎ", "gi", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぐ", "gu", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("げ", "ge", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ご", "go", KanaType.HIRAGANA, KanaGroup.DAKUTEN));

		hiraganaEntries.add(new KanaEntry("ざ", "za", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("じ", "ji", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ず", "zu", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぜ", "ze", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぞ", "zo", KanaType.HIRAGANA, KanaGroup.DAKUTEN));

		hiraganaEntries.add(new KanaEntry("だ", "da", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぢ", "di", "ji (di)", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("づ", "du", "zu (du)", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("で", "de", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ど", "do", KanaType.HIRAGANA, KanaGroup.DAKUTEN));

		hiraganaEntries.add(new KanaEntry("ば", "ba", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("び", "bi", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぶ", "bu", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("べ", "be", KanaType.HIRAGANA, KanaGroup.DAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぼ", "bo", KanaType.HIRAGANA, KanaGroup.DAKUTEN));

		hiraganaEntries.add(new KanaEntry("ぱ", "pa", KanaType.HIRAGANA, KanaGroup.HANDAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぴ", "pi", KanaType.HIRAGANA, KanaGroup.HANDAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぷ", "pu", KanaType.HIRAGANA, KanaGroup.HANDAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぺ", "pe", KanaType.HIRAGANA, KanaGroup.HANDAKUTEN));
		hiraganaEntries.add(new KanaEntry("ぽ", "po", KanaType.HIRAGANA, KanaGroup.HANDAKUTEN));

		hiraganaEntries.add(new KanaEntry("きゃ", "kya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("きゅ", "kyu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("きょ", "kyo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("しゃ", "sha", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("しゅ", "shu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("しょ", "sho", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("ちゃ", "cha", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ちゅ", "chu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ちょ", "cho", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("にゃ", "nya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("にゅ", "nyu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("にょ", "nyo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("ひゃ", "hya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ひゅ", "hyu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ひょ", "hyo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("みゃ", "mya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("みゅ", "myu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("みょ", "myo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("りゃ", "rya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("りゅ", "ryu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("りょ", "ryo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("ぎゃ", "gya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ぎゅ", "gyu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ぎょ", "gyo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("じゃ", "ja", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("じゅ", "ju", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("じょ", "jo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("びゃ", "bya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("びゅ", "byu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("びょ", "byo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("ぴゃ", "pya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ぴゅ", "pyu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ぴょ", "pyo", KanaType.HIRAGANA, KanaGroup.YOUON));

		hiraganaEntries.add(new KanaEntry("ぢゃ", "dya", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ぢゅ", "dyu", KanaType.HIRAGANA, KanaGroup.YOUON));
		hiraganaEntries.add(new KanaEntry("ぢょ", "dyo", KanaType.HIRAGANA, KanaGroup.YOUON));
		
		hiraganaEntries.add(new KanaEntry("ゔ", "vu", KanaType.HIRAGANA, KanaGroup.DAKUTEN, false));
		
		hiraganaEntries.add(new KanaEntry("っ", "ttsu", KanaType.HIRAGANA, KanaGroup.SOKUON));

		hiraganaEntries = Collections.unmodifiableList(hiraganaEntries);

		return hiraganaEntries;
	}

	private void setHiraganaStrokePaths(Map<String, List<KanjivgEntry>> kanaAndStrokePaths) {

		for (KanaEntry currentKanaEntry : hiraganaEntries) {

			String kanaJapanese = currentKanaEntry.getKanaJapanese();

			List<KanjivgEntry> strokePaths = kanaAndStrokePaths.get(kanaJapanese);

			if (strokePaths == null) {
				throw new RuntimeException("strokePaths == null: " + kanaJapanese);
			}

			currentKanaEntry.setStrokePaths(strokePaths);
		}
	}

	public List<KanaEntry> getAllKatakanaKanaEntries() {

		if (katakanaEntries != null) {
			return katakanaEntries;
		}

		katakanaEntries = new ArrayList<KanaEntry>();

		katakanaEntries.add(new KanaEntry("ア", "a", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("イ", "i", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ウ", "u", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("エ", "e", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("オ", "o", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("カ", "ka", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("キ", "ki", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ク", "ku", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ケ", "ke", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("コ", "ko", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("サ", "sa", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("シ", "shi", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ス", "su", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("セ", "se", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ソ", "so", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("タ", "ta", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("チ", "chi", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ツ", "tsu", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("テ", "te", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ト", "to", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("ナ", "na", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ニ", "ni", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ヌ", "nu", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ネ", "ne", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ノ", "no", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("ハ", "ha", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ヒ", "hi", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("フ", "fu", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ヘ", "he", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ホ", "ho", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("マ", "ma", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ミ", "mi", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ム", "mu", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("メ", "me", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("モ", "mo", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("ヤ", "ya", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ユ", "yu", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ヨ", "yo", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("ラ", "ra", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("リ", "ri", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ル", "ru", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("レ", "re", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ロ", "ro", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("ワ", "wa", KanaType.KATAKANA, KanaGroup.GOJUUON));
		katakanaEntries.add(new KanaEntry("ヰ", "wi", KanaType.KATAKANA, KanaGroup.GOJUUON, false));
		katakanaEntries.add(new KanaEntry("ヱ", "we", KanaType.KATAKANA, KanaGroup.GOJUUON, false));
		katakanaEntries.add(new KanaEntry("ヲ", "wo", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("ン", "n", KanaType.KATAKANA, KanaGroup.GOJUUON));

		katakanaEntries.add(new KanaEntry("ガ", "ga", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ギ", "gi", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("グ", "gu", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ゲ", "ge", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ゴ", "go", KanaType.KATAKANA, KanaGroup.DAKUTEN));

		katakanaEntries.add(new KanaEntry("ザ", "za", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ジ", "ji", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ズ", "zu", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ゼ", "ze", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ゾ", "zo", KanaType.KATAKANA, KanaGroup.DAKUTEN));

		katakanaEntries.add(new KanaEntry("ダ", "da", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ヂ", "di", "ji (di)", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ヅ", "du", "zu (du)", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("デ", "de", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ド", "do", KanaType.KATAKANA, KanaGroup.DAKUTEN));

		katakanaEntries.add(new KanaEntry("バ", "ba", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ビ", "bi", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ブ", "bu", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ベ", "be", KanaType.KATAKANA, KanaGroup.DAKUTEN));
		katakanaEntries.add(new KanaEntry("ボ", "bo", KanaType.KATAKANA, KanaGroup.DAKUTEN));

		katakanaEntries.add(new KanaEntry("パ", "pa", KanaType.KATAKANA, KanaGroup.HANDAKUTEN));
		katakanaEntries.add(new KanaEntry("ピ", "pi", KanaType.KATAKANA, KanaGroup.HANDAKUTEN));
		katakanaEntries.add(new KanaEntry("プ", "pu", KanaType.KATAKANA, KanaGroup.HANDAKUTEN));
		katakanaEntries.add(new KanaEntry("ペ", "pe", KanaType.KATAKANA, KanaGroup.HANDAKUTEN));
		katakanaEntries.add(new KanaEntry("ポ", "po", KanaType.KATAKANA, KanaGroup.HANDAKUTEN));

		katakanaEntries.add(new KanaEntry("キャ", "kya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("キュ", "kyu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("キョ", "kyo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("シャ", "sha", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("シュ", "shu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ショ", "sho", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("チャ", "cha", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("チュ", "chu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("チョ", "cho", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ニャ", "nya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ニュ", "nyu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ニョ", "nyo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ヒャ", "hya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヒュ", "hyu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヒョ", "hyo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ミャ", "mya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ミュ", "myu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ミョ", "myo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("リャ", "rya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("リュ", "ryu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("リョ", "ryo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ギャ", "gya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ギュ", "gyu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ギョ", "gyo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ジャ", "ja", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ジュ", "ju", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ジョ", "jo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ビャ", "bya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ビュ", "byu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ビョ", "byo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ピャ", "pya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ピュ", "pyu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ピョ", "pyo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ウィ", "wi", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ウェ", "we", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ウォ", "wo", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ヷ", "va", KanaType.KATAKANA, KanaGroup.GOJUUON, false));
		katakanaEntries.add(new KanaEntry("ヸ", "vi", KanaType.KATAKANA, KanaGroup.GOJUUON, false));
		katakanaEntries.add(new KanaEntry("ヹ", "ve", KanaType.KATAKANA, KanaGroup.GOJUUON, false));
		katakanaEntries.add(new KanaEntry("ヺ", "vo", KanaType.KATAKANA, KanaGroup.GOJUUON, false));
		
		katakanaEntries.add(new KanaEntry("ヴァ", "va", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヴィ", "vi", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヴ", "vu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヴェ", "ve", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヴォ", "vo", KanaType.KATAKANA, KanaGroup.YOUON));
		
		katakanaEntries.add(new KanaEntry("イェ", "ye", KanaType.KATAKANA, KanaGroup.YOUON));
		
		katakanaEntries.add(new KanaEntry("ファ", "fa", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("フィ", "fi", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("フェ", "fe", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("フォ", "fo", KanaType.KATAKANA, KanaGroup.YOUON));
		
		katakanaEntries.add(new KanaEntry("ティ", "ti", KanaType.KATAKANA, KanaGroup.YOUON));	
		katakanaEntries.add(new KanaEntry("トゥ", "tu", KanaType.KATAKANA, KanaGroup.YOUON));
		
		katakanaEntries.add(new KanaEntry("ドゥ", "du", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ディ", "di", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("シェ", "she", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ジェ", "je", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("チェ", "che", KanaType.KATAKANA, KanaGroup.YOUON));

		katakanaEntries.add(new KanaEntry("ツァ", "tsa", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ツィ", "tsi", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ツェ", "tse", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ツォ", "tso", KanaType.KATAKANA, KanaGroup.YOUON));
							
		katakanaEntries.add(new KanaEntry("ヂャ", "dya", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヂュ", "dyu", KanaType.KATAKANA, KanaGroup.YOUON));
		katakanaEntries.add(new KanaEntry("ヂョ", "dyo", KanaType.KATAKANA, KanaGroup.YOUON));
		
		katakanaEntries.add(new KanaEntry("テュ", "tyu", KanaType.KATAKANA, KanaGroup.YOUON));
		
		katakanaEntries.add(new KanaEntry("デュ", "dyu", KanaType.KATAKANA, KanaGroup.YOUON));		
		
		katakanaEntries.add(new KanaEntry("フュ", "fyu", KanaType.KATAKANA, KanaGroup.YOUON));
		
		katakanaEntries.add(new KanaEntry("ッ", "ttsu", KanaType.KATAKANA, KanaGroup.SOKUON));
		katakanaEntries.add(new KanaEntry("ー", "ttsu2", KanaType.KATAKANA, KanaGroup.SOKUON));

		katakanaEntries = Collections.unmodifiableList(katakanaEntries);

		return katakanaEntries;
	}

	private void setKatakanaStrokePaths(Map<String, List<KanjivgEntry>> kanaAndStrokePaths) {

		for (KanaEntry currentKanaEntry : katakanaEntries) {

			String kanaJapanese = currentKanaEntry.getKanaJapanese();

			List<KanjivgEntry> strokePaths = kanaAndStrokePaths.get(kanaJapanese);

			if (strokePaths == null) {
				throw new RuntimeException("strokePaths == null: " + kanaJapanese);
			}

			currentKanaEntry.setStrokePaths(strokePaths);
		}
	}

	public List<KanaEntry> getAllAdditionalKanaEntries() {

		if (additionalEntries != null) {
			return additionalEntries;
		}

		additionalEntries = new ArrayList<KanaEntry>();

		additionalEntries.add(new KanaEntry("ゃ", "ya", KanaType.HIRAGANA, KanaGroup.OTHER));
		additionalEntries.add(new KanaEntry("ゅ", "yu", KanaType.HIRAGANA, KanaGroup.OTHER));
		additionalEntries.add(new KanaEntry("ょ", "yo", KanaType.HIRAGANA, KanaGroup.OTHER));

		additionalEntries.add(new KanaEntry("ァ", "a", KanaType.KATAKANA, KanaGroup.OTHER));
		additionalEntries.add(new KanaEntry("ィ", "i", KanaType.KATAKANA, KanaGroup.OTHER));
		additionalEntries.add(new KanaEntry("ェ", "e", KanaType.KATAKANA, KanaGroup.OTHER));

		additionalEntries.add(new KanaEntry("ャ", "ya", KanaType.KATAKANA, KanaGroup.OTHER));
		additionalEntries.add(new KanaEntry("ュ", "yu", KanaType.KATAKANA, KanaGroup.OTHER));
		additionalEntries.add(new KanaEntry("ョ", "yo", KanaType.KATAKANA, KanaGroup.OTHER));

		additionalEntries = Collections.unmodifiableList(additionalEntries);

		return additionalEntries;
	}

	private void setAdditionalStrokePaths(Map<String, List<KanjivgEntry>> kanaAndStrokePaths) {

		for (KanaEntry currentKanaEntry : additionalEntries) {

			String kanaJapanese = currentKanaEntry.getKanaJapanese();

			List<KanjivgEntry> strokePaths = kanaAndStrokePaths.get(kanaJapanese);

			if (strokePaths == null) {
				throw new RuntimeException("strokePaths == null: " + kanaJapanese);
			}

			currentKanaEntry.setStrokePaths(strokePaths);
		}
	}

	public Map<String, KanaEntry> getKanaCache() {
		return getKanaCache(false);
	}
	
	public Map<String, KanaEntry> getKanaCache(boolean addIsNotUse) {

		List<KanaEntry> allHiraganaEntries = getAllHiraganaKanaEntries();
		List<KanaEntry> allKatakanaKanaEntries = getAllKatakanaKanaEntries();
		List<KanaEntry> allAdditionalEntries = getAllAdditionalKanaEntries();

		Map<String, KanaEntry> kanaCache = new HashMap<String, KanaEntry>();

		for (KanaEntry kanaEntry : allHiraganaEntries) {
			
			if (addIsNotUse == false && kanaEntry.isUse() == false) {
				continue;
			}
			
			kanaCache.put(kanaEntry.getKanaJapanese(), kanaEntry);
		}

		for (KanaEntry kanaEntry : allKatakanaKanaEntries) {
			
			if (addIsNotUse == false && kanaEntry.isUse() == false) {
				continue;
			}

			kanaCache.put(kanaEntry.getKanaJapanese(), kanaEntry);
		}

		for (KanaEntry kanaEntry : allAdditionalEntries) {
			
			if (addIsNotUse == false && kanaEntry.isUse() == false) {
				continue;
			}
			
			kanaCache.put(kanaEntry.getKanaJapanese(), kanaEntry);
		}

		return kanaCache;
	}

	public KanaWord convertRomajiIntoHiraganaWord(Map<String, KanaEntry> hiraganaCache, String word)
			throws DictionaryException {

		word = word.toLowerCase(Locale.getDefault());

		List<KanaEntry> kanaEntries = new ArrayList<KanaEntry>();

		String remaingRestChars = "";

		String currentRestChars = "";

		for (int idx = 0; idx < word.length(); ++idx) {
			String currentChar = String.valueOf(word.charAt(idx));

			if (currentChar.equals(" ") == true) {
				continue;
			}

			currentRestChars += currentChar.toLowerCase(Locale.getDefault());

			if (currentRestChars.length() == 2 && currentRestChars.charAt(0) == currentRestChars.charAt(1)
					&& currentRestChars.charAt(0) != 'n') {

				KanaEntry kanaEntry = hiraganaCache.get("ttsu");

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "" + currentRestChars.charAt(1);

				continue;
			}

			if (currentRestChars.equals("a") == true || currentRestChars.equals("i") == true
					|| currentRestChars.equals("u") == true || currentRestChars.equals("e") == true
					|| currentRestChars.equals("o") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ka") == true || currentRestChars.equals("ki") == true
					|| currentRestChars.equals("ku") == true || currentRestChars.equals("ke") == true
					|| currentRestChars.equals("ko") == true || currentRestChars.equals("kya") == true
					|| currentRestChars.equals("kyu") == true || currentRestChars.equals("kyo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("sa") == true || currentRestChars.equals("shi") == true
					|| currentRestChars.equals("sha") == true || currentRestChars.equals("shu") == true
					|| currentRestChars.equals("sho") == true || currentRestChars.equals("su") == true
					|| currentRestChars.equals("se") == true || currentRestChars.equals("so") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ta") == true || currentRestChars.equals("tsu") == true
					|| currentRestChars.equals("te") == true || currentRestChars.equals("to") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("chi") == true || currentRestChars.equals("cha") == true
					|| currentRestChars.equals("chu") == true || currentRestChars.equals("cho") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.startsWith("n") == true || currentRestChars.equals("n'") == true) {

				boolean nProcessed = false;

				if (currentRestChars.equals("n'") == true) {
					KanaEntry kanaEntry = hiraganaCache.get("n");

					if (kanaEntry == null) {
						throw new DictionaryException("Can't find kanaEntry: " + word);
					}

					kanaEntries.add(kanaEntry);

					currentRestChars = "";

					nProcessed = true;
				}

				if (nProcessed == false
						&& (currentRestChars.equals("na") == true || currentRestChars.equals("ni") == true
								|| currentRestChars.equals("nu") == true || currentRestChars.equals("ne") == true
								|| currentRestChars.equals("no") == true || currentRestChars.equals("nya") == true
								|| currentRestChars.equals("nyu") == true || currentRestChars.equals("nyo") == true)) {

					KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

					if (kanaEntry == null) {
						throw new DictionaryException("Can't find kanaEntry: " + word);
					}

					kanaEntries.add(kanaEntry);

					currentRestChars = "";

					nProcessed = true;
				} else if (nProcessed == false && currentRestChars.length() > 1) {

					if (currentRestChars.startsWith("ny") == false) {
						KanaEntry kanaEntry = hiraganaCache.get("n");

						if (kanaEntry == null) {
							throw new DictionaryException("Can't find kanaEntry: " + word);
						}

						kanaEntries.add(kanaEntry);

						currentRestChars = currentRestChars.substring(1);

						nProcessed = true;
					}
				}

				if (nProcessed == false && currentRestChars.length() == 1 && idx == word.length() - 1) {
					KanaEntry kanaEntry = hiraganaCache.get("n");

					if (kanaEntry == null) {
						throw new DictionaryException("Can't find kanaEntry: " + word);
					}

					kanaEntries.add(kanaEntry);

					currentRestChars = "";

					nProcessed = true;
				}
			} else if (currentRestChars.equals("ha") == true || currentRestChars.equals("hi") == true
					|| currentRestChars.equals("he") == true || currentRestChars.equals("ho") == true
					|| currentRestChars.equals("hya") == true || currentRestChars.equals("hyu") == true
					|| currentRestChars.equals("hyo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("fu") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ma") == true || currentRestChars.equals("mi") == true
					|| currentRestChars.equals("mu") == true || currentRestChars.equals("me") == true
					|| currentRestChars.equals("mo") == true || currentRestChars.equals("mya") == true
					|| currentRestChars.equals("myu") == true || currentRestChars.equals("myo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ya") == true || currentRestChars.equals("yu") == true
					|| currentRestChars.equals("yo") == true || currentRestChars.equals("ye") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ra") == true || currentRestChars.equals("ri") == true
					|| currentRestChars.equals("ru") == true || currentRestChars.equals("re") == true
					|| currentRestChars.equals("ro") == true || currentRestChars.equals("rya") == true
					|| currentRestChars.equals("ryu") == true || currentRestChars.equals("ryo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("wa") == true || currentRestChars.equals("wo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ga") == true || currentRestChars.equals("gi") == true
					|| currentRestChars.equals("gu") == true || currentRestChars.equals("ge") == true
					|| currentRestChars.equals("go") == true || currentRestChars.equals("gya") == true
					|| currentRestChars.equals("gyu") == true || currentRestChars.equals("gyo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("za") == true || currentRestChars.equals("zu") == true
					|| currentRestChars.equals("ze") == true || currentRestChars.equals("zo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ji") == true || currentRestChars.equals("ja") == true
					|| currentRestChars.equals("ju") == true || currentRestChars.equals("jo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("da") == true || currentRestChars.equals("di") == true
					|| currentRestChars.equals("du") == true || currentRestChars.equals("de") == true
					|| currentRestChars.equals("do") == true || currentRestChars.equals("dya") == true
					|| currentRestChars.equals("dyu") == true || currentRestChars.equals("dyo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ba") == true || currentRestChars.equals("bi") == true
					|| currentRestChars.equals("bu") == true || currentRestChars.equals("be") == true
					|| currentRestChars.equals("bo") == true || currentRestChars.equals("bya") == true
					|| currentRestChars.equals("byu") == true || currentRestChars.equals("byo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("pa") == true || currentRestChars.equals("pi") == true
					|| currentRestChars.equals("pu") == true || currentRestChars.equals("pe") == true
					|| currentRestChars.equals("po") == true || currentRestChars.equals("pya") == true
					|| currentRestChars.equals("pyu") == true || currentRestChars.equals("pyo") == true) {

				KanaEntry kanaEntry = hiraganaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			}

			remaingRestChars = currentRestChars;
		}

		KanaWord result = new KanaWord();

		result.kanaEntries = kanaEntries;
		result.remaingRestChars = remaingRestChars;

		return result;
	}

	public KanaWord convertRomajiIntoKatakanaWord(Map<String, KanaEntry> kitakanaCache, String word)
			throws DictionaryException {

		word = word.toLowerCase(Locale.getDefault());

		List<KanaEntry> kanaEntries = new ArrayList<KanaEntry>();

		String remaingRestChars = "";

		String currentRestChars = "";

		for (int idx = 0; idx < word.length(); ++idx) {
			String currentChar = String.valueOf(word.charAt(idx));

			if (currentChar.equals(" ") == true) {
				continue;
			}

			currentRestChars += currentChar.toLowerCase(Locale.getDefault());

			if (idx > 0) {
				char previousChar = word.charAt(idx - 1);
				char currentCharChar = word.charAt(idx);

				if (previousChar == currentCharChar && isVowel(previousChar) == true
						&& isVowel(currentCharChar) == true) {
					KanaEntry kanaEntry = kitakanaCache.get("ttsu2");

					if (kanaEntry == null) {
						throw new DictionaryException("Can't find kanaEntry: " + word);
					}

					kanaEntries.add(kanaEntry);

					currentRestChars = "";

					continue;
				}
			}

			if (currentRestChars.length() == 2 && currentRestChars.charAt(0) == currentRestChars.charAt(1)
					&& currentRestChars.charAt(0) != 'n') {

				KanaEntry kanaEntry = kitakanaCache.get("ttsu");

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "" + currentRestChars.charAt(1);

				continue;
			}

			if (currentRestChars.equals("a") == true || currentRestChars.equals("i") == true
					|| currentRestChars.equals("u") == true || currentRestChars.equals("e") == true
					|| currentRestChars.equals("o") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ka") == true || currentRestChars.equals("ki") == true
					|| currentRestChars.equals("ku") == true || currentRestChars.equals("ke") == true
					|| currentRestChars.equals("ko") == true || currentRestChars.equals("kya") == true
					|| currentRestChars.equals("kyu") == true || currentRestChars.equals("kyo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("sa") == true || currentRestChars.equals("shi") == true
					|| currentRestChars.equals("sha") == true || currentRestChars.equals("shu") == true
					|| currentRestChars.equals("sho") == true || currentRestChars.equals("she") == true
					|| currentRestChars.equals("su") == true || currentRestChars.equals("se") == true
					|| currentRestChars.equals("so") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ta") == true || currentRestChars.equals("tsu") == true
					|| currentRestChars.equals("te") == true || currentRestChars.equals("to") == true
					|| currentRestChars.equals("ti") == true || currentRestChars.equals("tu") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("chi") == true || currentRestChars.equals("cha") == true
					|| currentRestChars.equals("chu") == true || currentRestChars.equals("cho") == true
					|| currentRestChars.equals("che") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.startsWith("n") == true || currentRestChars.equals("n'") == true) {

				boolean nProcessed = false;

				if (currentRestChars.equals("n'") == true) {
					KanaEntry kanaEntry = kitakanaCache.get("n");

					if (kanaEntry == null) {
						throw new DictionaryException("Can't find kanaEntry: " + word);
					}

					kanaEntries.add(kanaEntry);

					currentRestChars = "";

					nProcessed = true;
				}

				if (nProcessed == false
						&& (currentRestChars.equals("na") == true || currentRestChars.equals("ni") == true
								|| currentRestChars.equals("nu") == true || currentRestChars.equals("ne") == true
								|| currentRestChars.equals("no") == true || currentRestChars.equals("nya") == true
								|| currentRestChars.equals("nyu") == true || currentRestChars.equals("nyo") == true)) {

					KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

					if (kanaEntry == null) {
						throw new DictionaryException("Can't find kanaEntry: " + word);
					}

					kanaEntries.add(kanaEntry);

					currentRestChars = "";

					nProcessed = true;
				} else if (nProcessed == false && currentRestChars.length() > 1) {

					if (currentRestChars.startsWith("ny") == false) {
						KanaEntry kanaEntry = kitakanaCache.get("n");

						if (kanaEntry == null) {
							throw new DictionaryException("Can't find kanaEntry: " + word);
						}

						kanaEntries.add(kanaEntry);

						currentRestChars = currentRestChars.substring(1);

						nProcessed = true;
					}
				}

				if (nProcessed == false && currentRestChars.length() == 1 && idx == word.length() - 1) {
					KanaEntry kanaEntry = kitakanaCache.get("n");

					if (kanaEntry == null) {
						throw new DictionaryException("Can't find kanaEntry: " + word);
					}

					kanaEntries.add(kanaEntry);

					currentRestChars = "";

					nProcessed = true;
				}
			} else if (currentRestChars.equals("ha") == true || currentRestChars.equals("hi") == true
					|| currentRestChars.equals("he") == true || currentRestChars.equals("ho") == true
					|| currentRestChars.equals("hya") == true || currentRestChars.equals("hyu") == true
					|| currentRestChars.equals("hyo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("fu") == true || currentRestChars.equals("fa") == true
					|| currentRestChars.equals("fi") == true || currentRestChars.equals("fe") == true
					|| currentRestChars.equals("fo") == true || currentRestChars.equals("fyu") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			
			} else if (currentRestChars.equals("tsa") == true || currentRestChars.equals("tsi") == true
					|| currentRestChars.equals("tse") == true || currentRestChars.equals("tso") == true
					|| currentRestChars.equals("tyu") == true) {
				
				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ma") == true || currentRestChars.equals("mi") == true
					|| currentRestChars.equals("mu") == true || currentRestChars.equals("me") == true
					|| currentRestChars.equals("mo") == true || currentRestChars.equals("mya") == true
					|| currentRestChars.equals("myu") == true || currentRestChars.equals("myo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ya") == true || currentRestChars.equals("yu") == true
					|| currentRestChars.equals("yo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ra") == true || currentRestChars.equals("ri") == true
					|| currentRestChars.equals("ru") == true || currentRestChars.equals("re") == true
					|| currentRestChars.equals("ro") == true || currentRestChars.equals("rya") == true
					|| currentRestChars.equals("ryu") == true || currentRestChars.equals("ryo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("wa") == true || currentRestChars.equals("wo") == true
					|| currentRestChars.equals("wi") == true || currentRestChars.equals("we") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("va") == true || currentRestChars.equals("vi") == true || currentRestChars.equals("vu") == true 
					|| currentRestChars.equals("ve") == true || currentRestChars.equals("vo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ga") == true || currentRestChars.equals("gi") == true
					|| currentRestChars.equals("gu") == true || currentRestChars.equals("ge") == true
					|| currentRestChars.equals("go") == true || currentRestChars.equals("gya") == true
					|| currentRestChars.equals("gyu") == true || currentRestChars.equals("gyo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("za") == true || currentRestChars.equals("zu") == true
					|| currentRestChars.equals("ze") == true || currentRestChars.equals("zo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ji") == true || currentRestChars.equals("ja") == true
					|| currentRestChars.equals("ju") == true || currentRestChars.equals("jo") == true
					|| currentRestChars.equals("je") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("da") == true || currentRestChars.equals("di") == true
					|| currentRestChars.equals("du") == true || currentRestChars.equals("de") == true
					|| currentRestChars.equals("do") == true || currentRestChars.equals("di") == true
					|| currentRestChars.equals("dya") == true || currentRestChars.equals("dyu") == true
					|| currentRestChars.equals("dyo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("ba") == true || currentRestChars.equals("bi") == true
					|| currentRestChars.equals("bu") == true || currentRestChars.equals("be") == true
					|| currentRestChars.equals("bo") == true || currentRestChars.equals("bya") == true
					|| currentRestChars.equals("byu") == true || currentRestChars.equals("byo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			} else if (currentRestChars.equals("pa") == true || currentRestChars.equals("pi") == true
					|| currentRestChars.equals("pu") == true || currentRestChars.equals("pe") == true
					|| currentRestChars.equals("po") == true || currentRestChars.equals("pya") == true
					|| currentRestChars.equals("pyu") == true || currentRestChars.equals("pyo") == true) {

				KanaEntry kanaEntry = kitakanaCache.get(currentRestChars);

				if (kanaEntry == null) {
					throw new DictionaryException("Can't find kanaEntry: " + word);
				}

				kanaEntries.add(kanaEntry);

				currentRestChars = "";
			}

			remaingRestChars = currentRestChars;
		}

		KanaWord result = new KanaWord();

		result.kanaEntries = kanaEntries;
		result.remaingRestChars = remaingRestChars;

		return result;
	}

	public static class KanaWord {
		public List<KanaEntry> kanaEntries;

		public String remaingRestChars;
	}

	private static boolean isVowel(char char_) {
		if (char_ == 'e' || char_ == 'u' || char_ == 'i' || char_ == 'o' || char_ == 'a') {
			return true;
		} else {
			return false;
		}
	}

	public KanaWord convertKanaStringIntoKanaWord(String kana, Map<String, KanaEntry> kanaCache,
			boolean ignoreUnknownChars) {

		List<KanaEntry> kanaResultEntries = new ArrayList<KanaEntry>();

		int pos = 0;

		while (true) {
			if (pos >= kana.length()) {
				break;
			}

			String nextDoubleKanaPart = getNextDoubleKanaPart(kana, pos);

			if (nextDoubleKanaPart != null) {
				KanaEntry nextDoubleKanaPartKanaEntry = kanaCache.get(nextDoubleKanaPart);

				if (nextDoubleKanaPartKanaEntry != null) {
					kanaResultEntries.add(nextDoubleKanaPartKanaEntry);

					pos += 2;

					continue;
				}
			}

			String nextSingleKanaPart = getNextSingleKanaPart(kana, pos);

			if (nextSingleKanaPart != null) {
				KanaEntry nextSingleKanaPartKanaEntry = kanaCache.get(nextSingleKanaPart);

				if (nextSingleKanaPartKanaEntry != null) {
					kanaResultEntries.add(nextSingleKanaPartKanaEntry);

					pos += 1;

					continue;
				} else {
					if (ignoreUnknownChars == false && nextSingleKanaPart.equals("・") == false) {
						throw new RuntimeException(kana + " - " + nextSingleKanaPart);
					}

					pos += 1;
				}
			}
		}

		KanaWord result = new KanaWord();

		result.kanaEntries = kanaResultEntries;

		return result;
	}

	private static String getNextDoubleKanaPart(String kana, int startPos) {
		if (kana.length() < startPos + 2) {
			return null;
		}

		return kana.substring(startPos, startPos + 2);
	}

	private static String getNextSingleKanaPart(String kana, int startPos) {
		if (kana.length() < startPos) {
			return null;
		}

		return kana.substring(startPos, startPos + 1);
	}

	public String convertKatakanaToHiragana(String katakanaString) {

		if (katakanaString == null) {
			return null;
		}

		List<KanaEntry> allHiraganaKanaEntries = getAllHiraganaKanaEntries();
		List<KanaEntry> allKatakanaKanaEntries = getAllKatakanaKanaEntries();
		List<KanaEntry> allAdditionalEntries = getAllAdditionalKanaEntries();

		Map<String, KanaEntry> hiraganaCache = new HashMap<String, KanaEntry>();

		for (KanaEntry kanaEntry : allHiraganaKanaEntries) {
			hiraganaCache.put(kanaEntry.getKana(), kanaEntry);
		}

		Map<String, KanaEntry> katakanaCache = new HashMap<String, KanaEntry>();

		for (KanaEntry kanaEntry : allKatakanaKanaEntries) {
			katakanaCache.put(kanaEntry.getKanaJapanese(), kanaEntry);
		}

		for (KanaEntry kanaEntry : allAdditionalEntries) {
			katakanaCache.put(kanaEntry.getKanaJapanese(), kanaEntry);
		}

		StringBuffer result = new StringBuffer();

		int pos = 0;

		while (true) {
			if (pos >= katakanaString.length()) {
				break;
			}

			String nextDoubleKatakanaChar = getNextDoubleKanaPart(katakanaString, pos);

			if (nextDoubleKatakanaChar != null) {

				KanaEntry katakanaKanaEntry = katakanaCache.get(nextDoubleKatakanaChar);

				if (katakanaKanaEntry != null) {

					String romajiKatakana = katakanaKanaEntry.getKana();

					KanaEntry hiraganaKanaEntry = hiraganaCache.get(romajiKatakana);

					if (hiraganaKanaEntry == null) {
						return null;
					}

					result.append(hiraganaKanaEntry.getKanaJapanese());

					pos += 2;

					continue;
				}
			}

			String nextSingleKanaPart = getNextSingleKanaPart(katakanaString, pos);

			if (nextSingleKanaPart != null) {

				KanaEntry katakanaKanaEntry = katakanaCache.get(nextSingleKanaPart);

				if (katakanaKanaEntry != null) {

					String romajiKatakana = katakanaKanaEntry.getKana();

					KanaEntry hiraganaKanaEntry = hiraganaCache.get(romajiKatakana);

					if (hiraganaKanaEntry == null) {
						return null;
					}

					result.append(hiraganaKanaEntry.getKanaJapanese());

					pos += 1;

					continue;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}

		return result.toString();
	}
}
