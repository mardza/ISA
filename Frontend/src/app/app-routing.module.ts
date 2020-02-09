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
import {ClinicListComponent} from './components/admin/clinic/clinic-list/clinic-list.component';
import {RoomListComponent} from './components/admin/room/room-list/room-list.component';
import {RoomComponent} from './components/admin/room/room/room.component';
import {RoomResolver} from './services/resolvers/Room.resolver';
import {ClinicComponent} from './components/admin/clinic/clinic/clinic.component';
import {ClinicResolver} from './services/resolvers/Clinic.resolver';
import {HomeComponent} from './components/home/home.component';
import {PatientHomeComponentComponent} from './components/patient/patient-home-component/patient-home-component.component';
import {PatientClinicListComponent} from './components/patient/clinic/patient-clinic-list/patient-clinic-list.component';
import {PatientClinicComponent} from './components/patient/clinic/patient-clinic/patient-clinic.component';
import {PatientFindClinicComponent} from './components/patient/appointment/patient-find-clinic/patient-find-clinic.component';
import {AppointmentTypeListResolver} from './services/resolvers/AppointmentTypeList.resolver';
import {PatientFindDoctorComponent} from './components/patient/appointment/patient-find-doctor/patient-find-doctor.component';
import {ConfirmAppointmentComponent} from './components/patient/confirm-appointment/confirm-appointment.component';
import {DoctorResolver} from './services/resolvers/Doctor.resolver';
import {PatientAppointmentListComponent} from './components/patient/patient-appointment-list/patient-appointment-list.component';
import {AppointmentResolver} from './services/resolvers/Appointment.resolver';
import {AdminClinicHomeComponentComponent} from './components/admin-clinic/admin-clinic-home-component/admin-clinic-home-component.component';
import {AdminClinicAppointmentRequestListComponent} from './components/admin-clinic/admin-clinic-appointment-request-list/admin-clinic-appointment-request-list.component';
import {PatientAppointmentConfirmListComponent} from './components/patient/patient-appointment-confirm-list/patient-appointment-confirm-list.component';
import {ClinicRatingListComponent} from './components/patient/ratings/clinic-rating-list/clinic-rating-list.component';
import {DoctorRatingListComponent} from './components/patient/ratings/doctor-rating-list/doctor-rating-list.component';
import {AdminCenterHomeComponent} from './components/admin/admin-center-home/admin-center-home.component';


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
                path: 'clinics/:clinicId',
                component: PatientClinicComponent,
                resolve: {
                    clinic: ClinicResolver
                }
            },
            {
                path: 'clinics/:clinicId/appointments/:appointmentId/confirm-appointment',
                component: ConfirmAppointmentComponent,
                resolve: {
                    appointment: AppointmentResolver,
                    patient: CurrentUserResolver
                }
            },
            {
                path: 'find-clinic',
                component: PatientFindClinicComponent,
                resolve: {
                    appointmentTypeList: AppointmentTypeListResolver
                }
            },
            {
                path: 'find-clinic/:clinicId/doctors',
                component: PatientFindDoctorComponent,
                resolve: {
                    appointmentTypeList: AppointmentTypeListResolver,
                    clinic: ClinicResolver
                }
            },
            {
                path: 'find-clinic/:clinicId/doctors/:doctorEmail/confirm-appointment',
                component: ConfirmAppointmentComponent,
                resolve: {
                    clinic: ClinicResolver,
                    doctor: DoctorResolver,
                    patient: CurrentUserResolver
                }
            },
            {
                path: 'appointments-confirm',
                component: PatientAppointmentConfirmListComponent
            },
            {
                path: 'appointments',
                component: PatientAppointmentListComponent,
                data: {
                    old: false
                }
            },
            {
                path: 'appointments/old',
                component: PatientAppointmentListComponent,
                data: {
                    old: true
                }
            },
            {
                path: 'clinic-rating',
                component: ClinicRatingListComponent
            },
            {
                path: 'doctor-rating',
                component: DoctorRatingListComponent
            }
        ]
    },


    {
        path: 'admin-clinic',
        component: AdminClinicHomeComponentComponent,
        data: {
            allowedRoles: ['ROLE_ADMIN_CLINIC']
        },
        canActivate: [RoleGuard],
        canActivateChild: [RoleGuard],
        children: [
            {
                path: 'appointment-requests',
                component: AdminClinicAppointmentRequestListComponent
            }
        ]
    },


    {
        path: 'admin',
        component: AdminCenterHomeComponent,
        data: {
            allowedRoles: ['ROLE_ADMIN_CENTER']
        },
        canActivate: [RoleGuard],
        canActivateChild: [RoleGuard],
        children: [
            {
                path: 'approve-user-list',
                component: ApproveUserListComponent
            },
            {
                path: 'approve-user-list/:email',
                component: ApproveUserComponent,
                resolve: {
                    user: UserResolver
                }
            },
            {
                path: 'user-list',
                component: UserListComponent
            },
            {
                path: 'clinics',
                component: ClinicListComponent
            },
            {
                path: 'clinics/create',
                component: ClinicComponent
            },
            {
                path: 'clinics/:clinicId',
                component: ClinicComponent,
                resolve: {
                    clinic: ClinicResolver
                }
            },
            {
                path: 'rooms',
                component: RoomListComponent
            },
            {
                path: 'rooms/create',
                component: RoomComponent
            },
            {
                path: 'rooms/:id',
                component: RoomComponent,
                resolve: {
                    room: RoomResolver
                }
            }
        ]
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
