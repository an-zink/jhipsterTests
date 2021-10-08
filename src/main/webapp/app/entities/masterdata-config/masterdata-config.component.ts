import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMasterdataConfig } from 'app/shared/model/masterdata-config.model';
import { AccountService } from 'app/core';
import { MasterdataConfigService } from './masterdata-config.service';

@Component({
  selector: 'jhi-masterdata-config',
  templateUrl: './masterdata-config.component.html'
})
export class MasterdataConfigComponent implements OnInit, OnDestroy {
  masterdataConfigs: IMasterdataConfig[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected masterdataConfigService: MasterdataConfigService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.masterdataConfigService
      .query()
      .pipe(
        filter((res: HttpResponse<IMasterdataConfig[]>) => res.ok),
        map((res: HttpResponse<IMasterdataConfig[]>) => res.body)
      )
      .subscribe(
        (res: IMasterdataConfig[]) => {
          this.masterdataConfigs = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMasterdataConfigs();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMasterdataConfig) {
    return item.id;
  }

  registerChangeInMasterdataConfigs() {
    this.eventSubscriber = this.eventManager.subscribe('masterdataConfigListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
