# dianthus
- 基于Springboot+Spring Security+ JPA的整合的简单开发框架，前台用的html+themlef标签，仅适合学习交流 

### 系统说明
- 数据库相关配置在 application-dev.properties文件中，application.roperties的spring.profiles.active可以选择dev/prod/test不同的配置文件
- 系统访问地址：localhost:7777/dianthus  登录名/密码 admin/admin  新建用户密码为123456
- 整合swagger接口,所有@RestController的注解自动生成接口文档
- 项目工程包分类说明如下：  
  + api:接口包,系统对外提供的接口和系统页面内部调用的ajax  
  + common:通用类包  
  + config:系统配置相关包  
  + controller 控制层  
  + dao 数据访问层  
  + domain 数据库实体层  
  + dto 封装的实体层  
  + security  Spring Security 相关
  + service 服务层  
  + util 通用方法包  

### 待更新  
- 现在的系统UI是之前项目扒下来的，有时间给它换一换。  
- 系统日志和接口统计功能   
  
