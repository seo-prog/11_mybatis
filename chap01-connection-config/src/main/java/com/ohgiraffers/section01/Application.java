package com.ohgiraffers.section01;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Date;

public class Application {

    // DB 정보 필드 작성
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static String URL = "jdbc:mysql://localhost:3306/menudb";

    private static String USER = "gangnam";

    private static String PASS = "gangnam";

    public static void main(String[] args) {

        /*
        * -- 환경설정을 위함 --
        *
        * - 트랜잭션 종류
        * // 얘는 이걸 쓰도록 할지 정한다는 느낌
        * jdbcTransactionFactory : 수동 커밋
        * ManagedTransactionFactory : 오토커밋
        *
        * -------------------------------------
        *
        * PooledDateSource : ConnectionPool 을 사용함. ( 미리 그 상황을 준비해둔다는 의미) // 미리 어떤 커넥션 객체를 만들어놓고 요청 시 주기.
        * UnPooledDateSource : 사용하지 않음.
        * */

        // 환경 정보 저장 객체 // 아이디, 트랙잭션 종류, 풀 사용 여부
        Environment environment = new Environment(
                "dev",
                new JdbcTransactionFactory(),
                new PooledDataSource(DRIVER, URL,USER,PASS)
        );


        // 생성한 환경 설정 정보로 MyBatis 설정 객체 생성
        Configuration config = new Configuration(environment);

        config.addMapper(Mapper.class);


        /*
        * sqlSessionFactory : sqlSession 객체를 생성하지 위한 팩토이 역할의 인터페이스.
        * sqlSessionFactoryBuilder : sqlSessionFactory 타입의 객체응 생성하기 위한 빌드 역할
        * build(): 설정에 대한 정보를 담고 있는 configuration 타입의 객체 혹은 회부 설정 파일과 연관된 데이터를
        * 매개변수로 전달하면 sqlSessionFactory 타입의 객페를 반환하는 메소드.
        *
        * sqlSession : jdbc 의 connection 같은 객페
        * */

        // SqlSessionFactory 는 기본적으로 인터페이스이다. 일케 build 로 만들어서 선언을 해뒀으니까
        // 마이바티스가  변경이 되어도 우리는 그대로 사용할 수 있다.

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        System.out.println(sqlSessionFactory);

       // false 는 오토커밋 꺼준거. (우리가 실제 날리는 쿼리문을)
        SqlSession session = sqlSessionFactory.openSession(false);

        // 세션안에 들어있는 mapper 중에 하나를 꺼내온다는 뜻.
        // 세션에 많은 mapper 가 들어올꺼기 때문에 뭘 꺼낼지 생각.
        Mapper mapper = session.getMapper(Mapper.class);

        Date date = mapper.selectSysDate(); // 쿼리문은 메소드 이름.
        System.out.println(date);
        session.close();



    }
}
