package org.jpwh.converter;

import org.jpwh.model.advanced.GermanZipCode;
import org.jpwh.model.advanced.SwissZipCode;
import org.jpwh.model.advanced.ZipCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ZipCodeConverter implements AttributeConverter<ZipCode, String> {
	@Override
	public String convertToDatabaseColumn(ZipCode zipCode) {
		return zipCode.getValue();
	}

	@Override
	public ZipCode convertToEntityAttribute(String s) {
		if (s.length() == 5) {
			return new GermanZipCode(s);
		} else if (s.length() == 4) {
			return new SwissZipCode(s);
		}
		throw new IllegalArgumentException("Unsopported zipcode");
	}
}
