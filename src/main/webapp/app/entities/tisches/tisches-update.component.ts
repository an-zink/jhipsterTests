import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITisches, Tisches } from 'app/shared/model/tisches.model';
import { TischesService } from './tisches.service';

@Component({
  selector: 'jhi-tisches-update',
  templateUrl: './tisches-update.component.html'
})
export class TischesUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    tischPlatte: [],
    tischBein: []
  });

  constructor(protected tischesService: TischesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tisches }) => {
      this.updateForm(tisches);
    });
  }

  updateForm(tisches: ITisches) {
    this.editForm.patchValue({
      id: tisches.id,
      name: tisches.name,
      tischPlatte: tisches.tischPlatte,
      tischBein: tisches.tischBein
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tisches = this.createFromForm();
    if (tisches.id !== undefined) {
      this.subscribeToSaveResponse(this.tischesService.update(tisches));
    } else {
      this.subscribeToSaveResponse(this.tischesService.create(tisches));
    }
  }

  private createFromForm(): ITisches {
    return {
      ...new Tisches(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      tischPlatte: this.editForm.get(['tischPlatte']).value,
      tischBein: this.editForm.get(['tischBein']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITisches>>) {
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
