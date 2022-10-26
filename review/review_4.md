> RestTemplate 동작 원리
* 사용이유 : URLConnection이나 HttpClient사용시, 커넥션과정을 간략화하더라도 통신후에 파싱시 해당 포맷에 맞는 파싱 라이브러리로 직접해주어야한다. RestTemplate는 포함하고있는 HttpMessageConverter 클래스를 이용해 컨버팅 / 맵핑작업까지 일괄적으로 처리해준다.
  
![RestTemplate 구조](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile26.uf.tistory.com%2Fimage%2F99300D335A9400A52C16C1)

* 어플리케이션이 RestTemplate를 생성한다.(ConnectConfig) - read time out 및 connection time out 시간을 설정가능
* URI, HTTP메소드 등의 헤더정보나 파라미터를 담는다(ProductService in Order, OrderService in Product)
  *  헤더정보를 직접 입력할경우 exchange를 쓰거나 param에 HttpEntity객체를 사용한다.
  <pre>
    public HttpEntityRequestCallback(@Nullable Object requestBody, @Nullable Type responseType) {
        super(responseType);
        if (requestBody instanceof HttpEntity) {
            this.requestEntity = (HttpEntity)requestBody;
        } else if (requestBody != null) {
            this.requestEntity = new HttpEntity(requestBody);
        } else {
            this.requestEntity = HttpEntity.EMPTY;
        }
    }
  </pre>
* HttpEntity객체를 요청에 맞는 포맷으로 변환한다.
* RestTemplate 는 doExecute를 호출해 ClientHttpRequest를 생성하고 요청을 보낸다.
  <pre>
  ClientHttpRequest request = this.createRequest(url, method);
  if (requestCallback != null) {
    requestCallback.doWithRequest(request);
  }
  response = request.execute();
  </pre>
* ResponseErrorHandler 로 오류를 확인하고 있다면 처리로직을 태운다.(handleResponse 메서드)
  <pre>
  protected void handleResponse(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
    ResponseErrorHandler errorHandler = this.getErrorHandler();
    boolean hasError = errorHandler.hasError(response);
    if (this.logger.isDebugEnabled()) {
        try {
            int code = response.getRawStatusCode();
            HttpStatus status = HttpStatus.resolve(code);
            this.logger.debug("Response " + (status != null ? status : code));
        } catch (IOException var8) {
        }
    }
    if (hasError) {
        errorHandler.handleError(url, method, response);
    }
  }
  </pre>
* HttpMessageConverter 를 이용해서 응답메세지를 java object(Class responseType) 로 변환한다.
  <pre>
  responseExtractor != null ? responseExtractor.extractData(response) : null;
  </pre>
* 어플리케이션에 반환된다.


> Reference
* https://sjh836.tistory.com/141
* https://github.com/spring-projects/spring-framework/blob/main/spring-web/src/main/java/org/springframework/web/client/RestTemplate.java