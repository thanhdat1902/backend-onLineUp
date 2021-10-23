package demo.test.model.response;

import java.util.Date;

public class JwtResponse {
    private int id;
    private String token;
    private String type = "Bearer";
    private String email;
    private String name;
    private Date exp;
//    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String accessToken, int id, String email, String name, Date exp) {
        this.token = accessToken;
        this.email = email;
        this.name = name;
        this.id = id;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

}
