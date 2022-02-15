import { Component, Input, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-grafico-barra-horizontal',
  templateUrl: './grafico-barra-horizontal.component.html',
  styleUrls: ['./grafico-barra-horizontal.component.css']
})
export class GraficoBarraHorizontalComponent implements OnInit, OnDestroy {

  @Input() results: any[] = [];
  intervalo;

  // options
  showXAxis = true;
  showYAxis = true;
  gradient = true;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Juegos';
  showYAxisLabel = true;
  yAxisLabel = 'Votos';

  colorScheme = 'nightLights';

  constructor()
  {
    /* this.intervalo = setInterval( () => {
      const newResults = [...this.results];

      for (let i in  newResults)
    {
     newResults[i].value = Math.round(Math.random() * 500);
    }
      this.results = [...newResults];

    }, 1500); */


  }

  onSelect(event) {
    console.log(event);
  }

  ngOnInit(): void {
  }

  ngOnDestroy(){
    /* clearInterval(this.intervalo); */
  }

}
