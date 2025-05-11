package com.chj.gr.properties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "caller")
public class CallerDestinationProperties {
	
	private Map<String, DestinationClient> destination = new HashMap<>();

	public Map<String, DestinationClient> getDestination() {
		return destination;
	}
	public void setDestination(Map<String, DestinationClient> destination) {
		this.destination = destination;
	}
	
	private DestinationClient checkDestinationClient(String key) {
		Optional<DestinationClient> opClient = Optional.of(this.destination.get(key));
		DestinationClient destinationClient = opClient.orElseThrow(
				() -> new RuntimeException("Client to call not fount!"));
		
		Optional<String> opResourceUri = opClient.map(DestinationClient::getResourceUri);
		opResourceUri.orElseThrow(
				() -> new RuntimeException("Client ResourceUri not provided!"));
		
		if (StringUtils.isNotEmpty(destinationClient.getClientId())
				&& StringUtils.isNotEmpty(destinationClient.getClientSecret())
				&& StringUtils.isNotEmpty(destinationClient.getRegistrationId())
		) {
			Optional<String> opClienId = opClient.map(DestinationClient::getClientId);
			opClienId.orElseThrow(
					() -> new RuntimeException("Client ClientId not provided!"));
			
			Optional<String> opClientSecret = opClient.map(DestinationClient::getRegistrationId);
			opClientSecret.orElseThrow(
					() -> new RuntimeException("Client ClientSecret not provided!"));
			
			Optional<String> opRegistrationId = opClient.map(DestinationClient::getRegistrationId);
			opRegistrationId.orElseThrow(
					() -> new RuntimeException("Client RegistrationId not provided!"));
		}
		return destinationClient;
	}
	public DestinationClient getDestinationClient(String key) {
		DestinationClient DestinationClient = this.checkDestinationClient(key);
		return DestinationClient;
	}
	
	public boolean hasAtLeastOneSecuredClient() {
		if (this.destination != null && !this.destination.isEmpty()) {
			for (Map.Entry<String, DestinationClient> entry : this.destination.entrySet()) {
				DestinationClient DestinationClient = entry.getValue();
				if (   StringUtils.isNotEmpty(DestinationClient.getClientId())
					&& StringUtils.isNotEmpty(DestinationClient.getClientSecret())
					&& StringUtils.isNotEmpty(DestinationClient.getRegistrationId())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public io.swagger.v3.oas.models.security.Scopes getSwaggerScopes() {
		io.swagger.v3.oas.models.security.Scopes swaggerScopes = null;
		if (this.hasAtLeastOneSecuredClient()
				&& this.destination != null
				&& !this.destination.isEmpty()) {
			for (Map.Entry<String, DestinationClient> entry : this.destination.entrySet()) {
				DestinationClient DestinationClient = entry.getValue();
				if (StringUtils.isNotEmpty(DestinationClient.getScopes())) {
					for (Iterator<String> iterator = Arrays.asList(
							DestinationClient.getScopes().split(",")).iterator(); iterator.hasNext();) {
						String scope = iterator.next();
						if (swaggerScopes == null) {
							swaggerScopes = new io.swagger.v3.oas.models.security.Scopes();
						}
						swaggerScopes.addString(
								scope, new StringBuilder(scope.toUpperCase())
											.append(" access for [")
											.append(DestinationClient.getRegistrationId())
											.append("]").toString());
					}
				}
			}
		}
		return swaggerScopes;
	}
	
	public static class DestinationClient {
		private String resourceUri;
		private String clientId;
		private String clientSecret;
		private String scopes;
		private String registrationId;
		
		public DestinationClient() {
			super();
		}

		public String getResourceUri() {
			return resourceUri;
		}

		public void setResourceUri(String resourceUri) {
			this.resourceUri = resourceUri;
		}

		public String getClientId() {
			return clientId;
		}

		public void setClientId(String clientId) {
			this.clientId = clientId;
		}

		public String getClientSecret() {
			return clientSecret;
		}

		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}

		public String getScopes() {
			return scopes;
		}

		public void setScopes(String scopes) {
			this.scopes = scopes;
		}

		public String getRegistrationId() {
			return registrationId;
		}

		public void setRegistrationId(String registrationId) {
			this.registrationId = registrationId;
		}
	}
}