package com.ohgiraffers.common;


import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// sqlSession 관리 클래스
public class Template {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {
        if (sqlSessionFactory == null) {
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream("xmlconfig/mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory.openSession(false);
    }

    public static SqlSession getJavaSqlSession() {

        if (sqlSessionFactory == null) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream("src/main/resources/javaconfig/java-config.properties"));

                // 프로퍼티 GET 으로 꺼내올 수 있다.
                String driver = properties.getProperty("DRIVER");
                String url = properties.getProperty("URL");
                String user = properties.getProperty("USER");
                String pass = properties.getProperty("PASSWORD");

                Environment environment = new Environment(
                        "dev", // 그냥 이름 지어준거
                        new JdbcTransactionFactory(),
                        new PooledDataSource(driver, url, user, pass)
                );

                Configuration configuration = new Configuration(environment);
                // configuration 만들고 -> 매퍼 지정하고 -> 팩토리 만듬 기억나지?

                // 매퍼 지정
                // 경로 다 안쓰고 import 해줘도 됌.
                configuration.addMapper(com.ohgiraffers.section02.javaconfig.model.MenuMapper.class);

                sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory.openSession(false);
    }

    public static SqlSession getRemixSqlSession() {

        if (sqlSessionFactory == null) {
            Properties properties = new Properties();

            try {

                properties.load(new FileReader("src/main/resources/javaconfig/java-config.properties"));
                String driver = properties.getProperty("DRIVER");
                String url = properties.getProperty("URL");
                String user = properties.getProperty("USER");
                String pass = properties.getProperty("PASSWORD");

                Environment environment = new Environment(
                        "dev", // 그냥 이름 지어준거
                        new JdbcTransactionFactory(),
                        new PooledDataSource(driver, url, user, pass)
                );

                Configuration configuration = new Configuration(environment);
                // configuration 만들고 -> 매퍼 지정하고 -> 팩토리 만듬 기억나지?

                // 매퍼 지정
                // 경로 다 안쓰고 import 해줘도 됌.
                configuration.addMapper(com.ohgiraffers.section03.remix.model.MenuMapper.class);

                sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory.openSession(false);
    }
}
