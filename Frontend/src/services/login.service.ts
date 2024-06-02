import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
  }

  public login(username:string,password:string) : Observable<any> {
    const body = {
      "username": username, "password": password
    }
    return this.http.post("http://localhost:8080/api/auth/login",body);
  }
}
