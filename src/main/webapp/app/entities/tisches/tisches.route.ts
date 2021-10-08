import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Tisches } from 'app/shared/model/tisches.model';
import { TischesService } from './tisches.service';
import { TischesComponent } from './tisches.component';
import { TischesDetailComponent } from './tisches-detail.component';
import { TischesUpdateComponent } from './tisches-update.component';
import { TischesDeletePopupComponent } from './tisches-delete-dialog.component';
import { ITisches } from 'app/shared/model/tisches.model';

@Injectable({ providedIn: 'root' })
export class TischesResolve implements Resolve<ITisches> {
  constructor(private service: TischesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITisches> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Tisches>) => response.ok),
        map((tisches: HttpResponse<Tisches>) => tisches.body)
      );
    }
    return of(new Tisches());
  }
}

export const tischesRoute: Routes = [
  {
    path: '',
    component: TischesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.tisches.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TischesDetailComponent,
    resolve: {
      tisches: TischesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.tisches.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TischesUpdateComponent,
    resolve: {
      tisches: TischesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.tisches.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TischesUpdateComponent,
    resolve: {
      tisches: TischesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.tisches.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tischesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TischesDeletePopupComponent,
    resolve: {
      tisches: TischesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.tisches.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
