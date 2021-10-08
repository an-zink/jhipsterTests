import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { ContentTypeInfoService } from './content-type-info.service';

@Component({
  selector: 'jhi-content-type-info-delete-dialog',
  templateUrl: './content-type-info-delete-dialog.component.html'
})
export class ContentTypeInfoDeleteDialogComponent {
  contentTypeInfo: IContentTypeInfo;

  constructor(
    protected contentTypeInfoService: ContentTypeInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.contentTypeInfoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'contentTypeInfoListModification',
        content: 'Deleted an contentTypeInfo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-content-type-info-delete-popup',
  template: ''
})
export class ContentTypeInfoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ contentTypeInfo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ContentTypeInfoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.contentTypeInfo = contentTypeInfo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/content-type-info', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/content-type-info', { outlets: { popup: null } }]);
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
