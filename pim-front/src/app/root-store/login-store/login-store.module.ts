import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {StoreModule} from "@ngrx/store";
import {reducer} from "./reducer";
import {EffectsModule} from "@ngrx/effects";



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    StoreModule.forFeature('login', reducer),
    EffectsModule.forFeature([])
  ]
})
export class LoginStoreModule { }
