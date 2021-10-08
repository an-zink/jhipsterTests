import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAuto } from 'app/shared/model/auto.model';

type EntityResponseType = HttpResponse<IAuto>;
type EntityArrayResponseType = HttpResponse<IAuto[]>;

@Injectable({ providedIn: 'root' })
export class AutoService {
  public resourceUrl = SERVER_API_URL + 'api/autos';

  constructor(protected http: HttpClient) {}

  create(auto: IAuto): Observable<EntityResponseType> {
    return this.http.post<IAuto>(this.resourceUrl, auto, { observe: 'response' });
  }

  update(auto: IAuto): Observable<EntityResponseType> {
    return this.http.put<IAuto>(this.resourceUrl, auto, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAuto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAuto[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
