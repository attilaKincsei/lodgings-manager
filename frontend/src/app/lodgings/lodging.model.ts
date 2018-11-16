import {LodgingsType} from './lodgingsType.enum';
import {Todo} from '../todos/Todo.model';
import {User} from '../users/user.model';
import {LodgingsBuilder} from './lodgings.builder';

export class Lodging {
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


  constructor(lodgingsBuilder: LodgingsBuilder) {
    this._id = lodgingsBuilder.id;
    this._name = lodgingsBuilder.name;
    this._lodgingsType = lodgingsBuilder.lodgingsType;
    this._country = lodgingsBuilder.country;
    this._city = lodgingsBuilder.city;
    this._zipCode = lodgingsBuilder.zipCode;
    this._address = lodgingsBuilder.address;
    this._pricePerDay = lodgingsBuilder.pricePerDay;
    this._electricityBill = lodgingsBuilder.electricityBill;
    this._gasBill = lodgingsBuilder.gasBill;
    this._telecommunicationBill = lodgingsBuilder.telecommunicationBill;
    this._cleaningCost = lodgingsBuilder.cleaningCost;
    this._todos = lodgingsBuilder.todos;
    this._landLord = lodgingsBuilder.landLord;
    this._propertyManager = lodgingsBuilder.propertyManager;
  }


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

  get lodgingsType(): LodgingsType {
    return this._lodgingsType;
  }

  set lodgingsType(value: LodgingsType) {
    this._lodgingsType = value;
  }

  get country(): string {
    return this._country;
  }

  set country(value: string) {
    this._country = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get zipCode(): string {
    return this._zipCode;
  }

  set zipCode(value: string) {
    this._zipCode = value;
  }

  get address(): string {
    return this._address;
  }

  set address(value: string) {
    this._address = value;
  }

  get pricePerDay(): number {
    return this._pricePerDay;
  }

  set pricePerDay(value: number) {
    this._pricePerDay = value;
  }

  get electricityBill(): number {
    return this._electricityBill;
  }

  set electricityBill(value: number) {
    this._electricityBill = value;
  }

  get gasBill(): number {
    return this._gasBill;
  }

  set gasBill(value: number) {
    this._gasBill = value;
  }

  get telecommunicationBill(): number {
    return this._telecommunicationBill;
  }

  set telecommunicationBill(value: number) {
    this._telecommunicationBill = value;
  }

  get cleaningCost(): number {
    return this._cleaningCost;
  }

  set cleaningCost(value: number) {
    this._cleaningCost = value;
  }

  get todos(): Todo[] {
    return this._todos;
  }

  set todos(value: Todo[]) {
    this._todos = value;
  }

  get landLord(): User {
    return this._landLord;
  }

  set landLord(value: User) {
    this._landLord = value;
  }

  get propertyManager(): User {
    return this._propertyManager;
  }

  set propertyManager(value: User) {
    this._propertyManager = value;
  }
}
