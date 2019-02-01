package com.ace.contactUs;

import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = FeedService2.class, configurationPolicy = ConfigurationPolicy.REQUIRE,immediate=true)
@Designate(ocd = FeedConfiguration2.class)
public class FeedServiceImpl2 implements FeedService2 {
	private final Logger LOG = LoggerFactory.getLogger(FeedServiceImpl2.class);
	
	private FeedConfiguration2 config;

	@Reference
	private SlingSettingsService settings;

	@Activate
	public void activate(FeedConfiguration2 config) {
		this.config = config;
	}

	@Override
	public String[] getFeedUrl() {
		// TODO Auto-generated method stub
		//LOG.info("--------"+config.feedUrl());
		return config.feedUrl();
	}

}