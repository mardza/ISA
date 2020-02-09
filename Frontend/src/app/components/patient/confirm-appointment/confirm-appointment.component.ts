import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../../models/User.model';
import {Clinic} from '../../../models/Clinic.model';
import {Location} from '@angular/common';
import {AppointmentService} from '../../../services/http/appointment.service';
import {AppointmentCreate} from '../../../models/custom/AppointmentCreate.model';
import {Appointment} from '../../../models/Appointment.model';
import {HttpApiError} from '../../../models/HttpApiError.model';

@Component({
    selector: 'app-confirm-appointment',
    templateUrl: './confirm-appointment.component.html',
    styleUrls: ['./confirm-appointment.component.scss']
})
export class ConfirmAppointmentComponent implements OnInit {

    appointment: Appointment;
    clinic: Clinic;
    doctor: User;
    patient: User;
    datetime: Date;


    constructor(
        private route: ActivatedRoute,
        private location: Location,
        private appointmentService: AppointmentService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.appointment = this.route.snapshot.data.appointment;
        if (this.appointment) {
            this.clinic = this.appointment.clinic;
            this.doctor = this.appointment.doctor;
            this.datetime = this.appointment.time;
        } else {
            this.clinic = this.route.snapshot.data.clinic;
            this.doctor = this.route.snapshot.data.doctor;
            this.datetime = new Date(+this.route.snapshot.queryParams.time);
        }
        this.patient = this.route.snapshot.data.patient;
    }

    onConfirmClick() {

        if (this.appointment) {
            this.appointmentService
                .activateAppointment(this.appointment.id)
                .subscribe(
                    value => {
                        console.log(value);
                        this.router.navigate(['/patient/appointments']);
                    },
                    (error: HttpApiError) => {
                        console.log(error);
                        window.alert(error.message);
                    }
                )
        } else {
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
                        this.router.navigate(['/patient/appointments']);
                    },
                    (error: HttpApiError) => {
                        console.log(error);
                        window.alert(error.message);
                    }
                );
        }
    }

    onCancelClick() {
        this.location.back();
    }
}
