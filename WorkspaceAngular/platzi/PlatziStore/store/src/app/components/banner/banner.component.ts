import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {

  images: string[] = [
    'assets/images/banner1.jpg',
    'assets/images/banner2.png',
    'assets/images/banner3.jpg',
  ];

  constructor() { }

  ngOnInit(): void {
  }



}
