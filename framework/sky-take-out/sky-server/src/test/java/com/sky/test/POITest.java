package com.sky.test;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Classname POITest
 * @Description
 * @Date 2024/1/30 下午10:57
 * @Created by joneelmo
 */
public class POITest {
    /**
     * 通过POI写EXCEL内容
     * @throws IOException
     */
    public static void write() throws IOException {
        //在「内存」中创建excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        //在excel中创建一个sheet页
        XSSFSheet sheet = excel.createSheet("info");
        //在sheet中创建行对象,rownum从0开始
        XSSFRow row = sheet.createRow(0);
        //在行上创建单元格并写入文件内容
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("城市");
        //创建一个新行
        row = sheet.createRow(1);
        row.createCell(1).setCellValue("张三");
        row.createCell(2).setCellValue("珠海");
        //通过输出流将「内存」中的excel文件写到磁盘
        FileOutputStream out = new FileOutputStream("/home/joneelmo/info.xlsx");
        excel.write(out);

        //关闭资源
        out.close();
        excel.close();
    }

    /**
     * 通过POI读取EXCEL内容
     * @throws IOException
     */
    public static void read() throws IOException{
        FileInputStream inputStream = new FileInputStream("/home/joneelmo/下载/1、黑马程序员Java项目《苍穹外卖》企业级开发实战/资料/day12/itcast.xlsx");
        //读取指定excel
        XSSFWorkbook excel = new XSSFWorkbook(inputStream);
        //读取excel的指定sheet页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获取sheet中最后一行(有内容的行)的行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 0; i <= lastRowNum; i++) {
            //获取某一行
            XSSFRow row = sheet.getRow(i);

            String cellValue1 = row.getCell(1).getStringCellValue();
            String cellValue2 = row.getCell(2).getStringCellValue();
            System.out.println(cellValue1+";"+cellValue2);
        }

        //关闭资源
        excel.close();
        inputStream.close();
    }

    public static void main(String[] args) throws IOException {
//        write();
        read();
    }
}
