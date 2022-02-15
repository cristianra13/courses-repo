import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-red',
  templateUrl: './red.component.html',
  styleUrls: ['./red.component.css']
})
export class RedComponent implements OnInit, OnDestroy, OnChanges {

  @Input() title: string;
  @Output() clickEvent = new EventEmitter<number>();
  private clicks = 0;

  constructor() {
    console.log('%c_[constructor]_ RedComponent', 'color: red');
  }

  ngOnInit(): void {
    console.log('%c_[ngOnInit]_', 'color: red');
  }

  ngOnDestroy(): void {
    console.log('%c_[ngOnDestroy]_ RedComponent', 'color: red');
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log('%c_[ngOnChanges]_ RedComponent', 'color: red', changes);
  }

  onClick(){
    this.clicks++;
    this.clickEvent.emit(this.clicks);
  }

}
