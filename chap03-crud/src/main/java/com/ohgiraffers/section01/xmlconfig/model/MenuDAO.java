package com.ohgiraffers.section01.xmlconfig.model;


import com.ohgiraffers.dto.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

// 정말 DB 와 연결될 내용만 수행
public class MenuDAO {
    public List<MenuDTO> selectAllMenu(SqlSession sqlSession) {

        // 여러개니까 list !
        return sqlSession.selectList("MenuMapper.selectAllMenu");
    }

    public List<MenuDTO> selectMenuByCode(SqlSession sqlSession, int code) {

        return sqlSession.selectList("MenuMapper.selectMenuByCode", code);
    }

    public int insertMenu(SqlSession sqlSession, MenuDTO menu) {

        return sqlSession.insert("MenuMapper.insertMenu", menu);
    }

    public int updateMenu(SqlSession sqlSession, MenuDTO menu) {

        return sqlSession.update("MenuMapper.updateMenu", menu);
    }

    public int deleteMenu(SqlSession sqlSession, int code) {

        return sqlSession.delete("MenuMapper.deleteMenu", code);
    }
}
