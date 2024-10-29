package com.ohgiraffers.section02.javaconfig.service;

import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section02.javaconfig.model.MenuMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getJavaSqlSession;

public class MenuService {

    private MenuMapper menuMapper;

    public List<MenuDTO> selectAllMenu() {

        // 요청시마다 mapper 새롭게 생성
        SqlSession sqlSession = getJavaSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menuList = menuMapper.selectAllMenu();
        sqlSession.close();
        return menuList;

    }

    public List<MenuDTO> selectMenuByCode(int menuCode) {

        SqlSession sqlSession = getJavaSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menuList = menuMapper.selectByMenuCode(menuCode);
        sqlSession.close();
        return menuList;
    }

    public boolean insertMenuList(MenuDTO menu) {

        SqlSession sqlSession = getJavaSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int menuda = 0;
         menuda = menuMapper.insertMenuList(menu);
        if (menuda != 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        return menuda>0? true:false;
    }

    public boolean updateMenuList(MenuDTO menu) {

        SqlSession sqlSession = getJavaSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);
        int menuList = menuMapper.updateMenuList(menu);

        if (menuList != 0) {
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        return menuList>0? true:false;

    }

    public boolean deleteMenu(int menuCode) {

        SqlSession sqlSession = getJavaSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);
        int menuList = menuMapper.deleteMenu(menuCode);

        if(menuList>0){
            sqlSession.commit();
        }else{
            sqlSession.rollback();
        }
        return menuList>0? true:false;
    }
}