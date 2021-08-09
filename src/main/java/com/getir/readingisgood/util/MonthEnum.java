package com.getir.readingisgood.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MonthEnum {

    JANUARY(1, "JANUARY"),
    FEBRUARY(2, "FEBRUARY"),
    MARCH(3, "MARCH"),
    APRIL(4, "APRIL"),
    MAY(5, "MAY"),
    JUNE(6, "JUNE"),
    JULY(7, "JULY"),
    AUGUST(8, "AUGUST"),
    SEPTEMBER(9, "SEPTEMBER"),
    OCTOBER(10, "OCTOBER"),
    NOVEMBER(11, "NOVEMBER"),
    DECEMBER(12, "DECEMBER");

    private Integer id;
    private String name;

    public static MonthEnum getFromId(Integer id){
        for(MonthEnum monthEnum: values()){
            if(monthEnum.id.equals(id)){
                return monthEnum;
            }
        }
        return null;
    }
}
