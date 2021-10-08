import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'content-type-info',
        loadChildren: './content-type-info/content-type-info.module#JhipsterContentTypeInfoModule'
      },
      {
        path: 'masterdata-config',
        loadChildren: './masterdata-config/masterdata-config.module#JhipsterMasterdataConfigModule'
      },
      {
        path: 'auto',
        loadChildren: './auto/auto.module#JhipsterAutoModule'
      },
      {
        path: 'haus',
        loadChildren: './haus/haus.module#JhipsterHausModule'
      },
      {
        path: 'tisches',
        loadChildren: './tisches/tisches.module#JhipsterTischesModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEntityModule {}
