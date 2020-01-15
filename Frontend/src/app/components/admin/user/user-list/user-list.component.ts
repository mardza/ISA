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
    }

}
