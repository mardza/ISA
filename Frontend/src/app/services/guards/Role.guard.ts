import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable, of} from 'rxjs';
import {UserService} from '../http/user.service';
import {catchError, map} from 'rxjs/operators';
import {Injectable} from '@angular/core';

@Injectable({providedIn: 'root'})
export class RoleGuard implements CanActivate, CanActivateChild {

    constructor(
        private userService: UserService,
        private router: Router
    ) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        console.log('Starting RoleGuard on route: "' + state.url + '"');
        //let allowedRoles: string[] = route.data.allowedRoles;
        const allowedRoles = this.getAllowedRoles(route);
        if (!allowedRoles) {
            console.error('Check route allowedRoles data parameter');
        }
        return this.userService
            .getCurrentUserRole()
            .pipe(
                // catchError(err => {
                //     console.log(err);
                //     return of('UNDEFINED');
                // }),
                map((role: string) => {
                    console.log('RoleGuard: user role is ' + role);
                    localStorage.setItem('role', role);
                    if (allowedRoles.includes(role) || allowedRoles.includes('*')) {
                        console.log('RoleGuard: good role');
                        return true;
                    }
                    console.log('RoleGuard: wrong role, redirecting to login');
                    // TODO: redirect to 404 page
                    return this.router.createUrlTree(['/login']);
                })
            );
    }

    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        return this.canActivate(childRoute, state);
    }

    // Recursively find allowedRoles field on parent routes
    private getAllowedRoles(route: ActivatedRouteSnapshot): string[] {
        if (route === route.root) {
            console.log('Reached root route');
            return route.data.allowedRoles;
        } else if (route.data.allowedRoles) {
            console.log('Found allowedRoles on one of parent routes');
            return route.data.allowedRoles;
        } else {
            return this.getAllowedRoles(route.parent);
        }
    }
}
