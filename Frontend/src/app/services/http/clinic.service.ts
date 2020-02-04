import {Injectable} from '@angular/core';
import {globals} from '../../globals';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Clinic} from '../../models/Clinic.model';
import * as moment from 'moment';
import {map} from 'rxjs/operators';
import {Appointment} from '../../models/Appointment.model';
import {Price} from '../../models/Price.model';
import {ClinicPriceWrapper} from '../../models/custom/ClinicPriceWrapper.model';
import {DoctorAvailableWrapper} from '../../models/custom/DoctorAvailableWrapper.model';


@Injectable({providedIn: 'root'})
export class ClinicService {

    url = `${globals.backend}/clinics`;
    headerContentTypeJson = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(
        private http: HttpClient
    ) {
    }


    getClinics(query?: { date?: Date, appointmentType?: string, address?: string }): Observable<Clinic[]> {
        let params: HttpParams;
        if (query) {
            params = new HttpParams();
            if (query.date) {
                params = params.set('date', moment(query.date).format('yyyy-MM-dd'));
            }
        }
        return this.http
            .get<Clinic[]>(
                this.url,
                {
                    params: params
                }
            )
            .pipe(
                map(result => Clinic.toClinicList(result))
            );
    }

    getClinicById(id: number): Observable<Clinic> {
        return this.http
            .get<Clinic>(
                this.url + '/' + id
            )
            .pipe(
                map(response => Clinic.toClinic(response))
            );
    }

    createClinic(clinic: Clinic): Observable<Clinic> {
        return this.http
            .post(
                this.url,
                clinic,
                {
                    headers: this.headerContentTypeJson
                }
            )
            .pipe(
                map(response => Clinic.toClinic(response))
            );
    }

    updateClinic(id: number, clinic: Clinic): Observable<Clinic> {
        return this.http
            .put(
                this.url + '/' + id,
                clinic,
                {
                    headers: this.headerContentTypeJson
                }
            )
            .pipe(
                map(response => Clinic.toClinic(response))
            );
    }

    getPredefinedAppointments(id: number): Observable<Appointment[]> {
        return this.http
            .get<Appointment[]>(
                this.url + '/' + id + '/predefined-appointments'
            )
            .pipe(
                map(response => Appointment.toAppointmentList(response))
            );
    }

    search(query?: { appointmentTypeId?: number, date?: string }): Observable<ClinicPriceWrapper[]> {
        let params: HttpParams;
        if (query) {
            params = new HttpParams();
            if(query.appointmentTypeId){
                params = params.set('appointmentTypeId', query.appointmentTypeId.toString());
            }
            if (query.date) {
                params = params.set('date', query.date);
            }
        }
        return this.http
            .get<ClinicPriceWrapper[]>(
                this.url + '/search',
                {
                    params: params
                }
            )
            .pipe(
                map(response => ClinicPriceWrapper.toClinicPriceWrapperList(response))
            );
    }

    searchDoctors(clinicId: number, query?: { appointmentTypeId?: number, date?: string, firstName?: string, lastName?: string, rating?: number }): Observable<DoctorAvailableWrapper[]> {
        let params: HttpParams;
        if(query){
            params = new HttpParams();
            if(query.appointmentTypeId){
                params = params.set('appointmentTypeId', query.appointmentTypeId.toString());
            }
            if (query.date) {
                params = params.set('date', query.date);
            }
            if(query.firstName != null){
                params = params.set('firstName', query.firstName);
            }
            if (query.lastName != null) {
                params = params.set('lastName', query.lastName);
            }
            if (query.rating) {
                params = params.set('rating', query.rating.toString());
            }
        }
        return this.http
            .get<DoctorAvailableWrapper[]>(
                this.url + '/'+ clinicId +'/search-doctors',
                {
                    params: params
                }
            )
            .pipe(
                map(result => DoctorAvailableWrapper.toDoctorAvailableWrapperList(result))
            );
    }
}
