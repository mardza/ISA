import {Injectable} from '@angular/core';
import {globals} from '../../globals';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Appointment} from '../../models/Appointment.model';
import {map} from 'rxjs/operators';
import {AppointmentCreate} from '../../models/custom/AppointmentCreate.model';

@Injectable({providedIn: 'root'})
export class AppointmentService {

    url = `${globals.backend}/appointments`;
    headerContentTypeJson = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(
        private http: HttpClient
    ) {
    }

    getCurrentUserAppointments(old: boolean, patientApproved: boolean): Observable<Appointment[]> {
        let params: HttpParams;
        params = new HttpParams();
        if(old !== null && old !== undefined){
            params = params.set('old', old.toString());
        }
        if(patientApproved !== null && patientApproved !== undefined){
            params = params.set('patientApproved', patientApproved.toString());
        }
        return this.http
            .get<Appointment[]>(
                this.url + '/current-user',
                {
                    params: params
                }
            )
            .pipe(
                map(response => Appointment.toAppointmentList(response))
            );
    }

    getAdminClinicAppointmentRequests(): Observable<Appointment[]> {
        return this.http
            .get<Appointment[]>(
                this.url + '/admin-clinic-requests'
            )
            .pipe(
                map(response => Appointment.toAppointmentList(response))
            );
    }

    getAppointmentById(id: number): Observable<Appointment> {
        return this.http
            .get(
                this.url + '/' + id
            )
            .pipe(
                map(response => Appointment.toAppointment(response))
            );
    }

    createAppointment(appointment: AppointmentCreate): Observable<Appointment> {
        return this.http
            .post(
                this.url,
                appointment,
                {
                    headers: this.headerContentTypeJson
                }
            )
            .pipe(
                map(response => Appointment.toAppointment(response))
            );
    }

    // when patient confirms predefined appointment
    activateAppointment(id: number): Observable<Appointment> {
        return this.http
            .post(
                this.url + '/' + id + '/activate',
                null
            )
            .pipe(
                map(response => Appointment.toAppointment(response))
            );
    }

    // when clinic admin approves appointment request
    approveAppointment(id: number): Observable<Appointment> {
        return this.http
            .post(
                this.url + '/' + id + '/approve',
                null
            )
            .pipe(
                map(response => Appointment.toAppointment(response))
            );
    }

    // when clinic admin disapproves appointment request
    disapproveAppointment(id: number): Observable<Appointment> {
        return this.http
            .post(
                this.url + '/' + id + '/disapprove',
                null
            )
            .pipe(
                map(response => Appointment.toAppointment(response))
            );
    }

    patientApproveAppointment(id: number): Observable<Appointment> {
        return this.http
            .post(
                this.url + '/' + id + '/patient-approve',
                null
            )
            .pipe(
                map(response => Appointment.toAppointment(response))
            );
    }

    cancelAppointment(id: number): Observable<any> {
        return this.http
            .delete(
                this.url + '/' + id
            );
    }
}
