/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { ParametersOfContentTypeComponent } from 'app/entities/parameters-of-content-type/parameters-of-content-type.component';
import { ParametersOfContentTypeService } from 'app/entities/parameters-of-content-type/parameters-of-content-type.service';
import { ParametersOfContentType } from 'app/shared/model/parameters-of-content-type.model';

describe('Component Tests', () => {
  describe('ParametersOfContentType Management Component', () => {
    let comp: ParametersOfContentTypeComponent;
    let fixture: ComponentFixture<ParametersOfContentTypeComponent>;
    let service: ParametersOfContentTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ParametersOfContentTypeComponent],
        providers: []
      })
        .overrideTemplate(ParametersOfContentTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParametersOfContentTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParametersOfContentTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParametersOfContentType('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.parametersOfContentTypes[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
