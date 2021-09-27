import { ShoppingCartProduct } from "./shoppingCartProduct";

export class ShoppingCart {
    shoppingCartId!: number;
    shoppingCartProduct!: ShoppingCartProduct[];
    total!: Number;
}