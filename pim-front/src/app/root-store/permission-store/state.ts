import {PermissionResourceDto} from "../../dto/permission/permission-resource.dto";

export interface State {
  isLoadingData: boolean;
  isLoadingCreate: boolean;
  isLoadingDelete: boolean;
  isLoadingUpdate: boolean;
  isLoadingMassiveDeleted: boolean;
  isLoadingAddPermissionToRol: boolean;
  isLoader: boolean;
  error?: any
  permissions: PermissionResourceDto[],
  permissionInRoles: PermissionResourceDto[],
  permissionNotRole: PermissionResourceDto[],
}


export const InitialState: State = {
  isLoader: false,
  isLoadingData: false,
  isLoadingCreate: false,
  isLoadingDelete: false,
  isLoadingUpdate: false,
  isLoadingMassiveDeleted: false,
  isLoadingAddPermissionToRol: false,
  permissions: [],
  permissionInRoles: [],
  permissionNotRole: [],
}
