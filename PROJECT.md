# 프로젝트 개요

### 1. 구조

* maven multi-module로 구성
* 테스트상 같은 DB 참조하도록 구성. 다만 서로 다른 테이블을 참조하며 join하거나 다른 테이블을 조회하지 않는다.
* 모든 response, request 타입은 json으로 통일.

[//]: # (> 제품 흐름)
[//]: # (* 제품 등록 : 요청&#40;Client&#41; -> 제품등록&#40;Product&#41; -> 등록된 제품정보 전달&#40;Client&#41;)
[//]: # (* 제품 수정 : 요청&#40;Client&#41; -> 제품이 있는지 확인&#40;Product&#41; -> 제품 주문중인지 확인&#40;Order&#41; -> 제품 수정&#40;Product&#41; -> 변경 관련 정보 전달&#40;Client&#41; )
[//]: # (* 제품 삭제 : 요청&#40;)

### 2. 요청사항

> Order Application

1. 주문 등록(Create)
   * 현재 등록된 제품인지 확인후, 제품정보를 가져와 처리서
   * 구현 완료
2. 주문 상세 조회(Read)   
   * 구현 완료
3. 주문 리스트 전체 조회(Read)
   * 구현 완료
4. 주문 리스트 페이징 조회(Read + Pagination))
   * 구현 완료
5. 주문 수정 (Update)
   * 기존에 등록된 데이터들은 다 삭제처리
   * 현재 등록된 제품인지 확인후, 제품정보를 가져와 처리서
   * 구현 완료
6. 주문 삭제 (Delete)
   * 삭제는 delete가 아니라 update로 이전 기록을 남긴다.
   * 구현 완료
7. 구체적인 주문 구조? <- 잘 이해안감
8. RestTemplate 사용.
   * 일단 단순사용. connection pool 고려안함.
   * 구현 완료

> Product Application

1. 상품 등록(Create)
   * 구현 완료
3. 상품 상세 조회(Read)
   * 구현 완료
3. 상품 리스트 전체 조회(Read)
   * 구현 완료
4. 상품 리스트 페이징 조회(Read + Pagination))
   * 구현 완료
5. 상품 수정 (Update)
   * 현재 주문중인 제품인 확인후 처리.
   * 구현 완료
7. 상품 삭제 (Delete)
   * 현재 주문중인 제품인 확인후 처리.
   * 구현 완료

### 3. 일정

* 10.21 : 기본틀 구성.
* 10.22 : 어플리케이션간 통신 구성.
* 10.23 : 기본 기능완료. 시간이 남으면 배포 테스트.
* 10.24 : 오류 수정 및 기능 보완. 리뷰 내용 및 문서 정리. TODO의 exception 처리.
* 10.25 : 리뷰 내용 마지막 점검 및 테스트.
* 10.26 : 테스트 및 리뷰.


### 4.오류처리

* HttpMessageNotReadableException : Controller 데이터 맵핑 실패 (body -> json)
* HttpRequestMethodNotSupportedException : Method 가 일치하지 않을시.
* HttpMediaTypeNotSupportedException: content type이 일치하지 않을시.

### ?. 추가
* RestTemplate의 exchange와 (get|post)ForObject의 차이 및 사용하는 시점
* swagger에 관한 정보.
* RestTempate 리스트 파라미터로 전달안됨.
* order id가 max값이라 이전 max값을 알아야지만 다음 상품 등록가능. id값을 날짜로 하는것도 고려.
* RestTemplate의 getForObject로 list parameter 깔끔하게 넘겨보기(헤더 설정해보기).
* GET과 POST의 좀더 명확한 사용구분
* ControllerAdivce와 ErrorController 차이