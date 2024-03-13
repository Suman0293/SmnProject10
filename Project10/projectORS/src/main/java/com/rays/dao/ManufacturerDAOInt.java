package com.rays.dao;

import java.util.List;

import com.rays.common.BaseDAOInt;
import com.rays.dto.ManufacturerDTO;

public interface ManufacturerDAOInt extends BaseDAOInt<ManufacturerDTO> {

	//public List search(ManufacturerDTO dto);
	public List search(ManufacturerDTO dto, int pageNo, int pageSize);

	public void update(ManufacturerDTO dto);

	public long add(ManufacturerDTO dto);

	public void delete(ManufacturerDTO dto);

	public ManufacturerDTO findByPk(long pk);

}
