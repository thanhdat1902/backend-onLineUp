package com.server.onlineup.service.provider;

import com.server.onlineup.model.response.EmailVerificationReponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestService {

    public final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public EmailVerificationReponse getPostsPlainJSON() {
        String url = "https://graph.facebook.com/me?fields=email&access_token=GGQVlaMU5DYXg0M2tyTVJhZAXlBTTNpMDBTWktLVzM1T2VyZA3VnNk5ObkhXbzJOYWF2OHJQRk05dG1NSnpUTW9fVV94N2RGR0s5clBMRzVZAclVKR3UxbHpXb1VPMlByWTVKTHdqbHJjWkpnWUpJVWtiZAElNUVdHdXl5NTN0NUdPX0hGWjVnTU50eS1UckR3ck1SY3J3b3V0TTdLRmZAUbFEZD";
        return this.restTemplate.getForObject(url, EmailVerificationReponse.class);
    }

    public EmailVerificationReponse requestProfileFromFbToken(String token) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://graph.facebook.com/me")
                .queryParam("fields", "email")
                .queryParam("access_token", token)
                .build()
                .toUri();
        try {
            return this.restTemplate.getForObject(uri, EmailVerificationReponse.class);
        } catch (RestClientException e) {
            return null;
        }
    }
}