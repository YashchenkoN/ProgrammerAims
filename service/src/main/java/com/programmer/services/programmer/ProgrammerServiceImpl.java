package com.programmer.services.programmer;

import javax.inject.Inject;

import com.programmer.api.programmer.ProgrammerRequest;
import com.programmer.dao.ProgrammerDao;
import com.programmer.entity.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.programmer.utils.KeyGenerationUtil;

import java.util.List;

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
		return programmerDao.findByActivationKey(activationKey);
	}

	@Transactional(readOnly = true)
	@Override
	public Programmer read(Long id) {
		return programmerDao.read(id);
	}

	@Transactional
	@Override
	public Programmer create(Programmer programmer) {
		if(programmer != null) {
			programmer.setPassword(passwordEncoder.encode(programmer.getPassword()));
			programmerDao.create(programmer);
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
		return programmerDao.getListOfProgrammers();
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
