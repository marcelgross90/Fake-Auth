package de.fhws.fiw.fwpm.auth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelgross on 03.09.16.
 */
public class TokenCache {

	private static TokenCache instance;

	public static TokenCache getInstance() {
		if (instance == null) {
			instance = new TokenCache();
		}

		return instance;
	}

	private List<String> tokens;

	private TokenCache() {
		this.tokens = new ArrayList<>();
	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}

	public void addToken(String token) {
		this.tokens.add(token);
	}

	public boolean isInCache(String token) {
		return this.tokens.contains(token);
	}
}
