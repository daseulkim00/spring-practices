package com.douzone.fileupload.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service              //받아서 저장
public class FileUploadService {
   private static String SAVE_PATH = "/upload-images";  // 여기 저장해주고 (리소스)
   private static String URL_BASE = "/images";   // 가상 url을 만들고  두개를 매핑해준다.
   
   
   public static String restore(MultipartFile multipartFile) {
      String url = null;
      try {

         if (multipartFile.isEmpty()) {
            return url;   // 빈파일이면 url을 null로 보내줌
         }

         //파일 이름을 만들어야함(서버에 뭘로 저장할건지) 이름이겹치면 덮어써지니깐 이름은 클라이언트가 준걸쓰는게아니라 절대 안겹칠거를 만들어내야함generateSaveFilename
         String originFilename = multipartFile.getOriginalFilename();
         String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
      // 원래 파일이름에서 확장자(png)를 가져온다.
         
         String saveFilename = generateSaveFilename(extName); // 여기오면 밑에 uuid 실행된다.
         // 확장자를 넘겨준다

         long fileSize = multipartFile.getSize();

          System.out.println("##########" + originFilename);
          System.out.println("##########" + fileSize);
         
         //사용되는건 이거 
         System.out.println("##########" + saveFilename);

        // 이름을 만들어냄 
         
         
       // 어디에저장할지 SAVE_PATH = "/upload-images" 에 저장된다.
         OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);  //  실제로 파일이 저장된경로(c드라이브)
         
         byte[] data = multipartFile.getBytes();  // 내가보낸 png파일의 바이트 배열을 받는 식
         os.write(data);  // write-바이트 배열을 받아야 사용할ㅇ수 잇어서 위에서 맞춰준다.
         			      // 파일이 저장된다
         
         os.close();
         
         // 근데 SAVE_PATH로는 c드라이브에 접근이 안되서 가상 url을 만들어서 연결시켜준다.
         url = URL_BASE + "/" + saveFilename;
         
      } catch (IOException ex) {
         throw new RuntimeException("file upload error : " + ex);
      }
      
      return url;
      //c 드라이브 밑에 savepath 밑에 이미지가 저장되고 가상 url 까지 만듬  이거 두개를 연결시켜 주는 역할은 spring-servlet.xml에서 적어준다.
   }
   
   
  // 만약에 PNG가 들어오면 UUID(안겹치는 이름 만들어줌) . PNG 로 이름 저장된다  
   private static String generateSaveFilename(String extName) {
		return UUID.randomUUID() + "." + extName;
	   
	   /*
		 * String filename = "";
		 * 
		 * Calendar calendar = Calendar.getInstance();
		 * 
		 * filename += calendar.get(Calendar.YEAR); filename +=
		 * calendar.get(Calendar.MONTH); filename += calendar.get(Calendar.DATE);
		 * filename += calendar.get(Calendar.HOUR); filename +=
		 * calendar.get(Calendar.MINUTE); filename += calendar.get(Calendar.SECOND);
		 * filename += calendar.get(Calendar.MILLISECOND); filename += ("." + extName);
		 * return filename;
		 */
   }

}