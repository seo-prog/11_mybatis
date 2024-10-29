package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSession;

public class MenuService {

    private DynamicSqlMapper mapper;


    public void selectMenuByPrice(int price) {
        // 마이바티스에서 그냥 기본 자료형으로는 비교가 안된다. 그러니 map 에 담아서 넘긴다.
        // 1. 키값으로 구분하거나 2. dto 에 getter 가 있어야 한다. // 그러니 map 에 키값을 줘서 담은 후 넘겨준다 !

        SqlSession session = getSession();
        mapper = session.getMapper(DynamicSqlMapper.class);
        Map<String, Integer> map = new HashMap<>();
        map.put("price", price);

        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        }else{
            System.out.println("검색 결과가 없습니닷 !");
        }
        session.close();
    }

    public void searchMenu(SearchCriteria searchCriteria) {

        SqlSession session = getSession();
        mapper = session.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        }else{
            System.out.println("검색 결과가 없습니다 !");
        }
        session.close();
    }

    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {

        SqlSession session = getSession();
        mapper = session.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByCategory(searchCriteria);


        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        }else{
            System.out.println("검색 결과가 없습니다 !");
        }
        session.close();
    }

    public void searchMenuByRandomMenuCode(List<Integer> randomMenuCodeList) {

        SqlSession session = getSession();
        mapper = session.getMapper(DynamicSqlMapper.class);

        Map<String, List<Integer>> codeList = new HashMap<>();
        codeList.put("randomMenuCodeList", randomMenuCodeList);

        List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode(codeList);

        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        }else{
            System.out.println("검색 결과가 없습니다 !");
        }
        session.close();
    }

    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {

        SqlSession session = getSession();
        mapper = session.getMapper(DynamicSqlMapper.class);
        List<MenuDTO> menuList = mapper.searchMenuByCodeOrSearchAll(searchCriteria);
        if(menuList != null && menuList.size() > 0) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        }else{
            System.out.println("검색 결과가 없습니다 !");
        }
        session.close();
    }
}
