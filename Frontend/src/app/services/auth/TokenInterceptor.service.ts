import {HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Injectable} from '@angular/core';
import {AuthService} from './AuthService.service';
import {catchError} from 'rxjs/operators';
import {HttpApiError} from '../../models/HttpApiError.model';

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
                catchError((err: HttpErrorResponse) => {
                    if (err.status === 401) {
                        this.authService.logout();
                    }
                    return throwError(new HttpApiError(err));
                })
            );
    }
}
