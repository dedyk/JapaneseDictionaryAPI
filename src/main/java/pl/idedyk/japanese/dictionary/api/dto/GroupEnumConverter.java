package pl.idedyk.japanese.dictionary.api.dto;

public class GroupEnumConverter {

	public static GroupEnum unmarshal(String value) {
		return GroupEnum.getGroupEnum(value);
	}

	public static String marshal(GroupEnum value) {
		return value.getValue();
	}
}
