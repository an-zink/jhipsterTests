import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMasterdataConfig } from 'app/shared/model/masterdata-config.model';

@Component({
  selector: 'jhi-masterdata-config-detail',
  templateUrl: './masterdata-config-detail.component.html'
})
export class MasterdataConfigDetailComponent implements OnInit {
  masterdataConfig: IMasterdataConfig;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ masterdataConfig }) => {
      this.masterdataConfig = masterdataConfig;
    });
  }

  previousState() {
    window.history.back();
  }
}
