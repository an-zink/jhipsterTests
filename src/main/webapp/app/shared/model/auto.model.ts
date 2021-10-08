export interface IAuto {
  id?: string;
  name?: string;
  number?: number;
  modell?: string;
}

export class Auto implements IAuto {
  constructor(public id?: string, public name?: string, public number?: number, public modell?: string) {}
}
