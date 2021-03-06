import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
  AutoComponent,
  AutoDetailComponent,
  AutoUpdateComponent,
  AutoDeletePopupComponent,
  AutoDeleteDialogComponent,
  autoRoute,
  autoPopupRoute
} from './';

const ENTITY_STATES = [...autoRoute, ...autoPopupRoute];

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [AutoComponent, AutoDetailComponent, AutoUpdateComponent, AutoDeleteDialogComponent, AutoDeletePopupComponent],
  entryComponents: [AutoComponent, AutoUpdateComponent, AutoDeleteDialogComponent, AutoDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterAutoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
