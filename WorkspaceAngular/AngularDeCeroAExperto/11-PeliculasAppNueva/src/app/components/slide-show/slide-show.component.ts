import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import Swiper from 'swiper';
import { Movie } from '../../interfaces/cartelera-response';

@Component({
  selector: 'app-slide-show',
  templateUrl: './slide-show.component.html',
  styleUrls: ['./slide-show.component.css']
})
export class SlideShowComponent implements OnInit, AfterViewInit
{

  mySwiper: Swiper;
  @Input() movies: Movie[];

  constructor() { }

  ngAfterViewInit(): void
  {
    this.mySwiper = new Swiper('.swiper-container', {

      loop: true
      });
  }

  ngOnInit(): void
  {
  }

  onSlideNext()
  {
    this.mySwiper.slideNext();
  }

  onSlidePrevious()
  {
    this.mySwiper.slidePrev();
  }



}
