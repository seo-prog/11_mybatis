package com.ohgiraffers.section01.xmlconfig.service;

import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.model.MenuDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

// DB 랑 연결되는 DAO 에게 명령을 내리는 service

public class MenuService {

    // 얘는 (서비스) menuDAO 에 명령

    private final MenuDAO menuDAO;

    public MenuService() {
        menuDAO = new MenuDAO();
    }

    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession(); // sqlSession 객체 생성 // 날려줄 session

        // dao 에서 수행한 결과를 받아옴
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);
        sqlSession.close();
        return menuList;

    }

    public List<MenuDTO> selectMenuByCode(int menuCode) {

        SqlSession sqlSession = getSqlSession();
        System.out.println(sqlSession);

        // dao 에서 수행한 결과를 받아옴
        List<MenuDTO> menuList = menuDAO.selectMenuByCode(sqlSession, menuCode);
        sqlSession.close();
        return menuList;
    }

    public boolean registMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();
        System.out.println(sqlSession);

        int result = menuDAO.insertMenu(sqlSession, menu);

        if (result > 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0? true : false;

    }

    public boolean updateMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.updateMenu(sqlSession, menu);

        if(result > 0){
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        sqlSession.close();
        return result > 0? true : false;
    }

    public int deleteMenu(int menuCode) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.deleteMenu(sqlSession, menuCode);
        sqlSession.close();
        return result;
    }
}
