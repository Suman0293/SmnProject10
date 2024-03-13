import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manufacturerlist',
  templateUrl: './manufacturerlist.component.html',
  styleUrls: ['./manufacturerlist.component.css']
})
export class ManufacturerlistComponent implements OnInit {

 
  @ViewChildren("checkboxes") checkboxes:any
  deleteRecordList:any=[];
  isMasterSel:boolean = false;


  form: any = {
    pageNo:0,
    searchParams: {},
    list: [],
    deleteParams:{},
    nextlist: []
  }

  result: any = {};

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.search();
    

  }

  search() {

    var self = this
    this.httpClient.post('http://localhost:8084/Manufacturer/search/' + this.form.pageNo, this.form.searchParams).subscribe((res: any) => {
      self.form.list = res.result.data;
      self.form.result = res.result;
    })
  }

  

  previous(){
    this.form.pageNo--;
    this.search();
  }

  next(){
    this.form.pageNo++;
    this.search();
  }

  forward(page: any) {
    this.router.navigateByUrl(page);

  }

  onCheckboxChange(userId:number){
    console.log('Checkbox with ID', userId,'is checked/unchecked');
    this.form.deleteParams.id = userId;    
  }

  
    delete(){
      var self = this;
      this.httpClient.get('http://localhost:8084/Manufacturer/delete/' + this.form.deleteParams.id).subscribe((res: any) => {
        self.form.message = res.result.message;
        console.log('message =>',self.form.message)  
        self.form.pageNo = 0;
        self.search();
      });

    }


}
