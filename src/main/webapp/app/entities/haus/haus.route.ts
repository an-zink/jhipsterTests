import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Haus } from 'app/shared/model/haus.model';
import { HausService } from './haus.service';
import { HausComponent } from './haus.component';
import { HausDetailComponent } from './haus-detail.component';
import { HausUpdateComponent } from './haus-update.component';
import { HausDeletePopupComponent } from './haus-delete-dialog.component';
import { IHaus } from 'app/shared/model/haus.model';

@Injectable({ providedIn: 'root' })
export class HausResolve implements Resolve<IHaus> {
  constructor(private service: HausService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHaus> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Haus>) => response.ok),
        map((haus: HttpResponse<Haus>) => haus.body)
      );
    }
    return of(new Haus());
  }
}

export const hausRoute: Routes = [
  {
    path: '',
    component: HausComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.haus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: HausDetailComponent,
    resolve: {
      haus: HausResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.haus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: HausUpdateComponent,
    resolve: {
      haus: HausResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.haus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: HausUpdateComponent,
    resolve: {
      haus: HausResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.haus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const hausPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: HausDeletePopupComponent,
    resolve: {
      haus: HausResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.haus.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
