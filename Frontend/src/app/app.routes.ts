import { Routes } from '@angular/router';
import {LoginPageComponent} from "./login-page/login-page.component";
import {RegisterComponent} from "./login-page/register/register.component";
import {MenuComponent} from "./menu/menu.component";

export const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginPageComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'menu', component: MenuComponent}
];
