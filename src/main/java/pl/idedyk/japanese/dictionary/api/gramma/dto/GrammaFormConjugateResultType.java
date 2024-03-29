package pl.idedyk.japanese.dictionary.api.gramma.dto;

public enum GrammaFormConjugateResultType {
	
	ADJECTIVE_I_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),	
	ADJECTIVE_I_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	ADJECTIVE_I_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	ADJECTIVE_I_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	ADJECTIVE_I_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	ADJECTIVE_I_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	ADJECTIVE_I_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	ADJECTIVE_I_VIRTUAL("Wirtualny typ", false),
	
	ADJECTIVE_I_KEIGO_LOW("Forma modestywna (skromna)", true),
	
	ADJECTIVE_NA_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	ADJECTIVE_NA_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),	
	ADJECTIVE_NA_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	ADJECTIVE_NA_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	ADJECTIVE_NA_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	ADJECTIVE_NA_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	ADJECTIVE_NA_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	ADJECTIVE_NA_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	ADJECTIVE_NA_KEIGO_LOW("Forma modestywna (skromna)", true),

	NOUN_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	NOUN_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),	
	NOUN_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	NOUN_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	NOUN_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	NOUN_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	NOUN_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	NOUN_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	NOUN_KEIGO_LOW("Forma modestywna (skromna)", true),

	VERB_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),

	VERB_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	VERB_TE("Twierdzenie", true),
	VERB_TE_NEGATIVE("Przeczenie", true),

	VERB_MASU_TE("Forma te od masu", false),
	
	ADJECTIVE_I_TE("Twierdzenie", true),
	ADJECTIVE_I_TE_NEGATIVE("Przeczenie", true),
	
	ADJECTIVE_NA_TE("Twierdzenie", true),
	ADJECTIVE_NA_TE_NEGATIVE("Przeczenie", true),
	
	ADJECTIVE_I_ADVERB("Forma przysłówkowa", false),
	ADJECTIVE_NA_ADVERB("Forma przysłówkowa", false),
	
	NOUN_TE("Twierdzenie", true),
	NOUN_TE_NEGATIVE("Przeczenie", true),
	
	VERB_STEM("Temat czasownika (forma niefinitywna 1, ang: stem)", false),
	
	VERB_MASHOU("Forma mashou", false),
	
	VERB_POTENTIAL_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_POTENTIAL_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_POTENTIAL_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_POTENTIAL_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),

	VERB_POTENTIAL_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_POTENTIAL_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_POTENTIAL_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_POTENTIAL_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	VERB_POTENTIAL_TE("Twierdzenie", true),
	VERB_POTENTIAL_TE_NEGATIVE("Przeczenie", true),

	VERB_PASSIVE_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_PASSIVE_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_PASSIVE_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_PASSIVE_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),

	VERB_PASSIVE_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_PASSIVE_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_PASSIVE_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_PASSIVE_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	VERB_PASSIVE_TE("Twierdzenie", true),
	VERB_PASSIVE_TE_NEGATIVE("Przeczenie", true),

	VERB_CAUSATIVE_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_CAUSATIVE_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),

	VERB_CAUSATIVE_COLLOQUIAL_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_COLLOQUIAL_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_COLLOQUIAL_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_CAUSATIVE_COLLOQUIAL_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	VERB_CAUSATIVE_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_CAUSATIVE_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),

	VERB_CAUSATIVE_COLLOQUIAL_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_COLLOQUIAL_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_COLLOQUIAL_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_CAUSATIVE_COLLOQUIAL_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	VERB_CAUSATIVE_TE("Twierdzenie", true),
	VERB_CAUSATIVE_TE_NEGATIVE("Przeczenie", true),

	VERB_CAUSATIVE_COLLOQUIAL_TE("Twierdzenie", true),
	VERB_CAUSATIVE_COLLOQUIAL_TE_NEGATIVE("Przeczenie", true),
	
	VERB_CAUSATIVE_PASSIVE_INFORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_PASSIVE_INFORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_PASSIVE_INFORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_CAUSATIVE_PASSIVE_INFORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),

	VERB_CAUSATIVE_PASSIVE_FORMAL_PRESENT("Twierdzenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_PASSIVE_FORMAL_PRESENT_NEGATIVE("Przeczenie, czas teraźniejszy", true),
	VERB_CAUSATIVE_PASSIVE_FORMAL_PAST("Twierdzenie, czas przeszły", true),
	VERB_CAUSATIVE_PASSIVE_FORMAL_PAST_NEGATIVE("Przeczenie, czas przeszły", true),
	
	VERB_CAUSATIVE_PASSIVE_TE("Twierdzenie", true),
	VERB_CAUSATIVE_PASSIVE_TE_NEGATIVE("Przeczenie", true),
	
	VERB_VOLITIONAL("Forma hortatywna (wolicjonalna)", false),
	
	VERB_CONJECTURAL("Forma przypuszczająca", false),
	
	VERB_BA_AFFIRMATIVE("Twierdzenie", true),
	VERB_BA_NEGATIVE("Przeczenie", true),
	
	VERB_KEIGO_HIGH_1("Forma honoryfikatywna (wywyższająca) 1", true),
	VERB_KEIGO_HIGH_2("Forma honoryfikatywna (wywyższająca) 2", "Forma homofoniczna ze stroną bierną", true),
	VERB_KEIGO_LOW("Forma modestywna (skromna)", true),
	
	VERB_IMPERATIVE_FORM("Forma rozkazująca", false),
	VERB_IMPERATIVE_NOT_FORM("Forma rozkazująca (przeczenie)", false);
	
	private String name;
	
	private String info;
	
	private boolean show;
	
	GrammaFormConjugateResultType(String name, boolean show) {
		this.name = name;
		this.show = show;
	}

	GrammaFormConjugateResultType(String name, String info, boolean show) {
		this.name = name;
		this.info = info;
		this.show = show;
	}

	public String getName() {
		return name;
	}	

	public String getInfo() {
		return info;
	}

	public boolean isShow() {
		return show;
	}
}
