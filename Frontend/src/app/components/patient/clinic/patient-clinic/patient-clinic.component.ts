import { Component, OnInit } from '@angular/core';
import {Clinic} from '../../../../models/Clinic.model';
import {ClinicService} from '../../../../services/http/clinic.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-patient-clinic',
  templateUrl: './patient-clinic.component.html',
  styleUrls: ['./patient-clinic.component.scss']
})
export class PatientClinicComponent implements OnInit {

  clinic: Clinic;
  loading: boolean;


  constructor(
      private clinicService: ClinicService,
      private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.clinic = this.route.snapshot.data.clinic;
  }

}
