import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from './components/register/register.component';
import {ActivateAccountComponent} from './components/activate-account/activate-account.component';
import {ApproveUserListComponent} from './components/admin/approve-user-list/approve-user-list.component';
import {ApproveUserComponent} from './components/admin/approve-user-list/approve-user/approve-user.component';
import {RoleGuard} from './services/guards/Role.guard';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'activate-account/:activationId',
    component: ActivateAccountComponent
  },
  {
    path: 'admin/approve-user-list',
    component: ApproveUserListComponent,
    data: {
      allowedRoles: ['ROLE_ADMIN_CENTER']
    },
    canActivate: [RoleGuard]
  },
  {
    path: 'admin/approve-user-list/:id',
    component: ApproveUserComponent,
    data: {
      allowedRoles: ['ROLE_ADMIN_CENTER']
    },
    canActivate: [RoleGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
