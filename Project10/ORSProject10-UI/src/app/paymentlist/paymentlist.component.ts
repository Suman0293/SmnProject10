import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChildren } from '@angular/core';
import { QueryList } from '@angular/core/src/render3';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-paymentlist',
  templateUrl: './paymentlist.component.html',
  styleUrls: ['./paymentlist.component.css']
})
export class PaymentlistComponent implements OnInit {

  @ViewChildren("checkboxes") checkboxes:any
  deleteRecordList:any=[];
  isMasterSel:boolean = false;


  form: any = {
    pageNo:0,
    searchParams: {},
    list: [],
    deleteParams:{}
  
  
  }

  result: any = {};
  

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.search();
    
  }

  search() {

    var self = this
    this.httpClient.post('http://localhost:8084/payment/search', this.form.searchParams).subscribe((res: any) => {
      self.form.list = res.result.data;
    })
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
      this.httpClient.get('http://localhost:8084/payment/delete/' + this.form.deleteParams.id).subscribe((res: any) => {
        self.form.message = res.result.message;
        console.log('message =>',self.form.message)
        self.form.pageNo = 0;
        self.search();
      });

    }   

 }

