import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PermissionResourceDto} from "../dto/permission/permission-resource.dto";

@Injectable({
  providedIn: 'root'
})
export class RolePermissionRelationService {

  constructor(private readonly http: HttpClient) {
  }

  public addPermissionToRol(idRole?: string, idPermissions?: (string | undefined)[]): Observable<PermissionResourceDto> {
    return this.http.post('http://localhost:8081/role-permission-relation/add/permission-to-roles', {
      idRole,
      idPermissions
    });
  }
}
