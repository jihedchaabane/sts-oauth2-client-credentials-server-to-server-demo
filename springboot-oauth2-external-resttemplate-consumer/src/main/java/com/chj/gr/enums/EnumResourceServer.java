package com.chj.gr.enums;

/**
 * springboot-oauth2-client1-*.yml
 */
public enum EnumResourceServer {
	STS_DEFAULT_REGISTRATION(						"client0",  "sts-default-registration"),
	STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION("client1",  "sts-oauth2-client1-resource-server-registration"),
	STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION("client2",  "sts-oauth2-client2-resource-server-registration");

    private final String key;
    private final String registrationId;

    EnumResourceServer(String key, String registrationId) {
        this.key = key;
        this.registrationId = registrationId;
    }

	public String getKey() {
		return key;
	}

	public String getRegistrationId() {
		return registrationId;
	}
}
