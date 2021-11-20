package com.server.onlineup.service.provider.universallink.instances;

import com.server.onlineup.service.provider.universallink.constant.UniversalLinkConstant;

import java.util.concurrent.CompletableFuture;

public class UniversalLinkRoomDetail extends UniversalLinkInstance implements UniversalLinkInstanceImpl {

    public UniversalLinkRoomDetail(String roomId) {
        super();
        this.addParam("roomId", roomId);
    }

    public UniversalLinkRoomDetail(UniversalLinkInstance that) {
        super(that);
    }

    @Override
    public String getUniversalLinkType() {
        return UniversalLinkConstant.ROOM_DETAIL;
    }

    @Override
    public CompletableFuture<Boolean> isAbleToRead() {
        return super.isAbleToRead();
    }

    @Override
    public CompletableFuture<Object> onConsequentAction() {
        return super.onConsequentAction();
    }
}
