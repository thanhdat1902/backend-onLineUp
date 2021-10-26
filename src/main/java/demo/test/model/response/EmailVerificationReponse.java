package demo.test.model.response;

import java.io.Serializable;

public class EmailVerificationReponse implements Serializable {
    public String email;
    public String accessToken;
    public String id;

    public EmailVerificationReponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }
}
