package com.rays.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.ManufacturerDAOInt;
import com.rays.dao.PassengerDAOInt;
import com.rays.dto.ManufacturerDTO;

@Service
@Transactional
public class ManufacturerServiceImpl extends BaseServiceImpl<ManufacturerDTO, ManufacturerDAOInt>
		implements ManufacturerServiceInt {

	@Autowired
	public ManufacturerDAOInt manDao;

	public long add(ManufacturerDTO dto) {
		long pk = manDao.add(dto);
		return pk;
	}

	public void update(ManufacturerDTO dto) {
		manDao.update(dto);
	}

	/*
	 * @Transactional public List<ManufacturerDTO> search(ManufacturerDTO dto) {
	 * System.out.println("Search run in Manuservice......Suman"); return
	 * manDao.search(dto); }
	 */

	@Transactional
	public List<ManufacturerDTO> search(ManufacturerDTO dto, int pageNo, int pageSize) {
		System.out.println("Search run in Manuservice......Suman");
		return manDao.search(dto, pageNo, pageSize);
	}

	@Transactional
	public ManufacturerDTO delete(long id) {
		ManufacturerDTO dto = findById(id);
		if (dto != null) {
			manDao.delete(dto);
		}

		return dto;
	}

	@Transactional
	public ManufacturerDTO findById(Long id) {
		ManufacturerDTO dto = manDao.findByPk(id);
		return dto;
	}

}
