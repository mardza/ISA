import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort, MatTableDataSource} from '@angular/material';
import {Appointment} from '../../../models/Appointment.model';
import {AppointmentService} from '../../../services/http/appointment.service';

@Component({
    selector: 'app-patient-appointment-confirm-list',
    templateUrl: './patient-appointment-confirm-list.component.html',
    styleUrls: ['./patient-appointment-confirm-list.component.scss']
})
export class PatientAppointmentConfirmListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<Appointment>;
    loading: boolean;

    @ViewChild(MatSort, {static: true}) sort: MatSort;


    constructor(
        private appointmentService: AppointmentService
    ) {
    }


    ngOnInit() {
        this.columnsToDisplay = ['clinicName', 'typeName', 'roomName', 'doctorName', 'approved', 'finalPrice', 'time', 'button'];
        this.loading = true;
        this.appointmentService
            .getCurrentUserAppointments(false, false)
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

    onConfirmClick(appointmentId: number) {
        this.appointmentService
            .patientApproveAppointment(appointmentId)
            .subscribe(
                value => {
                    this.ngOnInit();
                },
                error => {
                    console.log(error);
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
