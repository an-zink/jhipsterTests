import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IAuto, Auto } from 'app/shared/model/auto.model';
import { AutoService } from './auto.service';

@Component({
  selector: 'jhi-auto-update',
  templateUrl: './auto-update.component.html'
})
export class AutoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    number: [],
    modell: []
  });

  constructor(protected autoService: AutoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ auto }) => {
      this.updateForm(auto);
    });
  }

  updateForm(auto: IAuto) {
    this.editForm.patchValue({
      id: auto.id,
      name: auto.name,
      number: auto.number,
      modell: auto.modell
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const auto = this.createFromForm();
    if (auto.id !== undefined) {
      this.subscribeToSaveResponse(this.autoService.update(auto));
    } else {
      this.subscribeToSaveResponse(this.autoService.create(auto));
    }
  }

  private createFromForm(): IAuto {
    return {
      ...new Auto(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      number: this.editForm.get(['number']).value,
      modell: this.editForm.get(['modell']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuto>>) {
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
