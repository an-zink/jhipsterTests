import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
  HausComponent,
  HausDetailComponent,
  HausUpdateComponent,
  HausDeletePopupComponent,
  HausDeleteDialogComponent,
  hausRoute,
  hausPopupRoute
} from './';

const ENTITY_STATES = [...hausRoute, ...hausPopupRoute];

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [HausComponent, HausDetailComponent, HausUpdateComponent, HausDeleteDialogComponent, HausDeletePopupComponent],
  entryComponents: [HausComponent, HausUpdateComponent, HausDeleteDialogComponent, HausDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterHausModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
