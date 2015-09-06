package com.programmer.programmer.service;

import com.programmer.aim.Aim;
import com.programmer.aim.service.AimService;
import com.programmer.programmer.Programmer;
import com.programmer.step.Difficult;
import com.programmer.step.Step;
import com.programmer.step.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Collections;

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
        Programmer programmer = new Programmer("admin", "admin", "admin", "ROLE_ADMIN");
        Aim aim = new Aim("Name", "New description", 1L);
        Step step = new Step(Difficult.EASY, "SPEC");
        aim.addStep(step);
        programmer.addAim(aim);
        stepService.add(step);
        aimService.update(aim);
        programmerService.update(programmer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Programmer programmer = programmerService.findByEmail(username);
        if(programmer == null) {
            throw new UsernameNotFoundException("programmer not found");
        }
        return createUser(programmer);
    }

    public void signin(Programmer programmer) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(programmer));
    }

    private Authentication authenticate(Programmer programmer) {
        return new UsernamePasswordAuthenticationToken(createUser(programmer), null, Collections.singleton(createAuthority(programmer)));
    }

    private User createUser(Programmer programmer) {
        return new User(programmer.getEmail(), programmer.getPassword(), Collections.singleton(createAuthority(programmer)));
    }

    private GrantedAuthority createAuthority(Programmer programmer) {
        return new SimpleGrantedAuthority(programmer.getRole());
    }

    public Programmer getLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            return programmerService.findByEmail(((User)principal).getUsername());
        }
        return null;
    }

    public boolean hasRole(String role) {
        if(getLogged().getRole().equals(role))
            return true;
        return false;
    }
}
