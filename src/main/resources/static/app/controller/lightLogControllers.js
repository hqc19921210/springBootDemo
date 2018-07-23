/**
 * Created by heqichao on 2018-7-22.
 */
function lightLogCtrl($scope, $http, $rootScope) {

    //为后台请求参数 带分页数据
    $scope.quereyData={
        page:1, //当前页码 初始化为1
        size:defaultSize, //每页数据量 defaultSize全局变量
        aaa:"aaa"//其他默认的业务参数
    };

    $scope.pageArr=[1];//页码数组
    $scope.pages= $scope.pageArr.length; //总页数

    //初始化数据
    $scope.init=function(){
        $http.post("/service/queryLightLogs",$scope.quereyData).success(function(data) {
            console.info(data);
            $scope.data = data.resultObj.list;
            $scope.pages=data.resultObj.pages;
            var indexes = [];
            for(var i=1;i<=$scope.pages;i++){
                indexes.push(i);
            }
            $scope.pageArr=indexes;
            $scope.quereyData.page=data.resultObj.pageNum;
        });
    }




    //初始化
    $scope.init();

    //翻页
    $scope.changePage=function(page){
        $scope.quereyData.page=page;
        $scope.init();
    }
}