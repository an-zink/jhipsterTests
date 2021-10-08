/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ContentTypeInfoUpdateComponent } from 'app/entities/content-type-info/content-type-info-update.component';
import { ContentTypeInfoService } from 'app/entities/content-type-info/content-type-info.service';
import { ContentTypeInfo } from 'app/shared/model/content-type-info.model';

describe('Component Tests', () => {
  describe('ContentTypeInfo Management Update Component', () => {
    let comp: ContentTypeInfoUpdateComponent;
    let fixture: ComponentFixture<ContentTypeInfoUpdateComponent>;
    let service: ContentTypeInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ContentTypeInfoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ContentTypeInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContentTypeInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContentTypeInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ContentTypeInfo('123');
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
        const entity = new ContentTypeInfo();
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
