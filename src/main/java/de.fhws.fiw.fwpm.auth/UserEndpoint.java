package de.fhws.fiw.fwpm.auth;

import de.fhws.fiw.fwpm.auth.model.User;
import de.fhws.fiw.fwpm.auth.model.UserFactory;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Path("/user")
public class UserEndpoint {

	private TokenCache tokenCache;

	@PostConstruct
	public void init() {
		tokenCache = TokenCache.getInstance();
	}

	@GET
	@Path("/{kNr}")
	public Response me(@PathParam("kNr") String knr, @HeaderParam("X-fhws-jwt-token") String token, @Context HttpHeaders headers) {

		isValidRequest(token, headers);

		Optional<User> user = Optional.empty();
		Optional<String> newToken = Optional.empty();

		if (tokenCache.isInCache(token)) {
			if (knr.equalsIgnoreCase("me")) {
				user = Optional.ofNullable(UserFactory.getUser(getRequester(token)));
			}
			else {
				user = Optional.ofNullable(UserFactory.getUser(knr));
			}
		}

		if (!tokenCache.isInCache(token) && headers.getRequestHeader(HttpHeaders.AUTHORIZATION).size() != 0) {
			newToken = Optional.ofNullable(login(getBasicUser(headers.getRequestHeader(HttpHeaders.AUTHORIZATION))));
			if (knr.equalsIgnoreCase("me")) {
				user = Optional.ofNullable(UserFactory.getUser(getBasicUser(headers.getRequestHeader(HttpHeaders.AUTHORIZATION))));
			} else {
				user = Optional.ofNullable(UserFactory.getUser(knr));
			}
		}

		if (user.isPresent()) {
			Response response;
			if (newToken.isPresent()){
				response = Response.ok(user.get()).header("X-fhws-jwt-token", newToken.get()).build();
			} else {
				response = Response.ok(user.get()).header("X-fhws-jwt-token", token).build();
			}
			return response;
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}



	private String login(String basicUser) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("tokenPart.");
		stringBuilder.append(newToken(basicUser));
		stringBuilder.append(".tokenPart");

		tokenCache.addToken(stringBuilder.toString());
		return stringBuilder.toString();
	}

	private String newToken(String basicUser) {
		Instant exp = Instant.now().plusSeconds(30l * 60l);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("exp", exp.getEpochSecond());
		jsonObject.put("sub", basicUser);

		StringBuilder token = new StringBuilder();
		token.append("{\"exp\":");
		token.append(exp.getEpochSecond());
		token.append(",\"sub\":\"");
		token.append(basicUser);
		token.append("\"}");

		return Base64.getEncoder().encodeToString(token.toString().getBytes());
	}

	private void isValidRequest(String token, HttpHeaders headers) {
		if (token == null && headers.getRequestHeader(HttpHeaders.AUTHORIZATION).size() == 0) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}

		if (!tokenCache.isInCache(token) && headers.getRequestHeader(HttpHeaders.AUTHORIZATION).size() == 0) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}


	private String getBasicUser(List<String> authHeaders) {
		String userBase64 ="";
		for (String authHeader : authHeaders) {
			if (authHeader.startsWith("Basic ")) {
				userBase64 = authHeader.split(" ")[1];
				break;
			}
		}
		return new String(Base64.getDecoder().decode(userBase64)).split(":")[0];

	}


	private String getRequester(String token) {
		if (token.isEmpty())
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);

		String[] tokenAsStrings = token.split("\\.");
		if (tokenAsStrings.length != 3) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}

		byte[] tokenContent =  Base64.getDecoder().decode(tokenAsStrings[1]);

		JSONObject object = new JSONObject(new String(tokenContent));

		return object.getString("sub");
	}

}
