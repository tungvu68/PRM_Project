package com.example.PRM_Project.rest_controller;

import com.example.PRM_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/test")
public class RegisterApiController {

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




}
