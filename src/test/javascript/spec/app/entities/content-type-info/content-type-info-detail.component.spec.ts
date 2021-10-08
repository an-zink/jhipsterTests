/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ContentTypeInfoDetailComponent } from 'app/entities/content-type-info/content-type-info-detail.component';
import { ContentTypeInfo } from 'app/shared/model/content-type-info.model';

describe('Component Tests', () => {
  describe('ContentTypeInfo Management Detail Component', () => {
    let comp: ContentTypeInfoDetailComponent;
    let fixture: ComponentFixture<ContentTypeInfoDetailComponent>;
    const route = ({ data: of({ contentTypeInfo: new ContentTypeInfo('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ContentTypeInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ContentTypeInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContentTypeInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.contentTypeInfo).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
