import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {UserService} from '../http/user.service';
import {User} from '../../models/User.model';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class DoctorResolver implements Resolve<User> {

    constructor(
        private userService: UserService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<User> | Promise<User> | User {
        const email: string = route.params.doctorEmail;
        return this.userService.getUserByEmail(email);
    }
}
