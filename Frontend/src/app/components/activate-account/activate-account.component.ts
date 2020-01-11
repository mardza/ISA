import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/http/user.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-activate-account',
    templateUrl: './activate-account.component.html',
    styleUrls: ['./activate-account.component.scss']
})
export class ActivateAccountComponent implements OnInit {

    message: string;
    activationId: string;

    constructor(
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.message = 'Please wait while account is being activated...';
        this.activationId = this.route.snapshot.params.activationId;
        this.userService
            .activateAccount(this.activationId)
            .subscribe(
                value => {
                    this.message = 'Account successfully activated!<br/>Redirecting in 5 seconds...';
                    setTimeout(() => {
                        this.router.navigate(['/login']);
                    }, 5000);
                },
                error => {
                    this.message = 'Something went wrong.<br/> Check your activation link.';
                }
            );
    }

}
