FROM tomcat:9.0-jre8

ENV RUN_USER            tomcat
ENV RUN_GROUP           tomcat

# Add a tomcat user
RUN groupadd -r ${RUN_GROUP} && useradd -g ${RUN_GROUP} -d ${CATALINA_HOME} -s /bin/bash ${RUN_USER}
RUN chown -R tomcat:tomcat $CATALINA_HOME
RUN ls -lah $CATALINA_HOME
RUN su -c 'touch $CATALINA_HOME/include/this.still.works' tomcat
RUN su -c 'touch $CATALINA_HOME/work/this.will.fail' tomcat

