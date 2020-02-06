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

    getCurrentUserAppointments(old: boolean): Observable<Appointment[]> {
        let params: HttpParams;
        if(old !== null && old !== undefined){
            params = new HttpParams();
            params = params.set('old', old.toString());
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

    getAppointments(query?: {doctorEmail?: string, patientEmail?: string, approved?: boolean, clinicId?: number, predefined?: boolean, old?: boolean}): Observable<Appointment[]> {
        let params: HttpParams;
        if(query){
            params = new HttpParams();
            if(query.doctorEmail){
                params = params.set('doctorEmail', query.doctorEmail);
            }
            if(query.patientEmail){
                params = params.set('patientEmail', query.patientEmail);
            }
            if(query.approved !== null && query.approved !== undefined){
                params = params.set('approved', query.approved.toString());
            }
            if(query.clinicId){
                params = params.set('clinicId', query.clinicId.toString());
            }
            if(query.predefined !== null && query.predefined !== undefined){
                params = params.set('predefined', query.predefined.toString());
            }
            if(query.old !== null && query.old !== undefined){
                params = params.set('old', query.old.toString());
            }
        }
        return this.http
            .get<Appointment[]>(
                this.url,
                {
                    params: params
                }
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

    deleteAppointment(id: number): Observable<any> {
        return this.http
            .delete(
                this.url + '/' + id
            );
    }
}
