package com.ohgiraffers.section01.xmlconfig.controller;


import com.ohgiraffers.common.PrintResult;
import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.service.MenuService;

import java.util.List;
import java.util.Map;

public class MenuController {

    // view 대신 사용할 객체
    private final PrintResult printResult;

    // 컨트롤러의 명령을 받을 객체
    private final MenuService menuService;

    // 컨트롤러가 생성될 때 한번에 다 생성해줌.
    public MenuController(){
        printResult = new PrintResult();
        menuService = new MenuService();
    }

    // 매핑 대체로 생각
    public void selectAllMenu() {

        // menuService 에서 조회 처리
        List<MenuDTO> menuList = menuService.selectAllMenu();

        // modelandview 에 조건에 따라 다르게 넣었다고 생각
        if(menuList != null){
            printResult.printMenuList(menuList);
        }else{
            printResult.printErrorMessage("selectList");
        }

    }

    public void selectMenuByCode(Map<String, String> map) {

        int code = Integer.parseInt(map.get("menuCode"));
        List<MenuDTO> menuList = menuService.selectMenuByCode(code);

        if(menuList != null){
            // printResult 는 view (html) 역할
            printResult.printMenuList(menuList);
        }else{
            printResult.printErrorMessage("selectMenuByCode");
        }
    }

    public void regist(Map<String, String> parameter) {

        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);

        if(menuService.registMenu(menu)){
            printResult.printSuccessMessage("insert");
        }else{
            printResult.printErrorMessage("insert");
        }


    }

    public void modifyMenu(Map<String, String> stringStringMap) {

        String name = stringStringMap.get("name");
        int price = Integer.parseInt(stringStringMap.get("price"));
        int categoryCode = Integer.parseInt(stringStringMap.get("categoryCode"));
        int code = Integer.parseInt(stringStringMap.get("code"));

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);
        menu.setCode(code);

        if(menuService.updateMenu(menu)){
            printResult.printSuccessMessage("update");
        }else{
            printResult.printErrorMessage("update");
        }
    }

    public void deleteMenu(Map<String, String> stringStringMap) {

        int menuCode = Integer.parseInt(stringStringMap.get("menuCode"));
        int result = 0;
        result = menuService.deleteMenu(menuCode);

        if(result != 0){
            printResult.printSuccessMessage("delete");
        }else{
            printResult.printErrorMessage("delete");
        }

    }
}
