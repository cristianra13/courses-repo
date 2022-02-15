import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() product: Product;
  @Output() productClick: EventEmitter<any> = new EventEmitter();

  today = new Date();

  constructor() { }


  ngOnInit(): void {
  }

  addCart() {
    console.log('Se agrega al carrito');
    this.productClick.emit(this.product.id);
  }

}
