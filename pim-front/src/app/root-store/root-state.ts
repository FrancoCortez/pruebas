import {ConfigState} from './config';
import {LoginState} from './login-store';
import {RoleState} from './role-store';

export interface AppState {
  config: ConfigState.State;
  login: LoginState.State;
  role: RoleState.State;
}
