> spring bean의 life-cycle
* bean의 life-cycle을 크게 나누면 다음과 같다
    * 생성 / 초기화
    * 사용
    * 소멸
  
  
* bean의 생성과정
  
![빈의 생성과정](https://www.concretepage.com/spring/images/spring-bean-life-cycle-tutorial.jpg)
1. 빈 객체 생성
2. 빈에 정보를 채워넣어준다.
3. DI
4. 인터페이스 함수들 호출
5. BeanPostProcessor.postProcessBeforeInitialization 호출
6. 초기화 메소드 호출
7. BeanPostProcessor.postProcessAfterInitialization 호출
  
* bean의 소멸과정
  
![빈의 생성과정](https://jstobigdata.com/wp-content/uploads/2020/01/Spring_bean_destroy-1024x536.png)

1. 스프링 컨테이너 종료
2. @PreDestroy 메소드 실행
3. 구현한 Destory method 실행
4. DisposableBean 인터페이스의 destroy()메소드 호출

> Reference
* https://jstobigdata.com/spring/spring-bean-lifecycle-callbacks
* https://www.concretepage.com/spring/spring-bean-life-cycle-tutorial