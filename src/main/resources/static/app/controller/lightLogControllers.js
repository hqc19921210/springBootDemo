/**
 * Created by heqichao on 2018-7-22.
 */
function lightLogCtrl($scope, $http, $rootScope) {
    $scope.pages=0;
    $scope.clear=function(){
        $scope.quereyData.start=null;
        $scope.quereyData.end=null;
        $scope.quereyData.functionCode=null;
        $scope.quereyData.devEUI=null;
    }
    //时间组件
    $("#datepickerStrat"). datepicker().on('changeDate', function () {
        console.info("enter 1");
        $scope.quereyData.start=$("#datepickerStrat").val();
    });
    $("#datepickerEnd"). datepicker().on('changeDate', function (e) {
        console.info("enter 2");
        console.info(e);
        $scope.quereyData.end =$("#datepickerEnd").val();
    });

    //为后台请求参数 带分页数据
    $scope.showEntity={};
    $scope.quereyData={
        page:1, //当前页码 初始化为1
        size:defaultSize, //每页数据量 defaultSize全局变量
        aaa:"aaa"//其他默认的业务参数
    };
    $scope.pageArr=[1];//页码数组
    $scope.pages= $scope.pageArr.length; //总页数
    //初始化数据
    $scope.init=function(){
        console.info($scope.quereyData);
        $scope.showEntity={};
        $http.post("/service/queryLightLogs",$scope.quereyData).success(function(data) {
            console.info(data);
            $scope.data = data.resultObj.list;
            $scope.pages=data.resultObj.pages;
            $scope.quereyData.page=data.resultObj.pageNum;

        });
    }

    //初始化
    $scope.init();
    //翻页
    $scope.changePage=function(page){
        $scope.quereyData.page=page;
        console.info($scope.quereyData.page);
        $scope.init();
    }

    $scope.showLightLog=function(entity){
        console.info(entity);
        $scope.showEntity=entity;
    }




}