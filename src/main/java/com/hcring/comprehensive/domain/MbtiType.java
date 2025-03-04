package com.hcring.comprehensive.domain;

public enum MbtiType {
    ESTJ, ISTJ,
    ENTJ, ESFJ,
    ISFJ, ESTP,
    ISTP, ENTP,
    INTP, ESFP,
    ISFP, ENFJ,
    INFJ, ENFP,
    INFP, INTJ;

    /* fromString() 메서드를 추가하여, 혈액형 검증 로직을 BloodType 내부에서 처리 */
    public static MbtiType fromString(String type) {
        return switch (type.toUpperCase()) {
            case "ESTJ" -> ESTJ;
            case "ISTJ" -> ISTJ;
            case "ENTJ" -> ENTJ;
            case "INTJ" -> INTJ;
            case "ESFJ" -> ESFJ;
            case "ISFJ" -> ISFJ;
            case "ESTP" -> ESTP;
            case "ISTP" -> ISTP;
            case "ENTP" -> ENTP;
            case "INTP" -> INTP;
            case "ESFP" -> ESFP;
            case "ISFP" -> ISFP;
            case "ENFJ" -> ENFJ;
            case "INFJ" -> INFJ;
            case "ENFP" -> ENFP;
            case "INFP" -> INFP;
            default -> throw new IllegalArgumentException("Invalid MbtiType: " + type);
        };
    }
}
