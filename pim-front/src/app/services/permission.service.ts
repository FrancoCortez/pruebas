import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PermissionResourceDto} from "../dto/permission/permission-resource.dto";
import {UpdatePermissionResourceDto} from "../dto/permission/update-permission-resource.dto";

@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  constructor(private readonly http: HttpClient) {
  }

  public getAllPermissions(): Observable<PermissionResourceDto[]> {
    return this.http.get<PermissionResourceDto[]>('http://localhost:8081/permission')
  }

  public createPermission(role: { name: string, description?: string }): Observable<PermissionResourceDto> {
    return this.http.post('http://localhost:8081/permission', role);
  }

  public updatePermission(id?: string, role?: UpdatePermissionResourceDto): Observable<PermissionResourceDto> {
    return this.http.put(`http://localhost:8081/permission/${id}`, role);
  }

  public deletePermission(id?: string) {
    return this.http.delete(`http://localhost:8081/permission/${id}`);
  }

  public deleteMassivePermission(ids?: (string | undefined)[]) {
    return this.http.post('http://localhost:8081/permission/massive/deleted', ids);
  }

  public findByRoleId(id?: string): Observable<PermissionResourceDto[]> {
    return this.http.get<PermissionResourceDto[]>(`http://localhost:8081/permission/find-by-role/${id}`);
  }

  public findPermissionNotByRoleId(id?: string): Observable<PermissionResourceDto[]> {
    return this.http.get<PermissionResourceDto[]>(`http://localhost:8081/permission/find-by-not-role/${id}`);
  }
}
