package com.taiji.dianthus.config;


import com.taiji.dianthus.sso.SsoService;
import com.taiji.dianthus.sso.SsoServiceImpl;
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
        String srcSysIp = "192.168.211.1";
        //线上IP
//        String srcSysIp = "192.142.24.103";
        String srcSsoPort = "8089";
        String ssoAddr = "http://" + srcSysIp + ":" + srcSsoPort + "/SsoWebservice";
        Endpoint endpoint =  Endpoint.publish(ssoAddr, getSsoServiceImp());
        System.out.println("发布单点登录webservice成功!service-addr: " + ssoAddr + "?wsdl");
        return endpoint;
    }



}
