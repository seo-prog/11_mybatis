package com.ohgiraffers.section01;

import static com.ohgiraffers.section01.Template.getSqlSession;

public class Application {
    public static void main(String[] args) {

        // 싱글톤 확인
        // 팩토리는 한번만 만들어두면 우리는 session 만 바꿔가면서 하기 편함.
        System.out.println("session 1번 : "+ getSqlSession().hashCode());
        System.out.println("session 2번 : "+ getSqlSession().hashCode());
    }

}
