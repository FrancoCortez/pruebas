import {createSelector} from '@ngrx/store';
import {AppState} from "../root-state";
import {State} from "./state";

const selectorState = (state: AppState) => state.rolePermissionRelation;

export const isLoaderOutError = createSelector(selectorState, (state: State) => state.isLoader && !state.error);
