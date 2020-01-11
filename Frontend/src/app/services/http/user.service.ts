import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {User} from '../../models/User.model';
import {catchError, map, tap} from 'rxjs/operators';
import {globals} from '../../globals';

@Injectable({providedIn: 'root'})
export class UserService {

    url = `${globals.backend}/users`;
    headerContentTypeJson = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(
        private http: HttpClient
    ) {
    }

    register(user: User): Observable<User> {
        return this.http
            .post(
                this.url + '/register',
                user,
                {
                    headers: this.headerContentTypeJson
                }
            )
            .pipe(
                map(response => User.toUser(response))
            );
    }

    activateAccount(activationId: string): Observable<any> {
        return this.http
            .post(
                this.url + '/activate-registration/' + activationId,
                null
            )
            .pipe(
                tap(x => {
                    console.log(x);
                }),
                catchError(err => {
                    console.log(err);
                    return throwError(err);
                })
            );
    }


    handleError(error) {
        return throwError(JSON.parse(error.error));
    }
}
