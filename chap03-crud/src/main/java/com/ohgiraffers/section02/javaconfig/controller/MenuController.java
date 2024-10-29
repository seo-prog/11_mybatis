package com.ohgiraffers.section02.javaconfig.controller;

import com.ohgiraffers.common.PrintResult;
import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section02.javaconfig.service.MenuService;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuController {

    private final PrintResult printResult;

    private final MenuService menuService;


    public MenuController() {

        printResult = new PrintResult();
        menuService = new MenuService();
    }


    public void selectAllMenu() {

            List<MenuDTO> menuList = menuService.selectAllMenu();

            if (menuList != null) {
                printResult.printMenuList(menuList);
            }else{
                printResult.printErrorMessage("selectList");
            }

        }

    public void selectMenuByCode(Map<String, String> map) {

        int menuCode = Integer.parseInt(map.get("menuCode"));

        List<MenuDTO> menuList = menuService.selectMenuByCode(menuCode);

        if(menuList != null){

            printResult.printMenuList(menuList);
        }else{
            printResult.printErrorMessage("selectMenuByCode");
        }
    }


    public void regist(Map<String, String> map) {

        String name = map.get("name");
        int price = Integer.parseInt(map.get("price"));
        int categoryCode = Integer.parseInt(map.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);


        if( menuService.insertMenuList(menu) ){
            printResult.printSuccessMessage("insert");
        }else{
            printResult.printErrorMessage("insertMenuList");
        }
    }

    public void modifyMenu(Map<String, String> stringStringMap) {

        String code = stringStringMap.get("code");
        String name = stringStringMap.get("name");
        int price = Integer.parseInt(stringStringMap.get("price"));
        int categoryCode = Integer.parseInt(stringStringMap.get("categoryCode"));

        MenuDTO menu = new MenuDTO();

        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);

        if( menuService.updateMenuList(menu) ){
            printResult.printSuccessMessage("update");
        }else{
            printResult.printErrorMessage("updateMenuList");
        }

    }

    public void deleteMenu(Map<String, String> map) {

        int menuCode = Integer.parseInt(map.get("menuCode"));

        if(menuService.deleteMenu(menuCode)){
            printResult.printSuccessMessage("delete");
        }else{
            printResult.printErrorMessage("deleteMenu");
        }


    }
}


