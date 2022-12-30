<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 테스트</title>
</head>
<body>
<h3>Spring boot 파일 업로드 테스트</h3>

<form action="/files/upload" method="post" enctype="multipart/form-data">
   작성자 <input type="text" name="author" value="smith"><br>
   File <input type="file" name="files" multiple="multiple"><br>
   <button type="submit">업로드</button>
</form>

</body>
</html>