/**
 * Created by heqichao on 2018-7-22.
 */
function lightLogCtrl($scope, $http, $rootScope, $location) {
    $scope.pages=0;

    $scope.clear=function(){
        $scope.quereyData.start=null;
        $scope.quereyData.end=null;
        $scope.quereyData.functionCode=null;
        $scope.quereyData.devEUI=null;
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
        $scope.showEntity={};
        $http.post("/service/queryLightLogs",$scope.quereyData).success(function(data) {
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

    $scope.showLightLog=function(entity){
        $scope.showEntity=entity;
    }

    $scope.gotoDevChart=function(entity){
        $location.path("/module/devChart/"+entity.devEUI+"/"+entity.id);
    }

    $scope.deleteLog = function(){
        swal({
            title: "是否确定删除所查询到的数据？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定删除",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function(isConfirm){
            if (isConfirm) {
                $http.post("service/deleteLightLog",$scope.quereyData).success(function(data) {
                    if(data.resultObj == "errorMsg"){
                        swal(data.message, null, "error");
                    }else{
                        swal("删除成功", null, "success");
                        $scope.init();
                    }
                });
            }  else {
                swal("操作取消", null, "error");
            }
        });

    }
}