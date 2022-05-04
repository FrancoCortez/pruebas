import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, exhaustMap, map} from 'rxjs/operators';
import {of} from "rxjs";
import {Store} from "@ngrx/store";
import {RootStoreState} from "../index";
import {RolePermissionRelationService} from "../../services/role-permission-relation.service";
import {RolePermissionRelationAction} from "./index";

@Injectable()
export class Effects {

    addPermissionToRol = createEffect(() =>
        this.action$.pipe(
            ofType(RolePermissionRelationAction.addPermissionToRole),
            exhaustMap((action) =>
                this.rolePermissionRelationService.addPermissionToRol(action.roleId, action.permissionsIds).pipe(
                    map((response) => {
                        return RolePermissionRelationAction.addPermissionToRoleSuccess();
                    }),
                    catchError(e => of(RolePermissionRelationAction.addPermissionToRoleError({payload: e})))
                )
            )
        )
    );

    constructor(private action$: Actions,
                private rolePermissionRelationService: RolePermissionRelationService,
                private readonly store: Store<RootStoreState.AppState>) {
    }
}
