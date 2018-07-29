function equCtrl($scope, $http, $rootScope) {
	//为后台请求参数 带分页数据
    $scope.quereyData={
        page:1, //当前页码 初始化为1
        size:defaultSize, //每页数据量 defaultSize全局变量
    };
	$scope.pages=0;
    $scope.init=function(){
    	$http.post("service/getEquipments",$scope.quereyData).success(function(data) {
    		console.info(data);
    		$scope.equipments = data.resultObj.list;
    		$scope.pages=data.resultObj.pages;
    		$scope.pageArr=data.resultObj.navigatepageNums;
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
    
//    $('#onlineDatepicker').datepicker({
//	    format: "yyyy-mm-dd",
//	    todayBtn: "linked",
//	    autoclose:true,//加上这个参数
//	    language: "zh-CN"
//	});
    $scope.chkCmp = $rootScope.user.competence;
    $scope.addEqu = function() {
    	console.log($scope.Cdate)
        $http.post("service/addEqu",
        		{eid:$scope.eid,
    			eType:$scope.eType,
    			amount:$scope.amount,
    			eRange:$scope.eRange,
    			eTotal:$scope.eTotal,
    			alarms:$scope.alarms,
    			eRemark:$scope.eRemark,
    			seleCompany:$scope.seleCompany
    			}).success(function(data) {
			    	console.info(data);
			    	if(data.resultObj == "errorMsg"){
			    		$("#close-add-equ-modal").click();
			    		swal(data.message, null, "error");
			        }else{
			        	//修改成功后
			        	$scope.closeAddEquModal();
			        	swal("新增成功", null, "success");
			            $scope.reloadEquList();
			        }
        });
		
    };
    
    $scope.seleCompetenceChg = function(){
    	$http.get("service/getCompanySeleList").success(function(data) {
    		$scope.selCompanys = data.resultObj;

    	});
	};
    
    $scope.closeAddEquModal = function(){
		$scope.eid = null;
		$scope.eType = null;
		$scope.amount = null;
		$scope.eRange = null;
		$scope.eTotal = null;
		$scope.alarms = null;
		$scope.eRemark = null;
		$scope.seleCompany = null;
		$("#close-add-equ-modal").click();
	};
    
    $scope.reloadEquList = function(){
		$scope.init();
	};
	
	$scope.delEqu = function(eid){
		swal({   
            title: "是否确定删除该设备？",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "确定删除",   
            cancelButtonText: "取消", 
            closeOnConfirm: false,   
            closeOnCancel: false 
        }, function(isConfirm){   
            if (isConfirm) {     
            	$scope.delEquById(eid);
            }  else {     
                swal("操作取消", null, "error");   
            } 
        });
		
	}
	
	$scope.delEquById = function(eid){
		$scope.currDel = eid;
		$http.post("service/delEqu",
				{eid:$scope.currDel,}).success(function(data) {
				if(data.resultObj == "errorMsg"){
					swal(data.message, null, "error");
				}else{
					swal("删除成功", null, "success");
					$scope.reloadEquList();
				}
			});
	}
}