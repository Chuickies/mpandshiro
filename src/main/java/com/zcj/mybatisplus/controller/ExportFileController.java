package com.zcj.mybatisplus.controller;

import com.zcj.mybatisplus.entity.SysUser;
import com.zcj.mybatisplus.service.UserService;
import com.zcj.mybatisplus.utils.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class ExportFileController {
    @Autowired
    private UserService userService;

    @RequestMapping("/exportXls")
    public void exportFile(HttpServletResponse response) {
        try {
            String title = "内场客户报备表";
            //定义表的列名
            String[] rowsName = new String[]{"用户编号", "姓名", "邮件", "密码", "电话号码", "状态"};


            List<Object[]> dataList = new ArrayList<>();
            Object[] obj = null;
            List<SysUser> sysUsers = userService.list();
            if (sysUsers != null && sysUsers.size() > 0) {
                for (int i = 0; i < sysUsers.size(); i++) {
                    SysUser user = sysUsers.get(i);
                    obj = new Object[rowsName.length];
                    obj[0] = user.getUserId();
                    obj[1] = user.getUserName();
                    if(user.getEmail()!=null){
                        obj[2] = user.getEmail();
                    }else {
                        obj[2]="---";
                    }

                    obj[3] = user.getPassword();
                    if(user.getPhoneNum()!=null){
                        obj[4] = user.getEmail();
                    }else {
                        obj[4]="---";
                    }
                    obj[5] = user.getStatus();
                    dataList.add(obj);
                }
            }
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            ServletOutputStream outputStream = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(title.getBytes("gbk"), "iso8859-1") + ".xls");
            response.setContentType("application/msexcel");
            response.setCharacterEncoding("utf-8");
            ex.export(outputStream);
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
