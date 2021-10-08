/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { TischesUpdateComponent } from 'app/entities/tisches/tisches-update.component';
import { TischesService } from 'app/entities/tisches/tisches.service';
import { Tisches } from 'app/shared/model/tisches.model';

describe('Component Tests', () => {
  describe('Tisches Management Update Component', () => {
    let comp: TischesUpdateComponent;
    let fixture: ComponentFixture<TischesUpdateComponent>;
    let service: TischesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [TischesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TischesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TischesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TischesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tisches('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tisches();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
