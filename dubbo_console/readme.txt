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