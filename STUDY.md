> 추가

* 데드락
    * 데드락이랑 thread safe라는 개념의 차이가 있다. 일반적인 thread safe는 여러쓰레드에서 같은 자원에 접근할때 해당값이  무결성을 유지할수 있는지의 의미.
    * 데드락의 경우 오히려 thread safe하기위해 syncronized로 동기화 할경우 생길수 있다.
    * 상호 배제, 점유 상태로 대기, 선점 불가, 순환성 대기 가 **모두** 만족할 시에만 발생.
    * 상호배제는 특정 자원 로직을 동기화하는것 (자바면 로직이나 필드값 동기화).
    * 점유 상태로 대기는 동기화된 로직을 특정 작업에 할당해준 의미.
    * 선점 불가는 타 쓰레드의 작업의 자원을 못가져오는 경우.
    * 순횐대기는 문제가되는 각 프로세스가 서로의 동기화된 자료를 요구하고 있을경우.
* 모놀리식과 MSA의 핵심적인 차이점.
* DI의 이점.
* @Retention, @Inherited, @Target, @AliasFor 에 관하여.


### ?. 추가
* RestTemplate의 exchange와 (get|post)ForObject의 차이 및 사용하는 시점
* swagger에 관한 정보.
* RestTempate 리스트 파라미터로 전달안됨.
* order id가 max값이라 이전 max값을 알아야지만 다음 상품 등록가능. id값을 날짜로 하는것도 고려.
* RestTemplate의 getForObject로 list parameter 깔끔하게 넘겨보기(헤더 설정해보기).
* GET과 POST의 좀더 명확한 사용구분
* ControllerAdivce와 ErrorController 차이