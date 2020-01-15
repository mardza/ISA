import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../../../models/User.model';
import {UserService} from '../../../../services/http/user.service';

@Component({
    selector: 'app-approve-user',
    templateUrl: './approve-user.component.html',
    styleUrls: ['./approve-user.component.scss']
})
export class ApproveUserComponent implements OnInit {

    user: User;
    message: string;
    loading: boolean;


    constructor(
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router
    ) {
    }


    ngOnInit() {
        this.user = this.route.snapshot.data.user;
    }

    onApproveClick(approved: boolean){
        this.loading = true;
        this.userService
            .approveUser(this.user.email, approved, this.message)
            .subscribe(
                value => {
                    this.loading = false;
                    this.router.navigate(['/admin/approve-user-list']);
                },
                error => {
                    this.loading = false;
                    this.router.navigate(['/admin/approve-user-list']);
                }
            );
    }
}
