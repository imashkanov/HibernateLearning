package org.jpwh.model.advanced;

import java.util.Objects;

public abstract class ZipCode {
	protected String value;

	public ZipCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ZipCode zipCode = (ZipCode) o;
		return Objects.equals(value, zipCode.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
