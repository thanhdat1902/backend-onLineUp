package com.server.onlineup.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private String fb_id;

    @Column
    private String fcm_token;

    @OneToMany(mappedBy = "profileEntity",cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<RoomUserEntity> roomAsUser = new HashSet<RoomUserEntity>();

    @OneToMany(mappedBy = "profileEntity",cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<RoomAdminEntity> roomAsAdmin = new HashSet<RoomAdminEntity>();

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
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





    public void addAdminRoom(RoomEntity room) {
        RoomAdminEntity roomAdminEntity = new RoomAdminEntity(this, room);
        roomAsAdmin.add(roomAdminEntity);
    }
    public void addUserRoom(RoomEntity room) {
        RoomUserEntity roomUserEntity = new RoomUserEntity(this, room);
        roomAsUser.add(roomUserEntity);
    }



    // Getter & Setter
    public ProfileEntity() {}

    public ProfileEntity(String email, String id) {
        this.email = email;
        this.fb_id = id;
        this.password = "TmPpAsS";
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