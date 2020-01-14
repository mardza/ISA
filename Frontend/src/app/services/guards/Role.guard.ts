import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {UserService} from '../http/user.service';
import {catchError, map} from 'rxjs/operators';
import {Injectable} from '@angular/core';

@Injectable({providedIn: 'root'})
export class RoleGuard implements CanActivate {

    constructor(
        private userService: UserService,
        private router: Router
    ) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        console.log('Starting RoleGuard on route: "' + state.url + '"');
        const allowedRoles: string[] = route.data.allowedRoles;
        if(!allowedRoles){
            console.log('Check route allowedRoles data parameter');
        }
        return this.userService
            .getCurrentUserRole()
            .pipe(
                map((role: string) => {
                    console.log('RoleGuard: user role is ' + role);
                    localStorage.setItem('role', role);
                    if (allowedRoles.includes(role)) {
                        console.log('RoleGuard: good role');
                        return true;
                    }
                    console.log('RoleGuard: wrong role, redirecting to login');
                    return this.router.createUrlTree(['/login']);
                })
            );
    }
}
