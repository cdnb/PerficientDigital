package com.ace;

import java.util.ArrayList;
import java.util.List;

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
public class IconSlingModel {
	private final Logger LOG = LoggerFactory.getLogger(IconSlingModel.class);
	   

	   @ValueMapValue
	   private String[] icons;
	   
//	   private String text="12345";
//
//	   public String getText() {
//		   LOG.info("???????");
//		return text;
//	   }

	   private List<IconEntity> list;

	   @PostConstruct 
	   public void init() {
		   if (icons != null) {
	           this.list = new ArrayList<>();
	           for (String linkJsonString : icons) {
	             Gson gson = new Gson();
	             IconNameEntity ca = gson.fromJson(linkJsonString, IconNameEntity.class);
	             LOG.info(ca.getIconName()+"---------");
	             String[] a = ca.getIconName().split("\\|");
	             String iClass=a[1];
	             String iLink=a[2];
//	             switch(ca.getIconName()) {
//	             case "blogs":iClass="fa fa-rss" ;iLink="https://blogs.perficient.com/";
//            	 break;
//	             case "Twitter":iClass="fa fa-youtube" ;iLink="http://www.twitter.com/perficient";
//	            	 break;
//	             case "Facebook":iClass="fa fa-facebook-square" ;iLink="http://www.facebook.com/Perficient";
//	            	 break;
//	             case "LinkedIn":iClass="fa fa-linkedin-square" ;iLink="http://linkedin.com/company/perficient";
//	            	 break;
//	             case "Email":iClass="fa fa-youtube" ;iLink="https://www.youtube.com/user/Perficient";
//	            	 break;
//	             default:
//	            	  break; 
//	             }
	             IconEntity b = new IconEntity(iClass,iLink);
	             LOG.info(iLink+iClass);
	             list.add(b);
	           }
	          
	       } 
	    }

	    public List<IconEntity> getList() {
	    	LOG.info("size ++++++++++"+list.size());
	        return list;
	    }

		
}
