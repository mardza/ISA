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
import {
    MatDatepickerModule,
    MatIconModule,
    MatListModule,
    MatMenuModule,
    MatRadioModule,
    MatSelectModule, MatSortModule,
    MatTableModule
} from '@angular/material';
import {UserListComponent} from './components/admin/user/user-list/user-list.component';
import {ProfileComponent} from './components/profile/profile.component';
import {PasswordComponent} from './components/profile/password/password.component';
import {ClinicComponent} from './components/admin/clinic/clinic/clinic.component';
import {ClinicListComponent} from './components/admin/clinic/clinic-list/clinic-list.component';
import {RoomComponent} from './components/admin/room/room/room.component';
import {RoomListComponent} from './components/admin/room/room-list/room-list.component';
import {HomeComponent} from './components/home/home.component';
import {PatientHomeComponentComponent} from './components/patient/patient-home-component/patient-home-component.component';
import {PatientClinicListComponent} from './components/patient/clinic/patient-clinic-list/patient-clinic-list.component';
import {PatientClinicComponent} from './components/patient/clinic/patient-clinic/patient-clinic.component';
import {PatientClinicPredefinedAppointmentListComponent} from './components/patient/clinic/patient-clinic/patient-clinic-predefined-appointment-list/patient-clinic-predefined-appointment-list.component';
import {MinutesPipe} from './services/pipes/minutes.pipe';
import {PatientFindClinicComponent} from './components/patient/appointment/patient-find-clinic/patient-find-clinic.component';
import {MatMomentDateModule} from '@angular/material-moment-adapter';
import {EmptyComponent} from './components/empty/empty.component';
import {PatientFindDoctorComponent} from './components/patient/appointment/patient-find-doctor/patient-find-doctor.component';
import {ConfirmAppointmentComponent} from './components/patient/confirm-appointment/confirm-appointment.component';
import {PatientAppointmentListComponent} from './components/patient/patient-appointment-list/patient-appointment-list.component';
import {AdminClinicHomeComponentComponent} from './components/admin-clinic/admin-clinic-home-component/admin-clinic-home-component.component';
import {AdminClinicAppointmentRequestListComponent} from './components/admin-clinic/admin-clinic-appointment-request-list/admin-clinic-appointment-request-list.component';
import {PatientAppointmentConfirmListComponent} from './components/patient/patient-appointment-confirm-list/patient-appointment-confirm-list.component';
import {ClinicRatingListComponent} from './components/patient/ratings/clinic-rating-list/clinic-rating-list.component';
import {DoctorRatingListComponent} from './components/patient/ratings/doctor-rating-list/doctor-rating-list.component';
import {AdminCenterHomeComponent} from './components/admin/admin-center-home/admin-center-home.component';

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
        ProfileComponent,
        PasswordComponent,
        ClinicComponent,
        ClinicListComponent,
        RoomComponent,
        RoomListComponent,
        HomeComponent,
        PatientHomeComponentComponent,
        PatientClinicListComponent,
        PatientClinicComponent,
        PatientClinicPredefinedAppointmentListComponent,
        MinutesPipe,
        PatientFindClinicComponent,
        EmptyComponent,
        PatientFindDoctorComponent,
        ConfirmAppointmentComponent,
        PatientAppointmentListComponent,
        AdminClinicHomeComponentComponent,
        AdminClinicAppointmentRequestListComponent,
        PatientAppointmentConfirmListComponent,
        ClinicRatingListComponent,
        DoctorRatingListComponent,
        AdminCenterHomeComponent
    ],
    entryComponents: [
        EmptyComponent
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
        MatListModule,
        MatSelectModule,
        MatDatepickerModule,
        MatMomentDateModule,
        MatRadioModule,
        MatSortModule
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
