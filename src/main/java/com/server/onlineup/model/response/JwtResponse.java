package com.server.onlineup.model.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
//    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }


    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }


}
