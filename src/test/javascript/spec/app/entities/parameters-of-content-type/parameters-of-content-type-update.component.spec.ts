/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ParametersOfContentTypeUpdateComponent } from 'app/entities/parameters-of-content-type/parameters-of-content-type-update.component';
import { ParametersOfContentTypeService } from 'app/entities/parameters-of-content-type/parameters-of-content-type.service';
import { ParametersOfContentType } from 'app/shared/model/parameters-of-content-type.model';

describe('Component Tests', () => {
  describe('ParametersOfContentType Management Update Component', () => {
    let comp: ParametersOfContentTypeUpdateComponent;
    let fixture: ComponentFixture<ParametersOfContentTypeUpdateComponent>;
    let service: ParametersOfContentTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ParametersOfContentTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ParametersOfContentTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParametersOfContentTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParametersOfContentTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParametersOfContentType('123');
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
        const entity = new ParametersOfContentType();
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
