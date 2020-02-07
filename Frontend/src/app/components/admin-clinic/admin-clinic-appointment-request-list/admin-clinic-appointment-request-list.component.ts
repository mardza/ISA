import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort, MatTableDataSource} from '@angular/material';
import {Appointment} from '../../../models/Appointment.model';
import {AppointmentService} from '../../../services/http/appointment.service';

@Component({
    selector: 'app-admin-clinic-appointment-request-list',
    templateUrl: './admin-clinic-appointment-request-list.component.html',
    styleUrls: ['./admin-clinic-appointment-request-list.component.scss']
})
export class AdminClinicAppointmentRequestListComponent implements OnInit {

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
            .getAdminClinicAppointmentRequests()
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

    onApproveClick(appointmentId: number) {
        this.appointmentService
            .approveAppointment(appointmentId)
            .subscribe(
                value => {
                    console.log(value);
                    this.ngOnInit();
                },
                error => {
                    console.log(error);
                }
            );
    }

    onDisapproveClick(appointmentId: number) {
        this.appointmentService
            .disapproveAppointment(appointmentId)
            .subscribe(
                value => {
                    console.log(value);
                    this.ngOnInit();
                },
                error => {
                    console.log(error);
                }
            );
    }
}
