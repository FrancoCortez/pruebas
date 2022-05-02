import {createSelector} from '@ngrx/store';
import {AppState} from "../root-state";
import {State} from "./state";

const selectorLoginState = (state: AppState) => state.login;

export const isLoading = createSelector(selectorLoginState, (state: State) => state.isLoading)
export const isLoader = createSelector(selectorLoginState, (state: State) => state.isLoader)
export const error = createSelector(selectorLoginState, (state: State) => state.error)
