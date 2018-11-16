import {Component, Input, OnInit} from '@angular/core';
import {Lodging} from '../lodging.model';

@Component({
  selector: 'app-lodging',
  templateUrl: './lodging.component.html',
  styleUrls: ['./lodging.component.css']
})
export class LodgingComponent implements OnInit {

  @Input() lodging: Lodging;

  constructor() { }

  ngOnInit() {
  }

}
