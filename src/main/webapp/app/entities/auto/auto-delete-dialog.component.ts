import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuto } from 'app/shared/model/auto.model';
import { AutoService } from './auto.service';

@Component({
  selector: 'jhi-auto-delete-dialog',
  templateUrl: './auto-delete-dialog.component.html'
})
export class AutoDeleteDialogComponent {
  auto: IAuto;

  constructor(protected autoService: AutoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.autoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'autoListModification',
        content: 'Deleted an auto'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-auto-delete-popup',
  template: ''
})
export class AutoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ auto }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AutoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.auto = auto;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/auto', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/auto', { outlets: { popup: null } }]);
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
