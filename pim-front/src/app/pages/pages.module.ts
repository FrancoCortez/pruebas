import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {RippleModule} from "primeng/ripple";
import {PasswordModule} from "primeng/password";
import {CheckboxModule} from "primeng/checkbox";
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    CardModule,
    ButtonModule,
    InputTextModule,
    RippleModule,
    PasswordModule,
    CheckboxModule,
    FormsModule
  ],
  exports: [
    LoginComponent,
  ]
})
export class PagesModule { }
