import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Task } from '../entity/Task';
import {DatePipe} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  datePipe = new DatePipe("en-US");
  constructor(private http: HttpClient) {
  }
  public getTasks(id_list:number): Observable<any>{
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.http.get("http://localhost:8080/api/tasks/by-list-id/" + id_list, {headers:headers});
  }

  public createTask(task: Task, id_list: number) : Observable<any> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    const body = {
      "id": 0,
      "title": task.title,
      "description": task.description,
      "status": "IN_PROGRESS",
      "startDate": this.datePipe.transform(task.startDate,"yyyy-MM-dd"),
      "deadline": this.datePipe.transform(task.deadline,"yyyy-MM-dd")
    }
    return this.http.post("http://localhost:8080/api/tasks/for-list/" + id_list, body, {headers:headers});
  }

  public updateTask(task: Task) : Observable<any> {
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    const body = {
      "id": 0,
      "title": task.title,
      "description": task.description,
      "status": task.status,
      "startDate": this.datePipe.transform(task.startDate,"yyyy-MM-dd"),
      "deadline": this.datePipe.transform(task.deadline,"yyyy-MM-dd")
    }
    return this.http.put("http://localhost:8080/api/tasks/" + task.id, body, {headers:headers});
  }

  public deleteTask(id_task:number) : Observable<any>{
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('token'));
    return this.http.delete("http://localhost:8080/api/tasks/" + id_task, {headers:headers});
  }
}
