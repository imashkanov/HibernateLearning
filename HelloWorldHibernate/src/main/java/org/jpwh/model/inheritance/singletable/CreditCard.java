package org.jpwh.model.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {
	@NotNull //игнорирует Hibernate при таком типе наследования
	protected String cardNumber;
}
