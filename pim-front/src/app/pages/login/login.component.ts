import {Component, OnDestroy, OnInit} from '@angular/core';
import {AppConfig} from "../../api/appconfig";
import {Subscription} from "rxjs";
import {ConfigService} from "../../services/app.config.service";
import {Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {LoginAction, RootStoreState} from "../../root-store";


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
  subscription: Subscription = new Subscription();

  constructor(public configService: ConfigService, private router: Router, private readonly store: Store<RootStoreState.AppState>) {
  }

  ngOnInit(): void {
    this.config = this.configService.config;
    this.subscription = this.configService.configUpdate$.subscribe(config => {
      this.config = config;
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  toLogin() {
    this.store.dispatch(LoginAction.loginRequest({payload: {username: this.mail, password: this.password}}));
    this.router.navigate(['/home']).then();
  }
}
