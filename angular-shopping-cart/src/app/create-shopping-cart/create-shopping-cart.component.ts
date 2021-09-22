import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCartKey } from '../shoppingCartKey';

@Component({
  selector: 'app-create-shopping-cart',
  templateUrl: './create-shopping-cart.component.html',
  styleUrls: ['./create-shopping-cart.component.css']
})
export class CreateShoppingCartComponent implements OnInit {

  constructor(private shoppingCartService: ShoppingCartService, private router: Router) { }

  ngOnInit(): void {
  }

  createShoppingCart(shoppingCartId : number){
    return this.shoppingCartService.createShoppingCart(shoppingCartId);
  }

}
