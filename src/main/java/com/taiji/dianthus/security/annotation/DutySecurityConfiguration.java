package com.taiji.dianthus.security.annotation;

import com.taiji.dianthus.security.DianthusSecurityMetadataSource;
import com.taiji.dianthus.security.DianthusUserDetailServiceImpl;
import com.taiji.dianthus.service.sys.MenuService;
import com.taiji.dianthus.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName PmSecurityConfiguration
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
public class DianthusSecurityConfiguration {

        @Autowired
        private MenuService menuService;

        @Autowired
        private UserService userService;



        @Bean
        public DianthusUserDetailServiceImpl pmUserDetailServiceImpl() {
            DianthusUserDetailServiceImpl result = new DianthusUserDetailServiceImpl();
            result.setUserService(userService);
            return result;
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Bean
        public DianthusSecurityMetadataSource pmSecurityMetadataSource() {
            DianthusSecurityMetadataSource result = new DianthusSecurityMetadataSource();
            result.setMenuService(menuService);
            result.setUserService(userService);
            return result;
        }
}
