package com.programmer.services.programmer;

import com.programmer.entity.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
@Service
public class ProgrammerDetailsService implements UserDetailsService {

    @Autowired
    private ProgrammerService programmerService;

    @SuppressWarnings("unchecked")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Programmer programmer = programmerService.findByEmail(username);
        if(programmer == null) {
            throw new UsernameNotFoundException("programmer not found");
        }
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthorities(programmer);
        return createUser(programmer, authorities);
    }

    public void signin(Programmer programmer) {
        if(programmer != null) {
            UserDetails userDetails = loadUserByUsername(programmer.getEmail());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public boolean hasRole(String role) {
        Programmer programmer = programmerService.getLoggedProgrammer();
        return programmer != null && programmer.getRole() != null && programmer.getRole().toString().equals(role);
    }

    private User createUser(Programmer programmer, List<GrantedAuthority> authorities) {
        return new User(programmer.getEmail(), programmer.getPassword(), authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Programmer programmer) {
        return AuthorityUtils.createAuthorityList(programmer.getRole().toString());
    }

}
