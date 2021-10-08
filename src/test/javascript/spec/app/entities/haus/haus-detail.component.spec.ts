/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { HausDetailComponent } from 'app/entities/haus/haus-detail.component';
import { Haus } from 'app/shared/model/haus.model';

describe('Component Tests', () => {
  describe('Haus Management Detail Component', () => {
    let comp: HausDetailComponent;
    let fixture: ComponentFixture<HausDetailComponent>;
    const route = ({ data: of({ haus: new Haus('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [HausDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(HausDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HausDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.haus).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
