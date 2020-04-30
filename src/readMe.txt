### 上线须知
1. 更改项目工程下 \duty\src\main\java\com\taiji\duty\config\BeanConfig.java 中的注释endpoint() 放开
    线上IP srcSysIp 属性 改成线上IP地址
2. 更改数据库配置文件prod
3. tomcat启动类 TomcatApplication.java注释放开
4. build 打包 上线
