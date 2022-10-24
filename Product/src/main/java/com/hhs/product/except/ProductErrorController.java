package com.hhs.product.except;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * RestControllerAdvice에서 핸들링 되지 않은 에러 처리.
 */
@RestController
@Log4j2
public class ProductErrorController implements ErrorController {

    @RequestMapping("/error")   //springboot 기본 에러페이지
    public ResponseEntity<Map<String, String>> errorProcess(HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        String message = "처리할수 없는 요청입니다";
        HttpStatus status;
        try {
            //에러코드 확인.
            status = HttpStatus.resolve((int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        } catch (Exception e) { //기타 오류에대한 일괄 서버오류 처리.
            log.warn("Error occurred in error controller :: {} :: {}", e.getClass().getName(), e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (HttpStatus.NOT_FOUND.equals(status)) {
            message = "잘못된 요청 주소입니다.";
        }

        response.put("message", message);

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

}
