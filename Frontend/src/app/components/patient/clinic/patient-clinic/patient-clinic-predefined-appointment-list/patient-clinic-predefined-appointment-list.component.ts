import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Clinic} from '../../../../../models/Clinic.model';
import {Appointment} from '../../../../../models/Appointment.model';
import {ClinicService} from '../../../../../services/http/clinic.service';

@Component({
    selector: 'app-patient-clinic-predefined-appointment-list',
    templateUrl: './patient-clinic-predefined-appointment-list.component.html',
    styleUrls: ['./patient-clinic-predefined-appointment-list.component.scss']
})
export class PatientClinicPredefinedAppointmentListComponent implements OnInit {

    clinic: Clinic;
    appointments: Appointment[];
    loading: boolean;


    constructor(
        private route: ActivatedRoute,
        private clinicService: ClinicService
    ) { }


    ngOnInit() {
        this.clinic = this.route.snapshot.data.clinic;
        this.loading = true;
        this.clinicService
            .getPredefinedAppointments(this.clinic.id)
            .subscribe(
                value => {
                    this.appointments = value;
                    this.loading = false;
                },
                error => {
                    this.loading = false;
                }
            );
    }

}
