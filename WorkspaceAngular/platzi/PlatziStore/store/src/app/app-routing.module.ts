import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ProductComponent } from './components/product/product.component';
import { HomeComponent } from './components/home/home.component';
import { ProductsComponent } from './components/products/products.component';
import { ContactComponent } from './components/contact/contact.component';
import { CartComponent } from './components/cart/cart.component';

const routes: Routes = [
  { path: 'home', component:  HomeComponent},
  { path: 'products', component:  ProductsComponent},
  { path: 'contact', component:  ContactComponent},
  { path: 'home', component:  HomeComponent},
  { path: 'cart', component:  CartComponent},
  { path: '**', pathMatch: 'full', redirectTo: 'home'},
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
