<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>任务发布</title>
</head>
<script src="../../js/jquery-1.11.3.min.js"/>
<script>
    $(function () {

        $.get("http://localhost:8081/learn/activiti/getActReProcdefs.do", function (data) {
            $.each(data.actReProcdefs), function (i, d) {
                $("#table").append(
                    '<tr>' +
                    '<td><a href="http://localhost:8081/learn/activiti/process.do?id=' + d.id + '">启动流程</a></td>' +
                    '<td>' + d.rev + '</td>' +
                    '<td>' + d.category + '</td>' +
                    '<td>' + d.name + '</td>' +
                    '<td>' + d.key + '</td>' +
                    '<td>' + d.version + '</td>' +
                    '<td>' + d.deploymentId + '</td>' +
                    '<td>' + d.resourceName + '</td>' +
                    '<td>' + d.dgrmResourceName + '</td>' +
                    '<td>' + d.description + '</td>' +
                    '<td>' + d.hasStartFormKey + '</td>' +
                    '<td>' + d.hasGraphicalNotation + '</td>' +
                    '<td>' + d.suspensionState + '</td>' +
                    ' <td>' + d.tenantId + '</td>' +
                    '</tr>')
            }
        }

    })

    })
</script>
<body>
<center>
    <table id='table'>

    </table>
</center>

</body>
</html>