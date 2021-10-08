import { Component, OnInit, Input } from '@angular/core';
import { IContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { ContentTypeInfoService } from 'app/entities/content-type-info/content-type-info.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';
import { pipe } from 'rxjs';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
  selector: 'jhi-content-type-info-strapi-test',
  templateUrl: './content-type-info-strapi-test.component.html'
})
export class ContentTypeInfoStrapiComponent implements OnInit {
  sortedContentType: Array<IContentTypeInfo> = [];

  constructor(protected contentTypeInfoService: ContentTypeInfoService) {}

  ngOnInit() {
    this.loadALl();
  }

  loadALl() {
    this.contentTypeInfoService
      .query()
      .pipe(
        filter((res: HttpResponse<IContentTypeInfo[]>) => res.ok),
        map((res: HttpResponse<IContentTypeInfo[]>) => res.body)
      )
      .subscribe((res: IContentTypeInfo[]) => {
        this.sortedContentType = res;
      });
  }
}
