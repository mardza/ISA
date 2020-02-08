import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AppointmentType} from '../../../../models/AppointmentType.model';
import {ActivatedRoute} from '@angular/router';
import {EmptyComponent} from '../../../empty/empty.component';
import {Moment} from 'moment';
import * as moment from 'moment';
import {MAT_DATE_FORMATS} from '@angular/material';
import {ClinicService} from '../../../../services/http/clinic.service';
import {Clinic} from '../../../../models/Clinic.model';
import {DoctorAvailableWrapper} from '../../../../models/custom/DoctorAvailableWrapper.model';

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
    doctorAvailableWrapperList: DoctorAvailableWrapper[];

    clinic: Clinic;
    selectedDate: string;
    selectedAppointmentTypeId: number;
    selectedMoment: Moment;


    constructor(
        private route: ActivatedRoute,
        private clinicService: ClinicService
    ) {
    }


    ngOnInit() {
        this.appointmentTypeList = this.route.snapshot.data.appointmentTypeList;
        this.clinic = this.route.snapshot.data.clinic;
        this.selectedAppointmentTypeId = +this.route.snapshot.queryParams.appointmentTypeId;
        this.selectedDate = this.route.snapshot.queryParams.date;
        this.currentMoment = moment();

        if (!this.selectedAppointmentTypeId || !this.selectedDate) {
            this.clinicService
                .getDoctors(this.clinic.id)
                .subscribe(
                    value => {
                        this.doctorAvailableWrapperList = value;
                    },
                    error => {
                        console.log(error);
                    }
                );
        } else {
            this.selectedMoment = moment(this.selectedDate);
            this.clinicService
                .searchDoctors(
                    this.clinic.id,
                    {
                        appointmentTypeId: this.selectedAppointmentTypeId,
                        date: this.selectedDate,
                        firstName: '',
                        lastName: '',
                        rating: 1
                    })
                .subscribe(
                    value => {
                        this.doctorAvailableWrapperList = value;
                    },
                    error => {
                        console.log(error);
                    }
                );
        }
    }

    onSubmit(form: NgForm) {
        this.selectedAppointmentTypeId = form.value.selectedAppointmentTypeId;
        this.selectedDate = form.value.selectedDate.format('YYYY-MM-DD');

        this.clinicService
            .searchDoctors(
                this.clinic.id,
                {
                    appointmentTypeId: this.selectedAppointmentTypeId,
                    date: this.selectedDate,
                    firstName: form.value.selectedFirstName,
                    lastName: form.value.selectedLastName,
                    rating: form.value.selectedRating
                })
            .subscribe(
                value => {
                    this.doctorAvailableWrapperList = value;
                },
                error => {
                    console.log(error);
                }
            );
    }

    myFilter = (moment: Moment): boolean => {
        return moment.isSameOrAfter(this.currentMoment, 'day') && !this.isWeekend(moment);
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
