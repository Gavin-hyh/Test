package com.hyh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class LoadUtil {
	//�ϴ�ͼƬ
	public static String ScPhoto(MultipartFile loadimg){
		//��ȡ���·��
		String path ="D:/a";
		File f=new File(path);
		//�����ڣ�ֱ�Ӵ���
		if(!f.exists()){
			f.mkdirs();
		}
		//��ȡͼƬ���
		String filename = loadimg.getOriginalFilename();
		//ƴ�ӵ�ͼƬ·��
		String filepath=path+"//"+filename;
		File file = new File(filepath);
		//�ϴ�ͼƬ
		try {
			loadimg.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepath;
	}
	//����ͼƬ
	public static void ShowPhoto(String photo,HttpServletResponse response){
		//��ȡͼƬ�ĵ�ǰ·��  �����
		 FileInputStream fis=null;
		//��response  ��ȡһ��д�������
		ServletOutputStream os=null;
		try {
			fis = new FileInputStream(photo);
			 os = response.getOutputStream();
			 //��߶�д���ٶ�
			 byte[] b=new byte[1024];
			 //�߶���д
			 while(fis.read(b)!=-1){
				 os.write(b);
			 }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(os!=null){
					os.close();
				}
				if(fis!=null){
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	//����
	public static void downLoad(String filepath,HttpServletRequest request, HttpServletResponse response){
		//�����ļ���MiMe����
		response.setContentType(request.getSession().getServletContext().getMimeType(filepath));
		//����content-dispsition
		response.setHeader("Content-Disposition", "attachment;filename="+filepath);
		//��ȡĿ���ļ���ͨ��response��Ŀ���ļ�д���ͻ�
		try {
			//��ȡ�ļ�
			InputStream  in = new FileInputStream(filepath);
			OutputStream out=response.getOutputStream();
			//д�ļ�
			byte[] b=new byte[1024];
			while(in.read(b)!=-1){
				out.write(b);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
