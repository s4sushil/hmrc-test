package com.hmrc.shopping.basket.checkout;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import rx.observables.BlockingObservable;

public class ShoppingBasketCheckoutTest {

	@Test
	public void testEmptyBasket() {

		List<String> basket = Arrays.asList();
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
		assertEquals(BlockingObservable.from(calc.totalActualPrice()).last(), Double.valueOf(0.0));
	}

}
