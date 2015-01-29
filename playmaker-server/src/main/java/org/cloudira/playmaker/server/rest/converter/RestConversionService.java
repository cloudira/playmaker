package org.cloudira.playmaker.server.rest.converter;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

@Component
public class RestConversionService extends GenericConversionService {

	@Autowired
	private List<Converter<?, ?>> converters;

	@PostConstruct
	public void postConstruct() {
		DefaultConversionService.addDefaultConverters(this);
		for (Converter<?, ?> converter : converters) {
			addConverter(converter);
		}
	}

	/**
	 * Useful for converting to typed Collections.
	 * 
	 * For example, a List<String> could be converted to a List<EmailAddress> by
	 * converting to a targetType built with this method. The method call to
	 * convertCollection(source, List.class, EmailAddress.class) is same as
	 * calling: convert(source, TypeDescriptor.collection(List.class,
	 * TypeDescriptor.valueOf(EmailAddress.class));
	 * 
	 * 
	 * @param source
	 *            the source object
	 * @param collectionType
	 *            the collection type, which must implement Collection.
	 * @param elementType
	 *            the collection's element type, used to convert collection
	 *            elements
	 * @return the converted value
	 * @throws ConversionException
	 *             if a conversion exception occurred
	 * @throws IllegalArgumentException
	 *             if targetType is null, or sourceType is null but source is
	 *             not null
	 */
	@SuppressWarnings("unchecked")
	public <C extends Collection<V>, V> C convertCollection(Object source, Class<C> collectionType, Class<V> elementType) {
		return (C) convert(source, TypeDescriptor.collection(collectionType, TypeDescriptor.valueOf(elementType)));
	}

}
