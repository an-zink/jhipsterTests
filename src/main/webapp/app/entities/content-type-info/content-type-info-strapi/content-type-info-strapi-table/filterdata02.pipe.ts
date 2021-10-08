import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterdata02'
})
export class Filterdata02Pipe implements PipeTransform {
  transform(value: any, termEnvironment: any): any {
    // check ob suchVariable term undefiniert ist
    // if (termEnvironment === undefined) { return null; }
    // if (termContentTypeName === undefined) { return null; }

    // gibt geupdatetes Datenarry zurück
    return value.filter(function(contenttypeData) {
      return contenttypeData.enviroment.toLowerCase().includes(termEnvironment.toLowerCase());
      // return contenttypeData.enviroment.toLowerCase().includes(termEnvironment.toLowerCase() && termContentTypeName.toLowerCase);
    });
  }
}
