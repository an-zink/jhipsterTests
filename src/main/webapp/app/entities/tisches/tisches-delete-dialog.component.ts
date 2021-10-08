import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITisches } from 'app/shared/model/tisches.model';
import { TischesService } from './tisches.service';

@Component({
  selector: 'jhi-tisches-delete-dialog',
  templateUrl: './tisches-delete-dialog.component.html'
})
export class TischesDeleteDialogComponent {
  tisches: ITisches;

  constructor(protected tischesService: TischesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.tischesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tischesListModification',
        content: 'Deleted an tisches'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tisches-delete-popup',
  template: ''
})
export class TischesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tisches }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TischesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tisches = tisches;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tisches', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tisches', { outlets: { popup: null } }]);
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
