import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCartKey } from '../shoppingCartKey';

@Component({
  selector: 'app-add-product-to-shopping-cart',
  templateUrl: './add-product-to-shopping-cart.component.html',
  styleUrls: ['./add-product-to-shopping-cart.component.css']
})
export class AddProductToShoppingCartComponent implements OnInit {

  shoppingCartKey!: ShoppingCartKey;
  
  constructor(private shoppingCartService: ShoppingCartService, private router: Router) { }

  ngOnInit(): void {
  }

  addProduct(productId : number, shoppingCartId : number){
    this.shoppingCartKey = new ShoppingCartKey(productId, shoppingCartId);
    return this.shoppingCartService.addProductToShoppingCart(this.shoppingCartKey);
  }

}
