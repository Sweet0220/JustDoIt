import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {List} from "../entity/List";



@Injectable({
  providedIn: 'root'
})
export class ListService {


  constructor(private http: HttpClient) { }

  public getAllLists(id:number) : Observable<any> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.http.get("http://localhost:8080/api/lists/by-user-id/" + id, {headers:headers});
  }

  public deleteList(id: number) : Observable<any> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.http.delete("http://localhost:8080/api/lists/" + id, {headers:headers});
  }

  public updateList(list: List) : Observable<any> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    const body = {
      "id": 0,
      "name": list.name,
      "category": list.category,
      "priority": list.priority
    }
    return this.http.put("http://localhost:8080/api/lists/" + list.id, body, {headers:headers});
  }

  public createList(list: List, id_user: number) : Observable<any> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    const body = {
      "id": 0,
      "name": list.name,
      "category": list.category,
      "priority": list.priority
    }
    return this.http.post("http://localhost:8080/api/lists/for-user/" + id_user, body, {headers:headers});

  }


}
