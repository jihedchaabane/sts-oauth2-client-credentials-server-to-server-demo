package com.chj.gr.enums;

/**
 * sts-spring-boot-resource-server-*.yml
 */
public enum EnumResourceServer {
	STS_SPRING_BOOT_RESOURCE_SERVER_READ_WRITE("client0",  "sts-spring-boot-resource-server-read-write");

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
