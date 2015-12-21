package com.programmer.services.programmer;

import java.util.List;

import javax.inject.Inject;

import com.programmer.commons.ProgrammerRequest;
import com.programmer.entity.Programmer;
import com.programmer.dao.ProgrammerDao;
import com.programmer.utils.KeyGenerationUtil;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgrammerServiceImpl implements ProgrammerService {

	@Autowired
	private ProgrammerDao programmerDao;

	@Inject
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ProgrammerFormValidator validator;

	@Transactional(readOnly = true)
	@Override
	public Programmer findByEmail(String email) {
		return programmerDao.findByEmail(email);
	}

	@Transactional(readOnly = true)
	@Override
	public Programmer findByActivationKey(String activationKey) {
		return (Programmer) programmerDao.getSession()
				.createCriteria(Programmer.class)
				.add(Restrictions.eq("activationKey", activationKey))
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	@Override
	public Programmer findById(Long id) {
		return (Programmer) programmerDao.getSession()
				.createCriteria(Programmer.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Transactional
	@Override
	public Programmer create(Programmer programmer) {
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

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Programmer> getListOfProgrammers() {
		return programmerDao.getSession()
				.createCriteria(Programmer.class)
				.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Programmer getLoggedProgrammer() {
		return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@Transactional
	@Override
	public Programmer buildProgrammer(ProgrammerRequest authRequest) {
		Programmer programmer = new Programmer();
		programmer.setEmail(authRequest.getEmail());
		programmer.setActivationKey(KeyGenerationUtil.getKey());
		programmer.setName(authRequest.getName());
		programmer.setPassword(authRequest.getPassword());
		return programmer;
	}

	@Transactional
	@Override
	public Programmer getByEmailAndPassword(String email, String password) {
		Programmer result = null;
		if(validator.validateEmail(email) && password != null) {
			Programmer programmer = findByEmail(email);
			if(programmer != null && passwordEncoder.matches(password, programmer.getPassword())) {
				result = programmer;
			}
		}
		return result;
	}
}
