/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { ContentTypeInfoComponent } from 'app/entities/content-type-info/content-type-info.component';
import { ContentTypeInfoService } from 'app/entities/content-type-info/content-type-info.service';
import { ContentTypeInfo } from 'app/shared/model/content-type-info.model';

describe('Component Tests', () => {
  describe('ContentTypeInfo Management Component', () => {
    let comp: ContentTypeInfoComponent;
    let fixture: ComponentFixture<ContentTypeInfoComponent>;
    let service: ContentTypeInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ContentTypeInfoComponent],
        providers: []
      })
        .overrideTemplate(ContentTypeInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContentTypeInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContentTypeInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ContentTypeInfo('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contentTypeInfos[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
