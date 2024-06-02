import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../entity/User";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public addUser(user: User): Observable<any> {
    const body = {
      "id": 0,
      "email": user.email,
      "password": user.password,
      "username": user.username,
      "name": user.name
    }
    return this.http.post("http://localhost:8080/api/users", body, {responseType:"text"});
  }
}
