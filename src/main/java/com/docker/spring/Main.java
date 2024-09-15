package com.docker.spring;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Dto dto=new Dto();
        dto.setId(1L);
        dto.setName("name");
        dto.setFamily("family");

        Dto dto1=new Dto();
        dto1.setId(2L);
        dto1.setName("name1");
        dto1.setFamily("family1");

        List<Dto> list=new ArrayList<>();
        list.add(dto);
        list.add(dto1);

        WriteExcel writeExcelDemo=new WriteExcel();

        writeExcelDemo.prepareData(list);
    }
}
