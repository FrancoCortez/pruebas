import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {RoleResourceDto} from "../../../dto/roles/role-resource.dto";
import {Store} from "@ngrx/store";
import {RootStoreState} from "../../../root-store";
import {RoleAction, RoleSelector} from "../../../root-store/role-store";
import {NewRoleResourceDto} from "../../../dto/roles/new-role-resource.dto";
import {Observable} from "rxjs";
import {UpdateRoleResourceDto} from "../../../dto/roles/update-role-resource.dto";
import {PermissionAction, PermissionSelector} from "../../../root-store/permission-store";
import {PermissionResourceDto} from "../../../dto/permission/permission-resource.dto";
import {CDK_DRAG_CONFIG} from "@angular/cdk/drag-drop";
import {
  RolePermissionRelationAction,
  RolePermissionRelationSelector
} from "../../../root-store/role-permission-relation-store";

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  providers: [MessageService, ConfirmationService, {
    provide: CDK_DRAG_CONFIG, useValue: {
      dragStartThreshold: 0,
      pointerDirectionChangeThreshold: 5,
      zIndex: 200000000
    }
  }],
  styleUrls: ['./role.component.scss']
})
export class RoleComponent implements OnInit {
  city: string = "";
  city1: string = "";
  city2: string = "";

  selectedCategory: any = null;
  roleDialog: boolean = false;
  deleteRoleDialog: boolean = false;
  deleteRolesDialog: boolean = false;
  permissionDialog: boolean = false;
  role: RoleResourceDto = {};
  selectedRoles: RoleResourceDto[] = [];
  submitted: boolean = false;
  cols: any[] = [];
  statuses: any[] = [];
  roles: RoleResourceDto[] = [];

  isLoadingData: Observable<boolean> = this.store.select(RoleSelector.isLoadingData);
  isLoadingCreate: Observable<boolean> = this.store.select(RoleSelector.isLoadingCreate);
  isLoadingDelete: Observable<boolean> = this.store.select(RoleSelector.isLoadingDelete);
  isLoadingUpdate: Observable<boolean> = this.store.select(RoleSelector.isLoadingUpdate);

  permissionInRoles: PermissionResourceDto[] = [];
  permissionNotRoles: PermissionResourceDto[] = [];

  constructor(private readonly store: Store<RootStoreState.AppState>,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.store.dispatch(RoleAction.roleGetAllRequest());
    this.store.select(RoleSelector.roles).subscribe(s => this.roles = s);
    this.store.select(PermissionSelector.permissionNotRoles).subscribe(s => this.permissionNotRoles = [...s]);
    this.store.select(PermissionSelector.permissionInRoles).subscribe(s => this.permissionInRoles = [...s]);
    this.store.select(RoleSelector.isLoaderOutError).subscribe(subscriber => {
      if (subscriber) {
        this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'El Role se ha eliminado', life: 3000});
        this.role = {};
      }
    });
    this.store.select(RolePermissionRelationSelector.isLoaderOutError).subscribe(subscriber => {
      if (subscriber) {
        this.messageService.add({
          severity: 'success',
          summary: 'Éxito',
          detail: 'Los permisos se agregaron con exito',
          life: 3000
        });
        this.role = {};
        this.permissionDialog = false;
      }
    });
    this.cols = [
      {field: 'id', header: 'Id'},
      {field: 'code', header: 'Code'},
      {field: 'description', header: 'Descripción'},
      {field: 'status', header: 'Estado'},
      {field: 'createdAt', header: 'Creacion'},
      {field: 'updatedAt', header: 'Actualizacion'}
    ];

    this.statuses = [
      {name: 'ENABLED', code: 'ENABLED'},
      {name: 'DISABLED', code: 'DISABLED'}
    ];
  }

  openNew() {
    this.role = {};
    this.submitted = false;
    this.roleDialog = true;
  }

  deleteSelectedRoles() {
    this.deleteRolesDialog = true;
  }

  editRole(role: RoleResourceDto) {
    this.role = {...role};
    this.roleDialog = true;
  }

  deleteRole(role: RoleResourceDto) {
    this.deleteRoleDialog = true;
    this.role = {...role};
  }

  confirmDeleteSelected() {
    this.deleteRolesDialog = false;
    this.store.dispatch(RoleAction.roleMassiveDeletedRequest({ids: this.selectedRoles?.map(m => m.id)}))
    this.selectedRoles = [];
  }

  confirmDelete() {
    this.deleteRoleDialog = false;
    this.store.dispatch(RoleAction.roleDeleteRequest({id: this.role?.id}))
  }

  hideDialog() {
    this.roleDialog = false;
    this.submitted = false;
    this.role = {};
  }

  updateRole() {
  }

  saveRole(isCreate: boolean) {
    this.submitted = true;
    if (isCreate) {
      const newRole: NewRoleResourceDto = {
        name: this.role.name || '',
        description: this.role.description
      }
      this.store.dispatch(RoleAction.roleCreateRequest({role: newRole}));
    } else {
      const updateRole: UpdateRoleResourceDto = {
        name: this.role.name || '',
        description: this.role.description,
        status: this.role.status
      }
      this.store.dispatch(RoleAction.roleUpdateRequest({id: this.role.id, role: updateRole}))
    }
    this.hideDialog();
  }

  openPermissionDialog(role: RoleResourceDto) {
    this.store.dispatch(PermissionAction.permissionGetByRoleId({roleId: role.id}))
    this.store.dispatch(PermissionAction.permissionGetNotByRoleId({roleId: role.id}))
    this.role = role;
    this.permissionDialog = true;
  }

  attachPermission() {
    this.store.dispatch(RolePermissionRelationAction.addPermissionToRole({
      roleId: this.role.id,
      permissionsIds: this.permissionInRoles.map(m => m.id)
    }))
    this.role = {}
  }
}
