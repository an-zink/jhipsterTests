import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IContentTypeInfo, ContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { ContentTypeInfoService } from './content-type-info.service';

@Component({
  selector: 'jhi-content-type-info-update',
  templateUrl: './content-type-info-update.component.html'
})
export class ContentTypeInfoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    contentTypeName: [null, [Validators.minLength(2)]],
    enviroment: [null, [Validators.minLength(3), Validators.maxLength(3)]],
    lastModifiedByStrapiUser: [null, [Validators.minLength(2)]],
    lastModifiedDate: [null, [Validators.minLength(10), Validators.maxLength(10)]],
    numberOfEntries: [],
    numberOfParameters: []
  });

  constructor(
    protected contentTypeInfoService: ContentTypeInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ contentTypeInfo }) => {
      this.updateForm(contentTypeInfo);
    });
  }

  updateForm(contentTypeInfo: IContentTypeInfo) {
    this.editForm.patchValue({
      id: contentTypeInfo.id,
      contentTypeName: contentTypeInfo.contentTypeName,
      enviroment: contentTypeInfo.enviroment,
      lastModifiedDate: contentTypeInfo.lastModifiedDate,
      numberOfEntries: contentTypeInfo.numberOfEntries,
      numberOfParameters: contentTypeInfo.numberOfParameters
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const contentTypeInfo = this.createFromForm();
    if (contentTypeInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.contentTypeInfoService.update(contentTypeInfo));
    } else {
      this.subscribeToSaveResponse(this.contentTypeInfoService.create(contentTypeInfo));
    }
  }

  private createFromForm(): IContentTypeInfo {
    return {
      ...new ContentTypeInfo(),
      id: this.editForm.get(['id']).value,
      contentTypeName: this.editForm.get(['contentTypeName']).value,
      enviroment: this.editForm.get(['enviroment']).value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate']).value,
      numberOfEntries: this.editForm.get(['numberOfEntries']).value,
      numberOfParameters: this.editForm.get(['numberOfParameters']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContentTypeInfo>>) {
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
