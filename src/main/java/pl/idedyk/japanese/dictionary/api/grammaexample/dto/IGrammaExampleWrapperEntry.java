package pl.idedyk.japanese.dictionary.api.grammaexample.dto;

import java.io.Serializable;

public interface IGrammaExampleWrapperEntry extends Serializable {
	
	public String getPrefixKana();
	public String getPrefixRomaji();
	
	public String getKanji();
	public String getKana();
	public String getRomaji();

}
