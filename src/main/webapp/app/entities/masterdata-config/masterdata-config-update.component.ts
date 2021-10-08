import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMasterdataConfig, MasterdataConfig } from 'app/shared/model/masterdata-config.model';
import { MasterdataConfigService } from './masterdata-config.service';

@Component({
  selector: 'jhi-masterdata-config-update',
  templateUrl: './masterdata-config-update.component.html'
})
export class MasterdataConfigUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, []],
    path: [],
    clazz: [],
    collectionName: [],
    environment: [],
    contentType: [],
    port: [],
    url: []
  });

  constructor(
    protected masterdataConfigService: MasterdataConfigService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ masterdataConfig }) => {
      this.updateForm(masterdataConfig);
    });
  }

  updateForm(masterdataConfig: IMasterdataConfig) {
    this.editForm.patchValue({
      id: masterdataConfig.id,
      name: masterdataConfig.name,
      path: masterdataConfig.path,
      clazz: masterdataConfig.clazz,
      collectionName: masterdataConfig.collectionName,
      environment: masterdataConfig.environment,
      contentType: masterdataConfig.contentType,
      port: masterdataConfig.port,
      url: masterdataConfig.url
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const masterdataConfig = this.createFromForm();
    if (masterdataConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.masterdataConfigService.update(masterdataConfig));
    } else {
      this.subscribeToSaveResponse(this.masterdataConfigService.create(masterdataConfig));
    }
  }

  private createFromForm(): IMasterdataConfig {
    return {
      ...new MasterdataConfig(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      path: this.editForm.get(['path']).value,
      clazz: this.editForm.get(['clazz']).value,
      collectionName: this.editForm.get(['collectionName']).value,
      environment: this.editForm.get(['environment']).value,
      contentType: this.editForm.get(['contentType']).value,
      port: this.editForm.get(['port']).value,
      url: this.editForm.get(['url']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMasterdataConfig>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
