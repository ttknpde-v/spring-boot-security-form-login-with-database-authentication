package com.ttknpdev.understand.security.services;

import com.ttknpdev.understand.security.entities.Role;
import com.ttknpdev.understand.security.entities.User;
import com.ttknpdev.understand.security.log.Mylog;
import com.ttknpdev.understand.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserDetail implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetail(UserRepository userRepository) {
        /* CDI */
        this.userRepository = userRepository;
    }

    // this method will work after login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mylog.customUserDetailLog.info("method Override of UserDetailsService was working");

        User found = userRepository.findByUsernameOrEmail(username,username)
                .orElseThrow(() -> {
                    Mylog.customUserDetailLog.info("findByUsernameOrEmail() was wrong");
                    return new UsernameNotFoundException("User not exists by Username or Email");
                });
        /*
        User{ uid=2 , username='adam' , password='$2a$12$FMnuf5ZX3pyTTyGElUe81eISiYO7R/pKSa9.ZjVpJ8WybS414pp7W' ,
              email='adam@gmail.com' ,  roles=[com.ttknpdev.understand.security.entities.Role@73a89432] }
        */
        Mylog.customUserDetailLog.info("found store "+found);
        /*
            Difference hasAuthority() and hasRole() is
                       Has prefix         no prefix
            like       READ_ADMIN         ADMIN
        */
        /*
            the GrantedAuthority can be represented as a String and that String is sufficient in precision to be relied upon for an access control decision by an AccessDecisionManager
            (the first way)
        //        Set<GrantedAuthority> authorities = found.getRoles().stream()
        //                .map( (role) -> new SimpleGrantedAuthority( role.getRole() ))
        //                .collect( Collectors.toSet() );
        */
        List<GrantedAuthority> authorityList = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
                found.getRoles().stream().map(Role::getRole).toString()
        );
        authorityList.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User (
                username , // username
                found.getPassword() , // password
                authorityList // roles
        );
    }
}
