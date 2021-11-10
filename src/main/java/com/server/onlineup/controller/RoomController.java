package com.server.onlineup.controller;

import com.server.onlineup.model.entity.RoomEntity;
import com.server.onlineup.service.business.room.RoomBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomBiz roomBizService;

    @PostMapping("/create-room")
    public ResponseEntity createRoom(@RequestBody RoomEntity room) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return roomBizService.handleCreateRoom(user, room);
    }
}
