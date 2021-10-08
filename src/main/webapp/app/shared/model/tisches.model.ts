export interface ITisches {
  id?: string;
  name?: string;
  tischPlatte?: string;
  tischBein?: string;
}

export class Tisches implements ITisches {
  constructor(public id?: string, public name?: string, public tischPlatte?: string, public tischBein?: string) {}
}
