package com.server.onlineup.model.entity;

import com.server.onlineup.common.utils.TimeUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "universal_link")
public class UniversalLinkEntity {
    @Id
    public String id;
    public String params;
    public long created_time;

    public UniversalLinkEntity() {
    }

    public UniversalLinkEntity(String params) {
        this.created_time = TimeUtils.getCurrentTimestamp();
        this.id = UUID.randomUUID().toString();
        this.params = params;
    }

}