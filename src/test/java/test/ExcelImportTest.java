package test;

import com.alibaba.excel.support.ExcelTypeEnum;
import pojo.excelpojo.Person;
import utils.EasyExcelUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ExcelImportTest {


    public static void main(String[] args) {
        // pojoTest();
        stringTest();
    }

    private static void stringTest() {

        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\develop\\ideaworkspeace\\learn\\src\\test\\java\\excel\\withoutHead.xlsx");
            List<List<String>> lists = EasyExcelUtil.readExcelWithStringList(fileInputStream, ExcelTypeEnum.XLSX);
            for (List<String> list : lists) {
                System.out.println(list);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void pojoTest() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\develop\\ideaworkspeace\\learn\\src\\test\\java\\excel\\pojoTest.xlsx");
            List<Object> objects = EasyExcelUtil.readExcelWithModel(fileInputStream, Person.class, ExcelTypeEnum.XLSX);

            for (Object object : objects) {
                System.out.println(object);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
