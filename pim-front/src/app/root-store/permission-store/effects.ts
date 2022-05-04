import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, exhaustMap, map} from 'rxjs/operators';
import {of} from "rxjs";
import {Store} from "@ngrx/store";
import {RootStoreState} from "../index";
import {PermissionService} from "../../services/permission.service";
import {PermissionAction} from "./index";

@Injectable()
export class Effects {

    getPermission$ = createEffect(() =>
        this.action$.pipe(
            ofType(PermissionAction.permissionGetAllRequest),
            exhaustMap(() =>
                this.permissionService.getAllPermissions().pipe(
                    map((response) => {
                        return PermissionAction.permissionGetAllRequestSuccess({permissions: response});
                    }),
                    catchError(e => of(PermissionAction.permissionGetAllRequestError({payload: e})))
                )
            )
        )
    );
    createRole$ = createEffect(() =>
        this.action$.pipe(
            ofType(PermissionAction.permissionCreateRequest),
            exhaustMap(action =>
                this.permissionService.createPermission(action.permission).pipe(
                    map(() => {
                        this.store.dispatch(PermissionAction.permissionCreateRequestSuccess());
                        return PermissionAction.permissionGetAllRequest();
                    }),
                    catchError(e => of(PermissionAction.permissionCreateRequestError({payload: e})))
                )
            )
        )
    );
    updateRole$ = createEffect(() =>
        this.action$.pipe(
            ofType(PermissionAction.permissionUpdateRequest),
            exhaustMap(action =>
                this.permissionService.updatePermission(action?.id, action?.permission).pipe(
                    map(() => {
                        this.store.dispatch(PermissionAction.permissionUpdateRequestSuccess());
                        return PermissionAction.permissionGetAllRequest();
                    }),
                    catchError(e => of(PermissionAction.permissionUpdateRequestError({payload: e})))
                )
            )
        )
    );
    deleteRole$ = createEffect(() =>
        this.action$.pipe(
            ofType(PermissionAction.permissionDeleteRequest),
            exhaustMap(action =>
                this.permissionService.deletePermission(action?.id).pipe(
                    map(() => {
                        this.store.dispatch(PermissionAction.permissionDeleteRequestSuccess());
                        return PermissionAction.permissionGetAllRequest();
                    }),
                    catchError(e => of(PermissionAction.permissionDeleteRequestError({payload: e})))
                ),
            )
        ),
    );
    massiveDeletedRole$ = createEffect(() =>
        this.action$.pipe(
            ofType(PermissionAction.permissionMassiveDeletedRequest),
            exhaustMap(action =>
                this.permissionService.deleteMassivePermission(action?.ids).pipe(
                    map(() => {
                        this.store.dispatch(PermissionAction.permissionMassiveDeletedRequestSuccess());
                        return PermissionAction.permissionGetAllRequest();
                    }),
                    catchError(e => of(PermissionAction.permissionMassiveDeletedRequestError({payload: e})))
                ),
            )
        ),
    );

    getPermissionByRoleId = createEffect(() =>
        this.action$.pipe(
            ofType(PermissionAction.permissionGetByRoleId),
            exhaustMap((action) =>
                this.permissionService.findByRoleId(action?.roleId).pipe(
                    map((response) => {
                        return PermissionAction.permissionGetByRoleIdSuccess({permissions: response});
                    }),
                    catchError(e => of(PermissionAction.permissionGetByRoleIdError({payload: e})))
                )
            )
        )
    );

    getPermissionNotRoleId = createEffect(() =>
        this.action$.pipe(
            ofType(PermissionAction.permissionGetNotByRoleId),
            exhaustMap((action) =>
                this.permissionService.findPermissionNotByRoleId(action?.roleId).pipe(
                    map((response) => {
                        return PermissionAction.permissionGetByNotRoleIdSuccess({permissions: response});
                    }),
                    catchError(e => of(PermissionAction.permissionGetByNotRoleIdError({payload: e})))
                )
            )
        )
    );

    constructor(private action$: Actions,
                private permissionService: PermissionService,
                private readonly store: Store<RootStoreState.AppState>) {
    }
}
