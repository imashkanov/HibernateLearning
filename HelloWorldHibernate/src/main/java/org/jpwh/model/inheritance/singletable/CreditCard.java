package org.jpwh.model.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {
	@NotNull //���������� Hibernate ��� ����� ���� ������������
	protected String cardNumber;
}
