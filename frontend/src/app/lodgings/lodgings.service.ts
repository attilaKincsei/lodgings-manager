import { Injectable } from '@angular/core';
import {Lodging} from './lodging.model';
import {LodgingsBuilder} from './lodgings.builder';
import {LodgingsType} from './lodgingsType.enum';

@Injectable({
  providedIn: 'root'
})
export class LodgingsService {

  private lodgings: Lodging[] = [
    new LodgingsBuilder(1)
      .setName('sanyi')
      .setLodgingsType(LodgingsType.APARTMENT)
      .setCountry('Hungary')
      .setCity('Budapest')
      .build(),
    new LodgingsBuilder(2)
      .setName('laci')
      .setLodgingsType(LodgingsType.FAMILY_HOUSE)
      .setCountry('Hungary')
      .setCity('Szeged')
      .build(),
  ];

  constructor() { }

  getAllLodgings() {
    return this.lodgings.slice();
  }
}
