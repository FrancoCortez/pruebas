import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TopbarComponent} from './topbar/topbar.component';
import {MenuComponent} from './menu/menu.component';
import {FooterComponent} from './footer/footer.component';
import {ConfigComponent} from './config/config.component';
import {MenuitemComponent} from './menuitem/menuitem.component';
import {RouterModule} from "@angular/router";
import {RippleModule} from "primeng/ripple";
import {ButtonModule} from "primeng/button";
import {RadioButtonModule} from "primeng/radiobutton";
import {FormsModule} from "@angular/forms";
import {InputSwitchModule} from "primeng/inputswitch";


@NgModule({
  declarations: [
    TopbarComponent,
    MenuComponent,
    FooterComponent,
    ConfigComponent,
    MenuitemComponent
  ],
  exports: [
    FooterComponent,
    ConfigComponent,
    MenuComponent,
    TopbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    RippleModule,
    ButtonModule,
    RadioButtonModule,
    FormsModule,
    InputSwitchModule
  ]
})
export class ComponentModule {
}
