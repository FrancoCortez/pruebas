import { Component, OnInit } from '@angular/core';
import {DashboardComponent} from "../../pages/dashboard/dashboard/dashboard.component";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  constructor(public appMain: DashboardComponent) {}

}
