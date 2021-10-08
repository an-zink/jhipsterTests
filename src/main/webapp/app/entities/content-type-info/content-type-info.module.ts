import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { ContentTypeInfoStrapiComponent } from './content-type-info-strapi/content-type-info-strapi.component';
import { JhipsterSharedModule } from 'app/shared';
import {
  ContentTypeInfoComponent,
  ContentTypeInfoDetailComponent,
  ContentTypeInfoUpdateComponent,
  ContentTypeInfoDeletePopupComponent,
  ContentTypeInfoDeleteDialogComponent,
  contentTypeInfoRoute,
  contentTypeInfoPopupRoute
} from './';

const ENTITY_STATES = [...contentTypeInfoRoute, ...contentTypeInfoPopupRoute];

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ContentTypeInfoComponent,
    ContentTypeInfoDetailComponent,
    ContentTypeInfoUpdateComponent,
    ContentTypeInfoDeleteDialogComponent,
    ContentTypeInfoDeletePopupComponent,
    ContentTypeInfoStrapiComponent
  ],
  entryComponents: [
    ContentTypeInfoComponent,
    ContentTypeInfoUpdateComponent,
    ContentTypeInfoDeleteDialogComponent,
    ContentTypeInfoDeletePopupComponent,
    ContentTypeInfoStrapiComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterContentTypeInfoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
