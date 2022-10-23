# 프로젝트 개요

### 1. 구조

* maven multi-module로 구성
* 테스트상 같은 db 참조하도록 구성.
* 

ORDER APPLICATION :

### 2. 요청사항

> Order Application

1. 주문 등록(Create)료
   * 구현 완료
2. 주문 상세 조회(Read)
   * 구현 완료
3. 주문 리스트 전체 조회(Read)
   * 구현 완료
4. 주문 리스트 페이징 조회(Read + Pagination))
   * 구현 완료
5. 주문 수정 (Update)
   * 구현 완료
6. 주문 삭제 (Delete)
   * 구현 완료
7. 구체적인 주문 구조? <- 잘 이해안감. 확인필요.
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
   * 구현 완료
7. 상품 삭제 (Delete)
   * 구현 삭제

### 3. 일정뷰

* 10.21 : 기본틀 구성.
* 10.22 : 어플리케이션간 통신 구성.
* 10.23 : 기본 기능완료. 시간이 남으면 배포 테스트.
* 10.24 : 오류 수정 및 기능 보완. 리뷰 내용 및 문서 정리. TODO의 exception 처리.
* 10.25 : 리뷰 내용 마지막 점검 및 테스트.
* 10.26 : 테스트 및 리.

### ?. 추가
* RestTemplate의 exchange와 (get|post)ForObject의 차이 및 사용하는 시점
* swagger에 관한 정보.
* RestTempate 리스트 파라미터로 전달안됨.
* order id가 max값이라 이전 max값을 알아야지만 다음 상품 등록가능. id값을 날짜로 하는것도 고려.
* RestTemplate의 getForObject로 list parameter 깔끔하게 넘겨보기(헤더 설정해보기).
* GET과 POST의 좀더 명확한 사용구분