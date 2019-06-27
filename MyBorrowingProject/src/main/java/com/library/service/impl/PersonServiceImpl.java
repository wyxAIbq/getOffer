package com.library.service.impl;

import com.library.dao.PersonDao;
import com.library.entity.Person;
import com.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService{

	@Autowired
	private PersonDao personDao;

}
