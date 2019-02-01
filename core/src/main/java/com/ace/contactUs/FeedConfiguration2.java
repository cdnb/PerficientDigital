package com.ace.contactUs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Email Service")
public @interface FeedConfiguration2 {
	@AttributeDefinition(name = "email", description = "name|css|path")
	String[] feedUrl();
}

