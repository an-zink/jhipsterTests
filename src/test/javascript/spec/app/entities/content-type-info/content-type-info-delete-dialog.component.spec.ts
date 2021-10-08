/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { ContentTypeInfoDeleteDialogComponent } from 'app/entities/content-type-info/content-type-info-delete-dialog.component';
import { ContentTypeInfoService } from 'app/entities/content-type-info/content-type-info.service';

describe('Component Tests', () => {
  describe('ContentTypeInfo Management Delete Component', () => {
    let comp: ContentTypeInfoDeleteDialogComponent;
    let fixture: ComponentFixture<ContentTypeInfoDeleteDialogComponent>;
    let service: ContentTypeInfoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ContentTypeInfoDeleteDialogComponent]
      })
        .overrideTemplate(ContentTypeInfoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContentTypeInfoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContentTypeInfoService);
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
