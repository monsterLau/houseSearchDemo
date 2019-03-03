package com.lau.houseSearchDemo.service;

import com.lau.houseSearchDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.lau.houseSearchDemo.domain.User user=userRepository.findByUsername(s);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        System.out.println("s: "+s);
        System.out.println("user: "+user.getUsername());
        return user;
    }
}
