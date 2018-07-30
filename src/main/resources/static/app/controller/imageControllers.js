function imageCharCtrl($scope, $http, $rootScope) {
    //window.onresize = myChart.resize;

    var myChart = echarts.init(document.getElementById('imageCharMain'));

//    $scope.test={"aaa":123};
//    //翻页中当前页面编号，默认为第一页
//    var pageNo = 1;
//    // data里包含多一项edgesData，所以初始总页面数为-1
//    var totalPageNum = -1;

    //把连接路径写死
    var edgesData = [
        {
          "lineStyle": {
            "normal": {
              "color": "black",
              "width": 5
            }
          },
          "source": 0,
          "target": 1
        },
        {
          "lineStyle": {
            "normal": {
              "color": "black",
              "width": 5
            }
          },
          "source": 1,
          "target": 2
        },
        {
          "lineStyle": {
            "normal": {
              "color": "black",
              "width": 5
            }
          },
          "source": 2,
          "target": 3
        },
        {
          "lineStyle": {
            "normal": {
              "color": "black",
              "width": 5
            }
          },
          "source": 3,
          "target": 4
        },
        {
          "lineStyle": {
            "normal": {
              "color": "black",
              "width": 5
            }
          },
          "source": 4,
          "target": 5
        }
      ];
    //定义节点状态颜色
    var showColor = {
    	"N" : "rgb(50,205,50)",
    	"B" : "rgb(255,215,0)",
    	"F" : "rgb(255,0,0)"
    }
  //定义节点位置
    var nodePosition = [
    	{x:100,y:100},
    	{x:200,y:100},
    	{x:300,y:100},
    	{x:300,y:200},
    	{x:200,y:200},
    	{x:100,y:200}
    ]
  //为后台请求参数 带分页数据
    $scope.quereyData={
        page:1, //当前页码 初始化为1
        size:6, //每页数据量 defaultSize全局变量
    };

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
        console.log(params);
    });

//    //点击页面时，改变图表展示数据
//    $scope.changePage = function (page) {
//        pageNo = page;
//        var responseData = JSON.parse($scope.respones);
//        myChart.setOption({
//            series: [{
//                // 根据名字对应到相应的系列
//                data: responseData[pageNo].page
//            }]
//        })
//    };
    
    //节点工厂
    function nodeFactory(id, name, formatter, status, position){
    	return {
    		"symbolSize": 50,
            "name": name,
            "x": position.x,
            "tooltip": {
              "formatter": formatter
            },
            "y": position.y,
            "itemStyle": {
              "normal": {
                "color": status
              }
            },
            "fixed": true,
            "id": id
    	};
    	
    }
    
    $scope.init=function(){
    	$http.post("service/getEquipments",$scope.quereyData).success(function(data) {
    		console.info(data);
    		$scope.equipments = data.resultObj.list;
    		$scope.initKey = (data.resultObj.pageNum-1)*6+1;
    		$scope.nodeList = [];
    		for(i = 0; i<$scope.equipments.length; i++ ){
    			$scope.equ = $scope.equipments[i];
    			$scope.formatter = "杆塔"+(i+$scope.initKey)+
					    			"<br/>杆塔ID："+$scope.equ.eid+
					    			"<br/>设备种类："+(!$scope.equ.type ? "" : $scope.equ.type)+
					    			"<br/>设备数量："+$scope.equ.amount+
					    			"<br/>雷击总数："+$scope.equ.total+
					    			"<br/>报警次数："+$scope.equ.alarms+
					    			"<br/>上线时间："+$scope.equ.onlineTime;
    			
    			$scope.nodeList.push(nodeFactory($scope.equ.id,
    									"杆塔"+(i+$scope.initKey),
			    						$scope.formatter,
			    						showColor[$scope.equ.eStatus],
			    						nodePosition[i]));
    		}
    		myChart.setOption({
    			series: [{
    				// 根据名字对应到相应的系列
    				edges: edgesData,
    				data: $scope.nodeList
    			}]
    		})
    		window.onresize = myChart.resize;
    		$scope.pages=data.resultObj.pages;
    		$scope.pageArr=data.resultObj.navigatepageNums;
    		$scope.quereyData.page=data.resultObj.pageNum;
    		myChart.hideLoading();
    	});
    }
  //初始化
    $scope.init();

    //翻页
    $scope.changePage=function(page){
        $scope.quereyData.page=page;
        $scope.init();
    }
    
//    $http({
//        method: 'GET',
//        url: 'totalData.json'
//    }).then(function successCallback(response) {
//        $scope.respones = JSON.stringify(response.data);
//        $scope.tempData = JSON.stringify(response.data);
//        //动态渲染翻页的页数
//        $scope.pageBlock = [];
//        //计算总页数
//        for (i in  response.data) {
//            totalPageNum += 1;
//        }
//        for (i = 0; i < totalPageNum; i++) {
//            $scope.pageBlock.push(parseInt(i + 1));
//        }
//        //模拟异步执行
//        setTimeout(function () {
//            myChart.hideLoading();
//            myChart.setOption({
//                series: [{
//                    // 根据名字对应到相应的系列
//                     edges: edgesData,
//                    data: response.data[pageNo].page
//                }]
//            })
//        }, 300);
//    }, function errorCallback(response) {
//        // 请求失败执行代码
//        $scope.tempData = response.data;
//    });
}