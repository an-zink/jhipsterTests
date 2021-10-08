import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IHaus, Haus } from 'app/shared/model/haus.model';
import { HausService } from './haus.service';

@Component({
  selector: 'jhi-haus-update',
  templateUrl: './haus-update.component.html'
})
export class HausUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nr: [],
    name: []
  });

  constructor(protected hausService: HausService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ haus }) => {
      this.updateForm(haus);
    });
  }

  updateForm(haus: IHaus) {
    this.editForm.patchValue({
      id: haus.id,
      nr: haus.nr,
      name: haus.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const haus = this.createFromForm();
    if (haus.id !== undefined) {
      this.subscribeToSaveResponse(this.hausService.update(haus));
    } else {
      this.subscribeToSaveResponse(this.hausService.create(haus));
    }
  }

  private createFromForm(): IHaus {
    return {
      ...new Haus(),
      id: this.editForm.get(['id']).value,
      nr: this.editForm.get(['nr']).value,
      name: this.editForm.get(['name']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHaus>>) {
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
