function imageCharCtrl($scope, $http, $rootScope) {
    //window.onresize = myChart.resize;

    var myChart = echarts.init(document.getElementById('imageCharMain'));

    $scope.test={"aaa":123};
    //翻页中当前页面编号，默认为第一页
    var pageNo = 1;
    // data里包含多一项edgesData，所以初始总页面数为-1
    var totalPageNum = -1;

//对于准备好的dom，初始化echarts实例


    //图表的基础设置。
    var option = {
        animation: true,
        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        tooltip: {},
        series: [{
            type: 'graph',
            layout: 'none',
            data: [],
            itemStyle: {
                normal: {
                    borderColor: '#fff',
                    borderWidth: 1,
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowOffsetY: 4
                }
            },
            label: {
                normal: {
                    show: true,
                    position: ['100%', '0'],
                    formatter: '{b}',
                    fontSize: 15,
                    color: "black"
                }
            },
            edges: []
        }]
    };
    // 使用基本 的配置项和数据显示图表。
    myChart.setOption(option);
    //显示loading
    myChart.showLoading();

//点击事件
    myChart.on('click', function (params) {
        // console.log(params);
        //alert("点击事件！\n请在console查看详情");
        console.log($scope.test);
    });

    //点击页面时，改变图表展示数据
    $scope.changePage = function (page) {
        pageNo = page;
        var responseData = JSON.parse($scope.respones);
        myChart.setOption({
            series: [{
                // 根据名字对应到相应的系列
                data: responseData[pageNo].page
            }]
        })
    };
    $http({
        method: 'GET',
        url: 'totalData.json'
    }).then(function successCallback(response) {
        $scope.respones = JSON.stringify(response.data);
        $scope.tempData = JSON.stringify(response.data);
        //动态渲染翻页的页数
        $scope.pageBlock = [];
        //计算总页数
        for (i in  response.data) {
            totalPageNum += 1;
        }
        for (i = 0; i < totalPageNum; i++) {
            $scope.pageBlock.push(parseInt(i + 1));
        }
        //模拟异步执行
        setTimeout(function () {
            myChart.hideLoading();
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    edges: response.data[0].edgesData,
                    data: response.data[pageNo].page
                }]
            })
        }, 300);
    }, function errorCallback(response) {
        // 请求失败执行代码
        $scope.tempData = response.data;
    });
}