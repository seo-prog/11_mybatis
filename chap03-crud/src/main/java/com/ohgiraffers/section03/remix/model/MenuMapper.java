package com.ohgiraffers.section03.remix.model;


import com.ohgiraffers.dto.MenuDTO;

import java.util.List;

// xml 파일이랑 매핑하려면 경로와 이름을 똑같이 !! 잡아줘야 한다.
public interface MenuMapper {

    List<MenuDTO> selectAllMenu();

    List<MenuDTO> selectMenuByCode(int menuCode);

    int insertMenu(MenuDTO menu);

    int updateMenu(MenuDTO menu);

    int deleteMenu(int menuCode);
}
