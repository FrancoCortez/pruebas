import {createSelector} from '@ngrx/store';
import {AppState} from "../root-state";
import {State} from "./state";

const selectorState = (state: AppState) => state.permission;


export const isLoadingData = createSelector(selectorState, (state: State) => state.isLoadingData)
export const isLoader = createSelector(selectorState, (state: State) => state.isLoader)
export const error = createSelector(selectorState, (state: State) => state.error)
export const permission = createSelector(selectorState, (state: State) => state.permissions)
export const permissionInRoles = createSelector(selectorState, (state: State) => state.permissionInRoles);
export const permissionNotRoles = createSelector(selectorState, (state: State) => state.permissionNotRole);

export const isLoadingCreate = createSelector(selectorState, (state: State) => state.isLoadingCreate);
export const isLoadingUpdate = createSelector(selectorState, (state: State) => state.isLoadingUpdate);
export const isLoadingDelete = createSelector(selectorState, (state: State) => state.isLoadingDelete);
export const isLoadingMassiveDeleted = createSelector(selectorState, (state: State) => state.isLoadingMassiveDeleted)
export const isLoaderOutError = createSelector(selectorState, (state: State) => state.isLoader && !state.error);
export const isLoadingAddPermissionToRol = createSelector(selectorState, (state: State) => state.isLoadingAddPermissionToRol)
