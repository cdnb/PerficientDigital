package com.ace.guideDetail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;


 


@Model(adaptables = { SlingHttpServletRequest.class,
        Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL) 
public class SlingModel {
	   private final Logger LOG = LoggerFactory.getLogger(SlingModel.class);
 
	   //可以把该名字的文件注成text名字的
	   @ValueMapValue(name="cq:tags")
	   @Optional
	   private String[] text;
	   
	   
	   public String[] tags() {
		   LOG.info("+++++++++++++++");
		   LOG.info(text[0]);
			return text;
		}
	    
	    
}
