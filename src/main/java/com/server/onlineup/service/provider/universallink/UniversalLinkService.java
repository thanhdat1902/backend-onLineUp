package com.server.onlineup.service.provider.universallink;

import com.server.onlineup.common.constant.UniversalLinkEnum;
import com.server.onlineup.common.exception.APIException;
import com.server.onlineup.common.response.BaseResponse;
import com.server.onlineup.model.entity.UniversalLinkEntity;
import com.server.onlineup.repository.UniversalLinkRepository;
import com.server.onlineup.service.provider.universallink.instances.UniversalLinkInstance;
import com.server.onlineup.service.provider.universallink.instances.UniversalLinkInstanceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UniversalLinkService {

    @Autowired
    UniversalLinkRepository repository;

    public ResponseEntity getParams(String code) {

        UniversalLinkEntity entity = repository.findByCodeAsync(code).join();
        UniversalLinkInstanceImpl instance = new UniversalLinkInstance(entity).cast();

        if (entity == null || !instance.isAbleToRead().join()) {
            catchUniversalLinkError();
        }

        return instance.onConsequentAction().thenApply((res) ->
                BaseResponse.Builder()
                        .addMessage(UniversalLinkEnum.SUCCESS)
                        .addData((res != null) ? res : entity.params)
                        .build()
        ).join();

    }

    public String create(UniversalLinkInstance instance) {
        UniversalLinkEntity entity = instance.buildEntity();
        repository.saveAsync(entity).join();
        return instance.getLink();
    }

    private void catchUniversalLinkError() {
        throw new APIException(BaseResponse.Builder()
                .addErrorStatus(HttpStatus.BAD_REQUEST)
                .addMessage(UniversalLinkEnum.INVALID)
        );
    }
}
