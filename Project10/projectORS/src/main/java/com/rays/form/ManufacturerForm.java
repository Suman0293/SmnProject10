package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.ManufacturerDTO;


public class ManufacturerForm extends BaseForm {
	
	@NotEmpty(message = "please enter manufacturerName")
	public String manufacturerName;

	@NotEmpty(message = "please enter manufacturerPrice")
	public String manufacturerPrice;

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturerPrice() {
		return manufacturerPrice;
	}

	public void setManufacturerPrice(String manufacturerPrice) {
		this.manufacturerPrice = manufacturerPrice;
	}

	@Override
	public BaseDTO getDto() {
		ManufacturerDTO dto = initDTO(new ManufacturerDTO());
		dto.setManufacturerName(manufacturerName);
		dto.setManufacturerPrice(manufacturerPrice);
	
		return dto;
	}

}
