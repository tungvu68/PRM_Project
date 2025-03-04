package com.example.PRM_Project.untils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestHelper {

    public static long startTimer() {
        return System.currentTimeMillis();
    }

    public static ResponseEntity<Map<String, Object>> sendResult(
            Object data, String messageCode, String message, HttpStatus status, long startTime) {

        long duration = System.currentTimeMillis() - startTime; // Tính thời gian xử lý

        Map<String, Object> response = new HashMap<>();

        // Metadata chứa thông tin request
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("processing_time", duration / 1000.0); // Chuyển ms thành giây
        metadata.put("server_time", Instant.now().toString());

        // Message object giống Postman example
        Map<String, Object> messageObject = new HashMap<>();
        messageObject.put("duration", duration);
        messageObject.put("lang_code", "en");
        messageObject.put("message", message); // Có thể null
        messageObject.put("message_code", messageCode);
        messageObject.put("show", false);
        messageObject.put("status", status.name());

        // Response chính
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("data", data);
        responseData.put("message_object", messageObject);
        responseData.put("version", "1.0.0"); // Phiên bản API (tùy chỉnh)

        // Tổng hợp response
        response.put("metadata", metadata);
        response.put("response_data", responseData);
        response.put("status", status.value());

        return new ResponseEntity<>(response, status);
    }

    // Overload cho trường hợp không truyền message
    public static ResponseEntity<Map<String, Object>> sendResult(
            Object data, String messageCode, HttpStatus status, long startTime) {
        return sendResult(data, messageCode, null, status, startTime);
    }

}
