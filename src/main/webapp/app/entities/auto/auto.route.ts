import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Auto } from 'app/shared/model/auto.model';
import { AutoService } from './auto.service';
import { AutoComponent } from './auto.component';
import { AutoDetailComponent } from './auto-detail.component';
import { AutoUpdateComponent } from './auto-update.component';
import { AutoDeletePopupComponent } from './auto-delete-dialog.component';
import { IAuto } from 'app/shared/model/auto.model';

@Injectable({ providedIn: 'root' })
export class AutoResolve implements Resolve<IAuto> {
  constructor(private service: AutoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAuto> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Auto>) => response.ok),
        map((auto: HttpResponse<Auto>) => auto.body)
      );
    }
    return of(new Auto());
  }
}

export const autoRoute: Routes = [
  {
    path: '',
    component: AutoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.auto.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AutoDetailComponent,
    resolve: {
      auto: AutoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.auto.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AutoUpdateComponent,
    resolve: {
      auto: AutoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.auto.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AutoUpdateComponent,
    resolve: {
      auto: AutoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.auto.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const autoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AutoDeletePopupComponent,
    resolve: {
      auto: AutoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.auto.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
