import {Action, createReducer, on} from "@ngrx/store";
import {InitialState, State} from './state';
import * as action from './actions';

const featureReducer = createReducer(
  InitialState,
  on(action.roleGetAllRequest, (state) => ({
    ...state,
    isLoadingData: true,
    error: null,
  })),
  on(action.roleGetAllRequestSuccess, (state, {roles}) => ({
    ...state,
    isLoadingData: false,
    roles: roles,
  })),
  on(action.roleGetAllRequestError, (state, {payload}) => ({
    ...state,
    isLoadingData: false,
    error: payload
  })),

  on(action.roleCreateRequest, (state) => ({
    ...state,
    isLoadingCreate: true,
  })),
  on(action.roleCreateRequestSuccess, (state) => ({
    ...state,
    isLoadingCreate: false,
  })),
  on(action.roleCreateRequestError, (state, {payload}) => ({
    ...state,
    isLoadingCreate: false,
    error: payload,
  })),

  on(action.roleUpdateRequest, (state) => ({
    ...state,
    isLoadingUpdate: true,
  })),
  on(action.roleUpdateRequestSuccess, (state) => ({
    ...state,
    isLoadingUpdate: false,
  })),
  on(action.roleUpdateRequestError, (state, {payload}) => ({
    ...state,
    isLoadingUpdate: false,
    error: payload,
  })),

  on(action.roleDeleteRequest, (state) => ({
    ...state,
    isLoadingDelete: true,
    isLoader: false,
  })),

  on(action.roleDeleteRequestSuccess, (state) => ({
    ...state,
    isLoadingDelete: false,
    isLoader: true,
  })),

  on(action.roleDeleteRequestError, (state, {payload}) => ({
    ...state,
    isLoadingDelete: true,
    isLoader: false,
    error: payload
  })),


  on(action.roleMassiveDeletedRequest, (state) => ({
    ...state,
    isLoadingMassiveDeleted: true,
    isLoader: false,
  })),
  on(action.roleMassiveDeletedRequestSuccess, (state) => ({
    ...state,
    isLoadingMassiveDeleted: false,
    isLoader: true,
  })),
  on(action.roleMassiveDeletedRequestError, (state, {payload}) => ({
    ...state,
    isLoadingMassiveDeleted: true,
    isLoader: false,
    error: payload
  })),
)

export function reducer(state: State | undefined, action: Action) {
  return featureReducer(state, action);
}
