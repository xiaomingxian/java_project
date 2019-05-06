<html>

<head>
    <title>INDEX</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<!-- https://cloud.tinymce.com/stable/tinymce.min.js-->
<script src='https://cloud.tinymce.com/stable/tinymce.min.js'></script>
<script src='js/tinymce/tinymce.js'></script>
<script src='js/tinymce/langs/zh_CN.js'></script>

<body>
<center><h4>INDEX</h4>

    <br>
    <form action="./learn/date" method="post">

        name:<input type="text" name="name"><br> pass:
        <input type="password" name="password"><br> date:
        <input type="date" name="date"><br>
        <input type="submit" value="登录">
    </form>

    <br>
    <#--<a href="http://localhost:8081/learn/upload.do">上传</a>-->
    <br>
    <br>
    <br>
    <br>
    <center><a href="http://localhost:8081/learn/activiti/getPage.do">任务发布</a></center>
    <center>
        <h4>Tincymce</h4>
        <form action="http://localhost:8081/learn/tincymce/insert.do" method="post">
            <textarea name="value"></textarea>
            <input type="submit" value="提交">
        </form>
        <br>
        <br>
        <br>
        <h4>上传流程部署压缩文件</h4>
        <br>
        <form action="./process/deploy.do" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="submit" value="提交">
        </form>
    </center>
</body>
<script>tinymce.init({selector: 'textarea', language: "zh_CN"});</script>
</html>