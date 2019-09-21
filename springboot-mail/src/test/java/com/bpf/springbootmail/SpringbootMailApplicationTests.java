package com.bpf.springbootmail;

import com.bpf.springbootmail.common.utils.FileUtil;
import com.bpf.springbootmail.common.utils.ZipUtil;
import com.bpf.springbootmail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailApplicationTests {

	@Autowired
	private MailService mailService;
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * 发送简单纯文本邮件
	 */
	@Test
	public void sendSimpleMail() {
		mailService.sendSimpleMail("luoqiang@efunong.com", "发送邮件测试", "大家好，这是我用springboot进行发送邮件测试");
	}

	/**
	 * 发送HTML邮件
	 */
	@Test
	public void sendHtmlMail() {
		String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件" + "</font></h3></body></html>";
		mailService.sendHtmlMail("luoqiang@efunong.com", "发送邮件测试", content);
	}

	/**
	 * 发送带附件的邮件
	 */
	@Test
	public void sendAttachmentMail() {
		String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有附件哦" + "</font></h3></body></html>";
		String filePath = "E:/Applications/excel/edm.zip";
		//E:\Applications\excel\edm.zip
//		String dirName = "E:/Applications/excel/FileUtil/";// 创建目录
//		FileUtil.createDir(dirName);// 调用方法创建目录
//		String fileName = dirName + "/file1.txt";// 创建文件
//		String fileName2 = dirName + "/file1.txt";// 创建文件
//		FileUtil.FileUtil(fileName);// 调用方法创建文件
//		FileUtil.FileUtil(fileName2);// 调用方法创建文件
//		List<File> srcfile=new ArrayList<File>();
//		srcfile.add(new File(fileName));
//		srcfile.add(new File(fileName2));
//		File zipfile = new File(dirName+"edm.zip");
//		ZipUtil.zipFiles(srcfile, zipfile);
//		String filePath = zipfile.getPath();
		mailService.sendAttachmentMail("luoqiang@efunong.com", "发送邮件测试", content, filePath);
	}

	/**
	 * 发送带图片的邮件
	 */
	@Test
	public void sendInlineResourceMail() {
		String rscPath = "your picture path";
		String rscId = "001";
		String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有图片哦" + "</font></h3>"
				+ "<img src=\'cid:" + rscId + "\'></body></html>";
		mailService.sendInlineResourceMail("receiver@email.com", "发送邮件测试", content, rscPath, rscId);
	}

}
