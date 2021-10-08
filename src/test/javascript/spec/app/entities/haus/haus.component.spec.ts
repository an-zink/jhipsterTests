/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { HausComponent } from 'app/entities/haus/haus.component';
import { HausService } from 'app/entities/haus/haus.service';
import { Haus } from 'app/shared/model/haus.model';

describe('Component Tests', () => {
  describe('Haus Management Component', () => {
    let comp: HausComponent;
    let fixture: ComponentFixture<HausComponent>;
    let service: HausService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [HausComponent],
        providers: []
      })
        .overrideTemplate(HausComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HausComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HausService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Haus('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.haus[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
