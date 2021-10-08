import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MasterdataConfig } from 'app/shared/model/masterdata-config.model';
import { MasterdataConfigService } from './masterdata-config.service';
import { MasterdataConfigComponent } from './masterdata-config.component';
import { MasterdataConfigDetailComponent } from './masterdata-config-detail.component';
import { MasterdataConfigUpdateComponent } from './masterdata-config-update.component';
import { MasterdataConfigDeletePopupComponent } from './masterdata-config-delete-dialog.component';
import { IMasterdataConfig } from 'app/shared/model/masterdata-config.model';

@Injectable({ providedIn: 'root' })
export class MasterdataConfigResolve implements Resolve<IMasterdataConfig> {
  constructor(private service: MasterdataConfigService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMasterdataConfig> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MasterdataConfig>) => response.ok),
        map((masterdataConfig: HttpResponse<MasterdataConfig>) => masterdataConfig.body)
      );
    }
    return of(new MasterdataConfig());
  }
}

export const masterdataConfigRoute: Routes = [
  {
    path: '',
    component: MasterdataConfigComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.masterdataConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MasterdataConfigDetailComponent,
    resolve: {
      masterdataConfig: MasterdataConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.masterdataConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MasterdataConfigUpdateComponent,
    resolve: {
      masterdataConfig: MasterdataConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.masterdataConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MasterdataConfigUpdateComponent,
    resolve: {
      masterdataConfig: MasterdataConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.masterdataConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const masterdataConfigPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MasterdataConfigDeletePopupComponent,
    resolve: {
      masterdataConfig: MasterdataConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.masterdataConfig.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
