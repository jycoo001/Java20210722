<%--
  Created by IntelliJ IDEA.
  User: 12430
  Date: 2021/9/14
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script src="${pageContext.request.contextPath}/static/echarts.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            $.post(
                '${pageContext.request.contextPath}/banji?method=selectbanjitongji',
                function (jsonObj) {
                    console.log(jsonObj);
                    var xArray = new Array();
                    var yArray = new Array();
                    $(jsonObj).each(function () {
                        xArray.push(this.name);
                        yArray.push(this.count);
                    });
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: 'ECharts'
                        },
                        tooltip: {},
                        legend: {
                            data: ['班级']
                        },
                        xAxis: {
                            data: xArray
                        },
                        yAxis: {},
                        series: [
                            {
                                name: '人数',
                                type: 'bar',
                                data: yArray
                            }
                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表
                    myChart.setOption(option);
                },
                'json'
            );


    </script>
</body>
</html>
