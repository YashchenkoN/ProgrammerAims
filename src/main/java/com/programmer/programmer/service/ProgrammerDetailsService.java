package com.programmer.programmer.service;

import com.programmer.aim.Aim;
import com.programmer.aim.service.AimService;
import com.programmer.programmer.Programmer;
import com.programmer.programmer.roles.ProgrammerRole;
import com.programmer.step.Difficult;
import com.programmer.step.Step;
import com.programmer.step.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by kolyan on 8/11/15.
 */
public class ProgrammerDetailsService implements UserDetailsService {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private AimService aimService;

    @Autowired
    private StepService stepService;

    @PostConstruct
    protected void initialize() {
        Programmer programmer = new Programmer("admin", "admin", "admin", new ProgrammerRole());
        programmerService.add(programmer);
        Aim aim = new Aim("Name", "New description", 1L);
        Step step = new Step(Difficult.EASY, "SPEC");
        stepService.add(step);
        aim.addStep(step);
        programmer.addAim(aim);
        aimService.update(aim);

    }

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

    private User createUser(Programmer programmer, List<GrantedAuthority> authorities) {
        return new User(programmer.getEmail(), programmer.getPassword(), authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Programmer programmer) {
        return AuthorityUtils.createAuthorityList(programmer.getRole().toString());
    }

}
