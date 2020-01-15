import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {UserService} from '../../../services/http/user.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-password',
    templateUrl: './password.component.html',
    styleUrls: ['./password.component.scss']
})
export class PasswordComponent implements OnInit {

    loading: boolean;


    constructor(
        private userService: UserService,
        private router: Router
    ) {
    }


    ngOnInit() {
    }

    onConfirmClick(form: NgForm) {
        if (form.valid) {
            this.loading = true;
            this.userService
                .changePassword(form.value.password1)
                .subscribe(
                    value => {
                        this.router.navigate(['/profile']);
                        this.loading = false;
                    },
                    error => {
                        this.loading = false;
                    }
                );
        }
    }
}
