package com.apress.JWT_Security_Authentication.security;


import com.apress.JWT_Security_Authentication.models.User;
import com.apress.JWT_Security_Authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository UserRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = UserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found !"));
        return  user ;

    }


}
