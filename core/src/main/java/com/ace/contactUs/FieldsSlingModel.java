package com.ace.contactUs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
 


@Model(adaptables = Resource.class)
public class FieldsSlingModel {
	   private final Logger LOG = LoggerFactory.getLogger(FieldsSlingModel.class);
 
	   @Inject
	   @Optional
	   private String[] fields;
	  
	   private List<Fields> list;
	 
	   @PostConstruct 
	   protected void init() {
	       if (fields != null) {
	           this.list = new ArrayList<>();
	           for (String linkJsonString : fields) {
	        	   Gson gson = new Gson();
	        	   Fields cate = gson.fromJson(linkJsonString,Fields.class);        	   
	        	   if("ture".equals(cate.getCheckbox())) {
	        		   cate.setRequired("required");
	        		   cate.setName("field");
	        		   LOG.info(cate.getName()+cate.getRequired());
	        	   }
	                list.add(cate);
	                
	              }
	          }
	       }
	    
	    public List<Fields> getList() {
	    	LOG.info("size ++++++++++"+list.size());
	        return list;
	    }
		
	    
}
