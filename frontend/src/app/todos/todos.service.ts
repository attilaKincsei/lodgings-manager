import { Injectable } from '@angular/core';
import {Todo} from './Todo.model';

@Injectable({
  providedIn: 'root'
})
export class TodosService {

  todos: Todo[] = [

  ];

  constructor() { }
}
