
package com.anishd1.moneymanager.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final RestClient.Builder restClientBuilder;

    @Value("${brevo.api.url}")
    private String apiUrl;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String fromEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    public void sendEmail(String to, String subject, String body) {
        try {
            sendMessage(to, subject, body, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendEmailWithAttachment(
            String to,
            String subject,
            String body,
            byte[] attachment,
            String filename
    ) throws MessagingException {

        try {
            sendMessage(to, subject, body, attachment, filename);
        } catch (RestClientException e) {
            throw new MessagingException(
                    "Failed to send email with attachment", e
            );
        }
    }

    private void sendMessage(
            String to,
            String subject,
            String body,
            byte[] attachment,
            String filename
    ) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("sender", Map.of(
                "name", senderName,
                "email", fromEmail
        ));

        payload.put("to", List.of(
                Map.of("email", to)
        ));

        payload.put("subject", subject);
        payload.put("textContent", body);

        if (attachment != null && attachment.length > 0) {
            String encodedAttachment =
                    Base64.getEncoder().encodeToString(attachment);

            payload.put("attachment", List.of(
                    Map.of(
                            "name", filename,
                            "content", encodedAttachment
                    )
            ));
        }

        restClientBuilder.build()
                .post()
                .uri(apiUrl)
                .header("api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .toBodilessEntity();
    }
}
