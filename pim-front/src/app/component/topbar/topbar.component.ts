import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {DashboardComponent} from "../../pages/dashboard/dashboard/dashboard.component";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit {
  // @ts-ignore
  items: MenuItem[];

  constructor(public appMain: DashboardComponent) {
  }

  ngOnInit(): void {
  }

}
