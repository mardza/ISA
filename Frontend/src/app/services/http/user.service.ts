import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
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

    getUsers(query?: {approved?: boolean, activated?: boolean, roleName?: string, firstName?: string, lastName?: string, insuranceNumber?: string}): Observable<User[]> {
        let params: HttpParams;
        if(query){
            params = new HttpParams();
            if(query.approved !== null && query.approved !== undefined){
                params = params.set('approved', query.approved.toString());
            }
            if(query.activated !== null && query.activated !== undefined){
                params = params.set('activated', query.activated.toString());
            }
            if(query.roleName){
                params = params.set('roleName', query.roleName);
            }
            if(query.firstName){
                params = params.set('firstName', query.firstName);
            }
            if(query.lastName){
                params = params.set('lastName', query.lastName);
            }
            if(query.insuranceNumber){
                params = params.set('insuranceNumber', query.insuranceNumber);
            }
        }

        return this.http
            .get<User[]>(
                this.url,
                {
                    params: params
                }
            )
            .pipe(
                map(response => User.toUserList(response))
            );
    }
}
