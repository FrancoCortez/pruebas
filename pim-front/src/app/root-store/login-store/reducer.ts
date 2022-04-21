import {Action, createReducer, on} from "@ngrx/store";
import {InitialState, State} from './state';
import * as action from './actions';

const featureReducer = createReducer(
  InitialState,
  on(action.loginRequest, (state, {payload}) => ({
    ...state,
    isLoading: true,
    isLoader: false,
    login: payload,
    error: null,
  })),
  on(action.loginSuccess, (state) => ({
    ...state,
    isLoading: false,
    isLoader: true
  })),
  on(action.loginError, (state, {payload}) => ({
    ...state,
    isLoading: false,
    isLoader: false,
    error: payload,
    login: null
  })),
)

export function reducer(state: State | undefined, action: Action) {
  return featureReducer(state, action);
}
