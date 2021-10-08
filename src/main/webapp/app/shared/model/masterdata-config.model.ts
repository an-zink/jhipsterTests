export interface IMasterdataConfig {
  id?: string;
  name?: string;
  path?: string;
  clazz?: string;
  collectionName?: string;
  environment?: string;
  contentType?: string;
  port?: string;
  url?: string;
}

export class MasterdataConfig implements IMasterdataConfig {
  constructor(
    public id?: string,
    public name?: string,
    public path?: string,
    public clazz?: string,
    public collectionName?: string,
    public environment?: string,
    public contentType?: string,
    public port?: string,
    public url?: string
  ) {}
}
