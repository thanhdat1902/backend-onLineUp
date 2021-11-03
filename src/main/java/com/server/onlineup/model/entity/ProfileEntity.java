package com.server.onlineup.model.entity;

import com.server.onlineup.common.utils.NumberUtils;

import javax.persistence.*;

@Entity
@Table(name = "Profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column
    private String id_facebook;

    @Column
    private String fcm_token;

    public String getFb_id() {
        return id_facebook;
    }

    public void setFb_id(String fb_id) {
        this.id_facebook = fb_id;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ProfileEntity() {
    }

    public ProfileEntity(String email, String id) {
        this.email = email;
        this.id_facebook = id;
        this.password = NumberUtils.generateRandomString((int) (Math.floor(Math.random())));
        this.fullName = "Unknown";
        this.roles = "USER";
    }

    public ProfileEntity(String email, String fullName, String password) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roles = "USER";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String roles;

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "users_roles",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }
}