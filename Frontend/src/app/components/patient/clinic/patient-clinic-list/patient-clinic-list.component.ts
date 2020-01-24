import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {Clinic} from '../../../../models/Clinic.model';
import {ClinicService} from '../../../../services/http/clinic.service';

@Component({
    selector: 'app-patient-clinic-list',
    templateUrl: './patient-clinic-list.component.html',
    styleUrls: ['./patient-clinic-list.component.scss']
})
export class PatientClinicListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<Clinic>;
    loading: boolean;


    constructor(
        private clinicService: ClinicService
    ) {
    }

    ngOnInit() {
        this.columnsToDisplay = ['name', 'address', 'description'];
        this.loading = true;
        this.clinicService
            .getClinics()
            .subscribe(
                value => {
                    this.dataSource = new MatTableDataSource<Clinic>();
                    this.dataSource.data = value;
                    this.loading = false;
                },
                error => {
                    this.loading = false;
                }
            );
    }
}
