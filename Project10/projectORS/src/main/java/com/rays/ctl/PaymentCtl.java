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
import com.rays.dto.PaymentDTO;
import com.rays.form.PaymentForm;
import com.rays.service.PaymentServiceInt;

@RestController
@RequestMapping(value = "payment")
public class PaymentCtl extends BaseCtl<PaymentForm, PaymentDTO, PaymentServiceInt> {

	@Autowired
	public PaymentServiceInt payService;

	@PostMapping("search")
	public ORSResponse search(@RequestBody PaymentForm form) {

		PaymentDTO dto = (PaymentDTO) form.getDto();

		ORSResponse res = new ORSResponse(true);
			
	
		res.addData(payService.search(dto));

		return res;
	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody PaymentForm form) {
		ORSResponse res = new ORSResponse(true);
		PaymentDTO dto = (PaymentDTO) form.getDto();
		payService.update(dto);
		res.addMessage("Updated successfully");
		return res;
	}
	
	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) { 

		ORSResponse res = new ORSResponse();

	    PaymentDTO dto = payService.findById(id);

	if (dto != null) {

			payService.delete(id);
			
			res.addMessage("User Successfully Deleted");

	}else{
			res.addMessage("User is not found");
			
		}
		return res;

	}

}
