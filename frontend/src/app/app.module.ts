import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LodgingsComponent } from './lodgings/lodgings.component';
import { LodgingComponent } from './lodgings/lodging/lodging.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UsersComponent } from './users/users.component';
import { TodosComponent } from './todos/todos.component';
import { LodgingDetailsComponent } from './lodgings/lodging-details/lodging-details.component';
import {AppRoutingModule} from './app-routing.module';
import { TodoComponent } from './todos/todo/todo.component';
import { LodgingAddComponent } from './lodgings/lodging-add/lodging-add.component';
import { UserLodgingsComponent } from './users/user-lodgings/user-lodgings.component';

@NgModule({
  declarations: [
    AppComponent,
    LodgingsComponent,
    LodgingComponent,
    HeaderComponent,
    FooterComponent,
    UsersComponent,
    TodosComponent,
    LodgingDetailsComponent,
    TodoComponent,
    LodgingAddComponent,
    UserLodgingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
