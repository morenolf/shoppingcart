import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';
import { Router } from '@angular/router';
import { User } from '../user';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  user:  User = new User();
  users: any = [];
  isPromotionalDate : boolean = false;

  constructor(private shoppingCartService: ShoppingCartService, private router: Router) { }

  ngOnInit(): void {    
    this.fetchUsers();
  }
  
  fetchUsers() {
    return this.shoppingCartService.getUsers().subscribe((data: {}) => {
      this.users = data;
    });
  }

  selectUser(userId : number){
    console.log(new Date() + "selection user");
    this.shoppingCartService.validateUser(userId).subscribe((data: User) => {   
      this.user = data;
      console.log(new Date() + "selected user 1 " + this.isPromotionalDate);
      this.shoppingCartService.setPromotionalDate(this.isPromotionalDate).subscribe(() => {
        console.log(new Date() + "selected user 2 ");
        this.router.navigateByUrl('/products', { state: this.user });
      });            
    });
  }

}
