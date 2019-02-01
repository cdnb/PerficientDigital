package com.ace;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import javax.annotation.PostConstruct;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue; 
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Model(adaptables = { SlingHttpServletRequest.class,
        Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL) 
public class NavigationSlingModel {
	   private final Logger LOG = LoggerFactory.getLogger(NavigationSlingModel.class);
	   

	   @ValueMapValue
	   private String[] navigation;
	   

	private List<NavigationEntity> list;

	   @SlingObject
	   private ResourceResolver resourceResolver;

	 
	   @PostConstruct 
	   public void init() {
		   if (navigation != null) {
	           this.list = new ArrayList<>();
	           for (String linkJsonString : navigation) {
	             Gson gson = new Gson();
	             NavigationEntity ca = gson.fromJson(linkJsonString, NavigationEntity.class);
	             LOG.info(ca.getMenuPath());
	             LOG.info(ca.getLab()+"lab");
 	             Resource childResource = resourceResolver.getResource(ca.getMenuPath());
	             Iterator<Resource> childrenResource = childResource.getChildren().iterator();
	             List<NavigationEntity> childPages = new ArrayList<NavigationEntity>();
					while(childrenResource.hasNext()) {
						Resource cR = childrenResource.next();
						if(cR.getName().equalsIgnoreCase("Jcr:content")) continue;
						
						NavigationEntity childPage = new NavigationEntity(cR.getName(),cR.getPath());
						childPages.add(childPage);
					}
					ca.setChildPage(childPages);
	             list.add(ca);
	           }
	          
	       } 
	    }

	    public List<NavigationEntity> getList() {
	    	LOG.info("size ++++++++++"+list.size());
	    	LOG.info(list.get(0).getLab());
	        return list;
	    }

		
}
