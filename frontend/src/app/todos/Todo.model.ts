import {Lodging} from '../lodgings/lodging.model';
import {Status} from './Status.enum';

export class Todo{
  private _id: number;
  private _name: string;
  private _lodgings: Lodging;
  private _deadline: string;
  private _description: string;
  private _price: number;
  private status: Status = Status.NEW;


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get lodgings(): Lodging {
    return this._lodgings;
  }

  set lodgings(value: Lodging) {
    this._lodgings = value;
  }

  get deadline(): string {
    return this._deadline;
  }

  set deadline(value: string) {
    this._deadline = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }
}
