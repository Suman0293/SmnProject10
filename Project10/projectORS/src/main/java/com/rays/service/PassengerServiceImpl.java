package com.rays.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.PassengerDAOInt;
import com.rays.dto.PassengerDTO;
import com.rays.dto.PaymentDTO;



@Service
@Transactional
public class PassengerServiceImpl extends BaseServiceImpl<PassengerDTO, PassengerDAOInt> implements PassengerServiceInt {
	

	@Autowired
	public PassengerDAOInt pasDao;
	
	
	public long add(PassengerDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void update(PassengerDTO dto) {
        pasDao.update(dto);		
	}
	
	@Transactional()
	public List<PassengerDTO> search(PassengerDTO dto) {
		System.out.println("Search run in Baseservice......Naman");
		return baseDao.search(dto);
	}
	
	@Transactional
	public PassengerDTO delete(long id) {
		PassengerDTO dto = findById(id);
		if(dto != null) {
			pasDao.delete(dto);
		}
		return dto;
	}


	@Transactional
	public PassengerDTO findById(Long id) {
		PassengerDTO dto = pasDao.findByPk(id);
		return dto;
	}
}
