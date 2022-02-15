import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GreenComponent } from './green.component';



@NgModule({
    declarations: [GreenComponent],
    exports: [
        GreenComponent
    ],
    imports: [
        CommonModule
    ]
})
export class GreenModule { }
