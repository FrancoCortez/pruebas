import {createAction, props} from "@ngrx/store";


export const addPermissionToRole = createAction('addPermissionToRole', props<{ roleId?: string, permissionsIds?: (string | undefined)[] }>());
export const addPermissionToRoleSuccess = createAction('addPermissionToRoleSuccess');
export const addPermissionToRoleError = createAction('addPermissionToRoleError', props<{ payload: any }>());

