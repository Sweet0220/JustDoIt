import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {User} from "../../entity/User";
import {Router} from "@angular/router";

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

  constructor(private loginService : LoginService, private router: Router) {
  }

  public login(){
    const usernameInput : HTMLInputElement = <HTMLInputElement>document.getElementById("username");
    const passwordInput : HTMLInputElement = <HTMLInputElement>document.getElementById("password");
    this.username = usernameInput.value;
    this.password = passwordInput.value;
    this.loginService.login(this.username,this.password).subscribe(response => {
      const user:User = response.user as User;
      let json = JSON.stringify(user);
      localStorage.setItem("token",response.token);
      localStorage.setItem("user",json);
      this.router.navigateByUrl("/menu");
    }, error => {
      console.log(error);
    });
  }

}
