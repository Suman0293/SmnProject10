package com.rays.dao;

import java.util.List;

import com.rays.common.BaseDAOInt;
import com.rays.dto.PassengerDTO;
import com.rays.dto.PaymentDTO;


public interface PassengerDAOInt extends BaseDAOInt<PassengerDTO> {
	
	public List search(PassengerDTO dto);
	
    public void update(PassengerDTO dto);
	
	public void delete(PassengerDTO dto); 
	
	public PassengerDTO findByPk(long pk);

}
