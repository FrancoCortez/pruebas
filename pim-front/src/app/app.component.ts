import {Component, OnInit} from '@angular/core';
import {PrimeNGConfig} from "primeng/api";
import {Store} from "@ngrx/store";
import {RootStoreState} from "./root-store";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  menuMode = 'static';
  constructor(private primengConfig: PrimeNGConfig, private readonly store: Store<RootStoreState.AppState>) {}

  ngOnInit() {
    this.primengConfig.ripple = true;
    document.documentElement.style.fontSize = '14px';
  }
}
