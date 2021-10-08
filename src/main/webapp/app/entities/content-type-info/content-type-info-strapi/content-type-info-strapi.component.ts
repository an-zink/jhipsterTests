import { Component, OnInit, Input } from '@angular/core';
import { IContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { ContentTypeInfoService } from 'app/entities/content-type-info/content-type-info.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';

@Component({
  selector: 'jhi-content-type-info-strapi',
  templateUrl: './content-type-info-strapi.component.html',
  styleUrls: ['./content-type-info-strapi.component.scss']
})
export class ContentTypeInfoStrapiComponent implements OnInit {
  contentTypeEnvironment: IContentTypeInfo[];
  contentTypeDataSorted: IContentTypeInfo[][];

  constructor(protected contentTypeInfoService: ContentTypeInfoService) {
    this.loadDataSort();
  }

  ngOnInit() {
    this.loadInfo();
  }

  loadInfo() {
    console.log('hallo');
    this.contentTypeInfoService
      .findEnvironment()
      .pipe(
        filter((res: HttpResponse<IContentTypeInfo[]>) => res.ok),
        map((res: HttpResponse<IContentTypeInfo[]>) => res.body)
      )
      .subscribe((res: IContentTypeInfo[]) => {
        this.contentTypeEnvironment = res;
      });
  }
  loadDataSort() {
    this.contentTypeInfoService
      .findContentTypeDataSorted()
      .pipe(
        filter((res: HttpResponse<IContentTypeInfo[][]>) => res.ok),
        map((res: HttpResponse<IContentTypeInfo[][]>) => res.body)
      )
      .subscribe((res: IContentTypeInfo[][]) => {
        this.contentTypeDataSorted = res;
      });
  }

  priviousState() {
    window.history.back();
  }
}
