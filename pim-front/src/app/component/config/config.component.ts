import {Component, OnInit} from '@angular/core';
import {AppConfig} from "../../api/appconfig";
import {Subscription} from "rxjs";
import {AppComponent} from "../../app.component";
import {DashboardComponent} from "../../pages/dashboard/dashboard/dashboard.component";
import {ConfigService} from "../../services/app.config.service";
import {PrimeNGConfig} from "primeng/api";

@Component({
  selector: 'app-config',
  templateUrl: './config.component.html',
  styleUrls: ['./config.component.scss']
})
export class ConfigComponent implements OnInit {
  scale: number = 14;

  scales: any[] = [12, 13, 14, 15, 16];

  // @ts-ignore
  config: AppConfig;

  // @ts-ignore
  subscription: Subscription;

  constructor(public app: AppComponent, public appMain: DashboardComponent, public configService: ConfigService, public primengConfig: PrimeNGConfig) {
  }

  ngOnInit() {
    this.config = this.configService.config;
    this.subscription = this.configService.configUpdate$.subscribe(config => {
      this.config = config;
      this.scale = 14;

      this.applyScale();
    });
  }

  onConfigButtonClick(event: any) {
    this.appMain.configActive = !this.appMain.configActive;
    this.appMain.configClick = true;
    event.preventDefault();
  }

  incrementScale() {
    this.scale++;
    this.applyScale();
  }

  decrementScale() {
    this.scale--;
    this.applyScale();
  }

  applyScale() {
    document.documentElement.style.fontSize = this.scale + 'px';
  }

  onRippleChange(ripple: any) {
    this.primengConfig.ripple = ripple;
    this.configService.updateConfig({...this.config, ...{ripple}});
  }

  onInputStyleChange() {
    this.configService.updateConfig(this.config);
  }

  changeTheme(theme: string, dark: boolean) {
    let themeElement = document.getElementById('theme-css');
    // @ts-ignore
    themeElement.setAttribute('href', 'assets/theme/' + theme + '/theme.css');
    this.configService.updateConfig({...this.config, ...{theme, dark}});
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
