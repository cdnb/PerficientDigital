package com.ace;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Demo Service")
public @interface FeedConfiguration {
	@AttributeDefinition(name = "Enter Path", description = "name|css|path")
	String[] feedUrl();
}

