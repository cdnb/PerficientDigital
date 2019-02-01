package com.ace.emailGuides;

import com.ace.NavigationEntity;
import com.adobe.acs.commons.email.EmailService;
import com.google.gson.Gson;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.jcr.JsonItemWriter;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.*;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Component(service=Runnable.class)
@Designate(ocd = EmailSchedulerConf.class)
public class EmailScheduler implements Runnable {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private Scheduler scheduler;

    @Reference
    private EmailService emailService;

    @Reference
    private Repository repository;
    
    @Reference
    private ResourceResolverFactory resourceResolverFactory;
    
//   @SlingObject
//   private ResourceResolver resourceResolver;
   
   

    private int schedulerID;
    private int guidesNumber;
    private String path;

    @Activate
    protected void activate(EmailSchedulerConf conf) {
    	removeScheduler();
    	logger.error("Active email schdular");
        schedulerID = conf.schedulerName().hashCode();
        guidesNumber = conf.guidesNumber();
        path = conf.path();
//        addScheduler(conf);
    }

    @Modified
    protected void modified(EmailSchedulerConf conf) {
        removeScheduler();
        schedulerID = conf.schedulerName().hashCode(); // update schedulerID
//        addScheduler(conf);
    }

    @Deactivate
    protected void deactivate(EmailSchedulerConf conf) {
        removeScheduler();
    }

    /**
     * Remove a scheduler based on the scheduler ID
     */
    private void removeScheduler() {
        logger.debug("Removing Scheduler Job '{}'", schedulerID);
        scheduler.unschedule(String.valueOf(schedulerID));
    }
    /**
     * Add a scheduler based on the scheduler ID
     */
//    private void addScheduler(EmailSchedulerConf conf) {
//    	logger.info("hhh");
//        if (conf.serviceEnabled()) {
//        	logger.info("ooo");
//            ScheduleOptions sopts = scheduler.EXPR(conf.scheduler_expression());
//            scheduler.schedule(this, sopts);
//        	scheduler.NOW();
//            logger.debug("Scheduler added succesfully");
//        } else {
//            removeScheduler();
//            logger.debug("EmailScheduler is Disabled, no scheduler job created");
//        }
//    }

    @Override
    public void run() {
        try {
            sendGuides();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        //logger.error("########log for "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        catch (LoginException e) {
			e.printStackTrace();
		}
    }

    public void sendGuides() throws RepositoryException, LoginException {
        
        //GET EMAIL PARAM 	
        String[] recipients = getRecipients();
        logger.info("1");
        String html = getGuides();
        logger.info("2");

        //SEND EMAIL
        String templatePath = "/etc/notification/email/html/sky/bn.html";
        Map<String, String> emailParams = new HashMap<>();
        emailParams.put("guides", html);
        try {
            List<String> failureList = emailService.sendEmail(templatePath, emailParams, recipients);
            if (!failureList.isEmpty()) {
                logger.error("######GUIDES SEND FAILED");
            } else {
                logger.error("######GUIDES SEND SUCCESSFULLY");
            }
        } catch (Exception e) {
            logger.error("FAIL TO SEND EMAIL:"+e);
        }
    }

    public String[] getRecipients() throws RepositoryException, LoginException {
    	String[] recipients = null;
    	 Map<String, Object> param = new HashMap<String, Object>();
         param.put(ResourceResolverFactory.SUBSERVICE,"datawrite");
    	ResourceResolver resourceResolver = null;
        try {
            if(resourceResolverFactory == null) {
                logger.info("***********************resourceResolverFactory is null******************************");
            }
            else {
                resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
                logger.info("**********************resource resolver id****************"+resourceResolver.getUserID());
                logger.info(path);
                Resource resource = resourceResolver.getResource("/content/t00-homepage/jcr:content/footer/contactform");
                ValueMap readMap = resource.getValueMap();
                
                Iterator<Resource> childrenResource = resource.getChildren().iterator();
                List<String> recipient = new ArrayList<String>();
        			while(childrenResource.hasNext()) {
        				Resource cR = childrenResource.next();
        				readMap = cR.getValueMap();
        				recipient.add(readMap.get("Work Email", ""));
        			}
        			String[] strings = new String[recipient.size()];
        			
        			recipients = recipient.toArray(strings);
        			logger.info(recipient.toString());
            }
        }
        catch (LoginException e) {
            logger.error("LoginException",e);
        } finally{
            if(resourceResolver != null && resourceResolver.isLive()){
                resourceResolver.close();
            }
        }
     
        return recipients;

    }
 
    public String getGuides() throws RepositoryException, LoginException {
    	StringBuilder html = new StringBuilder("");
        int i = 0;
    	logger.info("3");
    	Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE,"datawrite");
        ResourceResolver resourceResolver = null;
        resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
    	Resource resource = resourceResolver.getResource(path);
        Iterator<Resource> childrenResource = resource.getChildren().iterator();
        
		while(childrenResource.hasNext()&&i<guidesNumber) {
			logger.info("4");
			Resource cR = childrenResource.next();
			if(cR.getName().equalsIgnoreCase("Jcr:content")) continue;
			if(!cR.getChild("jcr:content").getValueMap().get("sling:resourceType","").equals("/apps/PeficientDigit/components/structure/t03GuideDetailPage")) continue;
            logger.info("5");
            String title = cR.getChild("jcr:content").getValueMap().get("jcr:title","");
            logger.info("7");

            logger.info("__________________"+title);

            String url = "http://localhost:4502/content/PeficientDigit/"+cR.getName();

            html.append("<a href='" + url + "'>" + title + "</a><br/>");
            logger.info("++++++++"+url);
            i++;
            }		
        
        return html.toString();
    }
}
