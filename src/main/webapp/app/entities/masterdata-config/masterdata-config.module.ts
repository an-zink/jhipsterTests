import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
  MasterdataConfigComponent,
  MasterdataConfigDetailComponent,
  MasterdataConfigUpdateComponent,
  MasterdataConfigDeletePopupComponent,
  MasterdataConfigDeleteDialogComponent,
  masterdataConfigRoute,
  masterdataConfigPopupRoute
} from './';

const ENTITY_STATES = [...masterdataConfigRoute, ...masterdataConfigPopupRoute];

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MasterdataConfigComponent,
    MasterdataConfigDetailComponent,
    MasterdataConfigUpdateComponent,
    MasterdataConfigDeleteDialogComponent,
    MasterdataConfigDeletePopupComponent
  ],
  entryComponents: [
    MasterdataConfigComponent,
    MasterdataConfigUpdateComponent,
    MasterdataConfigDeleteDialogComponent,
    MasterdataConfigDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterMasterdataConfigModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
