import {createAction, props} from "@ngrx/store";
import {PermissionResourceDto} from "../../dto/permission/permission-resource.dto";
import {NewPermissionResourceDto} from "../../dto/permission/new-permission-resource.dto";
import {UpdatePermissionResourceDto} from "../../dto/permission/update-permission-resource.dto";


export const permissionCreateRequest = createAction('permissionCreateRequest', props<{ permission: NewPermissionResourceDto }>());
export const permissionCreateRequestSuccess = createAction('permissionCreateRequestSuccess');
export const permissionCreateRequestError = createAction('permissionCreateRequestError', props<{ payload: any }>());


export const permissionUpdateRequest = createAction('permissionUpdateRequest', props<{ id?: string, permission?: UpdatePermissionResourceDto }>());
export const permissionUpdateRequestSuccess = createAction('permissionUpdateRequestSuccess');
export const permissionUpdateRequestError = createAction('permissionUpdateRequestError', props<{ payload: any }>());

export const permissionDeleteRequest = createAction('permissionDeleteRequest', props<{ id?: string }>());
export const permissionDeleteRequestSuccess = createAction('permissionDeleteRequestSuccess');
export const permissionDeleteRequestError = createAction('permissionDeleteRequestError', props<{ payload: any }>());

export const permissionMassiveDeletedRequest = createAction('permissionMassiveDeletedRequest', props<{ ids?: (string | undefined)[] }>());
export const permissionMassiveDeletedRequestSuccess = createAction('permissionMassiveDeletedRequestSuccess');
export const permissionMassiveDeletedRequestError = createAction('permissionMassiveDeletedRequestError', props<{ payload: any }>());

export const permissionGetAllRequest = createAction('permissionGetAllRequest');
export const permissionGetAllRequestSuccess = createAction('permissionGetAllRequestSuccess', props<{ permissions: PermissionResourceDto[] }>());
export const permissionGetAllRequestError = createAction('permissionGetAllRequestError', props<{ payload: any }>());


export const permissionGetByRoleId = createAction('permissionGetByRoleId', props<{ roleId?: string }>());
export const permissionGetByRoleIdSuccess = createAction('permissionGetByRoleIdSuccess', props<{ permissions: PermissionResourceDto[] }>());
export const permissionGetByRoleIdError = createAction('permissionGetByRoleIdError', props<{ payload: any }>());

export const permissionGetNotByRoleId = createAction('permissionGetNotByRoleId', props<{ roleId?: string }>());
export const permissionGetByNotRoleIdSuccess = createAction('permissionGetByNotRoleIdSuccess', props<{ permissions: PermissionResourceDto[] }>());
export const permissionGetByNotRoleIdError = createAction('permissionGetByNotRoleIdError', props<{ payload: any }>());
