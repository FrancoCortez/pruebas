import {createAction, props} from "@ngrx/store";

export const loginRequest =createAction('', props<{ payload: { username: string, password: string}}>());
export const loginSuccess = createAction('');
export const loginError = createAction('', props<{ payload: any}>())
