import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../../services/auth/AuthService.service';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {skip} from 'rxjs/operators';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

    msg: string;
    authSubscription: Subscription;

    constructor(
        private authService: AuthService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.authSubscription = this.authService.loggedIn
            .pipe(skip(1)).subscribe(
            value => {
                if (value) {
                    this.router.navigate(['/home']);
                } else {
                    this.msg = 'Could not log in';
                }
            }
        );
    }

    ngOnDestroy() {
        if (this.authSubscription) {
            this.authSubscription.unsubscribe();
        }
    }

    onSubmit(form: NgForm) {
        if (form.valid) {
            this.authService.login(form.value.email, form.value.password);
        }
    }

}
