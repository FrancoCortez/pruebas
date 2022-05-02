import {Component, OnDestroy, OnInit} from '@angular/core';
import {AppConfig} from "../../api/appconfig";
import {Observable, Subscription} from "rxjs";
import {ConfigService} from "../../services/app.config.service";
import {Store} from "@ngrx/store";
import {LoginAction, LoginSelector, RootStoreState} from "../../root-store";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  valCheck: string[] = ['remember'];
  password: string = '';
  mail: string = '';
  config: AppConfig = {};
  configSubscription: Subscription = new Subscription();
  isLoading: Observable<boolean> = this.store.select(LoginSelector.isLoading);

  constructor(public configService: ConfigService, private readonly store: Store<RootStoreState.AppState>) {
  }

  ngOnInit(): void {
    this.config = this.configService.config;
    this.configSubscription = this.configService.configUpdate$.subscribe(config => this.config = config);
  }

  ngOnDestroy(): void {
    if (this.configSubscription) {
      this.configSubscription.unsubscribe();
    }
  }

  toLogin() {
    this.store.dispatch(LoginAction.loginRequest({username: this.mail, password: this.password}));
  }
}
