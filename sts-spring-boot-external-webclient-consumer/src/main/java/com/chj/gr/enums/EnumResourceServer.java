package com.chj.gr.enums;

/**
 * sts-spring-boot-external-webclient-consumer-*.yml
 */
public enum EnumResourceServer {
	STS_SPRING_BOOT_RESOURCE_SERVER_READ_WRITE("client0",  "sts-spring-boot-resource-server-read-write"),
	STS_SPRING_BOOT_RESOURCE_SERVER_READ      ("client00", "sts-spring-boot-resource-server-read"),
	STS_SPRING_BOOT_RESOURCE_SERVER_WRITE     ("client01", "sts-spring-boot-resource-server-write"),
	STS_SPRING_BOOT_RESOURCE_SERVER_NONE      ("client02", "sts-spring-boot-resource-server-none"),
	
	CLIENT1_RESOURCE_SERVER(		"client1", "oauth2-client1-resource-server"),
	CLIENT2_RESOURCE_SERVER(		"client2", "oauth2-client2-resource-server"),
	CLIENT3_RESOURCE_SERVER(		"client3", "oauth2-client3-resource-server");

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
