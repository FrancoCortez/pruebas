import {RoleResourceDto} from "../../dto/roles/role-resource.dto";

export interface State {
  isLoadingData: boolean;
  isLoadingCreate: boolean;
  isLoadingDelete: boolean;
  isLoadingUpdate: boolean;
  isLoadingMassiveDeleted: boolean;
  isLoader: boolean;
  error?: any
  roles?: RoleResourceDto[]
}


export const InitialState: State = {
  isLoader: false,
  isLoadingData: false,
  isLoadingCreate: false,
  isLoadingDelete: false,
  isLoadingUpdate: false,
  isLoadingMassiveDeleted: false,
  roles: [],
}
