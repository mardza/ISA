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

    search(query?: { appointmentTypeId?: number, date?: Date }): Observable<ClinicPriceWrapper[]> {
        let params: HttpParams;
        if (query) {
            params = new HttpParams();
            if(query.appointmentTypeId){
                params = params.set('appointmentTypeId', query.appointmentTypeId.toString());
            }
            if (query.date) {
                params = params.set('date', moment(query.date).format('YYYY-MM-DD'));
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
}
