package org.jpwh.model.advanced;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class MonetaryAmount implements Serializable {
	protected final BigDecimal value;

	protected final Currency currency;

	public MonetaryAmount(BigDecimal value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Currency getCurrency() {
		return currency;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MonetaryAmount that = (MonetaryAmount) o;
		return value.equals(that.value) &&
				currency.equals(that.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, currency);
	}

	@Override
	public String toString() {
		return getValue() + " " + getCurrency();
	}
}
