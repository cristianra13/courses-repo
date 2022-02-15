import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RedComponent } from './red.component';



@NgModule({
  declarations: [RedComponent],
  exports: [
    RedComponent
  ],
  imports: [
    CommonModule
  ]
})
export class RedModule { }
