import {createAction, props} from "@ngrx/store";
import {RoleResourceDto} from "../../dto/roles/role-resource.dto";
import {NewRoleResourceDto} from "../../dto/roles/new-role-resource.dto";
import {UpdateRoleResourceDto} from "../../dto/roles/update-role-resource.dto";

export const roleCreateRequest = createAction('roleCreateRequest', props<{ role: NewRoleResourceDto }>());
export const roleCreateRequestSuccess = createAction('roleCreateRequestSuccess');
export const roleCreateRequestError = createAction('roleCreateRequestError', props<{ payload: any }>());


export const roleUpdateRequest = createAction('roleUpdateRequest', props<{ id?: string, role?: UpdateRoleResourceDto }>());
export const roleUpdateRequestSuccess = createAction('roleUpdateRequestSuccess');
export const roleUpdateRequestError = createAction('roleUpdateRequestError', props<{ payload: any }>());

export const roleDeleteRequest = createAction('roleDeleteRequest', props<{ id?: string }>());
export const roleDeleteRequestSuccess = createAction('roleDeleteRequestSuccess');
export const roleDeleteRequestError = createAction('roleDeleteRequestError', props<{ payload: any }>());

export const roleMassiveDeletedRequest = createAction('roleMassiveDeletedRequest', props<{ ids?: (string | undefined)[] }>());
export const roleMassiveDeletedRequestSuccess = createAction('roleMassiveDeletedRequestSuccess');
export const roleMassiveDeletedRequestError = createAction('roleMassiveDeletedRequestError', props<{ payload: any }>());

export const roleGetAllRequest = createAction('roleGetAllRequest');
export const roleGetAllRequestSuccess = createAction('roleGetAllRequestSuccess', props<{ roles: RoleResourceDto[] }>());
export const roleGetAllRequestError = createAction('roleGetAllRequestError', props<{ payload: any }>());
