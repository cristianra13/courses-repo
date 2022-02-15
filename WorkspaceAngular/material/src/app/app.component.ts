import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  // templateUrl: './app.component.html',
  template: '<p>{{title}}</p> <app-table></app-table>',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Welcome 🌎 from 🚀';

  value = 0;
  isProgressVisible = true;

  constructor(private snackBar: MatSnackBar) {
  }

  ngOnInit(): void{
    this.increaseProgressBar();
  }

  private increaseProgressBar(){
    const interval = setInterval(() => {
      this.value++;
      console.log('Progress increase', this.value);
      if (this.value >= 100){
        clearInterval(interval);
      }
    }, 25); // se ejecuta cada 25 milisegundos
  }

  openSnackBar() {
    const snackBarRef =  this.snackBar.open('Hola desde snackbar + angular', 'DESHACER', {duration: 2000});
    // snackBarRef.afterDismissed().subscribe(() => console.log('Se ha cerrado'));

    snackBarRef.onAction().subscribe(() => {
      console.log('Esto se ejecuta al deshacer o al hacer clic en deshacer');
    });
  }

}
