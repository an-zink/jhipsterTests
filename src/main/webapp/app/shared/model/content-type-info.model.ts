export interface IContentTypeInfo {
  id?: string;
  contentTypeName?: string;
  enviroment?: string;
  lastModifiedDate?: string;
  numberOfEntries?: number;
  numberOfParameters?: number;
}

export class ContentTypeInfo implements IContentTypeInfo {
  constructor(
    public id?: string,
    public contentTypeName?: string,
    public enviroment?: string,
    public lastModifiedDate?: string,
    public numberOfEntries?: number,
    public numberOfParameters?: number
  ) {}
}
