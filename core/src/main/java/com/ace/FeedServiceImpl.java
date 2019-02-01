package com.ace;

import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = FeedService.class, configurationPolicy = ConfigurationPolicy.REQUIRE,immediate=true)
@Designate(ocd = FeedConfiguration.class)
public class FeedServiceImpl implements FeedService {
	private final Logger LOG = LoggerFactory.getLogger(IconServlet.class);
	
	private FeedConfiguration config;

	@Reference
	private SlingSettingsService settings;

	@Activate
	public void activate(FeedConfiguration config) {
		this.config = config;
	}

	@Override
	public String[] getFeedUrl() {
		// TODO Auto-generated method stub
		//LOG.info("--------"+config.feedUrl());
		return config.feedUrl();
	}

}