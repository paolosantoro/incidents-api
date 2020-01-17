package com.numerico.api.incidents.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class KeycloakAuthenticationToken extends UsernamePasswordAuthenticationToken {
	
	private static final long serialVersionUID = -6849429397778143002L;
	
	private Map<String, Object> userAttributes;

	public KeycloakAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
		this.userAttributes = new HashMap<>();
	}

	public KeycloakAuthenticationToken(Object principal, Object credentials,
			Map<String, Object> userAttributes, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.userAttributes = userAttributes;
	}

	public Map<String, Object> getUserAttributes() {
		return userAttributes;
	}
}
