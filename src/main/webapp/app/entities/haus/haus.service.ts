import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHaus } from 'app/shared/model/haus.model';

type EntityResponseType = HttpResponse<IHaus>;
type EntityArrayResponseType = HttpResponse<IHaus[]>;

@Injectable({ providedIn: 'root' })
export class HausService {
  public resourceUrl = SERVER_API_URL + 'api/haus';

  constructor(protected http: HttpClient) {}

  create(haus: IHaus): Observable<EntityResponseType> {
    return this.http.post<IHaus>(this.resourceUrl, haus, { observe: 'response' });
  }

  update(haus: IHaus): Observable<EntityResponseType> {
    return this.http.put<IHaus>(this.resourceUrl, haus, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IHaus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHaus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
