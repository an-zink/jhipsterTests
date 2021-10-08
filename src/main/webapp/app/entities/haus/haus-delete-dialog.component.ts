import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHaus } from 'app/shared/model/haus.model';
import { HausService } from './haus.service';

@Component({
  selector: 'jhi-haus-delete-dialog',
  templateUrl: './haus-delete-dialog.component.html'
})
export class HausDeleteDialogComponent {
  haus: IHaus;

  constructor(protected hausService: HausService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.hausService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'hausListModification',
        content: 'Deleted an haus'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-haus-delete-popup',
  template: ''
})
export class HausDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ haus }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(HausDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.haus = haus;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/haus', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/haus', { outlets: { popup: null } }]);
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
