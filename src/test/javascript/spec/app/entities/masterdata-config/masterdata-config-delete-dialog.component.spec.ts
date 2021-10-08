/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { MasterdataConfigDeleteDialogComponent } from 'app/entities/masterdata-config/masterdata-config-delete-dialog.component';
import { MasterdataConfigService } from 'app/entities/masterdata-config/masterdata-config.service';

describe('Component Tests', () => {
  describe('MasterdataConfig Management Delete Component', () => {
    let comp: MasterdataConfigDeleteDialogComponent;
    let fixture: ComponentFixture<MasterdataConfigDeleteDialogComponent>;
    let service: MasterdataConfigService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [MasterdataConfigDeleteDialogComponent]
      })
        .overrideTemplate(MasterdataConfigDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MasterdataConfigDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MasterdataConfigService);
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
