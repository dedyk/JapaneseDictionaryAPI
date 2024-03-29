package pl.idedyk.japanese.dictionary.api.example.dto;

public enum ExampleGroupType {

	ADJECTIVE_I_II_GRADATION("Stopniowanie (II poziom, bardziej)"),

	ADJECTIVE_I_III_GRADATION("Stopniowanie (III poziom, najbardziej)"),

	ADJECTIVE_I_NARU("Stawać się"),

	ADJECTIVE_I_N_DESU("Forma wyjaśniająca"),

	ADJECTIVE_I_SUGIRU("Zbyt wiele"),

	ADJECTIVE_I_DESHOU("Prawdopodobnie, ok. 60%"),

	ADJECTIVE_I_SOU_DESU_LOOKS_LIKE("Wygląda na to, że ... (forma imperceptywna)",
			"Bardziej używane do odbieranych przez zmysły informacji"),

	ADJECTIVE_I_KAMOSHI_REMASEN("Prawdopodobnie, ok. 30%"),

	ADJECTIVE_I_TO_II_TO_OTHERS("Mieć nadzieję, że ... (mówienie o innych)"),

	ADJECTIVE_I_TO_II_TO_ME("Mieć nadzieję, że ... (mówienie o sobie)"),

	ADJECTIVE_I_TOKI("Kiedy ..., to ..."),

	ADJECTIVE_I_SOU_DESU_HEAR("Słyszałem, że ..."),

	ADJECTIVE_I_TTE("Forma tte", "Zastępuje そうです oraz と言っていました"),

	ADJECTIVE_I_TARA("Jeśli (tryb warunkowy), to ..."),

	ADJECTIVE_I_NAKUTE_MO_II_DESU("Nie trzeba"),

	ADJECTIVE_I_MITAI_DESU("Wygląda, jak", "Bardziej używane z analizy sytuacji, do przymiotników częściej forma そうです"),

	ADJECTIVE_I_TO("Kiedy A staje się, wtedy również B staje się",
			"Mówi o rzeczach oczywistych, z której druga wynika z pierwszej"),

	ADJECTIVE_I_HAZU_DESU("Powinno być / Miało być"),

	ADJECTIVE_I_QUESTIONS_WITH_LARGER_SENTENCES("Pytania w zdaniach"),

	ADJECTIVE_I_SURU("Sprawiać, że coś jest ..."),

	ADJECTIVE_I_NONI("Mimo że jest ..., to ..."),

	ADJECTIVE_I_YOUNI("Podobny do ..., jak ..."),

	ADJECTIVE_I_TE_MO("Nawet, jeśli"),
	ADJECTIVE_I_TE_MO_NEGATIVE("Nawet, jeśli nie"),
	
	ADJECTIVE_I_IMPERATIVE("Rozkaz (bądź)"),
	
	ADJECTIVE_I_IMPERATIVE_NOT("Zakaz (nie bądź)"),

	ADJECTIVE_NA_II_GRADATION("Stopniowanie (II poziom, bardziej)"),

	ADJECTIVE_NA_III_GRADATION("Stopniowanie (III poziom, najbardziej)"),

	ADJECTIVE_NA_NARU("Stawać się"),

	ADJECTIVE_NA_NA_DESU("Forma wyjaśniająca"),

	ADJECTIVE_NA_SUGIRU("Zbyt wiele"),

	ADJECTIVE_NA_DESHOU("Prawdopodobnie, ok. 60%"),

	ADJECTIVE_NA_SOU_DESU_LOOKS_LIKE("Wygląda na to, że ... (forma imperceptywna)",
			"Bardziej używane do odbieranych przez zmysły informacji"),

	ADJECTIVE_NA_KAMOSHI_REMASEN("Prawdopodobnie, ok. 30%"),

	ADJECTIVE_NA_TO_II_TO_OTHERS("Mieć nadzieję, że ... (mówienie o innych)"),

	ADJECTIVE_NA_TO_II_TO_ME("Mieć nadzieję, że ... (mówienie o sobie)"),

	ADJECTIVE_NA_TOKI("Kiedy ..., to ..."),

