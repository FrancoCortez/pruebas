import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {StoreModule} from "@ngrx/store";
import {EffectsModule} from "@ngrx/effects";
import {ConfigModule} from "./config/config.module";
import {LoginStoreModule} from "./login-store";



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ConfigModule,
    LoginStoreModule,
    StoreModule.forRoot({}),
    EffectsModule.forRoot([]),
  ]
})
export class RootStoreModule { }
