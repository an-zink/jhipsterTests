/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { MasterdataConfigDetailComponent } from 'app/entities/masterdata-config/masterdata-config-detail.component';
import { MasterdataConfig } from 'app/shared/model/masterdata-config.model';

describe('Component Tests', () => {
  describe('MasterdataConfig Management Detail Component', () => {
    let comp: MasterdataConfigDetailComponent;
    let fixture: ComponentFixture<MasterdataConfigDetailComponent>;
    const route = ({ data: of({ masterdataConfig: new MasterdataConfig('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [MasterdataConfigDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MasterdataConfigDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MasterdataConfigDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.masterdataConfig).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
