package demo.test.model.response;

public class LoginResponse {
    private String accessToken;
    private String exp;
    public LoginResponse(String accessToken, String exp ) {
        this.accessToken = accessToken;
        this.exp = exp;
    }
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
