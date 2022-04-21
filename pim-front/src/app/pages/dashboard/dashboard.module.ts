import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import {SplitterModule} from "primeng/splitter";
import { DashboardComponent } from './dashboard/dashboard.component';
import {RouterModule} from "@angular/router";
import {ComponentModule} from "../../component/component.module";
import {TableModule} from "primeng/table";
import {MenuModule} from "primeng/menu";
import {ChartModule} from "primeng/chart";



@NgModule({
  declarations: [
    HomeComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    SplitterModule,
    RouterModule,
    ComponentModule,
    TableModule,
    MenuModule,
    ChartModule
  ],
  exports: [
    HomeComponent,
    DashboardComponent
  ]
})
export class DashboardModule { }
