import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {AppointmentTypeService} from '../http/appointment-type.service';
import {AppointmentType} from '../../models/AppointmentType.model';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class AppointmentTypeListResolver implements Resolve<AppointmentType[]> {

    constructor(
        private appointmentTypeService: AppointmentTypeService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AppointmentType[]> | Promise<AppointmentType[]> | AppointmentType[] {
        return this.appointmentTypeService.getAppointmentTypes();
    }
}
