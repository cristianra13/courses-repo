import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeComponent} from './home.component';
import {BlueComponent} from '../blue/blue.component';
import {GreenComponent} from '../green/green.component';
import {RedComponent} from '../red/red.component';
import {BlueModule} from '../blue/blue.module';
import {GreenModule} from '../green/green.module';
import {RedModule} from '../red/red.module';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    BlueModule,
    GreenModule,
    RedModule,
    FormsModule
  ],
  /*
    Se usa para que el componente sea publico
    esto se usa cuando se importa el componente dentro de un modulo el
    cual no es app.module ya que es el principal, posterior, el modulo se debe importar en imports del app.module
   */
  exports: [HomeComponent]
})
export class HomeModule { }
