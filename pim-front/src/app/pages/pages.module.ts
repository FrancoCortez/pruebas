import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login/login.component';
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {RippleModule} from "primeng/ripple";
import {PasswordModule} from "primeng/password";
import {CheckboxModule} from "primeng/checkbox";
import {FormsModule} from "@angular/forms";
import {BlockUIModule} from "primeng/blockui";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {PermissionComponent} from './permission/permission.component';


@NgModule({
  declarations: [
    LoginComponent,
    PermissionComponent,
  ],
  imports: [
    CommonModule,
    CardModule,
    ButtonModule,
    InputTextModule,
    RippleModule,
    PasswordModule,
    CheckboxModule,
    FormsModule,
    BlockUIModule,
    ProgressSpinnerModule
  ],
  exports: [
    LoginComponent,
  ]
})
export class PagesModule {
}