	ADJECTIVE_NA_SOU_DESU_HEAR("Słyszałem, że ..."),

	ADJECTIVE_NA_TTE("Forma tte", "Zastępuje そうです oraz と言っていました"),

	ADJECTIVE_NA_TARA("Jeśli (tryb warunkowy), to ..."),

	ADJECTIVE_NA_NAKUTE_MO_II_DESU("Nie trzeba"),

	ADJECTIVE_NA_MITAI_DESU("Wygląda, jak", "Bardziej używane z analizy sytuacji, do przymiotników częściej forma そうです"),

	ADJECTIVE_NA_TO("Kiedy A staje się, wtedy również B staje się",
			"Mówi o rzeczach oczywistych, z której druga wynika z pierwszej"),

	ADJECTIVE_NA_HAZU_DESU("Powinno być / Miało być"),

	ADJECTIVE_NA_QUESTIONS_WITH_LARGER_SENTENCES("Pytania w zdaniach"),

	ADJECTIVE_NA_SURU("Sprawiać, że coś jest ..."),

	ADJECTIVE_NA_NONI("Mimo że jest ..., to ..."),

	ADJECTIVE_NA_YOUNI("Podobny do ..., jak ..."),

	ADJECTIVE_NA_TE_MO("Nawet, jeśli"),
	ADJECTIVE_NA_TE_MO_NEGATIVE("Nawet, jeśli nie"),
	
	ADJECTIVE_NA_IMPERATIVE("Rozkaz (bądź)"),

	ADJECTIVE_NA_IMPERATIVE_NOT("Zakaz (nie bądź)"),
	
	NOUN_NI_NARU("Stawać się"),
	
	NOUN_SURU("Tworzenie czynności"),

	NOUN_LIKE("Lubić"),
	NOUN_DISLIKE("Nie lubić"),

	NOUN_NA_DESU("Forma wyjaśniająca"),

	NOUN_DESHOU("Prawdopodobnie, ok. 60%"),

	NOUN_HOSHII("Chcieć (I i II osoba)"),

	NOUN_HOSHIGATE_IRU("Chcieć (III osoba)"),

	NOUN_KAMOSHI_REMASEN("Prawdopodobnie, ok. 30%"),

	NOUN_AGERU("Dać (od siebie, ktoś komuś)"),

	NOUN_KURERU("Dać (mnie)"),

	NOUN_MORAU("Otrzymać"),

	NOUN_TO_II_TO_OTHERS("Mieć nadzieję, że ... (mówienie o innych)"),

	NOUN_TO_II_TO_ME("Mieć nadzieję, że ... (mówienie o sobie)"),

	NOUN_TOKI("Kiedy ..., to ..."),

	NOUN_SOU_DESU_HEAR("Słyszałem, że ..."),

	NOUN_TTE("Forma tte", "Zastępuje そうです oraz と言っていました"),

	NOUN_TARA("Jeśli (tryb warunkowy), to ..."),

	NOUN_MITAI_DESU("Wygląda, jak", "Bardziej używane z analizy sytuacji"),

	NOUN_TO("Kiedy A staje się, wtedy również B staje się",
			"Mówi o rzeczach oczywistych, z której druga wynika z pierwszej"),

	NOUN_HAZU_DESU("Powinnien być / Miał być"),

	NOUN_QUESTIONS_WITH_LARGER_SENTENCES("Pytania w zdaniach"),

	NOUN_TO_IU("Nazywanie przedmiotu"),

	NOUN_NO_AIDA_NI("Podczas"),

	NOUN_NONI("Mimo że jest ..., to ..."),

	NOUN_YOUNA("Podobny do ..., jak ..."),

	NOUN_TE_MO("Nawet, jeśli"),
	NOUN_TE_MO_NEGATIVE("Nawet, jeśli nie"),

	NOUN_NI_SURU("Decydować się na"),
	
	NOUN_IMPERATIVE("Rozkaz (bądź)"),
	
	NOUN_IMPERATIVE_NOT("Zakaz (nie bądź)"),

