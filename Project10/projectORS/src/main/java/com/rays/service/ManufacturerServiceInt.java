package com.rays.service;

import java.util.List;

import com.rays.common.BaseServiceInt;
import com.rays.dto.ManufacturerDTO;

public interface ManufacturerServiceInt extends BaseServiceInt<ManufacturerDTO> {

	public long add(ManufacturerDTO dto);

	public List search(ManufacturerDTO dto, int pageNo, int pageSize);
	//public List search(ManufacturerDTO dto);

	public void update(ManufacturerDTO dto);

	public ManufacturerDTO delete(long id);

	public ManufacturerDTO findById(Long id);

}
