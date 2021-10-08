/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { TischesDetailComponent } from 'app/entities/tisches/tisches-detail.component';
import { Tisches } from 'app/shared/model/tisches.model';

describe('Component Tests', () => {
  describe('Tisches Management Detail Component', () => {
    let comp: TischesDetailComponent;
    let fixture: ComponentFixture<TischesDetailComponent>;
    const route = ({ data: of({ tisches: new Tisches('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [TischesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TischesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TischesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tisches).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
