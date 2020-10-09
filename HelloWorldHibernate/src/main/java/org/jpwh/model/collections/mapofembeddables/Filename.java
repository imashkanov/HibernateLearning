package org.jpwh.model.collections.mapofembeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Filename {
	@Column(nullable = false)
	protected String name;
	@Column(nullable = false)
	protected String extension;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Filename filename = (Filename) o;
		return Objects.equals(name, filename.name) &&
				Objects.equals(extension, filename.extension);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, extension);
	}
}
