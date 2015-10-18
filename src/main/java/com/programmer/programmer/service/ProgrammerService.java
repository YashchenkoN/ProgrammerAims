package com.programmer.programmer.service;

import com.programmer.programmer.Programmer;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public interface ProgrammerService {

    Programmer findByEmail(String email);

    Programmer findByActivationKey(String activationKey);

    Programmer findById(Long id);

    Programmer create(Programmer programmer);

    Programmer update(Programmer programmer);

    List<Programmer> getListOfProgrammers();

    Programmer getLoggedProgrammer();

}
