import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IHaus } from 'app/shared/model/haus.model';
import { AccountService } from 'app/core';
import { HausService } from './haus.service';

@Component({
  selector: 'jhi-haus',
  templateUrl: './haus.component.html'
})
export class HausComponent implements OnInit, OnDestroy {
  haus: IHaus[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected hausService: HausService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.hausService
      .query()
      .pipe(
        filter((res: HttpResponse<IHaus[]>) => res.ok),
        map((res: HttpResponse<IHaus[]>) => res.body)
      )
      .subscribe(
        (res: IHaus[]) => {
          this.haus = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInHaus();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IHaus) {
    return item.id;
  }

  registerChangeInHaus() {
    this.eventSubscriber = this.eventManager.subscribe('hausListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
