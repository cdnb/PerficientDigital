package com.ace.heroTab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.gson.Gson;

@Model(adaptables = { SlingHttpServletRequest.class,
        Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL) 
public class TabSlingModel {
	private final Logger LOG = LoggerFactory.getLogger(TabSlingModel.class);
	
	@ValueMapValue
	private String buttonText;
	@ValueMapValue
	private String strongText;
	@ValueMapValue
	private String text;
	@ValueMapValue
	private String productImageRef;
	@ValueMapValue
	private String path;
	
	public String getButtonText() {
		return buttonText;
	}
	public String getStrongText() {
		return strongText;
	}
	public String getText() {
		return text;
	}
	public String getProductImageRef() {
		return productImageRef;
	}
	public String getPath() {
		return path;
	}
	public String getUUID() {
		String uuid = "a"+buttonText+text
				+"";
		return uuid.replaceAll(" ", "");
	}
}
