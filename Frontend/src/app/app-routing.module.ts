import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from './components/register/register.component';
import {ActivateAccountComponent} from './components/activate-account/activate-account.component';
import {ApproveUserListComponent} from './components/admin/approve-user-list/approve-user-list.component';
import {ApproveUserComponent} from './components/admin/approve-user-list/approve-user/approve-user.component';
import {RoleGuard} from './services/guards/Role.guard';
import {UserResolver} from './services/resolvers/User.resolver';
import {UserListComponent} from './components/admin/user/user-list/user-list.component';
import {ProfileComponent} from './components/profile/profile.component';
import {CurrentUserResolver} from './services/resolvers/CurrentUser.resolver';
import {PasswordComponent} from './components/profile/password/password.component';


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
    path: 'profile',
    component: ProfileComponent,
    data: {
      allowedRoles: ['*']
    },
    resolve: {
      user: CurrentUserResolver,
    },
    canActivate: [RoleGuard]
  },
  {
    path: 'profile/password',
    component: PasswordComponent,
    data: {
      allowedRoles: ['*']
    },
    canActivate: [RoleGuard]
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
    path: 'admin/approve-user-list/:email',
    component: ApproveUserComponent,
    data: {
      allowedRoles: ['ROLE_ADMIN_CENTER']
    },
    resolve: {
      user: UserResolver
    },
    canActivate: [RoleGuard]
  },
  {
    path: 'admin/user-list',
    component: UserListComponent,
    data: {
      allowedRoles: ['ROLE_ADMIN_CENTER']
    },
    canActivate: [RoleGuard]
  },
  {
    path: '**',
    redirectTo: '/'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
