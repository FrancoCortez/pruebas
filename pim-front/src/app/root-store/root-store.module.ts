import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StoreModule} from "@ngrx/store";
import {EffectsModule} from "@ngrx/effects";
import {ConfigModule} from "./config/config.module";
import {LoginStoreModule} from "./login-store";
import {RoleStoreModule} from "./role-store";
import {PermissionStoreModule} from "./permission-store";
import {RolePermissionRelationStoreModule} from "./role-permission-relation-store";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ConfigModule,
    LoginStoreModule,
    RoleStoreModule,
    PermissionStoreModule,
    RolePermissionRelationStoreModule,
    StoreModule.forRoot({}),
    EffectsModule.forRoot([]),
  ]
})
export class RootStoreModule {
}
