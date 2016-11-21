package com.hmrc.shopping.basket.checkout;

import java.util.List;

import rx.Observable;

public class ShoppingPriceCalculator {


	private Observable<String> basketObservable;
	private Observable<Double> totalPriceObservable;
	
	public ShoppingPriceCalculator(List<String> basketItems) {
		basketObservable = Observable.from(basketItems);
	}

	public Observable<? extends Object> totalActualPrice() {
		// TODO Auto-generated method stub
		return totalPriceObservable;
	}

}
