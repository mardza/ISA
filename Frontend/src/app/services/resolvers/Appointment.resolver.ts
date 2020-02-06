import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Appointment} from '../../models/Appointment.model';
import {AppointmentService} from '../http/appointment.service';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class AppointmentResolver implements Resolve<Appointment> {

    constructor(
        private appointmentService: AppointmentService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Appointment> | Promise<Appointment> | Appointment {
        const id: number = route.params.appointmentId;
        return this.appointmentService.getAppointmentById(id);
    }
}
