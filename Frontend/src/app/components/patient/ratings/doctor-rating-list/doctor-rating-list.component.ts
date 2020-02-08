import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSelectChange, MatSort, MatTableDataSource} from '@angular/material';
import {UserService} from '../../../../services/http/user.service';
import {User} from '../../../../models/User.model';

@Component({
    selector: 'app-patient-rating-list',
    templateUrl: './doctor-rating-list.component.html',
    styleUrls: ['./doctor-rating-list.component.scss']
})
export class DoctorRatingListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<User>;
    loading: boolean;

    @ViewChild(MatSort, {static: true}) sort: MatSort;


    constructor(
        private userService: UserService
    ) {
    }

    ngOnInit() {
        this.columnsToDisplay = ['fullName', 'specialisation', 'patientRating'];
        this.loading = true;
        this.userService
            .getPatientDoctors()
            .subscribe(
                value => {
                    this.dataSource = new MatTableDataSource<User>();
                    this.dataSource.data = value;
                    this.dataSource.sort = this.sort;
                    this.loading = false;
                },
                error => {
                    this.loading = false;
                }
            );
    }

    onRatingChange(doctorId: number, changeEvent: MatSelectChange) {
        this.userService
            .rateDoctor(doctorId, changeEvent.value)
            .subscribe(
                value => {
                    console.log(value);
                },
                error => {
                    console.log(error);
                }
            );
    }
}
