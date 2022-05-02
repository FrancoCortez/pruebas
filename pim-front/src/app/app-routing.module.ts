import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {HomeComponent} from "./pages/dashboard/home/home.component";
import {DashboardComponent} from "./pages/dashboard/dashboard/dashboard.component";
import {RoleComponent} from "./pages/dashboard/role/role.component";
import {PermissionComponent} from "./pages/permission/permission.component";

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {component: LoginComponent, path: 'login'},
  {
    component: DashboardComponent, path: 'home',
    children: [
      {path: '', component: HomeComponent},
      {path: 'role', component: RoleComponent},
      {path: 'permission', component: PermissionComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
