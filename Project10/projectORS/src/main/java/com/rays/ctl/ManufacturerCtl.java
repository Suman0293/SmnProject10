package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.ManufacturerDTO;
import com.rays.form.ManufacturerForm;
import com.rays.service.ManufacturerServiceInt;
import com.rays.service.PassengerServiceInt;

@RestController
@RequestMapping(value = "Manufacturer")
public class ManufacturerCtl extends BaseCtl<ManufacturerForm, ManufacturerDTO, ManufacturerServiceInt> {

	@Autowired
	public ManufacturerServiceInt manService;

	@PostMapping("/search/{pageNo}")
	public ORSResponse search(@RequestBody ManufacturerForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse(true);

		ManufacturerDTO dto = (ManufacturerDTO) form.getDto();

		res.addData(manService.search(dto, pageNo, 5));
		

		return res;
	}

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid ManufacturerForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!(res.isSuccess())) {
			return res;
		}

		ManufacturerDTO dto = (ManufacturerDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			manService.update(dto);
			res.addMessage("Manufacturer Updated Successfully..!!!");
		} else {
			manService.add(dto);
			res.addMessage("Manufacturer Registered Successfully..!!!");
		}
		res.addData(dto.getId());
		return res;
	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody ManufacturerForm form) {
		ORSResponse res = new ORSResponse(true);
		ManufacturerDTO dto = (ManufacturerDTO) form.getDto();
		manService.update(dto);
		res.addMessage("Updated successfully");
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		ManufacturerDTO dto = manService.findById(id);

		if (dto != null) {

			manService.delete(id);

			res.addMessage("Manufacturer Successfully Deleted");

		} else {
			res.addMessage("Select Alteast One Record");
		}
		return res;
	}

}
