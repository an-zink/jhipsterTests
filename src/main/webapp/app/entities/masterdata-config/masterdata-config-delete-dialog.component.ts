import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMasterdataConfig } from 'app/shared/model/masterdata-config.model';
import { MasterdataConfigService } from './masterdata-config.service';

@Component({
  selector: 'jhi-masterdata-config-delete-dialog',
  templateUrl: './masterdata-config-delete-dialog.component.html'
})
export class MasterdataConfigDeleteDialogComponent {
  masterdataConfig: IMasterdataConfig;

  constructor(
    protected masterdataConfigService: MasterdataConfigService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.masterdataConfigService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'masterdataConfigListModification',
        content: 'Deleted an masterdataConfig'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-masterdata-config-delete-popup',
  template: ''
})
export class MasterdataConfigDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ masterdataConfig }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MasterdataConfigDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.masterdataConfig = masterdataConfig;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/masterdata-config', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/masterdata-config', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
