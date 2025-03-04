package com.example.PRM_Project.dto;

import java.util.HashMap;
import java.util.Map;

public enum SysMessageType {
    SYS_SUCCESSFULLY("0001"),
    SYS_ERROR("0002"),
    SYS_INVALID_INPUT("003"),
    SYS_INTERNAL_ERROR("004"),
    SYS_PARAM_ERROR("005"),
    SYS_PERMISSION_ERROR("006"),
    SYS_TOKEN_EXPIRED_ERROR("007"),
    SYS_TOKEN_INVALID_ERROR("008"),
    FIELD_REQUIRED_IS_NONE("M1"),
    DATA_EXISTS("M2"),
    CREATED_SUCCESS("M3"),
    DELETE_SUCCESS("M4"),
    DATA_NOT_EXISTS("M5"),
    UPDATE_SUCCESS("M6"),
    SYS_AUTH_FAILED("M7");


    private final String code;
    private static final Map<String, SysMessageType> CODE_MAP = new HashMap<>();

    static {
        for (SysMessageType type : values()) {
            CODE_MAP.put(type.code, type);
        }
    }

    SysMessageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SysMessageType fromCode(String code) {
        return CODE_MAP.getOrDefault(code, null);
    }
}
