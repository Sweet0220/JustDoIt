import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  standalone: true,
  imports: [
    FormsModule
  ],
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  username: string = '';
  password: string = '';

  constructor() {}

  onSubmit() {
    // Handle login logic here
    console.log('Username:', this.username);
    console.log('Password:', this.password);
  }
}
