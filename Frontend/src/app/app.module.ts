import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {TokenInterceptorService} from './services/auth/TokenInterceptor.service';
import {ActivateAccountComponent} from './components/activate-account/activate-account.component';
import {ApproveUserListComponent} from './components/admin/approve-user-list/approve-user-list.component';
import {ApproveUserComponent} from './components/admin/approve-user-list/approve-user/approve-user.component';
import {HeaderComponent} from './components/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule, MatListModule, MatMenuModule, MatTableModule} from '@angular/material';
import {UserListComponent} from './components/admin/user/user-list/user-list.component';
import {UserComponent} from './components/admin/user/user/user.component';
import {UserEditComponent} from './components/admin/user/user-edit/user-edit.component';
import {ProfileComponent} from './components/profile/profile.component';
import {PasswordComponent} from './components/profile/password/password.component';
import {ClinicComponent} from './components/clinic/clinic/clinic.component';
import {ClinicListComponent} from './components/clinic/clinic-list/clinic-list.component';
import {RoomComponent} from './components/room/room/room.component';
import {RoomListComponent} from './components/room/room-list/room-list.component';
import { AppointmentTypeComponent } from './components/appointment-type/appointment-type/appointment-type.component';
import { AppointmentTypeListComponent } from './components/appointment-type/appointment-type-list/appointment-type-list.component';
import { HomeComponent } from './components/home/home.component';
import { PatientHomeComponentComponent } from './components/patient/patient-home-component/patient-home-component.component';
import { PatientClinicListComponent } from './components/patient/clinic/patient-clinic-list/patient-clinic-list.component';
import { PatientClinicComponent } from './components/patient/clinic/patient-clinic/patient-clinic.component';
import { NewAppointmentComponent } from './components/patient/appointment/new-appointment/new-appointment.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegisterComponent,
        ActivateAccountComponent,
        ApproveUserListComponent,
        ApproveUserComponent,
        HeaderComponent,
        UserListComponent,
        UserComponent,
        UserEditComponent,
        ProfileComponent,
        PasswordComponent,
        ClinicComponent,
        ClinicListComponent,
        RoomComponent,
        RoomListComponent,
        AppointmentTypeComponent,
        AppointmentTypeListComponent,
        HomeComponent,
        PatientHomeComponentComponent,
        PatientClinicListComponent,
        PatientClinicComponent,
        NewAppointmentComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        MatToolbarModule,
        MatIconModule,
        MatMenuModule,
        MatTableModule,
        MatListModule
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
