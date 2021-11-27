package com.server.onlineup.controller;

import com.server.onlineup.model.request.UniversalLinkRequest;
import com.server.onlineup.service.provider.universallink.UniversalLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping(path = "/link")
public class UniversalLinkController {
    @Autowired
    private UniversalLinkService universalLinkService;

    @PostMapping(path = "/params")
    public ResponseEntity getParams(@RequestBody UniversalLinkRequest req) {
        return universalLinkService.getParams(req.code);
    }
}
