package com.server.onlineup.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "test_obj_2")
public class JavaObj {
    @Id
    public String id;

    public String name;

    public JavaObj() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}