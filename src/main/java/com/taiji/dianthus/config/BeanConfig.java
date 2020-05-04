package com.taiji.dianthus.config;


import com.taiji.dianthus.sso.SsoService;
import com.taiji.dianthus.sso.SsoServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 *
 * jgj-txl com.taiji.admin.config BeanConfig.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:19:53
 *
 * desc:
 */
@Configuration
public class BeanConfig {

    @Value("${sso.url}")
    private String ssoUrl;

    @Bean(name = "beanIns")
    public BeanConfig getBeanIns() {
        BeanConfig bean = new BeanConfig();
        return bean;
    }

	@Bean(name = "SsoServiceImpl")
	public SsoService getSsoServiceImp() {
        SsoService logService = new SsoServiceImpl();
		return logService;
	}

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint =  Endpoint.publish(ssoUrl, getSsoServiceImp());
        System.out.println("发布单点登录webservice成功!service-addr: " + ssoUrl + "?wsdl");
        return endpoint;
    }

}
