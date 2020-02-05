import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../../models/User.model';
import {Clinic} from '../../../models/Clinic.model';
import {NgForm} from '@angular/forms';
import {Location} from '@angular/common';

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
        private location: Location
    ) {
    }

    ngOnInit() {
        this.clinic = this.route.snapshot.data.clinic;
        this.doctor = this.route.snapshot.data.doctor;
        this.patient = this.route.snapshot.data.patient;
        this.datetime = new Date(+this.route.snapshot.queryParams.time);
    }

    onConfirmClick(form: NgForm) {

    }

    onCancelClick() {
        this.location.back();
    }
}
