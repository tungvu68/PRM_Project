package com.example.PRM_Project.services;

import com.example.PRM_Project.model.User;
import com.example.PRM_Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public int registerUser(String first_name, String last_name, String email, String password){
        return userRepository.registerNewUser(first_name, last_name, email, password);
    }

    public Optional<User> loginUser(String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password);
    }
    public String getHashedPasswordByGmail(String email){
        return userRepository.checkUserPasswordByEmail(email);
    }


}
