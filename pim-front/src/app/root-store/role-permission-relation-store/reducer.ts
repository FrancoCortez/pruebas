import {Action, createReducer, on} from "@ngrx/store";
import {InitialState, State} from './state';
import * as action from './actions';

const featureReducer = createReducer(
    InitialState,
    on(action.addPermissionToRole, (state: State) => ({
        ...state,
        isLoadingData: true,
        isLoader: false,
        error: null,
    })),
    on(action.addPermissionToRoleSuccess, (state: State) => ({
        ...state,
        isLoadingData: false,
        isLoader: true,
    })),
    on(action.addPermissionToRoleError, (state: State, {payload}) => ({
        ...state,
        isLoadingData: false,
        isLoader: false,
        error: payload
    })),
)

export function reducer(state: State, action: Action) {
    return featureReducer(state, action);
}
