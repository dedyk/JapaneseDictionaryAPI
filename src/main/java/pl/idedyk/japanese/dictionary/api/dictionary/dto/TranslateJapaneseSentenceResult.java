package pl.idedyk.japanese.dictionary.api.dictionary.dto;

import java.util.ArrayList;
import java.util.List;

public class TranslateJapaneseSentenceResult {
	
	private List<Token> tokenList = new ArrayList<Token>();

	public void addToken(TokenType tokenType, String token, int startIdx, int endIdx, FindWordResult findWordResult) {		
		tokenList.add(new Token(tokenType, token, startIdx, endIdx, findWordResult));		
	}
	
	public List<Token> getTokenList() {
		return tokenList;
	}

	//

	public static class Token {
		
		private TokenType tokenType;
		
		private int startIdx = 0;
		private int endIdx = 0;
		
		private String token;
		
		private FindWordResult findWordResult;

		private Token(TokenType tokenType, String token, int startIdx, int endIdx, FindWordResult findWordResult) {
			
			this.tokenType = tokenType;
			
			this.startIdx = startIdx;
			this.endIdx = endIdx;
			
			this.token = token;
			
			this.findWordResult = findWordResult;
		}

		public TokenType getTokenType() {
			return tokenType;
		}

		public int getStartIdx() {
			return startIdx;
		}

		public int getEndIdx() {
			return endIdx;
		}

		public String getToken() {
			return token;
		}

		public FindWordResult getFindWordResult() {
			return findWordResult;
		}
	}
	
	public static enum TokenType {
		
		FOUND,
		
		UNKNOWN;		
	}
}
