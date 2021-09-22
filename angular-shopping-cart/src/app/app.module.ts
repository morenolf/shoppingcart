import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { CreateShoppingCartComponent } from './create-shopping-cart/create-shopping-cart.component';
import { DeleteShoppingCartComponent } from './delete-shopping-cart/delete-shopping-cart.component';
import { AddProductToShoppingCartComponent } from './add-product-to-shopping-cart/add-product-to-shopping-cart.component';
import { RemoveProductToShoppingCartComponent } from './remove-product-to-shopping-cart/remove-product-to-shopping-cart.component';
import { AppRoutingModule } from './app-routing.module';
import { ProductsComponent } from './products/products.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateShoppingCartComponent,
    DeleteShoppingCartComponent,
    AddProductToShoppingCartComponent,
    RemoveProductToShoppingCartComponent, 
    FormsModule,
    HttpClientModule,
    ProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
