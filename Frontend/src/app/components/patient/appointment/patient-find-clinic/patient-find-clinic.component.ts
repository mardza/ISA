import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AppointmentType} from '../../../../models/AppointmentType.model';
import {ActivatedRoute} from '@angular/router';
import {MAT_DATE_FORMATS} from '@angular/material';
import {Moment} from 'moment';
import {EmptyComponent} from '../../../empty/empty.component';
import * as moment from 'moment';
import {ClinicService} from '../../../../services/http/clinic.service';
import {ClinicPriceWrapper} from '../../../../models/custom/ClinicPriceWrapper.model';


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
    clinicPriceWrapperList: ClinicPriceWrapper[];

    selectedAppointmentTypeId: number;
    selectedDate: string;


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
        this.selectedAppointmentTypeId = form.value.selectedAppointmentTypeId;
        this.selectedDate = form.value.selectedDate.format('YYYY-MM-DD');

        console.log(form.value);
        this.clinicService
            .search({appointmentTypeId: this.selectedAppointmentTypeId, date: this.selectedDate})
            .subscribe(
                value => {
                    console.log(value);
                    this.clinicPriceWrapperList = value;
                },
                error => {
                    console.log(error);
                }
            )
    }

    myFilter = (moment: Moment): boolean => {
        return moment.isAfter(this.currentMoment, 'day') && !this.isWeekend(moment);
    };

    // Returns true if day is weekend
    isWeekend(moment: Moment): boolean {
        if (moment) {
            const day = moment.weekday();
            return (day === 0 || day === 6);
        }
        return undefined;
    }
}
