package com.example.PRM_Project.rest_controller;

import com.example.PRM_Project.dto.SysMessageType;
import com.example.PRM_Project.dto.UserResponseDTO;
import com.example.PRM_Project.model.User;
import com.example.PRM_Project.services.UserService;
import com.example.PRM_Project.untils.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserAPIController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestParam("first_name")String first_name,
                                          @RequestParam("last_name")String last_name,
                                          @RequestParam("email")String email,
                                          @RequestParam("password")String password){

        if(first_name.isEmpty()||last_name.isEmpty()||email.isEmpty()||password.isEmpty()){
            return new ResponseEntity<>("pls enter all fields", HttpStatus.BAD_REQUEST);
        }
        String hashed_pass = BCrypt.hashpw(password, BCrypt.gensalt());

        int result  = userService.registerUser(first_name, last_name, email, hashed_pass);
        if(result!= 1){
            return new ResponseEntity<>("Failed to register user", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Map<String, String> loginRequest) {
        long startTime = RequestHelper.startTimer();
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            if (email.isEmpty() || password.isEmpty()) {
                return RequestHelper.sendResult(null, SysMessageType.SYS_INVALID_INPUT.getCode(),
                        "Email and password are required", HttpStatus.BAD_REQUEST, startTime);
            }

            String hashed_password = userService.getHashedPasswordByGmail(email);
            if (hashed_password == null) {
                return RequestHelper.sendResult(null, SysMessageType.DATA_NOT_EXISTS.getCode(),
                        "User not found", HttpStatus.NOT_FOUND, startTime);
            }

            if (!BCrypt.checkpw(password, hashed_password)) {
                return RequestHelper.sendResult(null, SysMessageType.SYS_AUTH_FAILED.getCode(),
                        "User not found", HttpStatus.NOT_FOUND, startTime);
            }

            Optional<User> userOptional = userService.loginUser(email, hashed_password);
            if (userOptional.isEmpty()) {
                return RequestHelper.sendResult(null, SysMessageType.DATA_NOT_EXISTS.getCode(),
                        "User not found", HttpStatus.NOT_FOUND, startTime);
            }
            // appply DTO
            User user = userOptional.get();
            Map<String, Object> userData = new HashMap<>();
            userData.put("userId", user.getUser_id());
            userData.put("firstName", user.getFirst_name());
            userData.put("lastName", user.getLast_name());
            userData.put("email", user.getEmail());


            return RequestHelper.sendResult(userData, SysMessageType.SYS_SUCCESSFULLY.getCode(), "Login successful", HttpStatus.OK, startTime);
        }catch (Exception e){
            return RequestHelper.sendResult("", SysMessageType.SYS_ERROR.getCode(), "failed", HttpStatus.BAD_REQUEST, startTime);
        }
    }


}
