import {Injectable} from "@angular/core";
import {AuthService} from "../../services/auth/auth.service";
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {LoginAction} from "./index";
import {catchError, exhaustMap, map} from 'rxjs/operators';
import {of} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export class Effects {
  login$ = createEffect(() =>
    this.action$.pipe(
      ofType(LoginAction.loginRequest),
      exhaustMap(action =>
        this.authService.login({username: action.username, password: action.password}).pipe(
          map((token: any) => {
            localStorage.setItem('accessToken', token.accessToken)
            this.router.navigate(['/home']).then();
            return LoginAction.loginSuccess({token: token.accessToken});
          }),
          catchError(e => of(LoginAction.loginError({payload: e})))
        )
      )
    )
  )

  constructor(private action$: Actions, private authService: AuthService, private router: Router,) {
  }
}
