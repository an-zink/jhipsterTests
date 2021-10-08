import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITisches } from 'app/shared/model/tisches.model';

@Component({
  selector: 'jhi-tisches-detail',
  templateUrl: './tisches-detail.component.html'
})
export class TischesDetailComponent implements OnInit {
  tisches: ITisches;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tisches }) => {
      this.tisches = tisches;
    });
  }

  previousState() {
    window.history.back();
  }
}
