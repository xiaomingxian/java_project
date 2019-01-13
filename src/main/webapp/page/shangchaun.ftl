<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<form name="serForm" action="http://localhost:8081/learn/fileUpload1.do" method="post" enctype="multipart/form-data">
    <h4>采用流的方式上传文件</h4>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>

<form name="Form2" action="http://localhost:8081/learn/fileUpload2.do" method="post" enctype="multipart/form-data">
    <h4>采用multipart提供的file.transfer方法上传文件</h4>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>

<form name="Form2" action="http://localhost:8081/learn/springUpload.do" method="post" enctype="multipart/form-data">
    <h4>使用spring mvc提供的类的方法上传文件</h4>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>
</body>
</html>