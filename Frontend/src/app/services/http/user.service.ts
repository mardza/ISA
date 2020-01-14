import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {User} from '../../models/User.model';
import {catchError, map} from 'rxjs/operators';
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
            );
    }

    getCurrentUserRole(): Observable<String> {
        return this.http
            .get<String>(
                `${globals.backend}/auth/current-user-role`,
                {
                    responseType: 'text' as 'json' // so that http client does not parse response as json
                }
            )
    }
}
