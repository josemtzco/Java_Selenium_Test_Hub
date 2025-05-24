package com.jmtech.core.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Properties;

public class RestAssuredFactory {
    private static final ConcurrentHashMap<Long, Map<String, RequestSpecification>> restAssuredMap = new ConcurrentHashMap<>();
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = RestAssuredFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }

    // Default service configurations
    private static final Map<String, String> DEFAULT_SERVICES = Map.of(
            "main", properties.getProperty("api.main.url", "http://localhost:8080"),
            "auth", properties.getProperty("api.auth.url", "http://localhost:8081"),
            "payment", properties.getProperty("api.payment.url", "http://localhost:8082"));

    private RestAssuredFactory() {
        // Private constructor to prevent instantiation
    }

    public static synchronized void createRestAssured() {
        Long threadId = Thread.currentThread().threadId();
        if (!restAssuredMap.containsKey(threadId)) {
            Map<String, RequestSpecification> serviceSpecs = new ConcurrentHashMap<>();

            // Initialize all services
            DEFAULT_SERVICES.forEach((service, baseUrl) -> {
                RequestSpecification requestSpec = RestAssured.given()
                        .baseUri(baseUrl)
                        .contentType("application/json")
                        .accept("application/json");
                serviceSpecs.put(service, requestSpec);
            });

            restAssuredMap.put(threadId, serviceSpecs);
        }
    }

    public static RequestSpecification getRestAssured(String service) {
        Long threadId = Thread.currentThread().threadId();
        Map<String, RequestSpecification> serviceSpecs = restAssuredMap.get(threadId);

        if (serviceSpecs == null) {
            throw new IllegalStateException("REST Assured has not been initialized. Call createRestAssured first.");
        }

        RequestSpecification spec = serviceSpecs.get(service);
        if (spec == null) {
            throw new IllegalArgumentException(
                    "Service '" + service + "' not found. Available services: " + serviceSpecs.keySet());
        }

        return spec;
    }

    public static void addService(String serviceName, String baseUrl) {
        Long threadId = Thread.currentThread().threadId();
        Map<String, RequestSpecification> serviceSpecs = restAssuredMap.get(threadId);

        if (serviceSpecs == null) {
            throw new IllegalStateException("REST Assured has not been initialized. Call createRestAssured first.");
        }

        RequestSpecification requestSpec = RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json");

        serviceSpecs.put(serviceName, requestSpec);
    }

    public static void resetRestAssured() {
        Long threadId = Thread.currentThread().threadId();
        restAssuredMap.remove(threadId);
    }
}