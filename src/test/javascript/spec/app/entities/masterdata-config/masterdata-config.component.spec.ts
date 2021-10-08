/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { MasterdataConfigComponent } from 'app/entities/masterdata-config/masterdata-config.component';
import { MasterdataConfigService } from 'app/entities/masterdata-config/masterdata-config.service';
import { MasterdataConfig } from 'app/shared/model/masterdata-config.model';

describe('Component Tests', () => {
  describe('MasterdataConfig Management Component', () => {
    let comp: MasterdataConfigComponent;
    let fixture: ComponentFixture<MasterdataConfigComponent>;
    let service: MasterdataConfigService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [MasterdataConfigComponent],
        providers: []
      })
        .overrideTemplate(MasterdataConfigComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MasterdataConfigComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MasterdataConfigService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MasterdataConfig('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.masterdataConfigs[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
