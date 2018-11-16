import { Component, OnInit } from '@angular/core';
import {LodgingsService} from './lodgings.service';
import {Lodging} from './lodging.model';

@Component({
  selector: 'app-lodgings',
  templateUrl: './lodgings.component.html',
  styleUrls: ['./lodgings.component.css']
})
export class LodgingsComponent implements OnInit {

  private _lodgings: Lodging[];

  constructor(private lodgingsService: LodgingsService) { }

  ngOnInit() {
    this._lodgings = this.lodgingsService.getAllLodgings();
  }


  get lodgings(): Lodging[] {
    return this._lodgings;
  }
}
