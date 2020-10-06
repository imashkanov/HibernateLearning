package org.jpwh.model.inheritance.embeddable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AttributeOverrides({
		@AttributeOverride(name = "name", column = @Column(name = "DIMENSIONS_NAME")),
		@AttributeOverride(name = "symbol", column = @Column(name = "DIMENSIONS_SYMBOL"))
})
public class Dimensions extends Measurement {
	//какие-то поля
}
