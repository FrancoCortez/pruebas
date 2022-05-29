import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomeComponent} from './home/home.component';
import {SplitterModule} from "primeng/splitter";
import {DashboardComponent} from './dashboard/dashboard.component';
import {RouterModule} from "@angular/router";
import {ComponentModule} from "../../component/component.module";
import {TableModule} from "primeng/table";
import {MenuModule} from "primeng/menu";
import {ChartModule} from "primeng/chart";
import {BlockUIModule} from "primeng/blockui";
import {RoleComponent} from "./role/role.component";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {FileUploadModule} from "primeng/fileupload";
import {RatingModule} from "primeng/rating";
import {DialogModule} from "primeng/dialog";
import {DropdownModule} from "primeng/dropdown";
import {RadioButtonModule} from "primeng/radiobutton";
import {FormsModule} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {RippleModule} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {TooltipModule} from "primeng/tooltip";
import {PermissionComponent} from './permission/permission.component';
import {PickListModule} from "primeng/picklist";
import {AccordionModule} from "primeng/accordion";


@NgModule({
  declarations: [
    HomeComponent,
    DashboardComponent,
    RoleComponent,
    PermissionComponent,
  ],
  imports: [
    CommonModule,
    SplitterModule,
    RouterModule,
    ComponentModule,
    TableModule,
    MenuModule,
    ChartModule,
    BlockUIModule,
    ToastModule,
    ToolbarModule,
    FileUploadModule,
      RatingModule,
      DialogModule,
      DropdownModule,
      RadioButtonModule,
      FormsModule,
      InputNumberModule,
      RippleModule,
      InputTextModule,
      InputTextareaModule,
      TooltipModule,
      PickListModule,
      AccordionModule
  ],
  exports: [
    HomeComponent,
    DashboardComponent
  ]
})
export class DashboardModule {
}
