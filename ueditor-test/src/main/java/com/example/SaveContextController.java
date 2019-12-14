package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 保存富文本内容到本地
 * @author Administrator
 *
 */
@Controller
public class SaveContextController {

	@RequestMapping(value = "/saveContextToLocal", method = RequestMethod.POST)
	@ResponseBody
	public void saveContextToLocal(String text) {
		System.out.println("接收到前台文本："+text);
		// 向文件userNote.txt中写入
        File f = new File("c:/userNote.txt");
        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上               
                PrintWriter pw = new PrintWriter(fw);               
        ) {
            pw.println(text);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
