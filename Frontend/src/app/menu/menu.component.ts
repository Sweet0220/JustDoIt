import {Component, OnInit} from '@angular/core';
import {Router, RouterOutlet} from "@angular/router";
import {User} from "../../entity/User";
import {ListService} from "../../services/list.service";
import {FormsModule} from "@angular/forms";
import {List} from "../../entity/List";
import {Task} from "../../entity/Task";
import {CommonModule, DatePipe} from "@angular/common";
import {TaskService} from "../../services/task.service";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    RouterOutlet, FormsModule, CommonModule
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {
  user: User = new User();
  list: Array<List> = new Array<List>();
  editToggle: boolean = false;
  addToggle: boolean = false;
  listToEdit: List = new List();
  selectedList: List = new List();

  task: Array<Task> = new Array<Task>();
  addTaskToggle: boolean = false;
  editTaskToggle: boolean = false;
  taskToEdit: Task = new Task();




  ngOnInit(): void {
    const json: any = localStorage.getItem("user");
    this.user = JSON.parse(json) as User;
    this.getAllLists(this.user.id);
  }

  constructor(private router: Router, private listService: ListService, private taskService: TaskService) {
  }

  public logout() {
    this.router.navigate(['/login']);
  }

  public getAllLists(id: number): void {
    this.list = new Array<List>();
    id = this.user.id;
    this.listService.getAllLists(id).subscribe((list) => {
      this.list = list as Array<List>;
    });
  }

  public getAllTasks(id_list: number): void {
    this.task = new Array<Task>();
    this.taskService.getTasks(id_list).subscribe((task) => {
      this.task = task as Array<Task>;
    });
  }



  public deleteList(id: number, event:any): void {
    event.stopPropagation();
    this.listService.deleteList(id).subscribe(() => {
      this.selectedList = new List();
      this.task = new Array<Task>();
      this.getAllLists(this.user.id);
    })
  }

  public createList() {
    let id_user = this.user.id;

    let nameInput: HTMLInputElement = <HTMLInputElement>document.getElementById("name");
    let name: string = nameInput.value;

    let categoryInput: HTMLSelectElement = <HTMLSelectElement>document.getElementById("category");
    let category: string = categoryInput.value;

    let priorityInput: HTMLInputElement = <HTMLInputElement>document.getElementById("priority");
    let priority: number = priorityInput.valueAsNumber;


    if (name != "" && category != "" && priority != -1) {
      let list: List = new List();
      list.id = 0;
      list.name = name;
      list.category = category;
      list.priority = priority;

      this.listService.createList(list, id_user).subscribe(response => {
        console.log(response);
        this.getAllLists(this.user.id);
        this.addToggle = false;
      })


    }
  }

  public toggleEdit(list: List, event:any) {
    event.stopPropagation();
    this.editToggle = true;
    this.listToEdit = list;
  }

  public updateList() {
    let nameInput: HTMLInputElement = <HTMLInputElement>document.getElementById("name_edit");
    let name: string = nameInput.value;

    let categoryInput: HTMLSelectElement = <HTMLSelectElement>document.getElementById("category_edit");
    let category: string = categoryInput.value;

    let priorityInput: HTMLInputElement = <HTMLInputElement>document.getElementById("priority_edit");
    let priority: number = priorityInput.valueAsNumber;

    this.listToEdit.name = name;
    this.listToEdit.category = category;
    this.listToEdit.priority = priority;

    this.listService.updateList(this.listToEdit).subscribe(response => {
      console.log(response);
      this.getAllLists(this.user.id);
      this.editToggle = false;
    })
  }

  public selectList(list: List) {
    this.selectedList = list;
    console.log(this.selectedList);
    this.taskService.getTasks(list.id).subscribe((task) => {
      this.task = task as Array<Task>;
    })
  }

  public setTitle(): string {
    if (this.selectedList.name != null && this.selectedList.name != "") {
      return this.selectedList.name;
    }
    return "Select a List.";
  }

  public toggleStatus(task: Task, event: any) {
    event.stopPropagation();
    if(task.status == "IN_PROGRESS")
      task.status = "DONE";
    else
      task.status = "IN_PROGRESS";

    this.taskService.updateTask(task).subscribe(response => {
      this.getAllTasks(this.selectedList.id);
    });
  }
  public selectCheckClass(task: Task): string {
    if (task.status == "DONE")
      return "checkedBox";
    return "uncheckedBox";
  }

  public toggleTaskEdit(task: Task) {
    this.editTaskToggle = true;
    this.taskToEdit = task;
  }

  public updateTask(){
    let datePipe = new DatePipe("en-US");
    let task: Task = new Task();

    let titleInput: HTMLInputElement = <HTMLInputElement>document.getElementById("editTask_name");
    let title: string = titleInput.value;

    let descriptionInput: HTMLInputElement = <HTMLInputElement>document.getElementById("editTask_description");
    let description: string = descriptionInput.value;

    let startDateInput: HTMLInputElement = <HTMLInputElement>document.getElementById("editTask_startDate");
    let startDate: string = startDateInput.value;

    let deadlineInput: HTMLInputElement = <HTMLInputElement>document.getElementById("editTask_deadline");
    let deadline: string = deadlineInput.value;

    this.taskToEdit.title = title;
    this.taskToEdit.description = description;
    this.taskToEdit.startDate = new Date(Date.parse(startDate));
    this.taskToEdit.deadline = new Date(Date.parse(deadline));

    this.taskService.updateTask(this.taskToEdit).subscribe(response => {
      console.log(response);
      this.getAllTasks(this.selectedList.id);
      this.editTaskToggle = false;
    })

  }

  public toggleAddTask() {
    if (this.selectedList.name != null && this.selectedList.name != "") {
      this.addTaskToggle = true;
    }
  }

  public deleteTask(id_task: number, event: any) {
    event.stopPropagation();
    this.taskService.deleteTask(id_task).subscribe(() => {
      this.getAllTasks(this.selectedList.id);
    })

  }

  public createTask() {
    let datePipe = new DatePipe("en-US");
    let task: Task = new Task();

    let titleInput: HTMLInputElement = <HTMLInputElement>document.getElementById("task_name");
    let title: string = titleInput.value;

    let descriptionInput: HTMLInputElement = <HTMLInputElement>document.getElementById("task_description");
    let description: string = descriptionInput.value;

    let startDateInput: HTMLInputElement = <HTMLInputElement>document.getElementById("task_startDate");
    let startDate: string = startDateInput.value;

    let deadlineInput: HTMLInputElement = <HTMLInputElement>document.getElementById("task_deadline");
    let deadline: string = deadlineInput.value;


    if (title != "" && description != "" && startDate != "" && deadline != "") {
      task.id = 0;
      task.title = title;
      task.description = description;
      task.startDate = new Date(Date.parse(startDate));
      console.log(task.startDate);
      task.deadline = new Date(Date.parse(deadline));

      this.taskService.createTask(task, this.selectedList.id).subscribe(response => {
        this.getAllTasks(this.selectedList.id);
        this.addTaskToggle = false;
      })
    }


  }
}
