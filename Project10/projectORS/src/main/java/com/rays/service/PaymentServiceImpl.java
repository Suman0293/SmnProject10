package com.rays.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.PaymentDAOInt;
import com.rays.dto.PaymentDTO;

@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl<PaymentDTO, PaymentDAOInt> implements PaymentServiceInt {

	@Autowired
	public PaymentDAOInt payDao;

	public long add(PaymentDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(PaymentDTO dto) {
		payDao.update(dto);
	}

	@Transactional()
	public List<PaymentDTO> search(PaymentDTO dto) {
		System.out.println("Search run in Baseservice......Suman");
		// return baseDao.search(dto, pageNo, pageSize);
		return baseDao.search(dto);
	}

	@Transactional
	public PaymentDTO delete(long id) {
		PaymentDTO dto = findById(id);
		if (dto != null) {
			payDao.delete(dto);
		}
		return dto;
	}

	@Transactional
	public PaymentDTO findById(Long id) {
		PaymentDTO dto = payDao.findByPk(id);
		return dto;
	}

}
