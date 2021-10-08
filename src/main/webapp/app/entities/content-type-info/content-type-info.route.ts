import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { ContentTypeInfoService } from './content-type-info.service';
import { ContentTypeInfoComponent } from './content-type-info.component';
import { ContentTypeInfoDetailComponent } from './content-type-info-detail.component';
import { ContentTypeInfoUpdateComponent } from './content-type-info-update.component';
import { ContentTypeInfoDeletePopupComponent } from './content-type-info-delete-dialog.component';
import { IContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { ContentTypeInfoStrapiComponent } from './content-type-info-strapi/content-type-info-strapi.component';
// import { ContentTypeInfoStrapiTestComponent } from './content-type-info-strapi/content-type-info-strapi-test/content-type-info-strapi-test.component';

@Injectable({ providedIn: 'root' })
export class ContentTypeInfoResolve implements Resolve<IContentTypeInfo> {
  constructor(private service: ContentTypeInfoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IContentTypeInfo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ContentTypeInfo>) => response.ok),
        map((contentTypeInfo: HttpResponse<ContentTypeInfo>) => contentTypeInfo.body)
      );
    }
    return of(new ContentTypeInfo());
  }
}

export const contentTypeInfoRoute: Routes = [
  {
    path: '',
    component: ContentTypeInfoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.contentTypeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ContentTypeInfoDetailComponent,
    resolve: {
      contentTypeInfo: ContentTypeInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.contentTypeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ContentTypeInfoUpdateComponent,
    resolve: {
      contentTypeInfo: ContentTypeInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.contentTypeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ContentTypeInfoUpdateComponent,
    resolve: {
      contentTypeInfo: ContentTypeInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.contentTypeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const contentTypeInfoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ContentTypeInfoDeletePopupComponent,
    resolve: {
      contentTypeInfo: ContentTypeInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.contentTypeInfo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'strapi',
    component: ContentTypeInfoStrapiComponent,
    resolve: {
      contentTypeInfo: ContentTypeInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhipsterApp.contentTypeInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
  // {
  //   path: 'strapiTest',
  //   component: ContentTypeInfoStrapiTestComponent,
  //   resolve: {
  //   contentTypeInfo: ContentTypeInfoResolve
  //   },
  //   data: {
  //     authorities: ['ROLE_USER'],
  //     pageTitle: 'jhipsterApp.contentTypeInfo.home.title'
  //   },
  //   canActivate: [UserRouteAccessService]
  // }
];
