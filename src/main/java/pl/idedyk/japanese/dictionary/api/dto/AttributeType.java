package pl.idedyk.japanese.dictionary.api.dto;

public enum AttributeType {
	
	VERB_TRANSITIVITY("czasownik przechodni", true),
	VERB_TRANSITIVITY_PAIR("odpowiadający czasownik przechodni", false),
	
	VERB_INTRANSITIVITY("czasownik nieprzechodni", true),
	VERB_INTRANSITIVITY_PAIR("odpowiadający czasownik nieprzechodni", false),
	
	VERB_KEIGO_HIGH("czasownik honoryfikatywny (wywyższający)", true),
	
	VERB_KEIGO_LOW("czasownik modestywny (uniżający)", true),
	
	SURU_VERB("suru czasownik", true),
	
	COMMON_WORD("słowo powszechnego użycia", true),
	
	KANJI_ALONE("Zwykle pisany przy użyciu kanji", true),
	KANA_ALONE("zwykle pisany przy użyciu kana", true),
	
	ARCHAISM("archaizm", true),
	
	OBSCURE("słowo mało znane, zapomniane", true),
	
	OBSOLETE("słowo przestarzałe", true),
	
	/*
	SUFFIX("przyrostek", true),
	NOUN_SUFFIX("przyrostek do rzeczowników", true),
	
	PREFIX("prefiks", true),
	NOUN_PREFIX("prefiks do rzeczowników", true),
	*/
		
	ONAMATOPOEIC_OR_MIMETIC_WORD("onomatopeiczne lub mimetyczne słowo", true),
	
	ATEJI("ateji (fonetyczne) czytanie", true),
	
	ALTERNATIVE("alternatywa", false),
	
	RELATED("słowo powiązanie", false);
	
	private String name;
	
	private boolean show;
	
	AttributeType(String name, boolean show) {
		this.name = name;
		this.show = show;
	}

	public String getName() {
		return name;
	}

	public boolean isShow() {
		return show;
	}
}
