import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.css']
})
export class PassengerComponent implements OnInit {

  form: any = {
    data:  {}
    
  };


  constructor(private httpService: HttpClient,public serviceLocator:ServiceLocatorService,public route:ActivatedRoute) { 

    var self = this;

            serviceLocator.getPathVariable(route,function(params){
              self.form.data.id = params["id"];
              console.log('I GOT ID', self.form.data.id);
            })

  }

  ngOnInit() {
    if(this.form.data.id && this.form.data.id > 0)
      this.display()
  }

  save() {
    var self = this;
    this.httpService.post('http://localhost:8084/Passenger/save', this.form.data).subscribe((res: any) => {
       
      self.form.message = res.result.message;
      self.form.inputerror =  res.result.inputerror;

        if (res.success) {
                self.form.message = "Data is saved";
                  self.form.data.id = res.result.data;
                 
        }
      }, error => {
               console.error('Error occurred while saving:', error);
      });
  }
  
  display() {
    var self = this;
    this.httpService.get('http://localhost:8084/Passenger/get/' + self.form.data.id).subscribe((res: any) => {

   
      if (res.success) {
        console.log("get data => " ,res.result.data)
        self.form.data.id = res.result.data.id;
        self.form.data.passengerName = res.result.data.passengerName;
        self.form.data.destination = res.result.data.destination;
        self.form.data.ticketPrice = res.result.data.ticketPrice;       
      }
    })
  }

  update() {
  
    var self = this;
    this.httpService.post('http://localhost:8084/Passenger/update', this.form.data).subscribe((res: any) => {
      
      self.form.message = res.result.message;      
      self.form.inputerror = res.result.inputerror;
      if (res.success) {
        //self.form.data = res.result.data;
       self.form.message ="Updated Successfully";
  
        }
      }, error => {
        console.error('Error occurred while saving:', error);
      });
    }


}
