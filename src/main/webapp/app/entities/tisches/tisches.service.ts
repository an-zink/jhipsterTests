import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITisches } from 'app/shared/model/tisches.model';

type EntityResponseType = HttpResponse<ITisches>;
type EntityArrayResponseType = HttpResponse<ITisches[]>;

@Injectable({ providedIn: 'root' })
export class TischesService {
  public resourceUrl = SERVER_API_URL + 'api/tisches';

  constructor(protected http: HttpClient) {}

  create(tisches: ITisches): Observable<EntityResponseType> {
    return this.http.post<ITisches>(this.resourceUrl, tisches, { observe: 'response' });
  }

  update(tisches: ITisches): Observable<EntityResponseType> {
    return this.http.put<ITisches>(this.resourceUrl, tisches, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ITisches>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITisches[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
