import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BlueComponent } from './blue.component';



@NgModule({
    declarations: [BlueComponent],
    exports: [
        BlueComponent
    ],
    imports: [
        CommonModule
    ]
})
export class BlueModule { }
