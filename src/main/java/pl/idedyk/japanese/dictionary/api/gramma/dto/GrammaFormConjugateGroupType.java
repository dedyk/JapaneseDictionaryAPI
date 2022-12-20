package pl.idedyk.japanese.dictionary.api.gramma.dto;

public enum GrammaFormConjugateGroupType {
	
	ADJECTIVE_I_INFORMAL("Forma nieformalna (prosta)"),
	ADJECTIVE_I_FORMAL("Forma formalna"),
	
	ADJECTIVE_I_KEIGO("Keigo"),

	ADJECTIVE_NA_INFORMAL("Forma nieformalna (prosta)"),
	ADJECTIVE_NA_FORMAL("Forma formalna"),
	
	ADJECTIVE_NA_KEIGO("Keigo"),

	NOUN_INFORMAL("Forma nieformalna (prosta)"),
	NOUN_FORMAL("Forma formalna"),
	
	NOUN_KEIGO("Keigo"),
	
	VERB_FORMAL("Forma formalna (długa)"),
	VERB_INFORMAL("Forma nieformalna (prosta)"),
	
	ADJECTIVE_I_TE("Forma te"),
	ADJECTIVE_NA_TE("Forma te"),
	NOUN_TE("Forma te"),
	VERB_TE("Forma te"),
	
	VERB_MASU_TE("Forma te od masu"),
	
	ADJECTIVE_I_ADVERB("Forma przysłówkowa"),
	ADJECTIVE_NA_ADVERB("Forma przysłówkowa"),
	
	VERB_STEM("Temat czasownika (ang: stem)"),
	
	VERB_MASHOU("Forma mashou"),
	
	VERB_POTENTIAL_INFORMAL("Forma potencjalna (prosta)"),
	
	VERB_POTENTIAL_FORMAL("Forma potencjalna (formalna)"),
	
	VERB_POTENTIAL_TE("Forma potencjalna, forma te"),

	VERB_PASSIVE_INFORMAL("Forma bierna (prosta)"),
	
	VERB_PASSIVE_FORMAL("Forma bierna (formalna)"),
	
	VERB_PASSIVE_TE("Forma bierna, forma te"),

	VERB_CAUSATIVE_INFORMAL("Forma sprawcza (kauzatywna, prosta)"),	
	VERB_CAUSATIVE_COLLOQUIAL_INFORMAL("Forma sprawcza (kauzatywna, potoczna, prosta)"),
	
	VERB_CAUSATIVE_FORMAL("Forma sprawcza (kauzatywna, formalna)"),	
	VERB_CAUSATIVE_COLLOQUIAL_FORMAL("Forma sprawcza (kauzatywna, potoczna, formalna)"),
	
	VERB_CAUSATIVE_TE("Forma sprawcza (kauzatywna), forma te"),
	VERB_CAUSATIVE_COLLOQUIAL_TE("Forma sprawcza (kauzatywna, potoczna), forma te"),

	VERB_CAUSATIVE_PASSIVE_INFORMAL("Forma sprawczo-bierna (prosta)"),
	
	VERB_CAUSATIVE_PASSIVE_FORMAL("Forma sprawczo-bierna (formalna)"),
	
	VERB_CAUSATIVE_PASSIVE_TE("Forma sprawczo-bierna, forma te"),
	
	VERB_VOLITIONAL("Forma wolicjonalna"),
	
	VERB_BA("Forma ba"),
	
	VERB_KEIGO("Keigo"),
	
	VERB_VIRTUAL("Grupa pomocnicza", false);
	
	private String name;
	
	private boolean show;
	
	GrammaFormConjugateGroupType(String name) {
		this(name, true);
	}
	
	GrammaFormConjugateGroupType(String name, boolean show) {
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
