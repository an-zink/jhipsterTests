/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { AutoComponent } from 'app/entities/auto/auto.component';
import { AutoService } from 'app/entities/auto/auto.service';
import { Auto } from 'app/shared/model/auto.model';

describe('Component Tests', () => {
  describe('Auto Management Component', () => {
    let comp: AutoComponent;
    let fixture: ComponentFixture<AutoComponent>;
    let service: AutoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [AutoComponent],
        providers: []
      })
        .overrideTemplate(AutoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AutoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AutoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Auto('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.autos[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
