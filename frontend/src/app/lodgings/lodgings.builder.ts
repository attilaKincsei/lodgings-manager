import {LodgingsType} from './lodgingsType.enum';
import {Todo} from '../todos/Todo.model';
import {User} from '../users/user.model';
import {Lodging} from './lodging.model';

export class LodgingsBuilder {
  private _id: number;
  private _name: string;
  private _lodgingsType: LodgingsType;
  private _country: string;
  private _city: string;
  private _zipCode: string;
  private _address: string;

  private _pricePerDay: number;
  private _electricityBill: number;
  private _gasBill: number;
  private _telecommunicationBill: number;
  private _cleaningCost: number;

  private _todos: Todo[];

  private _landLord: User;

  private _propertyManager: User;


  constructor(id: number) {
    this._id = id;
  }

  get id(): number {
    return this._id;
  }

  setId(value: number): LodgingsBuilder {
    this._id = value;
    return this;
  }

  get name(): string {
    return this._name;
  }

  setName(value: string): LodgingsBuilder {
    this._name = value;
    return this;
  }

  get lodgingsType(): LodgingsType {
    return this._lodgingsType;
  }

  setLodgingsType(value: LodgingsType): LodgingsBuilder {
    this._lodgingsType = value;
    return this;
  }

  get country(): string {
    return this._country;
  }

  setCountry(value: string): LodgingsBuilder {
    this._country = value;
    return this;
  }

  get city(): string {
    return this._city;
  }

  setCity(value: string): LodgingsBuilder {
    this._city = value;
    return this;
  }

  get zipCode(): string {
    return this._zipCode;
  }

  setZipCode(value: string): LodgingsBuilder {
    this._zipCode = value;
    return this;
  }

  get address(): string {
    return this._address;
  }

  setAddress(value: string): LodgingsBuilder {
    this._address = value;
    return this;
  }

  get pricePerDay(): number {
    return this._pricePerDay;
  }

  setPricePerDay(value: number): LodgingsBuilder {
    this._pricePerDay = value;
    return this;
  }

  get electricityBill(): number {
    return this._electricityBill;
  }

  setElectricityBill(value: number): LodgingsBuilder {
    this._electricityBill = value;
    return this;
  }

  get gasBill(): number {
    return this._gasBill;
  }

  setGasBill(value: number): LodgingsBuilder {
    this._gasBill = value;
    return this;
  }

  get telecommunicationBill(): number {
    return this._telecommunicationBill;
  }

  setTelecommunicationBill(value: number): LodgingsBuilder {
    this._telecommunicationBill = value;
    return this;
  }

  get cleaningCost(): number {
    return this._cleaningCost;
  }

  setCleaningCost(value: number): LodgingsBuilder {
    this._cleaningCost = value;
    return this;
  }

  get todos(): Todo[] {
    return this._todos;
  }

  setTodos(value: Todo[]): LodgingsBuilder {
    this._todos = value;
    return this;
  }

  get landLord(): User {
    return this._landLord;
  }

  setLandLord(value: User): LodgingsBuilder {
    this._landLord = value;
    return this;
  }

  get propertyManager(): User {
    return this._propertyManager;
  }

  setPropertyManager(value: User): LodgingsBuilder {
    this._propertyManager = value;
    return this;
  }

  build(): Lodging {
    return new Lodging(this);
  }
}
