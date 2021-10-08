/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ParametersOfContentTypeDetailComponent } from 'app/entities/parameters-of-content-type/parameters-of-content-type-detail.component';
import { ParametersOfContentType } from 'app/shared/model/parameters-of-content-type.model';

describe('Component Tests', () => {
  describe('ParametersOfContentType Management Detail Component', () => {
    let comp: ParametersOfContentTypeDetailComponent;
    let fixture: ComponentFixture<ParametersOfContentTypeDetailComponent>;
    const route = ({ data: of({ parametersOfContentType: new ParametersOfContentType('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ParametersOfContentTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ParametersOfContentTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParametersOfContentTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.parametersOfContentType).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
