import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AngularFireModule } from '@angular/fire';
import { AngularFirestoreModule } from '@angular/fire/firestore';



import { AppComponent } from './app.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { GameOfTheYearComponent } from './pages/game-of-the-year/game-of-the-year.component';
import { AppRoutingModule } from './app-routing-module';
import { ComponentsModule } from './components/components.module';
import { environment } from '../environments/environment.prod';

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    GameOfTheYearComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ComponentsModule,
    HttpClientModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
