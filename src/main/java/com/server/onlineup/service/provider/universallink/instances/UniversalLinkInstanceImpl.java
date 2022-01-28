package com.server.onlineup.service.provider.universallink.instances;

import java.util.concurrent.CompletableFuture;

public interface UniversalLinkInstanceImpl {
    String getUniversalLinkType();

    CompletableFuture<Boolean> isAbleToRead();

    CompletableFuture<Object> onConsequentAction();
}
