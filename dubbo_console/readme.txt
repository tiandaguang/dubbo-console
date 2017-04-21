使用dubbo

这是提供者

TestRegistryService 接口 以jar包引入 使用maven方便消费者和 提供者进行引入


解决dubbo的spring2.5和 srpingMvc的jar包冲突

<dependency>  
  <groupId>com.alibaba</groupId>  
  <artifactId>dubbo</artifactId>  
  <version>${dubbo.version}</version>  
  <exclusions>  
   <exclusion>  
    <artifactId>spring</artifactId>  
    <groupId>org.springframework</groupId>  
   </exclusion>  
  </exclusions>  
</dependency>  

配置maven默认jdk
<profile>    
        <id>jdk-1.6</id>    
        <activation>    
            <activeByDefault>true</activeByDefault>    
            <jdk>1.6</jdk>    
        </activation>    
        <properties>    
            <maven.compiler.source>1.6</maven.compiler.source>    
            <maven.compiler.target>1.6</maven.compiler.target>    
            <maven.compiler.compilerVersion>1.6</maven.compiler.compilerVersion>    
        </properties>    
    </profile>



暴漏的接口在spRedis中

使用maven管理 方便 多个项目进行引用


dubbo xml报错 是缺少xsd
从github下载dubbo后 一般 xsd就放在
dubbo\dubbo-config\dubbo-config-spring\target\classes\META-INF 下面
选择windows-->preferrence-->xml->xmlcatalog-->add->catalog entry -->file system,
选择模版文件后，修改key值为“http://code.alibabatech.com/schema/dubbo/dubbo.xsd”，
然后在eclipse中文件报错处点击右键“validate”。
