import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth/AuthService.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  role: string;

  constructor(
      private authService: AuthService
  ) { }


  ngOnInit() {
    this.role = localStorage.getItem('role');
  }

  onClickLogout() {
    this.authService.logout();
  }
}
