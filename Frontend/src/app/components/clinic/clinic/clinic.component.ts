import {Component, OnInit} from '@angular/core';
import {Clinic} from '../../../models/Clinic.model';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {ClinicService} from '../../../services/http/clinic.service';

@Component({
    selector: 'app-clinic',
    templateUrl: './clinic.component.html',
    styleUrls: ['./clinic.component.scss']
})
export class ClinicComponent implements OnInit {

    clinic: Clinic;
    isCreateMode: boolean;
    isEditActive: boolean;
    loading: boolean;


    constructor(
        private route: ActivatedRoute,
        private clinicService: ClinicService,
        private router: Router
    ) {
    }


    ngOnInit() {
        this.clinic = this.route.snapshot.data.clinic;
        if (!this.clinic) {
            this.isCreateMode = true;
        }
    }

    onEditClick() {
        this.isEditActive = true;
    }

    onConfirmClick(form: NgForm) {
        if (form.valid) {
            this.loading = true;
            if (this.isCreateMode) {
                this.clinicService
                    .createClinic(Clinic.toClinic(form.value))
                    .subscribe(
                        value => {
                            this.router.navigate(['/clinics']);
                            this.loading = false;
                        },
                        error => {
                            this.loading = false;
                        }
                    );
            } else {
                this.clinicService
                    .updateClinic(this.clinic.id, Clinic.toClinic(form.value))
                    .subscribe(
                        value => {
                            this.isEditActive = false;
                            this.loading = false;
                        },
                        error => {
                            this.loading = false;
                        }
                    );
            }
        }
    }

    onCancelClick(form: NgForm) {
        this.isEditActive = false;
        form.reset(this.clinic);
    }
}
