import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../../models/User.model';
import {Clinic} from '../../../models/Clinic.model';
import {NgForm} from '@angular/forms';
import {Location} from '@angular/common';
import {AppointmentService} from '../../../services/http/appointment.service';
import {AppointmentCreate} from '../../../models/custom/AppointmentCreate.model';

@Component({
    selector: 'app-confirm-appointment',
    templateUrl: './confirm-appointment.component.html',
    styleUrls: ['./confirm-appointment.component.scss']
})
export class ConfirmAppointmentComponent implements OnInit {

    clinic: Clinic;
    doctor: User;
    patient: User;
    datetime: Date;


    constructor(
        private route: ActivatedRoute,
        private location: Location,
        private appointmentService: AppointmentService
    ) {
    }

    ngOnInit() {
        this.clinic = this.route.snapshot.data.clinic;
        this.doctor = this.route.snapshot.data.doctor;
        this.patient = this.route.snapshot.data.patient;
        this.datetime = new Date(+this.route.snapshot.queryParams.time);
    }

    onConfirmClick(form: NgForm) {
        const appointmentCreate: AppointmentCreate = new AppointmentCreate();
        appointmentCreate.time = this.datetime.getTime();
        appointmentCreate.doctorEmail = this.doctor.email;
        appointmentCreate.patientEmail = this.patient.email;
        appointmentCreate.clinicId = this.clinic.id;
        this.appointmentService
            .createAppointment(
                appointmentCreate
            )
            .subscribe(
                value => {
                    console.log(value);

                    // TODO: redirect to appointment list
                },
                error => {
                    console.log(error);
                }
            )
    }

    onCancelClick() {
        this.location.back();
    }
}
