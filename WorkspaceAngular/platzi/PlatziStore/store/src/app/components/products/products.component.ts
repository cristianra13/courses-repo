import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor() { }

  public products: Product[] = [
    {
      id: 1,
      image: 'assets/images/camiseta1.jpg',
      title: 'Camiseta 1',
      price: 18000,
      description: 'Camiseta chevre'
    },
    {
      id: 2,
      image: 'assets/images/camiseta2.png',
      title: 'Camiseta 2',
      price: 15000,
      description: 'Camiseta chevre'
    },
    {
      id: 3,
      image: 'assets/images/camiseta3.png',
      title: 'Camiseta 3',
      price: 25000,
      description: 'Camiseta chevre'
    },
    {
      id: 4,
      image: 'assets/images/camiseta4.jpg',
      title: 'Camiseta 4',
      price: 18000,
      description: 'Camiseta chevre'
    },
    {
      id: 5,
      image: 'assets/images/camiseta5.png',
      title: 'Camiseta 5',
      price: 18000,
      description: 'Camiseta chevre'
    },
    {
      id: 6,
      image: 'assets/images/camiseta6.png',
      title: 'Camiseta 6',
      price: 18000,
      description: 'Camiseta chevre'
    },
    {
      id: 7,
      image: 'assets/images/camiseta7.jpg',
      title: 'Camiseta 7',
      price: 18000,
      description: 'Camiseta chevre'
    }
  ];

  ngOnInit(): void {
  }

  clickProduct(id: number) {
    console.log('Product id:', id);
  }

}
