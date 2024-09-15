package com.docker.spring;



import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


    public class WriteExcel {
        //Blank workbook
        XSSFWorkbook workbook;

        //Create a blank sheet
        XSSFSheet sheet;
        Map<String, Object[]> data;
        private List<?> list;

        Object[] objects;
        List<Object> objectList;
        List<Object> objectListName;

        public WriteExcel() {
            workbook = new XSSFWorkbook();
        }

        //Prepare data to be written as an Object[]
        @SneakyThrows
        public void prepareData(List<?> list) {
            sheet = workbook.createSheet();

            Object fieldValue;

            objects = new Object[]{};
            data = new TreeMap<>();


            objectList = new ArrayList<>();
            objectListName = new ArrayList<>();
            int i = 0;

            for (Object Object : list) {
                i++;

                Class<?> clazz = Object.getClass();
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {

                    field.setAccessible(true);
                    fieldValue = field.get(Object);


                    if (objectListName.size() <= fields.length) {
                        objectListName.add(field.getName());
                    } else {
                        objectListName.clear();
                    }
                    objectList.add(fieldValue.toString());

                    if (objectList.size() == fields.length) {
                        if (objectListName.size() == fields.length) {
                            data.put(String.valueOf(Integer.valueOf(i)), objectListName.toArray());
                        }
                        i++;
                        data.put(String.valueOf(Integer.valueOf(i)), objectList.toArray());
                        objectList = new ArrayList<>();

                    }


                }


            }

            write();

        }

        //Iterate over data and write to sheet
        public void write() {
            Set<String> keyset = data.keySet();
            int rownum = 0;
            for (String key : keyset) {

                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }

            writeWorkBook();
        }


        //Write the workbook in file system
        public void writeWorkBook() {

            FileOutputStream out;

            try {
                out = new FileOutputStream("D:\\demo.xlsx");
                workbook.write(out);
                out.close();
                System.out.println("demo.xlsx written successfully on disk.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


    }
