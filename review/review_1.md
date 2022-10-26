> @SpringBootApplication이 수행하는 역할

* 포함된 annotation
    * @EnableAutoConfiguration
      * SpringBoot에서 기본적으로 사용되는 bean들을 자동으로 등록시켜주는 어노테이션
      * 해당 정보는 org/springframework/boot/spring-boot-autoconfigure/2.7.5/spring-boot-autoconfigure-2.7.5.jar!/META-INF/spring.factories 에서 확인가능
        <pre>
        # Initializers
        org.springframework.context.ApplicationContextInitializer=\
        org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\
        org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener
    
        # Application Listeners
        org.springframework.context.ApplicationListener=\
        org.springframework.boot.autoconfigure.BackgroundPreinitializer
    
        # Environment Post Processors
        org.springframework.boot.env.EnvironmentPostProcessor=\
        org.springframework.boot.autoconfigure.integration.IntegrationPropertiesEnvironmentPostProcessor
    
        # Auto Configuration Import Listeners
        org.springframework.boot.autoconfigure.AutoConfigurationImportListener=\
        org.springframework.boot.autoconfigure.condition.ConditionEvaluationReportAutoConfigurationImportListener
    
        # Auto Configuration Import Filters
        org.springframework.boot.autoconfigure.AutoConfigurationImportFilter=\
        org.springframework.boot.autoconfigure.condition.OnBeanCondition,\
        org.springframework.boot.autoconfigure.condition.OnClassCondition,\
        org.springframework.boot.autoconfigure.condition.OnWebApplicationCondition
        
        ...
        </pre>
      * 
    * @ComponentScan 
      * 소스내에있는 어노테이션을 스캔해서 bean으로 등록해주는 역할
      * 설정된 base-package를 기준으로 하위의 모든 클래스 정보를 가져와 체크한다.
      * 필터 설정이 된 클래스는 제외된다.
      * 타겟이 되는 어노테이션은 @Service, @Controller, @Repository등이 있다
    * @Configuration
      * 수동으로 bean을 등록하기위한 어노테이션
      * 설정에 관련된 bean을 등록시키기에 유용
      * 해당 클래스 내부에 @Bean으로 호출되는 메소드가 있고, 그 메소드 내부에서 @Bean 어노테이션을 보유한 메소드가 호출될경우, 단순히 메소드 호출이아닌 해당 bean을 리턴해준다.
      * bean 설정 과정에서 쓸데없는 객체의 생성이나, 사용자가 유도하지 않은 객체의 전달의 문제를 방지가능

> Reference
*  https://bamdule.tistory.com/31
*  https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html
*  https://m.blog.naver.com/sthwin/222131873998
*  https://amagrammer91.tistory.com/54