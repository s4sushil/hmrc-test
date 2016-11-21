package com.hmrc.shopping.basket.checkout;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hmrc.shoppping.model.ItemEnum;

import rx.observables.BlockingObservable;

public class ShoppingBasketCheckoutTest {

	@Test
	public void testEmptyBasket() {

		List<String> basket = Arrays.asList();
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
		assertEquals(BlockingObservable.from(calc.totalActualPrice()).last(), Double.valueOf(0.0));
	}

	@Test
	public void testActualPriceOfBasketFullOfApples() {
		List<String> basketOfApples = Arrays.asList(ItemEnum.APPLE.name(), ItemEnum.APPLE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfApples);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(1.20));
	}

	@Test
	public void testActualPriceOfBasketFullOfOranges() {
		List<String> basketOfOranges = Arrays.asList(ItemEnum.ORANGE.name(), ItemEnum.ORANGE.name(),
				ItemEnum.ORANGE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfOranges);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(0.75));
	}

	@Test
	public void testActualPriceOfBasketOfMultipleItems() {
		List<String> basket = Arrays.asList(ItemEnum.ORANGE.name(), ItemEnum.ORANGE.name(), ItemEnum.APPLE.name(),
				ItemEnum.APPLE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(1.70));
	}

	@Test
	public void testActualPriceOfBasketOfOneOrangeAndThreeApples() {
		List<String> basket = Arrays.asList(ItemEnum.ORANGE.name(), ItemEnum.APPLE.name(), ItemEnum.APPLE.name(),
				ItemEnum.APPLE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(2.05));
	}

	@Test
	public void testActualPriceOfBasketOfMultipleMixedItems() {
		List<String> basket = Arrays.asList(ItemEnum.ORANGE.name(), ItemEnum.ORANGE.name(), ItemEnum.ORANGE.name(),
				ItemEnum.ORANGE.name(), ItemEnum.APPLE.name(), ItemEnum.APPLE.name(), ItemEnum.APPLE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalActualPrice()).last(), is(2.80));
	}

	@Test
	public void testPromotionalPriceOfEmptyItemsInBasket() {
		List<String> basket = Arrays.asList();
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalPromotionalPriceObservable()).last(), is(0.0));
	}

	@Test
	public void testPromotionalPriceOfBasketFullOfApples() {
		List<String> basketOfApples = Arrays.asList(ItemEnum.APPLE.name(), ItemEnum.APPLE.name(),
				ItemEnum.APPLE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfApples);
		assertThat(BlockingObservable.from(calc.totalPromotionalPriceObservable()).last(), is(1.20));
	}

	@Test
	public void testPromotionalPriceOfBasketFullOfOranges() {
		List<String> basketOfOranges = Arrays.asList(ItemEnum.ORANGE.name(), ItemEnum.ORANGE.name(),
				ItemEnum.ORANGE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basketOfOranges);
		assertThat(BlockingObservable.from(calc.totalPromotionalPriceObservable()).last(), is(0.50));
	}

	@Test
	public void testPromotionalPriceOfBasketOfMultipleMixedItems() {
		List<String> basket = Arrays.asList(ItemEnum.ORANGE.name(), ItemEnum.ORANGE.name(), ItemEnum.ORANGE.name(),
				ItemEnum.ORANGE.name(), ItemEnum.APPLE.name(), ItemEnum.APPLE.name(), ItemEnum.APPLE.name());
		ShoppingPriceCalculator calc = new ShoppingPriceCalculator(basket);
		assertThat(BlockingObservable.from(calc.totalPromotionalPriceObservable()).last(), is(1.95));
	}

}
