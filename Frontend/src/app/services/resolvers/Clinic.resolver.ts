import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Clinic} from '../../models/Clinic.model';
import {ClinicService} from '../http/clinic.service';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class ClinicResolver implements Resolve<Clinic> {

    constructor(
        private clinicService: ClinicService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Clinic> | Promise<Clinic> | Clinic {
        const id: number = route.params.id;
        return this.clinicService.getClinicById(id);
    }
}
