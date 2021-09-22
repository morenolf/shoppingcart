import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCartKey } from '../shoppingCartKey';

@Component({
  selector: 'app-delete-shopping-cart',
  templateUrl: './delete-shopping-cart.component.html',
  styleUrls: ['./delete-shopping-cart.component.css']
})
export class DeleteShoppingCartComponent implements OnInit {

  constructor(private shoppingCartService: ShoppingCartService, private router: Router) { }

  ngOnInit(): void {
  }

  deleteShoppingCart(shoppingCartId : number){
    return this.shoppingCartService.removeShoppingCart(shoppingCartId);
  }

}