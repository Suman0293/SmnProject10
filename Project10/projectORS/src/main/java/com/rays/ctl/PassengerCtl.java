package com.rays.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.PassengerDTO;
import com.rays.dto.PaymentDTO;
import com.rays.form.PassengerForm;
import com.rays.form.PaymentForm;
import com.rays.service.PassengerServiceInt;



@RestController
@RequestMapping(value = "Passenger")
public class PassengerCtl extends BaseCtl<PassengerForm, PassengerDTO, PassengerServiceInt>{
	
	@Autowired
	public PassengerServiceInt pasService;

	@PostMapping("search")
	public ORSResponse search(@RequestBody PassengerForm form) {

		PassengerDTO dto = (PassengerDTO) form.getDto();

		ORSResponse res = new ORSResponse(true);

		res.addData(pasService.search(dto));

		return res;
	}
	
	@PostMapping("update")
	public ORSResponse update(@RequestBody PassengerForm form) {
		ORSResponse res = new ORSResponse(true);
		PassengerDTO dto = (PassengerDTO) form.getDto();
		pasService.update(dto);
		res.addMessage("Updated successfully");
		return res;
	}
	
	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) { 

		ORSResponse res = new ORSResponse();

		PassengerDTO dto = pasService.findById(id);

	if (dto != null) {

			pasService.delete(id);
			
			res.addMessage("User Successfully Deleted");

	}else{
			res.addMessage("User is not found");
			
		}
		return res;

	}


}
