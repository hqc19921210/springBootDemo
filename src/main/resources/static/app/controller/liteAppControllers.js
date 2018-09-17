function liteAppCtrl($scope, $http, $rootScope) {
	$scope.equStatus={
	        "N":"在线", 
	        "F":"故障", 
	        "B":"离线", 
	    };
	//为后台请求参数 带分页数据
    $scope.quereyData={
        page:1, //当前页码 初始化为1
        size:defaultSize, //每页数据量 defaultSize全局变量
    };
	$scope.pages=0;
	$scope.loadCtl={
    		search:false,	
    		addEnq:false
    };
	$scope.chkCmp = $rootScope.user.competence;
	$scope.infoEditable = true;
    $scope.init=function(){
    	$scope.loadCtl.search = true;
    	$http.post("service/queryLiteApps",$scope.quereyData).success(function(data) {
//    		console.info(data);
    		$scope.equipments = data.resultObj.list;
    		$scope.pages=data.resultObj.pages;
    		$scope.pageArr=data.resultObj.navigatepageNums;
    		$scope.quereyData.page=data.resultObj.pageNum;
    		$scope.loadCtl.search = false;
    	});
    }
  //初始化
    $scope.init();

    //翻页
    $scope.changePage=function(page){
        $scope.quereyData.page=page;
        $scope.init();
    }
    $scope.resetSearch=function(){
        $scope.quereyData.appId=null;
        $scope.quereyData.appName=null;
        $scope.quereyData.page=1;
        $scope.pages=0;
        $scope.init();
    }
    
    $scope.addEqu = function() {
    	$scope.loadCtl.addEnq = true;
    	$scope.addApp.seleCompany=$scope.seleCompany;
        $http.post("service/addLiteApp",$scope.addApp).success(function(data) {
			    	if(data.resultObj == "errorMsg"){
			    		swal(data.message, null, "error");
			        }else{
			        	//修改成功后
			        	swal("新增成功", null, "success");
			        	$scope.init();
			        }
			    	$scope.loadCtl.addEnq = false;
			    	$scope.closeAddEquModal();
        });
		
    };
    
    $scope.seleCompetenceChg = function(){
    	$http.get("service/getCompanySeleList").success(function(data) {
    		$scope.selCompanys = data.resultObj;

    	});
	};
    
    $scope.closeAddEquModal = function(){
		$scope.addApp = null;
		$scope.seleCompany = null;
		$("#close-add-equ-modal").click();
	};
    
	$scope.delEqu = function(eid){
		swal({   
            title: "是否确定删除该应用？",   
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
		$http.post("service/deleteLiteApp",
				{eid:$scope.currDel,}).success(function(data) {
				if(data.resultObj == "errorMsg"){
					swal(data.message, null, "error");
				}else{
					swal("删除成功", null, "success");
					$scope.init();
				}
			});
	}
	
	$scope.selEquInfo = function(eqm){
		$scope.currEqu = eqm;
		if($scope.chkCmp == 2){
			$scope.seleCompetenceChg();
		}
	};
	
	$scope.selEquSecret = function(sid){
		$scope.currSid = sid;
	};
	
	$scope.closeUpdateEquModal = function(){
		$scope.infoEditable = true;
		$("#close-info-equ-modal").click();
	};
	
	$scope.closeSecretModal = function(){
		$scope.appSecret=null;
		$("#close-reset-secret-modal").click();
	};
	
	$scope.chgEdit = function(){
		$scope.infoEditable = false;
	};
	
	$scope.updateEqu = function() {
		$scope.loadCtl.updEqu = true;
		$http.post("service/updLiteApp",$scope.currEqu).success(function(data) {
					if(data.resultObj == "errorMsg"){
						swal(data.message, null, "error");
					}else{
						//修改成功后
						swal("修改成功", null, "success");
					}
					$scope.init();
					$scope.currEqu=null;
					$scope.loadCtl.updEqu = false;
					$scope.closeUpdateEquModal();
				});
		
	};
	$scope.resetSecret = function() {
		$scope.loadCtl.resetSecret = true;
		$scope.appSecret.id=$scope.currSid;
		$http.post("service/resetSecret",$scope.appSecret).success(function(data) {
			if(data.resultObj == "errorMsg"){
				$scope.closeSecretModal();
				swal(data.message, null, "error");
			}else{
				//修改成功后
				$scope.closeSecretModal();
				swal("重置成功", null, "success");
				$scope.init();
			}
			$scope.currSid=null;
			$scope.appSecret=null;
			$scope.loadCtl.resetSecret = false;
		});
		
	};
	
	
	$scope.subEquDataChg = function(equ){
		swal({   
            title: "是否确定订阅数据变化？",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "确定订阅",   
            cancelButtonText: "取消", 
            closeOnConfirm: false,   
            closeOnCancel: false 
        }, function(isConfirm){   
            if (isConfirm) {     
            	$scope.subEquDataChgById(equ);
            }  else {     
                swal("操作取消", null, "error");   
            } 
        });
		
	}
	
	$scope.subEquDataChgById = function(equ){
		$http.post("service/subLiteDataChg",equ).success(function(data) {
				if(data.resultObj == "errorMsg"){
					swal("订阅失败", "请到电信开发者中心应用订阅中删除当前数据变化订阅，然后用下面的url重新在开发者中心订阅数据变化\r\n"+equ.callbackUrl, "error");
				}else{
					swal("订阅成功", "若仍接收不到数据变化请到电信开发者中心应用订阅中删除当前数据变化订阅，然后用下面的url重新在开发者中心订阅数据变化\r\n"+equ.callbackUrl, "success");
					console.log(data);
				}
			});
	}
}