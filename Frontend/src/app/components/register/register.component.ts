import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {User} from '../../models/User.model';
import {UserService} from '../../services/http/user.service';


@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    isLoading: boolean;
    error: string;


    constructor(
        private userService: UserService
    ) {
    }


    ngOnInit() {
        localStorage.removeItem('jwt');
        this.isLoading = false;
        this.error = '';
    }

    onSubmit(form: NgForm) {
        if (form.valid) {
            this.isLoading = true;
            let user: User = new User();
            user.email = form.value.email;
            user.password = form.value.password1;
            user.firstName = form.value.firstName;
            user.lastName = form.value.lastName;
            user.address = form.value.address;
            user.city = form.value.city;
            user.country = form.value.country;
            user.phone = form.value.phone;
            user.insuranceNumber = form.value.insuranceNumber;
            console.log(user);
            this.userService
                .register(user)
                .subscribe(
                    value => {
                        console.log(value);
                        this.isLoading = false;
                    },
                    error => {
                        this.error = error;
                        this.isLoading = false;
                    }
                );
        }
    }
}