	VERB_TAI("Chcieć (I i II osoba)", "Forma wolitywna"),

	VERB_TAGARU("Chcieć (III osoba)", "Forma wolitywna"),

	VERB_SUGIRU("Zbyt wiele"),

	VERB_LIKE("Lubić"), VERB_DISLIKE("Nie lubić"),

	VERB_STEM_NI_IKU("Idę, aby ..."),

	VERB_TE_IRU("Trwanie czynności/stanu"),

	VERB_TE_KUDASAI("Prośba 1", "Honoryfikatywny tryb rozkazujący"),
	VERB_TE_KURE("Prośba 2", "Niehonoryfikatywny tryb rozkazujący"),
	VERB_TE_CHOUDAI("Prośba 3", "Niehonoryfikatywny tryb rozkazujący"),

	VERB_TE_MO_II("Pozwolenie 1", "Dosłowne: nawet jeśli coś zrobisz, będzie dobrze"),

	VERB_TE_HA_IKEMASEN("Zakaz 1"),

	VERB_NAI_DE_KUDASAI("Zakaz 2"),
	
	VERB_IMPERATIVE_NOT("Zakaz 3 (forma prohibitywna)"),

	VERB_MADA_TE_IMASEN("Jeszcze nie"),

	VERB_TSUMORI_DESU("Zamiar"),

	VERB_KOTO_GA_ARU("Mieć doświadczenie"),

	VERB_N_DESU("Forma wyjaśniająca"),

	VERB_ADVICE("Rada"),

	VERB_NAKUCHA_IKEMASEN("Musieć 1"),

	VERB_DESHOU("Prawdopodobnie, ok. 60%"),

	VERB_TE_MIRU("Próbować 1"),
	
	VERV_YOU_TO_SURU("Próbować 2 / Zamierzać / Usiłować"),

	VERB_KAMOSHI_REMASEN("Prawdopodobnie, ok. 30%"),

	VERB_TARA("Jeśli (tryb warunkowy), to ..."),
	
	VERB_TARI("Forma egzemplifikatywna"),
	
	VERB_TAROU("Forma przypuszczająca czasu przeszłego"),

	VERB_TARA_DOU_DESU_KA("Rada lub zalecenie", "Uwaga: Nie używane w zaproszeniach"),

	VERB_OU_TO_OMOU("Wola (decyzja podjęta w momencie mówienia)"),

	VERB_OU_TO_OMOTTE_IRU("Wola (decyzja podjęta wcześniej)"),

	VERB_TE_OKU("Przygotować się / Zrobić coś wcześniej / Zostawić coś (w danym stanie)", "Aspekt preparatywny"),

	VERB_TE_AGERU("Dać czynność (od siebie, ktoś komuś)"),

	VERB_TE_KURERU("Dać czynność (mnie)"),

	VERB_TE_MORAU("Otrzymać czynność"),

	VERB_REQUEST("Prośby II (wybrane, od najbardziej grzecznej)"),

	VERB_TO_II_TO_OTHERS("Mieć nadzieję, że ... (mówienie o innych)"),

	VERB_TO_II_TO_ME("Mieć nadzieję, że ... (mówienie o sobie)"),

	VERB_TOKI("Kiedy ..., to ..."),

	VERB_TE_ARIGATOU("Dziękuję, że zrobiłeś"),

	VERB_KUTE_ARIGATOU("Dziękuję, że nie zrobiłeś"),

	VERB_TE_SUMIMASEN("Przepraszam, że zrobiłem"),

	VERB_KUTE_SUMIMASEN("Przepraszam, że nie zrobiłem"),

	VERB_SOU_DESU_HEAR("Słyszałem, że ..."),
	
	VERB_SOU_DESU_LOOKS_LIKE("Wygląda na to, że ... (forma imperceptywna)"),

	VERB_TTE("Forma tte", "Zastępuje そうです oraz と言っていました"),

	VERB_NAKUTE_MO_II_DESU("Nie trzeba tego robić"),

	VERB_MITAI_DESU("Wygląda, jak", "Bardziej używane z analizy sytuacji"),

	VERB_MAE_NI("Przed czynnością, robię ..."),

