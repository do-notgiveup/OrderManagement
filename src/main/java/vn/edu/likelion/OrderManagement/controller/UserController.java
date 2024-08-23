package vn.edu.likelion.OrderManagement.controller;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.OrderManagement.entity.UserEntity;
import vn.edu.likelion.OrderManagement.model.AuthRequest;
import vn.edu.likelion.OrderManagement.service.impl.JwtService;
import vn.edu.likelion.OrderManagement.service.impl.UserInfoService;
import vn.edu.likelion.OrderManagement.service.impl.UserServiceImpl;
import vn.edu.likelion.OrderManagement.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    UserInfoService userInfoService;

//    @Autowired
//    private JwtService jwtService;

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public String getUser() {
        return "Hello World";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

//    @PostMapping("/register")
//    public String addNewUser(@RequestBody UserEntity userInfo) {
//        return service.addUser(userInfo);
//    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity userEntity) {
        try {
            UserEntity user = userInfoService.addUser(userEntity);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

//    @PostMapping("/login")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthRequest userDTO) {
        UserEntity userEntity = userInfoService.authenticateUser(userDTO.getUsername(), userDTO.getPassword());
        if(userEntity != null) {
            String token = jwtService.generateToken(userEntity.getUsername());
            return token;
        }
        return "login fail!";
    }
}
