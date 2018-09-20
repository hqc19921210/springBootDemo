function liteEquCtrl($scope, $http, $rootScope) {
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
    	$http.post("service/queryLiteEqus",$scope.quereyData).success(function(data) {
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
        $scope.quereyData.deviceId=null;
        $scope.quereyData.name=null;
        $scope.quereyData.agreement=null;
        $scope.quereyData.page=1;
        $scope.pages=0;
        $scope.init();
    }
    
    $scope.addEqu = function() {
    	$scope.loadCtl.addEnq = true;
        $http.post("service/addLiteEqu",
        		{deviceId:$scope.deviceId,
        	name:$scope.name,
        	verification:$scope.verification,
        	type:$scope.type,
        	supportId:$scope.supportId,
        	supportName:$scope.supportName,
        	agreement:$scope.agreement,
        	remark:$scope.remark,
        	appId:$scope.seleApp,
    			seleCompany:$scope.seleCompany
    			}).success(function(data) {
			    	if(data.resultObj == "errorMsg"){
			    		swal(data.message, null, "error");
			        }else{
			        	//修改成功后
			        	swal("新增成功", null, "success");
			        	$scope.init();
			        }
			    	$scope.closeAddEquModal();
			    	$scope.loadCtl.addEnq = false;
        });
		
    };
    
    $scope.seleAddBtn = function(){
    	$scope.seleCompetenceChg();
    	$scope.seleAppChg();
    };
    
    $scope.seleCompetenceChg = function(){
    	$http.get("service/getCompanySeleList").success(function(data) {
    		$scope.selCompanys = data.resultObj;

    	});
	};
    
	$scope.seleAppChg = function(){
		$http.get("service/getAppSeleList").success(function(data) {
			$scope.selApps = data.resultObj;
			
		});
	};
	
    $scope.closeAddEquModal = function(){
		$scope.deviceId = null;
		$scope.name = null;
		$scope.verification = null;
		$scope.type = null;
		$scope.supportId = null;
		$scope.supportName = null;
		$scope.agreement = null;
		$scope.remark = null;
		$scope.seleCompany = null;
		$("#close-add-equ-modal").click();
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
		$http.post("service/deleteLiteEqu",
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
		$scope.seleAppChg();
		if($scope.chkCmp == 2){
			$scope.seleCompetenceChg();
		}
	};
	$scope.selEquCmd = function(eqm){
		$scope.loadCtl.cmdEqu = true;
		$scope.getEquForCmd(eqm.id)
	};
	
	$scope.closeUpdateEquModal = function(){
		$scope.infoEditable = true;
		$("#close-info-equ-modal").click();
	};
	
	$scope.chgEdit = function(){
		$scope.infoEditable = false;
	};
	
	$scope.updateEqu = function() {
		$scope.loadCtl.updEqu = true;
		$http.post("service/updLiteEqu",$scope.currEqu).success(function(data) {
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
	
	 $scope.getEquForCmd = function(eid){
	    	$http.post("service/getEquForCmd",{eid:eid}).success(function(data) {
	    		$scope.cmdEqu = data.resultObj;
	    		$scope.loadCtl.cmdEqu = false;

	    	});
		};
	
	$scope.commandEqu = function() {
		$scope.loadCtl.cmdEqu = true;
        $http.post("service/cmdLiteEqu",$scope.cmdEqu).success(function(data) {
			$scope.cmdEqu.res = data.resultObj;
			$scope.loadCtl.cmdEqu = false;
        });
		
    };
    $scope.closeCmdEquModal = function(){
    	$scope.cmdEqu.remark = null;
    	$scope.cmdEqu.res = null;
		$("#close-cmd-equ-modal").click();
	};
}