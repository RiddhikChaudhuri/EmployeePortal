import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeserviceService {

  employeeServiceProperyString = "Welcome to Employee Registration Website!!!";
  allarticlesUrl="http://localhost:8080/listofEmployees";
  createNewEmployeeURL="http://localhost:8080/addEmployee";
  constructor(private http:Http) {}

  showTodayDate(){
    let nDate = new Date();
    return nDate;
  }

  getListOfEmployees(){
    return this.http.get(this.allarticlesUrl).pipe(map(data=>this.extractData(data)));
  }

  private extractData(data) {
    let body = data.json();
      return body;
  }

  private handleError (error: Response | any) {
		console.error(error.message || error);
		return Observable.throw(error.status);
    }

    createNewEmployee(employee: employee):Observable<number> {
	    let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.createNewEmployeeURL, employee, options).pipe(map(data=>data.status));
    }
}
