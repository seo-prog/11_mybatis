package com.ohgiraffers.section01;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    /*
    * SqlSessionFactory 는 어플리케이션이 실행되는 동안 존재한다.
    * 어플리케이션이 실행되는 동안 여러 차례 SqlSessionFactory 를 다시 빌드하지 않는 것이 가장 좋은 형태이다.
    * 어플리케이션 스코프로 관리하기 위한 가장 간단한 방법은 싱글톤 패턴을 이용하는 것이다.*/

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession(){
        if(sqlSessionFactory == null){
           String resource = "mybatis-config.xml";

            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("sqlSessionFactory 의 hashCode: " + sqlSessionFactory.hashCode());

        return sqlSessionFactory.openSession(false);

        // 그러니 session 은 한 작업이 끝나면 close 로 닫아줘야한다 ! // factory 는 닫으면 안됨 ~! 계속 쓸꺼니까

        /*
        * openSession() : sqlSession 인터페이스 타입의 객체를 반환하는 메소드
        *
        * sqlSessionFactory 는 하나 만들어 둔 후 계속 사용하지만,
        * 그에 반환되는 sqlSession 은 호출시마다 새롭게 만들어 리턴해준다..
        * 메모리 효율 측면에서 공장은 하나만 짓고 session 이라는 내용물만 매번 새롭게 만들어서 뿌려준다고 생각.
        * */

    }



}
