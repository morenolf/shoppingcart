import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCart } from '../shoppingCart';
import { ShoppingCartKey } from '../shoppingCartKey';

@Component({
  selector: 'app-remove-product-to-shopping-cart',
  templateUrl: './remove-product-to-shopping-cart.component.html',
  styleUrls: ['./remove-product-to-shopping-cart.component.css']
})
export class RemoveProductToShoppingCartComponent implements OnInit {

  shoppingCart: Observable<ShoppingCart[]>;
  shoppingCartKey!: ShoppingCartKey;
  
  constructor(private shoppingCartService: ShoppingCartService, private router: Router) { }

  ngOnInit(): void {
  }

  deleteProduct(productId : number, shoppingCartId : number){
    this.shoppingCartKey = new ShoppingCartKey(productId, shoppingCartId);
    this.shoppingCart = this.shoppingCartService.removeProductToShoppingCart(this.shoppingCartKey);
  }

}
