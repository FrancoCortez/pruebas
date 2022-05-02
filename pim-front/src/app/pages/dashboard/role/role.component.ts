import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {RoleResourceDto} from "../../../dto/roles/role-resource.dto";
import {Store} from "@ngrx/store";
import {RootStoreState} from "../../../root-store";
import {RoleAction, RoleSelector} from "../../../root-store/role-store";
import {NewRoleResourceDto} from "../../../dto/roles/new-role-resource.dto";
import {Observable} from "rxjs";
import {UpdateRoleResourceDto} from "../../../dto/roles/update-role-resource.dto";

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  providers: [MessageService, ConfirmationService],
  styleUrls: ['./role.component.scss']
})
export class RoleComponent implements OnInit {

  roleDialog: boolean = false;
  deleteRoleDialog: boolean = false;
  deleteRolesDialog: boolean = false;
  role: RoleResourceDto = new RoleResourceDto();
  selectedRoles: RoleResourceDto[] = [];
  submitted: boolean = false;
  cols: any[] = [];
  statuses: any[] = [];
  roles: RoleResourceDto[] | undefined = [];

  isLoadingData: Observable<boolean> = this.store.select(RoleSelector.isLoadingData);
  isLoadingCreate: Observable<boolean> = this.store.select(RoleSelector.isLoadingCreate);
  isLoadingDelete: Observable<boolean> = this.store.select(RoleSelector.isLoadingDelete);
  isLoadingUpdate: Observable<boolean> = this.store.select(RoleSelector.isLoadingUpdate);

  constructor(private readonly store: Store<RootStoreState.AppState>,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.store.dispatch(RoleAction.roleGetAllRequest());
    this.store.select(RoleSelector.roles).subscribe(s => this.roles = s);
    this.store.select(RoleSelector.isLoaderOutError).subscribe(subscriber => {
      if (subscriber) {
        this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'El Role se ha eliminado', life: 3000});
        this.role = {};
      }
    })
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
      console.log('update')
      const updateRole: UpdateRoleResourceDto = {
        name: this.role.name || '',
        description: this.role.description,
        status: this.role.status
      }
      this.store.dispatch(RoleAction.roleUpdateRequest({id: this.role.id, role: updateRole}))
    }
    this.hideDialog();
  }
}
