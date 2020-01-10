import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {AuthService} from './AuthService.service';
import {tap} from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class TokenInterceptorService implements HttpInterceptor {

    constructor(
        private authService: AuthService
    ) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const modifiedReq = req.clone({
            headers: new HttpHeaders().set('Authorization', 'Bearer ' + localStorage.getItem('jwt'))
        });

        return next
            .handle(modifiedReq)
            .pipe(
                tap((event: HttpEvent<any>) => {
                    if (event instanceof HttpResponse) {
                        if (event.status === 401) {
                            this.authService.logout();
                        }
                    }
                })
            );
    }
}
