import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {User} from '../../../../models/User.model';
import {UserService} from '../../../../services/http/user.service';

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<User>;
    loading: boolean;


    constructor(
        private userService: UserService
    ) {
    }

    ngOnInit() {
        this.columnsToDisplay = ['firstName', 'lastName', 'email', 'role'];
        this.loading = true;
        this.userService
            .getUsers()
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
