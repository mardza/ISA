import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {globals} from '../../globals';
import {Router} from '@angular/router';


@Injectable({providedIn: 'root'})
export class AuthService {

    loggedIn: BehaviorSubject<boolean>;

    constructor(
        private http: HttpClient,
        private router: Router
    ) {
        this.loggedIn = new BehaviorSubject<boolean>(false);
    }


    login(email: string, password: string) {
        this.http
            .post<string>(
                globals.backend + '/auth/login',
                {
                    email: email,
                    password: password
                },
                {
                    responseType: 'text' as 'json' // so that http client does not parse response as json
                }
            )
            .subscribe(
                token => {
                    console.log('Auth good');
                    localStorage.setItem('jwt', token);
                    this.loggedIn.next(true);
                },
                error => {
                    console.log('Auth NOT good');
                    console.log(error);
                    this.loggedIn.next(false);
                }
            );
    }

    logout() {
        console.log('Logging out');
        localStorage.removeItem('jwt');
        this.loggedIn.next(false);
        this.router.navigate(['/login']);
    }
}
