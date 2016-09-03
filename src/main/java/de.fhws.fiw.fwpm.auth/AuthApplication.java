package de.fhws.fiw.fwpm.auth;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class AuthApplication extends ResourceConfig {

	public AuthApplication() {
		super();
		registerClasses(getResourceClasses());
	}

	public Set<Class<?>> getResourceClasses() {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(UserEndpoint.class);
		return classes;
	}
}