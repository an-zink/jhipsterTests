import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { AccountService } from 'app/core';
import { ContentTypeInfoService } from './content-type-info.service';

@Component({
  selector: 'jhi-content-type-info',
  templateUrl: './content-type-info.component.html'
})
export class ContentTypeInfoComponent implements OnInit, OnDestroy {
  contentTypeInfos: IContentTypeInfo[];
  currentAccount: any;
  eventSubscriber: Subscription;
  test: boolean;

  constructor(
    protected contentTypeInfoService: ContentTypeInfoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.contentTypeInfoService
      .query()
      .pipe(
        filter((res: HttpResponse<IContentTypeInfo[]>) => res.ok),
        map((res: HttpResponse<IContentTypeInfo[]>) => res.body)
      )
      .subscribe(
        (res: IContentTypeInfo[]) => {
          this.contentTypeInfos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInContentTypeInfos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IContentTypeInfo) {
    return item.id;
  }
  trackEnvironment(index: number, item: IContentTypeInfo) {
    return item.id;
  }

  registerChangeInContentTypeInfos() {
    this.eventSubscriber = this.eventManager.subscribe('contentTypeInfoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
