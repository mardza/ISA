import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../models/User.model';
import {NgForm} from '@angular/forms';
import {UserService} from '../../services/http/user.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

    user: User;
    isEditActive: boolean;
    loading: boolean;

    constructor(
        private route: ActivatedRoute,
        private userService: UserService
    ) {
    }


    ngOnInit() {
        this.user = this.route.snapshot.data.user;
        this.isEditActive = false;
    }

    onEditClick() {
        this.isEditActive = true;
    }

    onConfirmClick(form: NgForm) {
        if (form.valid) {
            this.loading = true;
            console.log(form.value);
            this.userService
                .updateUser(this.user.email, User.toUser(form.value))
                .subscribe(
                    value => {
                        this.isEditActive = false;
                        this.loading = false;
                    },
                    error => {
                        this.loading = false;
                    }
                );
        }
    }

    onCancelClick(form: NgForm) {
        this.isEditActive = false;
        form.reset(this.user);
    }
}
