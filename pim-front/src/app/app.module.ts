import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {CardModule} from "primeng/card";
import {PagesModule} from "./pages/pages.module";
import {DashboardModule} from "./pages/dashboard/dashboard.module";
import {ConfigService} from "./services/app.config.service";
import {MenuService} from "./services/app.menu.service";
import {ProductService} from "./services/productservice";
import {HttpClientModule} from "@angular/common/http";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {RootStoreModule} from "./root-store";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ButtonModule,
    RippleModule,
    InputTextModule,
    CardModule,
    PagesModule,
    DashboardModule,
    ReactiveFormsModule,
    HttpClientModule,
    RootStoreModule,
    StoreDevtoolsModule.instrument({
      name: 'NgRx Book Store DevTools',
      logOnly: true,
      features: {
        pause: true, // start/pause recording of dispatched actions
        lock: true, // lock/unlock dispatching actions and side effects
        persist: true, // persist states on page reloading
        export: true, // export history of actions in a file
        import: true, // import history of actions from a file
        jump: true, // jump back and forth (time travelling)
        skip: true, // skip (cancel) actions
        reorder: true, // drag and drop actions in the history list
        dispatch: true, // dispatch custom actions or action creators
        test: true // generate tests for the selected actions
      },
    })
  ],
  providers: [ConfigService, MenuService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
