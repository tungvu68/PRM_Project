package com.example.PRM_Project.rest_controller;

import com.example.PRM_Project.dto.UserResponseDTO;
import com.example.PRM_Project.model.User;
import com.example.PRM_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
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
    public ResponseEntity loginUser(@RequestParam("email") String email,
                                    @RequestParam("password") String password) {

        if(email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Email and password required", HttpStatus.BAD_REQUEST);
        }

        String hashed_password = userService.getHashedPasswordByGmail(email);
        if (hashed_password == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if(!BCrypt.checkpw(password, hashed_password)){
            return new ResponseEntity<>("Password or username incorrect", HttpStatus.BAD_REQUEST);
        }


        Optional<User> userOptional = userService.loginUser(email, hashed_password);
        if (userOptional.isEmpty()){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        // Chuyển đổi User sang UserResponseDTO
        UserResponseDTO dto = new UserResponseDTO(user.getUser_id(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
