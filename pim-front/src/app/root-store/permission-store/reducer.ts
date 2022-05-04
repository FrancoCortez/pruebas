import {Action, createReducer, on} from "@ngrx/store";
import {InitialState, State} from './state';
import * as action from './actions';

const featureReducer = createReducer(
    InitialState,
    on(action.permissionGetAllRequest, (state: State) => ({
      ...state,
      isLoadingData: true,
      error: null,
    })),
    on(action.permissionGetAllRequestSuccess, (state: State, {permissions}) => ({
      ...state,
      isLoadingData: false,
      permissions: permissions,
    })),
    on(action.permissionGetAllRequestError, (state: State, {payload}) => ({
      ...state,
      isLoadingData: false,
      error: payload
    })),

    on(action.permissionCreateRequest, (state: State) => ({
      ...state,
      isLoadingCreate: true,
    })),
    on(action.permissionCreateRequestSuccess, (state: State) => ({
      ...state,
      isLoadingCreate: false,
    })),
    on(action.permissionCreateRequestError, (state: State, {payload}) => ({
      ...state,
      isLoadingCreate: false,
      error: payload,
    })),

    on(action.permissionUpdateRequest, (state: State) => ({
      ...state,
      isLoadingUpdate: true,
    })),
    on(action.permissionUpdateRequestSuccess, (state: State) => ({
      ...state,
      isLoadingUpdate: false,
    })),
    on(action.permissionUpdateRequestError, (state: State, {payload}) => ({
      ...state,
      isLoadingUpdate: false,
      error: payload,
    })),

    on(action.permissionDeleteRequest, (state: State) => ({
      ...state,
      isLoadingDelete: true,
      isLoader: false,
    })),

    on(action.permissionDeleteRequestSuccess, (state: State) => ({
      ...state,
      isLoadingDelete: false,
      isLoader: true,
    })),

    on(action.permissionDeleteRequestError, (state: State, {payload}) => ({
      ...state,
      isLoadingDelete: true,
      isLoader: false,
      error: payload
    })),

    on(action.permissionMassiveDeletedRequest, (state: State) => ({
      ...state,
      isLoadingMassiveDeleted: true,
      isLoader: false,
    })),
    on(action.permissionMassiveDeletedRequestSuccess, (state: State) => ({
      ...state,
      isLoadingMassiveDeleted: false,
      isLoader: true,
    })),
    on(action.permissionMassiveDeletedRequestError, (state: State, {payload}) => ({
      ...state,
      isLoadingMassiveDeleted: true,
      isLoader: false,
      error: payload
    })),


    on(action.permissionGetByRoleId, (state: State) => ({
      ...state,
      isLoadingData: true,
      error: null,
    })),
    on(action.permissionGetByRoleIdSuccess, (state: State, {permissions}) => ({
      ...state,
      isLoadingData: false,
      permissionInRoles: permissions,
    })),
    on(action.permissionGetByRoleIdError, (state: State, {payload}) => ({
      ...state,
      isLoadingData: false,
      error: payload
    })),


    on(action.permissionGetNotByRoleId, (state: State) => ({
      ...state,
      isLoadingData: true,
      error: null,
    })),
    on(action.permissionGetByNotRoleIdSuccess, (state: State, {permissions}) => ({
      ...state,
      isLoadingData: false,
      permissionNotRole: permissions,
    })),
    on(action.permissionGetByNotRoleIdError, (state: State, {payload}) => ({
      ...state,
      isLoadingData: false,
      error: payload
    })),
)

export function reducer(state: State | undefined, action: Action) {
  return featureReducer(state, action);
}
