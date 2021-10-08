/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { MasterdataConfigUpdateComponent } from 'app/entities/masterdata-config/masterdata-config-update.component';
import { MasterdataConfigService } from 'app/entities/masterdata-config/masterdata-config.service';
import { MasterdataConfig } from 'app/shared/model/masterdata-config.model';

describe('Component Tests', () => {
  describe('MasterdataConfig Management Update Component', () => {
    let comp: MasterdataConfigUpdateComponent;
    let fixture: ComponentFixture<MasterdataConfigUpdateComponent>;
    let service: MasterdataConfigService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [MasterdataConfigUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MasterdataConfigUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MasterdataConfigUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MasterdataConfigService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MasterdataConfig('123');
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
        const entity = new MasterdataConfig();
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
