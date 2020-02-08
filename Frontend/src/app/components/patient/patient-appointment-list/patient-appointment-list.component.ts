import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Appointment} from '../../../models/Appointment.model';
import {AppointmentService} from '../../../services/http/appointment.service';
import {MatSort, MatTableDataSource} from '@angular/material';

@Component({
    selector: 'app-patient-appointment-list',
    templateUrl: './patient-appointment-list.component.html',
    styleUrls: ['./patient-appointment-list.component.scss']
})
export class PatientAppointmentListComponent implements OnInit {

    old: boolean;
    columnsToDisplay: string[];
    dataSource: MatTableDataSource<Appointment>;
    currentTime: Date;
    loading: boolean;

    @ViewChild(MatSort, {static: true}) sort: MatSort;


    constructor(
        private route: ActivatedRoute,
        private appointmentService: AppointmentService
    ) {
    }


    ngOnInit() {
        this.columnsToDisplay = ['clinicName', 'typeName', 'roomName', 'doctorName', 'approved', 'finalPrice', 'time', 'buttonCancel'];
        this.currentTime = new Date();
        this.loading = true;
        this.old = this.route.snapshot.data.old;
        this.appointmentService
            .getCurrentUserAppointments(this.old, true)
            .subscribe(
                value => {
                    this.dataSource = new MatTableDataSource<Appointment>();
                    this.dataSource.data = value;
                    this.dataSource.sort = this.sort;
                    this.loading = false;
                },
                error => {
                    console.log(error);
                    this.loading = false;
                }
            );
    }

    onCancelClick(appointmentId: number) {
        this.appointmentService
            .cancelAppointment(appointmentId)
            .subscribe(
                value => {
                    this.ngOnInit();
                },
                error => {
                    console.log(error);
                }
            );
    }
}
