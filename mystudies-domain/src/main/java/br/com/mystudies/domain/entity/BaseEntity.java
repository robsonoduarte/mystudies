package br.com.mystudies.domain.entity;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

@SuppressWarnings("serial") // as subclasse que devem ter o serial version
public abstract class BaseEntity implements Entity {

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}


	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this,obj);
	}


	@Override
	public String toString() {
		return reflectionToString(this, SHORT_PREFIX_STYLE);
	}

}
