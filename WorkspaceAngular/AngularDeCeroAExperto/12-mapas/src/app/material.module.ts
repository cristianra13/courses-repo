import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

// Angular material
import { MatSliderModule } from '@angular/material/slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatSliderModule,
    MatProgressBarModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule
  ],
  // se exportan para que puedan ser usados en todo el proyecto
  exports: [
    MatToolbarModule,
    MatButtonModule,
    MatProgressBarModule,
    MatCardModule,
    MatSnackBarModule,
    MatDialogModule,
    MatInputModule
  ]
})
export class MaterialModule { }
