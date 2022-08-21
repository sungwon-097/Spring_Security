package com.cos.security1.controller;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//    @Secured("ROLE_ADMIN") // 단일 권한
@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 두 개 이상의 권한
@RequestMapping("admin/user")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public @ResponseBody List<User> retrieveAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/userid/{id}")
    public @ResponseBody Optional<User> retrieveUserid(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Id[%s] not found", id));
        }
        return user;
    }
    @GetMapping("/username/{username}")
    public @ResponseBody Optional<User> retrieveUsername(@PathVariable String username){
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
        return Optional.of(user);
    }
    @GetMapping("/email/{email}")
    public @ResponseBody Optional<User> retrieveEmail(@PathVariable String email){
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Email[%s] not found", email));
        }
        return Optional.of(user);
    }
    @GetMapping("/provider/{provider}")
    public @ResponseBody
    Optional<List<User>> retrieveProvider(@PathVariable String provider){
        List<User> user = (List<User>) userRepository.findByProvider(provider);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Provider[%s] not found", provider));
        }
        return Optional.of(user);
    }
    @GetMapping("/role/{role}")
    public @ResponseBody Optional<List<User>> retrieveRole(@PathVariable String role){
        List<User> user = (List<User>) userRepository.findByRole(role);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Provider[%s] not found", role));
        }
        return Optional.of(user);
    }
    @DeleteMapping("/{id}")
    public @ResponseBody Optional<List<User>> deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        List<User> user = userRepository.findAll();
        return Optional.of(user);
    }
}