package com.jimzhang.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * index.jsp
 * <%@ page pageEncoding="utf-8"%>
 <!DOCTYPE html>
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Photo Upload</title>
 </head>
 <body>
 <h1>Select your photo and upload</h1>
 <hr/>
 <div style="color:red;font-size:14px;">${hint}</div>
 <form action="UploadServlet" method="post" enctype="multipart/form-data">
 Photo file: <input type="file" name="photo" />
 <input type="submit" value="Upload" />
 </form>
 </body>
 </html>
 * @author jimzhang
 * @version V1.0.0
 * @description 支持上传的Servlet
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-11 10:52
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 可以用request.getPart()方法获得名为photo的上传附件
        // 也可以用request.getParts()获得所有上传附件（多文件上传）
        // 然后通过循环分别处理每一个上传的文件
        Part part = request.getPart("photo");
        if (part != null && part.getSubmittedFileName().length() > 0) {
            // 用ServletContext对象的getRealPath()方法获得上传文件夹的绝对路径
            String savePath = request.getServletContext().getRealPath("/upload");
            // Servlet 3.1规范中可以用Part对象的getSubmittedFileName()方法获得上传的文件名
            // 更好的做法是为上传的文件进行重命名（避免同名文件的相互覆盖）
            part.write(savePath + "/" + part.getSubmittedFileName());
            request.setAttribute("hint", "Upload Successfully!");
        } else {
            request.setAttribute("hint", "Upload failed!");
        }
        // 跳转回到上传页面
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
