export class Price {
    id: number;
    price: number;
    discount: number;


    constructor() {
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
