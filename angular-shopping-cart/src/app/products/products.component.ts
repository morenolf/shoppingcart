import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ShoppingCart } from '../shopping-cart';
import { ShoppingCartService } from '../shopping-cart.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  shoppingCart: Observable<ShoppingCart[]>;

  constructor(private shoppingCartService: ShoppingCartService, private router: Router) { }

  ngOnInit(): void {
    this.fetchProductList();
  }

  createProductToShoppingCart(shoppingCartId : number) {
    this.router.navigate(['createShoppingCart', shoppingCartId]);
  }

  deleteShoppingCart(shoppingCartId : number) {
    this.router.navigate(['deleteShoppingCart', shoppingCartId]);
  }

  fetchProductList() {
    this.shoppingCart = this.shoppingCartService.listProducts();
  }

  addProductToShoppingCart(productId : number, shoppingCartId : number) {
    this.router.navigate(['addProduct', productId, shoppingCartId]);
  }

}
