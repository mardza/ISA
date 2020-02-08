import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSelectChange, MatSort, MatTableDataSource} from '@angular/material';
import {Clinic} from '../../../../models/Clinic.model';
import {ClinicService} from '../../../../services/http/clinic.service';

@Component({
    selector: 'app-clinic-rating-list',
    templateUrl: './clinic-rating-list.component.html',
    styleUrls: ['./clinic-rating-list.component.scss']
})
export class ClinicRatingListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<Clinic>;
    loading: boolean;

    @ViewChild(MatSort, {static: true}) sort: MatSort;


    constructor(
        private clinicService: ClinicService
    ) {
    }


    ngOnInit() {
        this.columnsToDisplay = ['name', 'address', 'city', 'country', 'description', 'patientRating'];
        this.loading = true;
        this.clinicService
            .getPatientClinics()
            .subscribe(
                value => {
                    this.dataSource = new MatTableDataSource<Clinic>();
                    this.dataSource.data = value;
                    this.dataSource.sort = this.sort;
                    this.loading = false;
                },
                error => {
                    this.loading = false;
                }
            );
    }

    onRatingChange(clinicId: number, changeEvent: MatSelectChange) {
        this.clinicService
            .rateClinic(clinicId, changeEvent.value)
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
