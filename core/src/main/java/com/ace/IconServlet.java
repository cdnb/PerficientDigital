package com.ace;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
 

//@Designate(ocd = FeedConfiguration2.class)
@Component(immediate = true, service = Servlet.class, property = { 
		 "sling.servlet.paths="+ "/bin/demoservlet", 
		 "sling.servlet.methods=" + "get" },
                 configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IconServlet extends SlingSafeMethodsServlet  {
	private final Logger LOG = LoggerFactory.getLogger(IconServlet.class);
	private static final long serialVersionUID = 1L;
//	private FeedConfiguration2 config;
	private String[] pagePath; 
	Resource resource;
	
	@Reference
	FeedService feedService;
	
//	@ObjectClassDefinition(name = "Demo Service")
//	public @interface FeedConfiguration2 {
//		@AttributeDefinition(name = "Enter Path", description = "name|css|path")
//		String[] getPath();
//	}
 
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		pagePath=feedService.getFeedUrl();
		request.setAttribute(DataSource.class.getName(), EmptyDataSource.instance());

		List<Resource> fakeResourceList = new ArrayList<Resource>();		 
		ValueMap vm = null;
		for (String page : pagePath) {
			 
			ResourceResolver resourceResolver = request.getResourceResolver();
			//resource = resourceResolver.resolve(page);
			//PrintWriter out = response.getWriter();
			//response.setContentType("text/plain");
			//out.println(resource.getPath());
			
			//LOG.info(page+"------------");
			//out.println(page+"------------");
			//out.flush();
			String emailToRecipients = "120872526@qq.com";
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.println(emailToRecipients);
			out.flush();

			 vm = new ValueMapDecorator(new HashMap<String, Object>());   		 
			 String Value = page;
			 String Text = page ; 
			 vm.put("value",Value);
			 vm.put("text",Text);
			 fakeResourceList.add(new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", vm));
			
			} 
		DataSource ds = new SimpleDataSource(fakeResourceList.iterator());
		request.setAttribute(DataSource.class.getName(), ds);
 
	}
 
//	@Activate
//	@Modified
//	protected void Activate(FeedConfiguration2 config) {
//		pagePath = config.getPath();
//	}
	

}
