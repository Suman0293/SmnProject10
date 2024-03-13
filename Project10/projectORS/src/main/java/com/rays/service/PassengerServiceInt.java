package com.rays.service;

import java.util.List;

import com.rays.common.BaseServiceInt;
import com.rays.dto.PassengerDTO;
import com.rays.dto.PaymentDTO;


public interface PassengerServiceInt extends BaseServiceInt<PassengerDTO>{
	
	public long add(PassengerDTO dto);

	public List search(PassengerDTO dto);
	
	public void update(PassengerDTO dto );
	
	public PassengerDTO delete(long id);
	
	public PassengerDTO findById(Long id);


}
