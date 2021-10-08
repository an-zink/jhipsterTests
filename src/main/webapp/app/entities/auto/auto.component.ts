import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAuto } from 'app/shared/model/auto.model';
import { AccountService } from 'app/core';
import { AutoService } from './auto.service';

@Component({
  selector: 'jhi-auto',
  templateUrl: './auto.component.html'
})
export class AutoComponent implements OnInit, OnDestroy {
  autos: IAuto[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected autoService: AutoService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.autoService
      .query()
      .pipe(
        filter((res: HttpResponse<IAuto[]>) => res.ok),
        map((res: HttpResponse<IAuto[]>) => res.body)
      )
      .subscribe(
        (res: IAuto[]) => {
          this.autos = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInAutos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAuto) {
    return item.id;
  }

  registerChangeInAutos() {
    this.eventSubscriber = this.eventManager.subscribe('autoListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
