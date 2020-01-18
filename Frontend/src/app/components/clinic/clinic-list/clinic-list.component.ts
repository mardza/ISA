import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {ClinicService} from '../../../services/http/clinic.service';
import {Clinic} from '../../../models/Clinic.model';

@Component({
    selector: 'app-clinic-list',
    templateUrl: './clinic-list.component.html',
    styleUrls: ['./clinic-list.component.scss']
})
export class ClinicListComponent implements OnInit {

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
