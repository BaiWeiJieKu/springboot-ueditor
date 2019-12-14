package com.example;

import com.baidu.ueditor.ActionEnter;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**与富文本界面交互
 * Created by ldb on 2017/4/9.
 */
@Controller
public class UEditorController {


	/**
	 * 在页面加载完成后读取本地文件内容进行显示
	 * @param model
	 * @return
	 */
    @RequestMapping("/")
    private String showPage(Model model){
    	File f = new File("c:/userNote.txt");
    	if(f.exists()) {
    		// 创建文件字符流
            // 缓存流必须建立在一个存在的流的基础上
        	StringBuilder sb = new StringBuilder();
            try (
            		FileReader fr = new FileReader(f); 
            		BufferedReader br = new BufferedReader(fr);
            	) 
            {
                while (true) {
                    // 一次读一行
                    String line = br.readLine();
                    if (null == line)
                        break;
                    sb.append(line);
                }
                model.addAttribute("context", sb.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    	}
        return "index";
    }

    /**
     *读取Ueditor配置类
     */
    @RequestMapping(value="/ueditorconfig")
    public void config(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
