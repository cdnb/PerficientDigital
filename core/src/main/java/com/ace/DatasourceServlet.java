package com.ace;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletException;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class DatasourceServlet extends SlingSafeMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	Resource resource;
	
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {
 
		request.setAttribute(DataSource.class.getName(), EmptyDataSource.instance());
		  
		ResourceResolver resolver = resource.getResourceResolver();
		 
		//Create an ArrayList to hold data
		List<Resource> fakeResourceList = new ArrayList<Resource>();
		 
		ValueMap vm = null; 
		 
		 
		//Add 5 values to drop down! 
		for (int i=0; i<5; i++)
		{
		 
		    //allocate memory to the Map instance
		 vm = new ValueMapDecorator(new HashMap<String, Object>());   
		 
		 
		 // Specify the value and text values
		 String Value = "value"+i ;
		 String Text = "text"+i ; 
		 
		    //populate the map
		 vm.put("value",Value);
		 vm.put("text",Text);
		 
		 fakeResourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm));
		}
		 
		 
		//Create a DataSource that is used to populate the drop-down control
		DataSource ds = new SimpleDataSource(fakeResourceList.iterator());
		request.setAttribute(DataSource.class.getName(), ds);
 
	}
}
