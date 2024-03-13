package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.PassengerDTO;


public class PassengerForm extends BaseForm{
	
	@NotEmpty(message = "please enter paymentMode")
	public String passengerName;

	@NotEmpty(message = "please enter destination")
	public String destination;

	@NotEmpty(message = "please enter ticketPrice")
	public String ticketPrice;

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	@Override
	public BaseDTO getDto() {
		
		PassengerDTO dto = initDTO(new PassengerDTO());
		
		dto.setPassengerName(passengerName);
		dto.setDestination(destination);
		dto.setTicketPrice(ticketPrice);
		return dto;

	}

}
