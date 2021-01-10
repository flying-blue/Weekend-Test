package com.fishpro.demo.mybatis.service;

import com.fishpro.demo.mybatis.dao.UserDao;
import com.fishpro.demo.mybatis.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : UserService
 * @Author : Administrator
 * @Date: 2021/1/9 18:05
 * @Description : UserService
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    /**
     * 读取excl并插入到数据中
     * @param file
     * @return
     */
    public Map<String,Object> uploadExcl(MultipartFile file) {
        Map<String,Object> ruslt = new HashMap<>();
        try {
            String fileName = file.getOriginalFilename();
            Workbook workbook;
            if(fileName.endsWith("xls")){
                workbook = new HSSFWorkbook(file.getInputStream());
            }else if(fileName.endsWith("xlsx")){
                workbook = new XSSFWorkbook(file.getInputStream());
            } else {
                ruslt.put("code","1");
                ruslt.put("message","文件格式非excl");
                return ruslt;
            }
            //判断第一页不为空
            if(null != workbook.getSheetAt(0)){
                //读取excl第二行，从1开始
                for(int rowNumofSheet = 1;rowNumofSheet <=workbook.getSheetAt(0).getLastRowNum();rowNumofSheet++){
                    if (null != workbook.getSheetAt(0).getRow(rowNumofSheet)) {
                        //定义行，并赋值
                        Row aRow = workbook.getSheetAt(0).getRow(rowNumofSheet);
                        User user = new User();
                        System.out.println(aRow.getLastCellNum());
                        for(int cellNumofRow=0;cellNumofRow<aRow.getLastCellNum();cellNumofRow++){
                            //读取rowNumOfSheet值所对应行的数据
                            //获得行的列数
                            Cell xCell = aRow.getCell(cellNumofRow);
                            Object cell_val;
                            if(cellNumofRow == 0){
                                if(xCell != null && !xCell.toString().trim().isEmpty()){
                                    cell_val = xCell.getStringCellValue();
                                    if(cell_val != null){
                                        String temp = (String)cell_val;
                                        user.setName(temp);
                                    }
                                }
                            }
                            if(cellNumofRow == 1){
                                if(xCell != null && !xCell.toString().trim().isEmpty()){
                                    cell_val = xCell.getStringCellValue();
                                    if(cell_val != null){
                                        String temp = (String)cell_val;
                                        if("男".equals(temp)){
                                            user.setSex("1");
                                        } else {
                                            user.setSex("0");
                                        }
                                        user.setCreateTime(new Date());
                                        userDao.insert(user);
                                    }
                                }
                            }
                        }
                    }
                }
                ruslt.put("code","0");
                ruslt.put("message","成功插入数据库！");
            }else {
                ruslt.put("code","1");
                ruslt.put("message","第一页EXCL无数据！");
            }
        }catch (Exception e){
            e.printStackTrace();
            ruslt.put("code","1");
            ruslt.put("message",e.getMessage());
        }
        return ruslt;
    }
}
