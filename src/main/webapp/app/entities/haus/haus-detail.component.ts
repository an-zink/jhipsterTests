import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHaus } from 'app/shared/model/haus.model';

@Component({
  selector: 'jhi-haus-detail',
  templateUrl: './haus-detail.component.html'
})
export class HausDetailComponent implements OnInit {
  haus: IHaus;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ haus }) => {
      this.haus = haus;
    });
  }

  previousState() {
    window.history.back();
  }
}
