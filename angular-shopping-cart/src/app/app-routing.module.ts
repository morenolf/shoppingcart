import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CreateShoppingCartComponent } from './create-shopping-cart/create-shopping-cart.component';
import { DeleteShoppingCartComponent } from './delete-shopping-cart/delete-shopping-cart.component';
import { AddProductToShoppingCartComponent } from './add-product-to-shopping-cart/add-product-to-shopping-cart.component';
import { RemoveProductToShoppingCartComponent } from './remove-product-to-shopping-cart/remove-product-to-shopping-cart.component';
import { ProductsComponent } from './products/products.component';

const routes: Routes = [

  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'products', component: ProductsComponent},
  { path: 'createShoppingCart', component: CreateShoppingCartComponent },
  { path: 'deleteShoppingCart', component: DeleteShoppingCartComponent },
  { path: 'addProduct', component: AddProductToShoppingCartComponent },
  { path: 'removeProduct', component: RemoveProductToShoppingCartComponent }

];

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class AppRoutingModule { }
