import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContentTypeInfo } from 'app/shared/model/content-type-info.model';

@Component({
  selector: 'jhi-content-type-info-detail',
  templateUrl: './content-type-info-detail.component.html'
})
export class ContentTypeInfoDetailComponent implements OnInit {
  contentTypeInfo: IContentTypeInfo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ contentTypeInfo }) => {
      this.contentTypeInfo = contentTypeInfo;
    });
  }

  previousState() {
    window.history.back();
  }
}
