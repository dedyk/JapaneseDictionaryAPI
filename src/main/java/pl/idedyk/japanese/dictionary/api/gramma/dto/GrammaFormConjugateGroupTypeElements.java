package pl.idedyk.japanese.dictionary.api.gramma.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class GrammaFormConjugateGroupTypeElements implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	int fixme = 1; // FM_FIXME: do usuniecia

	private GrammaFormConjugateGroupType grammaFormConjugateGroupType;
	
	private List<GrammaFormConjugateResult> grammaFormConjugateResults;

	public GrammaFormConjugateGroupType getGrammaFormConjugateGroupType() {
		return grammaFormConjugateGroupType;
	}

	public List<GrammaFormConjugateResult> getGrammaFormConjugateResults() {
		
		if (grammaFormConjugateResults == null) {
			grammaFormConjugateResults = new ArrayList<GrammaFormConjugateResult>();
		}
		
		return grammaFormConjugateResults;
	}

	public void setGrammaFormConjugateGroupType(GrammaFormConjugateGroupType grammaFormConjugateGroupType) {
		this.grammaFormConjugateGroupType = grammaFormConjugateGroupType;
	}
}
