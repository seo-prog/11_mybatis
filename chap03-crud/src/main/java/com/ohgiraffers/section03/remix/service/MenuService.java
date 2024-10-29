package com.ohgiraffers.section03.remix.service;

import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section03.remix.model.MenuMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getJavaSqlSession;
import static com.ohgiraffers.common.Template.getRemixSqlSession;

public class MenuService {


    public List<MenuDTO> selectAllMenu() {

        // 그 우리가 만들었던 세션
        SqlSession sqlSession = getRemixSqlSession();

        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menuList = menuMapper.selectAllMenu();
        sqlSession.close();
        return menuList;

    }

    public List<MenuDTO> selectMenuByCode(int menuCode) {
        SqlSession sqlSession = getRemixSqlSession();
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menu = menuMapper.selectMenuByCode(menuCode);
        sqlSession.close();
        return menu;
    }

    public boolean insertMenuList(MenuDTO menu) {

        SqlSession sqlSession = getJavaSqlSession();
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

        int menuda = 0;
        menuda = menuMapper.insertMenu(menu);
        if (menuda != 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        return menuda>0? true:false;
    }

    public boolean updateMenuList(MenuDTO menu) {

        SqlSession sqlSession = getJavaSqlSession();
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        int menuList = menuMapper.updateMenu(menu);

        if (menuList != 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        return menuList>0? true:false;

    }

    public boolean deleteMenu(int menuCode) {

        SqlSession sqlSession = getJavaSqlSession();
        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
        int menuList = menuMapper.deleteMenu(menuCode);

        if(menuList>0){
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        return menuList>0? true:false;
    }
}
