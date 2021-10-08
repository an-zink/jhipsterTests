/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { TischesComponent } from 'app/entities/tisches/tisches.component';
import { TischesService } from 'app/entities/tisches/tisches.service';
import { Tisches } from 'app/shared/model/tisches.model';

describe('Component Tests', () => {
  describe('Tisches Management Component', () => {
    let comp: TischesComponent;
    let fixture: ComponentFixture<TischesComponent>;
    let service: TischesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [TischesComponent],
        providers: []
      })
        .overrideTemplate(TischesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TischesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TischesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Tisches('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tisches[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
