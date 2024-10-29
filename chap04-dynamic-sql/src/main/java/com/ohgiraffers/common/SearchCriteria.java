package com.ohgiraffers.common;


// 검색 엔진 클래스 - 검색 기준과 내용을 담을 객체
public class SearchCriteria {
    // 검색 기준을 다룬다. 즉, 카테고리 또는 이름 두개의 검색 기준을 가지는데 이를 결정.

    private String condition;
    private String value;

    public SearchCriteria() {
    }

    public SearchCriteria(String condition, String value) {
        this.condition = condition;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public static void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public static void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "condition='" + condition + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
