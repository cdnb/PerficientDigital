package com.ace.emailGuides;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;



@ObjectClassDefinition(name = "R6", description = "Email Scheduler Configuration")
public @interface EmailSchedulerConf {
    /**
     * schedulerName
     * @return String name
     */
    @AttributeDefinition(name = "Scheduler name", description = "Scheduler name", type = AttributeType.STRING)
    public String schedulerName() default "Email Scheduler Configuration";
    /**
     * schedulerConcurrent
     * @return schedulerConcurrent
     */
    @AttributeDefinition(name = "Concurrent", description = "Schedule task concurrently", type = AttributeType.BOOLEAN)
    boolean schedulerConcurrent() default true;
    /**
     * serviceEnabled
     * @return serviceEnabled
     */
    @AttributeDefinition(name = "Enabled", description = "Enable Scheduler", type = AttributeType.BOOLEAN)
    boolean serviceEnabled() default true;
    /**
     * schedulerExpression
     * @return schedulerExpression
     */
    @AttributeDefinition(name = "Expression", description = "Cron-job expression. Default: run every hour.", type = AttributeType.STRING)
    String scheduler_expression() default "*/30 * * * * ?";
    /**
     * schedulerExpression
     * @return schedulerExpression
     */
    @AttributeDefinition(name = "GuidesNumber", description = "How much guides would u like to send by email?", type = AttributeType.INTEGER)
    int guidesNumber() default 1;
    /**
     * path
     * @return path
     */
    @AttributeDefinition(name = "path", description = "guide path", type = AttributeType.STRING)
    String path() default "";
}
