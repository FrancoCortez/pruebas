import {createAction, props} from "@ngrx/store";

export const loginRequest = createAction('loginRequest', props<{ username: string, password: string }>());
export const loginSuccess = createAction('loginSuccess', props<{ token: string }>());
export const loginError = createAction('loginError', props<{ payload: any }>())
