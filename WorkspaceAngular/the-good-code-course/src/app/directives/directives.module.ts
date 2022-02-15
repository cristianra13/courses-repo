import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DirectivesComponent } from './directives.component';
import {FormsModule} from '@angular/forms';



@NgModule({
    declarations: [DirectivesComponent],
    exports: [DirectivesComponent],
  imports: [CommonModule, FormsModule]
})
export class DirectivesModule { }
