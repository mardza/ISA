import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AppointmentType} from '../../../../models/AppointmentType.model';
import {ActivatedRoute} from '@angular/router';
import {MAT_DATE_FORMATS} from '@angular/material';
import {Moment} from 'moment';
import {EmptyComponent} from '../../../empty/empty.component';
import * as moment from 'moment';
import {ClinicService} from '../../../../services/http/clinic.service';


const YEAR_FORMAT = {
    display: {
        dateInput: 'DD.MM.YYYY'
    }
};

@Component({
    selector: 'app-patient-find-clinic',
    templateUrl: './patient-find-clinic.component.html',
    styleUrls: ['./patient-find-clinic.component.scss'],
    providers: [
        {
            provide: MAT_DATE_FORMATS,
            useValue: YEAR_FORMAT
        }
    ]
})
export class PatientFindClinicComponent implements OnInit {

    appointmentTypeList: AppointmentType[];
    customHeader = EmptyComponent;
    currentMoment: Moment;

    constructor(
        private route: ActivatedRoute,
        private clinicService: ClinicService
    ) {
    }


    ngOnInit() {
        this.appointmentTypeList = this.route.snapshot.data.appointmentTypeList;
        this.currentMoment = moment();
    }

    onSubmit(form: NgForm) {
        console.log(form.value);
        this.clinicService
            .search({appointmentTypeId: form.value.selectedAppointmentTypeId, date: (<Moment>form.value.selectedDate).toDate()})
            .subscribe(
                value => {
                    console.log(value);
                },
                error => {
                    console.log(error);
                }
            )
    }

    myFilter = (moment: Moment): boolean => {
        return moment.isSameOrAfter(this.currentMoment, 'day');
    };
}
