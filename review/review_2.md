> @Autowired
>> 개요
>* Autowired의 핵심적인역할은 AutowiredAnnotationBeanPostProcessor에서 실행
>* AutowiredAnnotationBeanPostProcessor는 SmartInstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor, PriorityOrdered, BeanFactoryAware의 구현체이다.
>* SmartInstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor는 bean의 초기화 메소드(@PostConstructor) 전후에서 특정 기능을 구현가능한 BeanPostProcessor을 상속받고있다.
>* PriorityOrdered는 객체의 순서에 관여하는 인터페이스이다.
>* BeanFactoryAware 는 bean 객체에 beanFactory(bean을 관리)객체요를 전달하는 인터페이스이다.
>> 작동원리
> * bean의 생명주기에 따라 bean이 생성되고, 후처리과정중 작동.
> * 자바 빈이 생성되고, @PostConstruct 호출 이전의 단계에서 진행된다
> * 먼저 생성된 빈의 메타데이터에서 autowired 어노테이션이 붙은 정보를 확인한다.
> * 얻은 정보를 AutowiredFieldElement로 캐스팅하고, inject 메소드를 호출한다.
> <pre>
>   ReflectionUtils.makeAccessible(field);
>   field.set(bean, value);
> </pre>
> * ReflectionUtils.makeAccessible으로 필드값을 수정가능하게 하고, autowired된 필드값에 해당 빈을 주입해준다.


> Reference
* https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-autowire
* https://beststar-1.tistory.com/40