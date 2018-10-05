import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { FormGroup, FormControl, Validators, FormBuilder }  from '@angular/forms';
import { employee} from './employee';
import {EmployeeserviceService} from './employeeservice.service';
import { map } from 'rxjs/operators';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Employee Registration';
  allEmployees : employee[];
  statusCode: number;
  requestProcessing = false;
   employeeIdToUpdate = null;
   processValidation = false;
  genderList = [
    'Male','Female'
  ]
  employeeForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    gender: new FormControl('', Validators.required),
    department: new FormControl('', Validators.required),
    dateOfBirth: new FormControl('', Validators.required)	   
});
constructor(private employeeService:EmployeeserviceService){};

ngOnInit(): void {
  this.getAllEmployees();
}

getAllEmployees(){
  this.employeeService.getListOfEmployees().subscribe(data=>this.allEmployees=data);
}

//Handle create and update article
onEmployeeUpdateFormSubmit() {
  this.processValidation = true;   
  if (this.employeeForm.invalid) {
       return; //Validation failed, exit from method.
  }   
  //Form is valid, now perform create or update
    this.preProcessConfigurations();
  let firstName = this.employeeForm.get('firstName').value.trim();
    let lastName = this.employeeForm.get('lastName').value.trim();
    let gender = this.employeeForm.get('gender').value.trim();
    let department = this.employeeForm.get('department').value.trim();
    let dateOfBirth = this.employeeForm.get('dateOfBirth').value.trim();	  
  if (this.employeeIdToUpdate === null) {  
    //Handle create article
    let newEmployee= new employee(firstName,lastName,gender,department,dateOfBirth);	  
    this.employeeService.createNewEmployee(newEmployee)
      .subscribe(successCode => {
              this.statusCode = successCode;
          this.getAllEmployees();	
        this.backtoCreateNewEmployee();
        },
          errorCode => this.statusCode = errorCode);
  } 
 }

 preProcessConfigurations() {
  this.statusCode = null;
this.requestProcessing = true;   
}

backtoCreateNewEmployee() {
  this.employeeIdToUpdate = null;
  this.employeeForm.reset();	  
this.processValidation = false;
}
}
