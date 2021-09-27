import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from '../product';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCart } from '../shoppingCart';
import { ShoppingCartKey } from '../shoppingCartKey';
import { User } from '../user';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  shoppingCart: ShoppingCart = new ShoppingCart();
  products: any = [];
  user: User = new User;
  shoppingCartId!: number ;

  constructor(private shoppingCartService: ShoppingCartService, private router: Router) {   }

  ngOnInit(): void {
    console.log(new Date() + ": ngOnInit");
    
    this.user=history.state;
    this.fetchProductList();
  }

  fetchProductList() {
    return this.shoppingCartService.listProducts().subscribe((data: {}) => {
      this.products = data;
    });
  }

  deleteShoppingCart(shoppingCartId : number) {
    console.log(new Date() + ": deleteShoppingCart");
    this.shoppingCartService.removeShoppingCart(shoppingCartId)
    .subscribe(
      data => {
        console.log(data);
        this.fetchProductList();
      },
      error => console.log(error));    
  }

  addProductToShoppingCart(productId : number) {    
    console.log(new Date() + ": addProductToShoppingCart 1 :");
    if(this.user.userId == undefined){
      this.router.navigate(['userLogin']);
    }else{
      this.shoppingCartService.createShoppingCart(this.user).subscribe(data => 
        {
          console.log(new Date() + ": addProductToShoppingCart 2 :");
          this.shoppingCartId = data;
          console.log(new Date() + ": shoppingCartId add product : " + this.shoppingCartId);
          this.shoppingCartService.addProductToShoppingCart(this.generateShoppingCartKey(productId)).subscribe(( data : ShoppingCart) => {
            console.log(new Date() + ": shopping car id add product :" + data.shoppingCartId);
            this.shoppingCart = data;
            console.log(new Date() + ": shopping car id add after assign:" + this.shoppingCart.shoppingCartId);
            this.fetchProductList();
          });
          
        }
      );

    }
  }

  removeProductFromShoppingCart(productId : number) {
    console.log(new Date() + ": removeProductFromShoppingCart 1" );
    if(this.user.userId == undefined){
      this.router.navigate(['userLogin']);
    }else{
      this.shoppingCartService.createShoppingCart(this.user).subscribe(data => 
        {
          console.log(new Date() + ": removeProductFromShoppingCart 2 :");
          this.shoppingCartId = data;
          console.log(new Date() + ": shoppingCartId remove product : " + this.shoppingCartId);
          this.shoppingCartService.removeProductToShoppingCart(this.generateShoppingCartKey(productId)).subscribe(( data : ShoppingCart) => {
            console.log(new Date() + ": shopping car id remove product :" + data.shoppingCartId);
            this.shoppingCart = data;
            this.fetchProductList();
          });
        }
      );

    }
  }

  generateShoppingCartKey(productId : number){
    let shop = new ShoppingCartKey(productId,this.shoppingCartId);
    console.log(new Date() + ": creating shopping cart key :" + shop.productId +" shpp id"+ shop.shoppingCartId);    
    return shop;
  }

}
