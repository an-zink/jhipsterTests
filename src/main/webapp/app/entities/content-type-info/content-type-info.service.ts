import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IContentTypeInfo } from 'app/shared/model/content-type-info.model';
import { Options } from 'selenium-webdriver/chrome';

type EntityResponseType = HttpResponse<IContentTypeInfo>;
type EntityArrayResponseType = HttpResponse<IContentTypeInfo[]>;
type EntityTowDimensionalArrayResponseType = HttpResponse<IContentTypeInfo[][]>;
type test = HttpResponse<string[]>;

@Injectable({ providedIn: 'root' })
export class ContentTypeInfoService {
  public resourceUrl = SERVER_API_URL + 'api/content-type-infos';
  public environmentUrl = SERVER_API_URL + 'api/content-type-infos/environment';
  public contentTypeNameUrl = SERVER_API_URL + 'api/content-type-infos/contentTypeName';
  public contentTypeDataSortUrl = SERVER_API_URL + 'api/content-type-infos/datasort';

  constructor(protected http: HttpClient) {}

  create(contentTypeInfo: IContentTypeInfo): Observable<EntityResponseType> {
    return this.http.post<IContentTypeInfo>(this.resourceUrl, contentTypeInfo, { observe: 'response' });
  }

  update(contentTypeInfo: IContentTypeInfo): Observable<EntityResponseType> {
    return this.http.put<IContentTypeInfo>(this.resourceUrl, contentTypeInfo, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IContentTypeInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IContentTypeInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  findEnvironment(): Observable<EntityArrayResponseType> {
    return this.http.get<IContentTypeInfo[]>(this.environmentUrl, { observe: 'response' });
  }
  findContentTypeNames(): Observable<EntityArrayResponseType> {
    return this.http.get<IContentTypeInfo[]>(this.contentTypeNameUrl, { observe: 'response' });
  }
  findContentTypeDataSorted(): Observable<EntityTowDimensionalArrayResponseType> {
    return this.http.get<IContentTypeInfo[][]>(this.contentTypeDataSortUrl, { observe: 'response' });
  }
}
