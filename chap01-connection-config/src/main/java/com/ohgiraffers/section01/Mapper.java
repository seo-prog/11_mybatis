package com.ohgiraffers.section01;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

// 우리가 작성해준 설정 기반으로 쿼리문 날려볼꺼임
public interface Mapper {

    @Select("SELECT CURDATE() FROM DUAL")
    // 기본 제공하는 현재 날짜를 출력해주는 쿼리문이다.
    Date selectSysDate();
}
