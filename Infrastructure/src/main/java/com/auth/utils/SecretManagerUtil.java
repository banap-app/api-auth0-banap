package com.auth.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecretManagerUtil {

    @Autowired
    @Value("${secretmanager.accessKey}")
    private String accessKey;

    @Autowired
    @Value("${secretmanager.secretKey}")
    private String secretKey;

    public SecretManagerUtil() {
        // Construtor vazio
    }

    @Autowired
    public void init() {
        if (accessKey == null || accessKey.isEmpty()) {
            throw new IllegalArgumentException("AccessKey must not be null or empty");
        }
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("SecretKey must not be null or empty");
        }
    }

    public String getSecret(String secretName, Region region) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(getAccessKey(), getSecretKey());
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving secret: " + e.getMessage(), e);
        }
        String jsonString = getSecretValueResponse.secretString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing JSON: " + e.getMessage(), e);
        }

        Map<String, String> secretMap = new HashMap<>();
        secretMap.put("JWT_SECRET", jsonNode.get("JWT_SECRET").asText());

        return secretMap.get("JWT_SECRET");
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}