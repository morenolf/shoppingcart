import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShoppingCartKey } from './shoppingCartKey';
import { User } from './user';
import { Product } from './product';
import { ShoppingCart } from './shoppingCart';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  constructor(private http: HttpClient) { }
  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }  
  private baseUrl = 'http://localhost:8080/shoppingcart';

  

  listProducts() : Observable<Product> {
    return this.http.get<Product>(this.baseUrl+'/listAllProducts/');
  }

  getUsers() : Observable<User>{
    return this.http.get<User>(this.baseUrl+'/getUsers/');
  }

  validateUser(userId : number) : Observable<User> {
    return this.http.post<User>(this.baseUrl+'/validateUser/', userId);
  }

  setPromotionalDate(promotionalDate : boolean) : Observable<any> {
    console.log(new Date() + "set promotional date service " + promotionalDate);
    return this.http.post(this.baseUrl+'/setPromotionalDates/', promotionalDate);
  }

  createShoppingCart(user: User) : Observable<number> {
    return this.http.post<number>(this.baseUrl+'/newShoppingCart/', user.identification);
  }

  removeShoppingCart(shoppingCartId: number) : Observable<any> {
    return this.http.post(this.baseUrl+'/removeShoppingCart/', shoppingCartId);
  }

  addProductToShoppingCart(shoppingCartKey: ShoppingCartKey) : Observable<ShoppingCart> {
    console.log(new Date() + ": addProductToShoppingCart serv 1" +shoppingCartKey.shoppingCartId + " " + shoppingCartKey.productId );
    return this.http.post<ShoppingCart>(this.baseUrl+'/addProductToShoppingCart/', shoppingCartKey );
  }

  removeProductToShoppingCart(shoppingCartKey: ShoppingCartKey) : Observable<any> {
    console.log(new Date() + ": removeProductToShoppingCart serv 1" + shoppingCartKey.shoppingCartId + " " + shoppingCartKey.productId );
    return this.http.post(this.baseUrl+'/removeProductFromShoppingCart/', shoppingCartKey);
  }
}
