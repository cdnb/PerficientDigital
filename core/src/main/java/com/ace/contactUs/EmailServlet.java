package com.ace.contactUs;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.jcr.JsonJcrNode;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.ace.FeedService;
import com.ace.NavigationEntity;
import com.adobe.acs.commons.email.EmailService;
import com.google.gson.Gson;

import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component(service=Servlet.class,
        property={
                Constants.SERVICE_DESCRIPTION + "=Email Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + "/bin/emailServlet"
        })
public class EmailServlet extends SlingAllMethodsServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(EmailServlet.class);

    @Reference
    EmailService emailService;

    @Reference
    private Repository repository;
    
    @Reference
	FeedService2 feedService;
    
    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String workEmail = request.getParameter("workEmail");
        String phone = request.getParameter("phone");
        String company = request.getParameter("company");
        String country = request.getParameter("country");
//        String canHelp = request.getParameter("canHelp");
        String subscribe = request.getParameter("subscribe");
        
        String message = request.getParameter("message");
        
        log.info(message+"++++++++++++++++++++++++");
//        Enumeration enu = request.getParameterNames();
//        while(enu.hasMoreElements()){  
//        	String paraName=(String)enu.nextElement();  
//        	log.info(paraName+": "+request.getParameter(paraName));
//            //emailParams.put("paraName",request.getParameter(paraName));
//
//        	}  

        String templatePath = "/etc/notification/email/html/sky/en.txt";

        Map<String, String> emailParams = new HashMap<>();
        emailParams.put("time",timeStamp);

        emailParams.put("firstName",firstName);
        emailParams.put("lastName",lastName);
        emailParams.put("workEmail",workEmail);
        emailParams.put("phone",phone);
        emailParams.put("company",company);
        emailParams.put("country",country);
//        emailParams.put("canHelp",canHelp);
        emailParams.put("subscribe",subscribe);
        emailParams.put("message",message);
        String[] recipients=feedService.getFeedUrl();
//        String recipient = "953792128@qq.com";
       // for (String reci : recipients) {
        	//emailParams.put("recipients", recipients);
           // log.info(reci+"+++++++++++++++");
            try {

                List<String> failureList = emailService.sendEmail(templatePath, emailParams, recipients);
                if (!failureList.isEmpty()) {
                    response.setStatus(500);
                } else {
                    storeData(firstName, lastName, workEmail,timeStamp);
                }

            } catch (Exception e) {
                log.error("FAIL TO SEND EMAIL:"+e);
            }
		//}
        
    }

    public void storeData(String firstName, String lastName, String workEmail,String timeStamp) throws Exception {

    	String info = "firstName: " + firstName + ", lastName: " + lastName + ", workEmail: " + workEmail;
        ContactUser contactUser = new ContactUser(firstName, lastName, workEmail);
        log.info(contactUser.toString());
        
        final Credentials creds = new SimpleCredentials("admin", "admin".toCharArray());
        Session session = repository.login(creds);
        Node node = session.getRootNode();
        
        Node myNode = node.getNode("content").getNode("t00-homepage");
        Node homeNode = myNode.getNode("jcr:content").getNode("footer");
        
        
        Node contactNode = homeNode.getNode("contactform");
        
        if (contactNode == null) {
            String[] initValue = {};
            contactNode = homeNode.addNode("contactform");
            contactNode.setProperty("contactUsers", initValue);
        }
        
        Node customer = contactNode.addNode("customer_"+"user"+firstName+"time"+timeStamp,"nt:unstructured"); 
        
        customer.setProperty("First Name", firstName);
        customer.setProperty("Last Name", lastName);
        customer.setProperty("Work Email", workEmail);
        customer.setPrimaryType("nt:unstructured");

        session.save();
        session.logout();
//        Property contactUsers = contactNode.getProperty("contactUsers");
//
//        Value[] values = contactUsers.getValues();
//
//        List<String> list = new ArrayList<>();
//        for (Value v : values) {
//            list.add(v.getString());
//        }
//        Gson gson = new Gson();
//        ContactUser ca = gson.fromJson(linkJsonString, ContactUser.class);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("firstName", firstName);
//        jsonObject.put("lastName", lastName);
//        jsonObject.put("workEmail", workEmail);
//
//        log.info("#######JSON CONtact String " + jsonObject.toString());
//        list.add(jsonObject.toString());
        
//        list.add(contactUser.toString());
        
        log.info("#######JSON CONtact String " + contactUser.toString());

//        String[] newValues = list.toArray(new String[1]);
//
//        contactNode.setPrimaryType("nt:unstructured");
//        contactNode.setProperty("contactUsers", newValues);
//        custId++;
//        session.save();
//        session.logout();

    }

}