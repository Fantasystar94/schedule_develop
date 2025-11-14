package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.UserReq.UserCreateReq;
import com.example.schedule_develop.dto.UserReq.UserLoginReq;
import com.example.schedule_develop.dto.UserReq.UserPutReq;
import com.example.schedule_develop.dto.UserRes.UserDelRes;
import com.example.schedule_develop.dto.UserRes.UserResData;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/schedule_Develop/users")
    public ResponseEntity<GlobalResponse<UserResData>> postApi(@RequestBody UserCreateReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(HttpStatus.CREATED.value(),"postResponse",req));
    }

    @GetMapping("/schedule_Develop/users")
    public ResponseEntity<GlobalResponse<List<UserResData>>> getApi() {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.findAll(HttpStatus.CREATED.value(),"getResponse"));
    }

    @GetMapping("/schedule_Develop/users/{id}")
    public ResponseEntity<GlobalResponse<UserResData>> getApiDetail(@PathVariable(required = false) Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.findDetail(HttpStatus.CREATED.value(),"getResponse", id));
    }

    @PutMapping("/schedule_Develop/users/{id}")
    public ResponseEntity<GlobalResponse<UserResData>> putApi(@PathVariable(required = false) Long id, @RequestBody UserPutReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.put(HttpStatus.CREATED.value(),"putResponse", id, req));
    }

    @DeleteMapping("/schedule_Develop/users/{id}")
    public ResponseEntity<GlobalResponse<UserDelRes>> putApi(@PathVariable(required = false) Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.delete(HttpStatus.NO_CONTENT.value(),"deleteResponse", id));
    }

    @PostMapping("/schedule_Develop/users/login")
    public ResponseEntity<GlobalResponse<UserResData>> login(@RequestBody UserLoginReq req, HttpServletRequest request) {

        try{
            GlobalResponse<UserResData> user = userService.login(req.getEmail(),req.getPassword());
            HttpSession session = request.getSession(true);
            session.setAttribute("LOGIN_USER",user.getData().getId());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (IllegalStateException e) {
            GlobalResponse<UserResData> error = new GlobalResponse<>(HttpStatus.UNAUTHORIZED.value(),e.getMessage(),null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        }
    }

}
