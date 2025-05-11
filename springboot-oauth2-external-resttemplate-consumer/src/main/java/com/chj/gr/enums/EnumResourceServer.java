package com.chj.gr.enums;

/**
 * springboot-oauth2-client1-*.yml
 */
public enum EnumResourceServer {
	STS_OAUTH2_CLIENT1_RESOURCE_SERVER_READ_WRITE("client0",  "sts-oauth2-client1-resource-server-read-write"),
	STS_OAUTH2_CLIENT2_RESOURCE_SERVER_READ_WRITE("client1",  "sts-oauth2-client2-resource-server-read-write");;

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