	VERB_TE_KARA("Po czynności, robię ..."),

	VERB_TE_SHIMAU("Zrobić cos do końca / Niestety coś stało się"),

	VERB_TO("Kiedy A staje się, wtedy również B staje się",
			"Mówi o rzeczach oczywistych, z której druga wynika z pierwszej"),

	VERB_NAGARA("W trakcie czynności, robię ... (forma gerundialna)", "Musi być ten sam podmiot"),

	VERB_BA_YOKATTA("Dobrze byłoby, gdybym zrobił"),

	VERB_TE_YOKATTA("Dobrze, że zrobiłem / Cieszę się, że zrobiłem"),

	VERB_NAKUTE_YOKATTA("Dobrze, że nie zrobiłem / Cieszę się, że nie zrobiłem"),

	VERB_BA_NEGATIVE_YOKATTA("Dobrze byłoby, gdybym nie zrobił"),

	VERB_KEIGO_KUDASAI("Grzeczna prośba",
			"Nie używane dla dobra mówiącego; Uwaga na dużą nieregularność, którego aplikacja może nie uwzględniać"),

	VERB_HAZU_DESU("Powinnien / Miał"),

	VERB_NAI_DE("Nie robiąc, ... (forma adnegatywna)"),

	VERB_QUESTIONS_WITH_LARGER_SENTENCES("Pytania w zdaniach"),

	VERB_YASUI("Łatwo coś zrobić"),

	VERB_NIKUI("Trudno coś zrobić"),

	VERB_TE_ARU("Być wprowadzone w pewien stan", "Aspekt rezultatywny"),

	VERB_TE_IRU_AIDA_NI("Podczas"),

	VERB_TE_HOSHII("Chcieć czegoś od kogoś"),

	VERB_NEGATIVE_TE_HOSHII("Chcieć, aby ktoś czegoś nie zrobił"),

	VERB_MAKE_LET("Zmuszać kogoś do zrobienia czegoś / Sprawić, że ktoś coś zrobi"),

	VERB_LET("Pozwalać komuś na zrobienie czegoś"),

	VERB_IMPERATIVE("Rozkaz 1"),
	
	VERB_NASAI("Rozkaz 2", "Forma przestarzała"),

	VERB_EBA("Jeśli ..., wtedy ..."),

	VERB_NONI("Mimo że ..., to ..."),

	VERB_TE_MO("Nawet, jeśli"),
	VERB_TE_MO_NEGATIVE("Nawet, jeśli nie"),

	VERB_KOTO_NI_SURU("Decydować się na"),

	VERB_KOTO_NI_SHITE_IRU("Starać się regularnie wykonywać"),

	VERB_MADE("Dopóki nie zrobię", "Czasownik przed made określa coś pozytywnego"),

	VERB_KATA(
			"Sposób robienia / Sposób użycia / Jak coś zrobić",
			"Uwaga: (1) aplikacja nie uwzględnia zmiany partykuły に na の w złożonych czasownikach; (2) aplikacja nie uwzględnia zmiany "
					+ "partykuły に lub へ na への stojącej przed czasownikiem w czasownikach określających ruch."),

	VERB_TE_KUDASARU("Robić coś (dla kogoś)"),

	VERB_TE_GORAN_NASAI("Proszę spróbuj", "Uwaga: nie używane wobec osób o wyższym statusie"),

	VERB_TE_MO_KAMAWANAI("Pozwolenie 2"),

	VERB_NAKUTE_MO_KAMAWANAI("Nie jest konieczne (potrzebne)"),

	VERB_NAKEREBA_NARANAI_NAKUTE_WA_NARANAI("Musieć 2 / Trzeba"),

	VERB_KA_MO_SHIRENAI("Być może"),
	
	VERB_MAI("Forma negatywno-przypuszczająca");

	// nakereba naranai
	// nakute wa naranai

	private String name;

	private String info;

	ExampleGroupType(String name) {
		this.name = name;
	}

	ExampleGroupType(String name, String info) {
		this.name = name;
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public String getInfo() {
		return info;
	}
}
