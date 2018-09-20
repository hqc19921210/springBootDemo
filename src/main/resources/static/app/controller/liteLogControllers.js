
function liteLogCtrl($scope, $http, $rootScope) {
    $scope.pages=0;

    $scope.clear=function(){
        $scope.quereyData.start=null;
        $scope.quereyData.end=null;
        $scope.quereyData.deviceId=null;
    }
    //时间组件
    $("#datepickerStrat"). datepicker().on('changeDate', function () {
        $scope.quereyData.start=$("#datepickerStrat").val();
    });
    $("#datepickerEnd"). datepicker().on('changeDate', function (e) {
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
    $scope.loading=false;
    $scope.init=function(){
        $scope.loading=true;
//        $scope.showEntity={};
        $http.post("/service/queryLites",$scope.quereyData).success(function(data) {
            $scope.data = data.resultObj.list;
            $scope.pages=data.resultObj.pages;
            $scope.quereyData.page=data.resultObj.pageNum;
            $scope.loading=false;
        });
    }

    //初始化
    $scope.init();
    //翻页
    $scope.changePage=function(page){
        $scope.quereyData.page=page;
        $scope.init();
    }

//    $scope.showLightLog=function(entity){
//        console.info(entity);
//        $scope.showEntity=entity;
//    }




}