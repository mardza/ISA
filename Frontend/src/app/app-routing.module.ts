import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
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
import {ClinicListComponent} from './components/clinic/clinic-list/clinic-list.component';
import {RoomListComponent} from './components/room/room-list/room-list.component';
import {RoomComponent} from './components/room/room/room.component';
import {RoomResolver} from './services/resolvers/Room.resolver';
import {ClinicComponent} from './components/clinic/clinic/clinic.component';
import {ClinicResolver} from './services/resolvers/Clinic.resolver';
import {HomeComponent} from './components/home/home.component';
import {PatientHomeComponentComponent} from './components/patient/patient-home-component/patient-home-component.component';
import {PatientClinicListComponent} from './components/patient/clinic/patient-clinic-list/patient-clinic-list.component';
import {PatientClinicComponent} from './components/patient/clinic/patient-clinic/patient-clinic.component';
import {PatientClinicPredefinedAppointmentListComponent} from './components/patient/clinic/patient-clinic/patient-clinic-predefined-appointment-list/patient-clinic-predefined-appointment-list.component';


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
        path: 'home',
        component: HomeComponent,
        data: {
            allowedRoles: ['*']
        },
        canActivate: [RoleGuard]
    },


    {
        path: 'patient',
        component: PatientHomeComponentComponent,
        data: {
            allowedRoles: ['ROLE_PATIENT']
        },
        canActivate: [RoleGuard],
        canActivateChild: [RoleGuard],
        children: [
            {
                path: 'clinics',
                component: PatientClinicListComponent
            },
            {
                path: 'clinics/:id',
                component: PatientClinicComponent,
                resolve: {
                    clinic: ClinicResolver
                }
            }
        ]
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
        path: 'clinics',
        component: ClinicListComponent,
        data: {
            allowedRoles: ['ROLE_PATIENT', 'ROLE_ADMIN_CENTER']
        },
        canActivate: [RoleGuard]
    },
    {
        path: 'clinics/create',
        component: ClinicComponent,
        data: {
            allowedRoles: ['ROLE_ADMIN_CENTER']
        },
        canActivate: [RoleGuard]
    },
    {
        path: 'clinics/:id',
        component: ClinicComponent,
        data: {
            allowedRoles: ['ROLE_ADMIN_CENTER']
        },
        resolve: {
            clinic: ClinicResolver
        },
        canActivate: [RoleGuard]
    },
    {
        path: 'rooms',
        component: RoomListComponent,
        data: {
            allowedRoles: ['ROLE_ADMIN_CENTER']
        },
        canActivate: [RoleGuard]
    },
    {
        path: 'rooms/create',
        component: RoomComponent,
        data: {
            allowedRoles: ['ROLE_ADMIN_CENTER']
        },
        canActivate: [RoleGuard]
    },
    {
        path: 'rooms/:id',
        component: RoomComponent,
        data: {
            allowedRoles: ['ROLE_ADMIN_CENTER']
        },
        resolve: {
            room: RoomResolver
        },
        canActivate: [RoleGuard]
    },
    {
        path: '**',
        redirectTo: '/home'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
