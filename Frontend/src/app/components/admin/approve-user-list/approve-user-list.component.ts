import {Component, OnInit} from '@angular/core';
import {User} from '../../../models/User.model';
import {UserService} from '../../../services/http/user.service';
import {MatTableDataSource} from '@angular/material';

@Component({
    selector: 'app-approve-user-list',
    templateUrl: './approve-user-list.component.html',
    styleUrls: ['./approve-user-list.component.scss']
})
export class ApproveUserListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<User>;
    loading: boolean;


    constructor(
        private userService: UserService
    ) {
    }


    ngOnInit() {
        this.columnsToDisplay = ['firstName', 'lastName', 'email', 'answerButton'];
        this.loading = true;
        this.userService
            .getUsers({approved: false, roleName: 'ROLE_PATIENT'})
            .subscribe(
                value => {
                    this.dataSource = new MatTableDataSource<User>();
                    this.dataSource.data = value;
                    this.loading = false;
                },
                error => {
                    this.loading = false;
                }
            );
    }

}
