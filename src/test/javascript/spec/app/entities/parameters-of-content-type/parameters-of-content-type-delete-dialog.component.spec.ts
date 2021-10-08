/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { ParametersOfContentTypeDeleteDialogComponent } from 'app/entities/parameters-of-content-type/parameters-of-content-type-delete-dialog.component';
import { ParametersOfContentTypeService } from 'app/entities/parameters-of-content-type/parameters-of-content-type.service';

describe('Component Tests', () => {
  describe('ParametersOfContentType Management Delete Component', () => {
    let comp: ParametersOfContentTypeDeleteDialogComponent;
    let fixture: ComponentFixture<ParametersOfContentTypeDeleteDialogComponent>;
    let service: ParametersOfContentTypeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ParametersOfContentTypeDeleteDialogComponent]
      })
        .overrideTemplate(ParametersOfContentTypeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParametersOfContentTypeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParametersOfContentTypeService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
