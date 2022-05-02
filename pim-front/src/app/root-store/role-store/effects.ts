import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, exhaustMap, map} from 'rxjs/operators';
import {of} from "rxjs";
import {RoleService} from "../../services/role.service";
import {RoleAction} from "./index";
import {Store} from "@ngrx/store";
import {RootStoreState} from "../index";

@Injectable(

)
export class Effects {
  getRoles$ = createEffect(() =>
    this.action$.pipe(
      ofType(RoleAction.roleGetAllRequest),
      exhaustMap(() =>
        this.roleService.getAllRoles().pipe(
          map((response) => {
            return RoleAction.roleGetAllRequestSuccess({roles: response});
          }),
          catchError(e => of(RoleAction.roleGetAllRequestError({payload: e})))
        )
      )
    )
  );
  createRole$ = createEffect(() =>
    this.action$.pipe(
      ofType(RoleAction.roleCreateRequest),
      exhaustMap(action =>
        this.roleService.createRole(action.role).pipe(
          map(() => {
            this.store.dispatch(RoleAction.roleCreateRequestSuccess());
            return RoleAction.roleGetAllRequest();
          }),
          catchError(e => of(RoleAction.roleCreateRequestError({payload: e})))
        )
      )
    )
  );
  updateRole$ = createEffect(() =>
    this.action$.pipe(
      ofType(RoleAction.roleUpdateRequest),
      exhaustMap(action =>
        this.roleService.updateRole(action?.id, action?.role).pipe(
          map(() => {
            this.store.dispatch(RoleAction.roleUpdateRequestSuccess());
            return RoleAction.roleGetAllRequest();
          }),
          catchError(e => of(RoleAction.roleUpdateRequestError({payload: e})))
        )
      )
    )
  );
  deleteRole$ = createEffect(() =>
    this.action$.pipe(
      ofType(RoleAction.roleDeleteRequest),
      exhaustMap(action =>
        this.roleService.deleteRole(action?.id).pipe(
          map(() => {
            this.store.dispatch(RoleAction.roleDeleteRequestSuccess());
            return RoleAction.roleGetAllRequest();
          }),
          catchError(e => of(RoleAction.roleDeleteRequestError({payload: e})))
        ),
      )
    ),
  );
  massiveDeletedRole$ = createEffect(() =>
    this.action$.pipe(
      ofType(RoleAction.roleMassiveDeletedRequest),
      exhaustMap(action =>
        this.roleService.deleteMassiveRole(action?.ids).pipe(
          map(() => {
            this.store.dispatch(RoleAction.roleMassiveDeletedRequestSuccess());
            return RoleAction.roleGetAllRequest();
          }),
          catchError(e => of(RoleAction.roleMassiveDeletedRequestError({payload: e})))
        ),
      )
    ),
  );

  constructor(private action$: Actions, private roleService: RoleService, private readonly store: Store<RootStoreState.AppState>) {
  }
}
