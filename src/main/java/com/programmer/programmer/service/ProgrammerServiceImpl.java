package com.programmer.programmer.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.programmer.programmer.Programmer;
import com.programmer.programmer.dao.ProgrammerDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgrammerServiceImpl implements ProgrammerService {

	@Autowired
	private ProgrammerDao programmerDao;

	@Inject
	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public Programmer findByEmail(String email) {
		return programmerDao.findByEmail(email);
	}

	@Transactional(readOnly = true)
	@Override
	public Programmer findByActivationKey(String activationKey) {
		return (Programmer) programmerDao.getSession().createCriteria(Programmer.class)
				.add(Restrictions.eq("activationKey", activationKey))
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	@Override
	public Programmer findById(Long id) {
		return (Programmer) programmerDao.getSession().createCriteria(Programmer.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Transactional
	@Override
	public Programmer add(Programmer programmer) {
		if(programmer != null) {
			programmer.setPassword(passwordEncoder.encode(programmer.getPassword()));
			programmerDao.add(programmer);
		}
		return programmer;
	}

	@Transactional
	@Override
	public Programmer update(Programmer programmer) {
		return programmerDao.update(programmer);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Programmer> getListOfProgrammers() {
		return programmerDao.getSession().createCriteria(Programmer.class)
				.list();
	}

}
