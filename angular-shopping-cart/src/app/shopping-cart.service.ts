import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShoppingCartKey } from './shoppingCartKey';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080/shoppingcart';

  listProducts() : Observable<any> {
    return this.http.get(this.baseUrl+'/listAllProducts/');
  }

  createShoppingCart(userIdentification: number) : Observable<any> {
    return this.http.post(this.baseUrl+'/newShoppingCart/', userIdentification);
  }

  removeShoppingCart(shoppingCartId: number) : Observable<any> {
    return this.http.post(this.baseUrl+'/removeShoppingCart/', shoppingCartId);
  }

  addProductToShoppingCart(shoppingCartKey: ShoppingCartKey) : Observable<any> {
    return this.http.post(this.baseUrl+'/addProductToShoppingCart/', shoppingCartKey);
  }

  removeProductToShoppingCart(shoppingCartKey: ShoppingCartKey) : Observable<any> {
    return this.http.post(this.baseUrl+'/removeProductFromShoppingCart/', shoppingCartKey);
  }
}
