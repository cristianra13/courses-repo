import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-green',
  templateUrl: './green.component.html',
  styleUrls: ['./green.component.css']
})
export class GreenComponent implements OnInit, OnDestroy, OnChanges {

  @Input() title: string;
  @Output() clickEvent = new EventEmitter<number>();
  private clicks = 0;

  constructor() {
    console.log('%c_[constructor]_ GreenComponent', 'color: green');
  }

  ngOnInit(): void {
    console.log('%c_[ngOnInit]_', 'color: green');
  }

  ngOnDestroy(): void {
    console.log('%c_[ngOnDestroy]_ GreenComponent', 'color: green');
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log('%c_[ngOnChanges]_ GreenComponent', 'color: green', changes);
  }

  onClick(){
    this.clicks++;
    this.clickEvent.emit(this.clicks);
  }

}
