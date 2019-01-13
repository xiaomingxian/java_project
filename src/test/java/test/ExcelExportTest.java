package test;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;
import pojo.excelpojo.Person;
import pojo.excelpojo.Teacher;

import javax.print.attribute.standard.SheetCollate;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelExportTest {

    String macPath="/Users/xxm/develop/workspace/learn/src/test/java/excel";

    /**
     * 无表头
     */
    @Test
    public void noHead() {
        try {
            FileOutputStream outputStream = new FileOutputStream(macPath+"\\withoutHead.xlsx");
            //FileOutputStream outputStream = new FileOutputStream("D:\\develop\\ideaworkspeace\\learn\\src\\test\\java\\excel\\withoutHead.xlsx");
            //写出位置 -- 类型 -- 无表头
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, false);
            //Sheet()
            Sheet sheet1 = new Sheet(1, 0);
            ArrayList<List<String>> data = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ArrayList<String> in = new ArrayList<>();

                for (int j = 0; j < 5; j++) {
                    in.add("content-" + j);

                }
                data.add(in);

            }
            writer.write0(data, sheet1);
            writer.finish();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void haveHead() {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(macPath+"/haveHead.xlsx");
            //FileOutputStream fileOutputStream = new FileOutputStream("D:\\develop\\ideaworkspeace\\learn\\src\\test\\java\\excel\\haveHead.xlsx");
            ExcelWriter writer = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);

            //Sheet()
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("设置SheetName");
            ArrayList<List<String>> data = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ArrayList<String> in = new ArrayList<>();

                for (int j = 0; j < 5; j++) {
                    in.add("content-" + j);

                }
                data.add(in);

            }
            writer.write0(data, sheet1);
            writer.finish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void haveColumName() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(macPath+"\\haveCloumHead.xlsx");
            //fileOutputStream = new FileOutputStream("D:\\develop\\ideaworkspeace\\learn\\src\\test\\java\\excel\\haveCloumHead.xlsx");
            ExcelWriter writer = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);

            //Sheet()
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("有列名");
            ArrayList<List<String>> data = new ArrayList<>();


            for (int i = 0; i < 10; i++) {
                ArrayList<String> in = new ArrayList<>();


                for (int j = 0; j < 5; j++) {
                    in.add("content-" + j);

                }

                data.add(in);

            }

            //添加列名
            ArrayList<List<String>> cloum = new ArrayList<>();


            for (int j = 0; j < 5; j++) {
                ArrayList<String> cloum_in = new ArrayList<>();
                cloum_in.add("第 " + j + " 列");
                cloum.add(cloum_in);
            }

            Table table = new Table(1);
            table.setHead(cloum);

            writer.write0(data, sheet1, table);
            writer.finish();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void pojoTest() {


        try {
            FileOutputStream outputStream = new FileOutputStream(macPath+"/pojoTest.xlsx");
            //FileOutputStream outputStream = new FileOutputStream("D:/develop/ideaworkspeace/learn/src/test/java/excel/pojoTest.xlsx");
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

            Sheet sheet = new Sheet(1, 0, Person.class);
            sheet.setSheetName("实体类添加注解自动生成表头");

            ArrayList<Person> data = new ArrayList<Person>();

            for (int i = 0; i < 10; i++) {
                Person person = new Person();
                person.setName("name" + i);
                person.setAge(new BigDecimal(18 + i));

                if (i % 2 == 0) {
                    person.setGender("女");
                } else {
                    person.setGender("男");

                }
                person.setAddress("China");
                person.setBirthday(new Date());

                data.add(person);
            }

            writer.write(data, sheet);
            writer.finish();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 不支持boolean类型字段？？？
     */
    @Test
    public void complexPojoTest() {

        try {
            FileOutputStream outputStream = new FileOutputStream(macPath+"/complexPojoTest.xlsx");
            //FileOutputStream outputStream = new FileOutputStream("D:/develop/ideaworkspeace/learn/src/test/java/excel/complexPojoTest.xlsx");

            ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

            //    sheet
            Sheet sheet = new Sheet(1, 0, Teacher.class);
            sheet.setSheetName("多级表头生成");


            ArrayList<Teacher> teachers = new ArrayList<Teacher>();
            for (int i = 0; i < 10; i++) {
                Teacher teacher = new Teacher();
                teacher.setId(i);
                teacher.setName("teacher" + i);
                teacher.setAge(18 + i);
                teacher.setAddress("China");
                teacher.setBirthday(new Date());
                teacher.setIsBos("是");
                teacher.setJiBie(i);

                teachers.add(teacher);

            }
            excelWriter.write(teachers, sheet);
            excelWriter.finish();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exportMoreTable() {


        try {
            FileOutputStream outputStream = new FileOutputStream(macPath+"/exportMoreTable.xlsx");
            //FileOutputStream outputStream = new FileOutputStream("D:/develop/ideaworkspeace/learn/src/test/java/excel/exportMoreTable.xlsx");

            ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("多张表导出");

            //    表1
            Table t1 = new Table(1);
            ArrayList<List<String>> data = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ArrayList<String> in = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    in.add("content" + j);
                }
                data.add(in);
            }
            excelWriter.write0(data, sheet, t1);

            //    表2
            Table t2 = new Table(2);
            t2.setClazz(Person.class);
            ArrayList<Person> people = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                {
                    Person person = new Person();
                    person.setName("name" + i);
                    person.setAge(new BigDecimal(18 + i));

                    if (i % 2 == 0) {
                        person.setGender("女");
                    } else {
                        person.setGender("男");

                    }
                    person.setAddress("China");
                    person.setBirthday(new Date());

                    people.add(person);
                }
            }
            excelWriter.write(people, sheet, t2);


            //    表3
            Table t3 = new Table(3);
            t3.setClazz(Teacher.class);
            ArrayList<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Teacher teacher = new Teacher();
                teacher.setId(i);
                teacher.setName("teacher" + i);
                teacher.setAge(18 + i);
                teacher.setAddress("China");
                teacher.setBirthday(new Date());
                teacher.setIsBos("是");
                teacher.setJiBie(i);

                teachers.add(teacher);

            }
            excelWriter.write(teachers, sheet, t3);
            //    表4
            Table table = new Table(4);
            ArrayList<List<String>> data2 = new ArrayList<>();


            for (int i = 0; i < 10; i++) {
                ArrayList<String> in = new ArrayList<>();


                for (int j = 0; j < 5; j++) {
                    in.add("content-" + j);

                }

                data2.add(in);

            }

            //添加列名
            ArrayList<List<String>> cloum = new ArrayList<>();


            for (int j = 0; j < 5; j++) {
                ArrayList<String> cloum_in = new ArrayList<>();
                cloum_in.add("第 " + j + " 列");
                cloum.add(cloum_in);
            }

            Table t4 = new Table(1);
            table.setHead(cloum);

            excelWriter.write0(data, sheet, table);

            excelWriter.finish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
