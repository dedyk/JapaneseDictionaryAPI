package pl.idedyk.japanese.dictionary.api.grammaexample.dto;

public enum GrammaExampleType {
	
	ADJECTIVE_I_FORMAL_PRESENT("Forma formalna / Twierdzenie, czas teraźniejszy", true),
	ADJECTIVE_I_FORMAL_PRESENT_NEGATIVE("Forma formalna / Przeczenie, czas teraźniejszy", true),
	ADJECTIVE_I_FORMAL_PAST("Forma formalna / Twierdzenie, czas przeszły", true),
	ADJECTIVE_I_FORMAL_PAST_NEGATIVE("Forma formalna / Przeczenie, czas przeszły", true),

	ADJECTIVE_I_INFORMAL_PRESENT("Forma nieformalna (prosta) / Twierdzenie, czas teraźniejszy", true),
	ADJECTIVE_I_INFORMAL_PRESENT_NEGATIVE("Forma nieformalna (prosta) / Przeczenie, czas teraźniejszy", true),	
	ADJECTIVE_I_INFORMAL_PAST("Forma nieformalna (prosta) / Twierdzenie, czas przeszły", true),
	ADJECTIVE_I_INFORMAL_PAST_NEGATIVE("Forma nieformalna (prosta) / Przeczenie, czas przeszły", true),

	
	;
	
	private String name;
	private String info;
	private boolean show;
	
	GrammaExampleType(String name, boolean show) {
		this.name = name;
		this.show = show;
	}

	GrammaExampleType(String name, String info, boolean show) {
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
