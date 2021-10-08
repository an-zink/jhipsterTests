export interface IHaus {
  id?: string;
  nr?: string;
  name?: string;
}

export class Haus implements IHaus {
  constructor(public id?: string, public nr?: string, public name?: string) {}
}
