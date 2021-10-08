import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
  TischesComponent,
  TischesDetailComponent,
  TischesUpdateComponent,
  TischesDeletePopupComponent,
  TischesDeleteDialogComponent,
  tischesRoute,
  tischesPopupRoute
} from './';

const ENTITY_STATES = [...tischesRoute, ...tischesPopupRoute];

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TischesComponent,
    TischesDetailComponent,
    TischesUpdateComponent,
    TischesDeleteDialogComponent,
    TischesDeletePopupComponent
  ],
  entryComponents: [TischesComponent, TischesUpdateComponent, TischesDeleteDialogComponent, TischesDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterTischesModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
