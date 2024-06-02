import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../../services/user.service";
import {User} from "../../../entity/User";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  email: string = '';
  username: string = '';
  password: string = '';
  name: string = '';

  constructor(private userService: UserService, private router: Router) {
  }

  onSubmit(): void {
    console.log('Form submitted');
    console.log('Email:', this.email);
    console.log('Username:', this.username);
    console.log('Password:', this.password);
    console.log('Name:', this.name);
  }

  public registerUser() {
    let emailInput: HTMLInputElement = <HTMLInputElement> document.getElementById("email");
    let email: string = emailInput.value;

    let usernameInput: HTMLInputElement = <HTMLInputElement> document.getElementById("username");
    let username: string = usernameInput.value;

    let passwordInput: HTMLInputElement = <HTMLInputElement> document.getElementById("password");
    let password: string = passwordInput.value;

    let nameInput: HTMLInputElement = <HTMLInputElement> document.getElementById("name");
    let name: string = nameInput.value;

    if(email != "" && username != "" && password != "" && name != ""){
      let user: User = new User();
      user.id = 0;
      user.email = email;
      user.username = username;
      user.password = password;
      user.name = name;
      this.userService.addUser(user).subscribe(response => {
        console.log(response);
        this.router.navigateByUrl("/login");
      })
    }


  }
}

