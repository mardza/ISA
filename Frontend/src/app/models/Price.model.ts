export class Price {
    id: number;
    price: number;
    discount: number;


    constructor() {
    }

    get finalPrice(): number {
        return this.price * (1 - this.discount / 100);
    }

    public static toPrice(price: any): Price {
        if(!price) {
            return undefined;
        }

        const priceToReturn: Price = new Price();
        priceToReturn.id = price.id;
        priceToReturn.price = price.price;
        priceToReturn.discount = price.discount;
        return priceToReturn;
    }
}
