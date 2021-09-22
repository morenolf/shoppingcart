export class ShoppingCartKey {
    productId: number;
    shoppingCartId: number;

    constructor(productId : number, shoppingCartId : number){
        this.productId = productId;
        this.shoppingCartId = shoppingCartId;
    }
}