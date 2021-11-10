package com.server.onlineup.service.business.room;

import com.server.onlineup.common.constant.AuthenticationEnum;
import com.server.onlineup.common.constant.RoomEnum;
import com.server.onlineup.common.exception.APIException;
import com.server.onlineup.common.response.BaseResponse;
import com.server.onlineup.model.entity.ProfileEntity;
import com.server.onlineup.model.entity.RoomEntity;
import com.server.onlineup.service.database.ProfileService;
import com.server.onlineup.service.database.RoomAdminService;
import com.server.onlineup.service.database.RoomService;
import com.server.onlineup.service.database.RoomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class RoomBiz {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomAdminService roomAdminService;
    @Autowired
    private RoomUserService roomUserService;
    @Autowired
    private ProfileService profileService;

    public ResponseEntity handleCreateRoom(UserDetails user, RoomEntity room) {
        // Load userDetail to profileUser
        Optional<ProfileEntity> currentUser = profileService.findByUsername(user.getUsername());
        if(!currentUser.isPresent()) {
            throw new APIException(BaseResponse.Builder().addMessage(AuthenticationEnum.INVALID_EMAIL));
        }

        // Add current user to admin
        room.addHost(currentUser.get());

        // Create room entity
        roomService.save(room);


        return BaseResponse.Builder()
                .addMessage(RoomEnum.CREATE_SUCCESS)
                .build();
    }
    public ResponseEntity handleJoinRoom(UserDetails user, RoomEntity room) {


        return BaseResponse.Builder()
                .addMessage(RoomEnum.JOIN_SUCCESS)
                .build();
    }
    public ResponseEntity handleAddCoHost(UserDetails user, String roomId) {
        return BaseResponse.Builder()
                .addMessage(RoomEnum.JOIN_SUCCESS)
                .build();
    }
}
