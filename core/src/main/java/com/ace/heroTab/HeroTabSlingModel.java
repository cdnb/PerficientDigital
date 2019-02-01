package com.ace.heroTab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.acs.commons.email.EmailService;
import com.google.gson.Gson;
 


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroTabSlingModel {
	   private final Logger logger = LoggerFactory.getLogger(HeroTabSlingModel.class);
	   //slingmodel中使用未定义注解不生效
//	   @Reference
//	    private Repository repository;
//	   
//	   @Reference
//	    private ResourceResolverFactory resourceResolverFactory;
	   
	   @ChildResource
	   private List<TabSlingModel> tabs;

	   
//	   @ChildResource
//	   private Resource tab;
	   
	   private List<Tab> list;
	 
	   @PostConstruct 
	   protected void init() throws RepositoryException, LoginException {
		   this.list = new ArrayList<Tab>();
//		   logger.info(list.size()+"555555");
		   logger.info(tabs.size()+"777");
//		   logger.info(list.size()+"666666");
//		   for (Tab t : list) {
//			   logger.info("-22");
//			   logger.info(t.getButtonText());
//		}
		   
//		   this.list = new ArrayList<Tab>();
//		   logger.info("+++++++++++++++++");
//		   logger.info(tab.toString());
//		   ValueMap readMap = tab.getValueMap();           
//           Iterator<Resource> childrenResource = tab.getChildren().iterator();           
//   			while(childrenResource.hasNext()) {
//   				logger.info("--------------");
//   				Resource cR = childrenResource.next();
//   				readMap = cR.getValueMap();
//   				
//       			list.add(new Tab(readMap.get("buttonText", ""), 
//       					readMap.get("path", ""),
//       					readMap.get("strongText", ""), 
//       					readMap.get("text", ""),
//       					readMap.get("productImageRef", "")));    
//       			logger.info("1111111");
//       			logger.info(list.toString());
//     			  			
//   			}
   			
	        }
	       
	    
	    public List<TabSlingModel> getList() {
	    	logger.info(tabs.get(0).getProductImageRef());
	        return tabs;
	    }
		
//	    public ArrayList<String[]> getPic() throws RepositoryException, LoginException {
//	    	ArrayList<String[]> list2 = new ArrayList<String[]>();
//	    	
//	    	 Map<String, Object> param = new HashMap<String, Object>();
//	    	 //configure it in osgi "user map"
//	         param.put(ResourceResolverFactory.SUBSERVICE,"data");
//	    	ResourceResolver resourceResolver = null;
//	        try {
//	            if(resourceResolverFactory == null) {
//	                logger.info("***********************resourceResolverFactory is null******************************");
//	            }
//	            else {
//	                resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
//	                logger.info("**********************resource resolver id****************"+resourceResolver.getUserID());
//
//	                Resource resource = resourceResolver.getResource("/content/t01/jcr:content/content1/tab");
//	                ValueMap readMap = resource.getValueMap();
//	                
//	                Iterator<Resource> childrenResource = resource.getChildren().iterator();
//	                
//	        			while(childrenResource.hasNext()) {
//	        				Resource cR = childrenResource.next();
//	        				readMap = cR.getValueMap();
//	        				
//	        				List<String> field = new ArrayList<String>();
//	        				field.add(readMap.get("buttonText", ""));
//	        				field.add(readMap.get("path", ""));
//	        				field.add(readMap.get("strongText", ""));
//	        				field.add(readMap.get("text", ""));
//	        				field.add(readMap.get("productImageRef", ""));
//	        				String[] strings = new String[field.size()];
//		        			
//		        			String[] fields  = field.toArray(strings);
//		        			list2.add(fields);
//		        			logger.info(field.toString());
//	        			}
//	        			
//	        			
//	            }
//	        }
//	        catch (LoginException e) {
//	            logger.error("LoginException",e);
//	        } finally{
//	            if(resourceResolver != null && resourceResolver.isLive()){
//	                resourceResolver.close();
//	            }
//	        }
//	     
//	        return list2;
//
//	    }
	    
}
