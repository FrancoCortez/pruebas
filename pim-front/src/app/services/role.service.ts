import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RoleResourceDto} from "../dto/roles/role-resource.dto";
import {UpdateRoleResourceDto} from "../dto/roles/update-role-resource.dto";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private readonly http: HttpClient) {
  }

  public getAllRoles(): Observable<RoleResourceDto[]> {
    return this.http.get<RoleResourceDto[]>('http://localhost:8081/roles')
  }

  public createRole(role: { name: string, description?: string }): Observable<RoleResourceDto> {
    return this.http.post('http://localhost:8081/roles', role);
  }

  public updateRole(id?: string, role?: UpdateRoleResourceDto): Observable<RoleResourceDto> {
    return this.http.put(`http://localhost:8081/roles/${id}`, role);
  }

  public deleteRole(id?: string) {
    return this.http.delete(`http://localhost:8081/roles/${id}`);
  }

  public deleteMassiveRole(ids?: (string | undefined)[]) {
    return this.http.post('http://localhost:8081/roles/massive/deleted', ids);
  }
}
