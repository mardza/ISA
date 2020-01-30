import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AppointmentType} from '../../../../models/AppointmentType.model';
import {ActivatedRoute} from '@angular/router';
import {EmptyComponent} from '../../../empty/empty.component';
import {Moment} from 'moment';
import * as moment from 'moment';
import {MAT_DATE_FORMATS} from '@angular/material';

const YEAR_FORMAT = {
    display: {
        dateInput: 'DD.MM.YYYY'
    }
};

@Component({
    selector: 'app-patient-find-doctor',
    templateUrl: './patient-find-doctor.component.html',
    styleUrls: ['./patient-find-doctor.component.scss'],
    providers: [
        {
            provide: MAT_DATE_FORMATS,
            useValue: YEAR_FORMAT
        }
    ]
})
export class PatientFindDoctorComponent implements OnInit {

    appointmentTypeList: AppointmentType[];
    customHeader = EmptyComponent;
    currentMoment: Moment;

    constructor(
        private route: ActivatedRoute
    ) {
    }

    // TODO: prepopulate form fields with info from url params.


    ngOnInit() {
        this.appointmentTypeList = this.route.snapshot.data.appointmentTypeList;
        this.currentMoment = moment();
    }

    onSubmit(form: NgForm) {

    }

    myFilter = (moment: Moment): boolean => {
        return moment.isSameOrAfter(this.currentMoment, 'day');
    };
}
