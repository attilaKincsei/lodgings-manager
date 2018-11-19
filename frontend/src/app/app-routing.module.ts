import {LodgingsComponent} from './lodgings/lodgings.component';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {TodosComponent} from './todos/todos.component';
import {LodgingAddComponent} from './lodgings/lodging-add/lodging-add.component';
import {UserLodgingsComponent} from './users/user-lodgings/user-lodgings.component';

const appRoutes = [
  {path: '', redirectTo: '/lodgings', pathMatch: 'full'},
  {path: 'lodgings', component: LodgingsComponent},
  {path: 'todos', component: TodosComponent},
  {path: 'lodgings/:id/add', component: LodgingAddComponent},
  {path: 'users/:id/lodgings', component: UserLodgingsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
