package com.server.onlineup.service.provider.universallink.instances;


import com.server.onlineup.model.entity.UniversalLinkEntity;
import com.server.onlineup.service.provider.universallink.constant.UniversalLinkConstant;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class UniversalLinkInstance implements UniversalLinkInstanceImpl {
    private JSONObject params;
    private String code;

    public UniversalLinkInstance() {
        this.params = new JSONObject();
        this.addType(getUniversalLinkType());
    }

    public UniversalLinkInstance(UniversalLinkEntity entity) {
        try {
            this.params = new JSONObject(entity != null ? entity.params : "{}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public UniversalLinkInstance(UniversalLinkInstance that) {
        this.params = that.params;
        this.code = that.code;
    }

    public UniversalLinkInstanceImpl cast() {
        switch (this.getType()) {
            case UniversalLinkConstant.ROOM_DETAIL:
                return new UniversalLinkRoomDetail(this);
            default:
                return this;
        }
    }

    @Override
    public String getUniversalLinkType() {
        return "";
    }

    @Override
    public CompletableFuture<Boolean> isAbleToRead() {
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletableFuture<Object> onConsequentAction() {
        return CompletableFuture.completedFuture(null);
    }

    public void setParamsFromString(String strParam) throws JSONException {
        this.params = new JSONObject(strParam);
    }

    public JSONObject getAllParams() {
        return this.params;
    }

    public void addType(String type) {
        this.addParam(UniversalLinkConstant.FIELD_TYPE, type);
    }

    public String getType() {
        return String.valueOf(this.getParam(UniversalLinkConstant.FIELD_TYPE));
    }

    public void addParam(String field, Object value) {
        try {
            if (this.params == null) return;
            this.params.put(field, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public UniversalLinkInstance appendParam(String field, Object value) {
        this.addParam(field, value);
        return this;
    }

    public Object getParam(String field) {
        try {
            return (this.params != null && this.params.has(field))
                    ? this.params.get(field)
                    : null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UniversalLinkEntity buildEntity() {
        UniversalLinkEntity entity = new UniversalLinkEntity(this.params.toString());
        this.code = entity.id;
        return entity;
    }

    public String getLink() {
        URI uri = UriComponentsBuilder
                .fromUriString(UniversalLinkConstant.BASE_LINK)
                .queryParam("code", this.code)
                .build()
                .toUri();
        return uri.toString();
    }

}
