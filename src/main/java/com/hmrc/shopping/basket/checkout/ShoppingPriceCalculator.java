package com.hmrc.shopping.basket.checkout;

import java.util.List;

import com.hmrc.shoppping.model.ItemEnum;

import rx.Observable;

public class ShoppingPriceCalculator {

	private Observable<String> basketObservable;
	private Observable<Double> totalPriceObservable;

	public ShoppingPriceCalculator(List<String> basketItems) {
		basketObservable = Observable.from(basketItems);
		totalPriceObservable = Observable.merge(getApplePrice(), getOrangePrice()).reduce(0.0, Double::sum);

	}

	public Observable<? extends Object> totalActualPrice() {
		// TODO Auto-generated method stub
		return totalPriceObservable;
	}

	private Observable<Double> getApplePrice() {
		return basketObservable.filter(ItemEnum.APPLE.name()::equals).map((eachOffer) -> ItemEnum.APPLE.getPrice());
	}

	private Observable<Double> getOrangePrice() {
		return basketObservable.filter(ItemEnum.ORANGE.name()::equals).map((eachOffer) -> ItemEnum.ORANGE.getPrice());
	}

}
