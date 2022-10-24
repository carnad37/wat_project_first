package com.hhs.order.except;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 에러요청 처리.
 */
@RestControllerAdvice
@Log4j2
public class OrderAdviceController {

    /**
     * Custom Exception 인 ServiceMessageException 핸들링.
     * @param e
     * @return
     */
    @ExceptionHandler({ServiceMessageException.class})
    public ResponseEntity<Map<String, String>> serviceMessage(ServiceMessageException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(returnMessage(e.getMessage()));
    }

    /**
     * Http method 불일치 오류 핸들링.
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, String> unsupportedType(HttpRequestMethodNotSupportedException e) {
        return returnMessage("잘못된 데이터 형식입니다.");
    }

    /**
     * HttpMessageNotReadableException 핸들링.
     * json 파싱 후 맵핑 등에서 실패시 발생.
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> unsupportedType(HttpMessageNotReadableException e) {
        return returnMessage("데이터 파싱에 실패하였습니다.");
    }

    /**
     * ContentType Exception 핸들링.
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Map<String, String> unsupportedType(HttpMediaTypeNotSupportedException e) {
        return returnMessage("ContentType은 json형식만 허용됩니다.");
    }

    /**
     * 모든 Exception 핸들링.
     * @param expt
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> commonServerError(Exception expt) {
        //기본적으로 ExceptionHandlerExceptionResolver에서 에러를 로그에 남김.
        //log.error("request fail :: " + e.getClass().getName() + " :: " + e.getMessage());
        return returnMessage("요청 처리에 실패했습니다");
    }

    /**
     * 단순 메세지 리턴 함수
     * @param message
     * @return
     */
    private Map<String, String> returnMessage(String message) {
        return returnMessage(message, null);
    }

    /**
     * 단순 메세지 리턴 함수
     * @param message
     * @return
     */
    private Map<String, String> returnMessage(String message, Map<String, String> addParam) {
        Map<String, String> map = new HashMap<>();
        if (addParam != null) map.putAll(addParam);
        map.put("message", message);
        return map;
    }

}
