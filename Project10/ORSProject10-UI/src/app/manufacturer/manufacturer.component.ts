import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-manufacturer',
  templateUrl: './manufacturer.component.html',
  styleUrls: ['./manufacturer.component.css']
})
export class ManufacturerComponent implements OnInit {

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
      this.httpService.post('http://localhost:8084/Manufacturer/save', this.form.data).subscribe((res: any) => {
         
        self.form.message = res.result.message;
        self.form.inputerror =  res.result.inputerror;
  
          if (res.success) {
                  self.form.message =res.result.message;
                    self.form.data.id = res.result.data;
                   
          }
        }, error => {
                 console.error('Error occurred while saving:', error);
        });
    }
    
    display() {
      var self = this;
      this.httpService.get('http://localhost:8084/Manufacturer/get/' + self.form.data.id).subscribe((res: any) => {
  
     
        if (res.success) {
          console.log("get data => " ,res.result.data)
          self.form.data.id = res.result.data.id;
          self.form.data.manufacturerName = res.result.data.manufacturerName;
          self.form.data.manufacturerPrice = res.result.data.manufacturerPrice;
                
        }
      })
    }
  
    update() {
  
      var self = this;
      this.httpService.post('http://localhost:8084/Manufacturer/update', this.form.data).subscribe((res: any) => {
        
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
