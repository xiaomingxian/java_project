<html>

<head>
    <title>INDEX</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<script src='https://cloud.tinymce.com/stable/tinymce.min.js'></script>
<script>tinymce.init({selector: 'textarea'});</script>

<body>
<center><h4>INDEX</h4></center>

<br>
<form action="http://localhost:8081/learn/date" method="post">

    name:<input type="text" name="name"><br> pass:
    <input type="password" name="password"><br> date:
    <input type="date" name="date"><br>
    <input type="submit" value="登录">
</form>

<br>
<a href="http://localhost:8081/learn/upload.do">上传</a>
<br>
<center><h4>Tincymce</h4></center>
<form action="http://localhost:8081/learn/tincymce/insert.do" method="post">
    <textarea name="value">Next, get a free Tiny Cloud API key!</textarea>
    <input type="submit" value="提交">
</form>



</body>

</html>