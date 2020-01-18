import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {globals} from '../../globals';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {AppointmentType} from '../../models/AppointmentType.model';

@Injectable({providedIn: 'root'})
export class AppointmentTypeService {

    url = `${globals.backend}/appointment-types`;
    headerContentTypeJson = new HttpHeaders({'Content-Type': 'application/json'});


    constructor(
        private http: HttpClient
    ) {
    }

    getAppointmentTypes(): Observable<AppointmentType[]> {
        return this.http
            .get<AppointmentType[]>(
                this.url
            )
            .pipe(
                map(response => AppointmentType.toAppointmentTypeList(response))
            );
    }
}
