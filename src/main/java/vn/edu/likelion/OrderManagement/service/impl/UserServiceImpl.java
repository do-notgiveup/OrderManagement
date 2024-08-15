package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.UserEntity;
import vn.edu.likelion.OrderManagement.repository.UserRepository;
import vn.edu.likelion.OrderManagement.service.UserService;

import java.util.Iterator;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userDetail = repository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserEntity userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }

    // ------ implements from UserService -----

    @Override
    public UserEntity create(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    @Override
    public Iterator<UserEntity> findAll() {
        return repository.findAll().iterator();
    }

    @Override
    public Optional<UserEntity> findById(int id) {
        return repository.findById(id);
    }
}