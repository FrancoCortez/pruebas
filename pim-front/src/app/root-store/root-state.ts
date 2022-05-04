import {ConfigState} from './config';
import {LoginState} from './login-store';
import {RoleState} from './role-store';
import {PermissionState} from "./permission-store";
import {RolePermissionRelationState} from "./role-permission-relation-store";

export interface AppState {
  config: ConfigState.State;
  login: LoginState.State;
  role: RoleState.State;
  permission: PermissionState.State;
  rolePermissionRelation: RolePermissionRelationState.State;
}
