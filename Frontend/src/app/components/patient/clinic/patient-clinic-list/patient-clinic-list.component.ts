import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort, MatTableDataSource} from '@angular/material';
import {Clinic} from '../../../../models/Clinic.model';
import {ClinicService} from '../../../../services/http/clinic.service';
import {NgForm} from '@angular/forms';

@Component({
    selector: 'app-patient-clinic-list',
    templateUrl: './patient-clinic-list.component.html',
    styleUrls: ['./patient-clinic-list.component.scss']
})
export class PatientClinicListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<Clinic>;
    loading: boolean;

    @ViewChild(MatSort, {static: true}) sort: MatSort;


    constructor(
        private clinicService: ClinicService
    ) {
    }

    ngOnInit() {
        this.columnsToDisplay = ['name', 'address', 'city', 'country', 'description'];
        this.loading = true;
        this.clinicService
            .getClinics()
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

    onSubmit(form: NgForm) {

    }
}
