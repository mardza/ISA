import {Injectable} from '@angular/core';
import {User} from '../../models/User.model';
import {UserService} from '../http/user.service';
import {Observable} from 'rxjs';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';

@Injectable({providedIn: 'root'})
export class UserResolver implements Resolve<User> {

    constructor(
        private userService: UserService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<User> | Promise<User> | User {
        const email: string = route.params.email;
        return this.userService.getUserByEmail(email);
    }
}
