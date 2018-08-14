function userCtrl($scope, $http, $rootScope) {
	//为后台请求参数 带分页数据
    $scope.quereyData={
        page:1, //当前页码 初始化为1
        size:defaultSize, //每页数据量 defaultSize全局变量
    };
    //loading控制
    $scope.loadCtl={
    		search:false,	
    		addUser:false,	
    		upPWD:false	
    };
	$scope.pages=0;
	$scope.chkCmp = $rootScope.user.competence;
	$scope.chkId = $rootScope.user.id;
    $scope.init=function(){
    	$scope.loadCtl.search = true;
    	$http.post("service/getUsers",$scope.quereyData).success(function(data) {
    		$scope.users = data.resultObj.list;
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
        $scope.quereyData.account=null;
        $scope.quereyData.company=null;
        $scope.quereyData.seleCompetence=null;
        $scope.quereyData.page=1;
        $scope.pages=0;
        $scope.init();
    }
	$scope.addUser = function() {
		$scope.loadCtl.addUser = true;
		$scope.encordPwd=hex_md5($scope.password);
        $http.post("service/addUser",
        		{account:$scope.account,
    			encordPwd:$scope.encordPwd,
    			company:$scope.company,
    			contact:$scope.contact,
    			phone:$scope.phone,
    			fax:$scope.fax,
    			email:$scope.email,
    			site:$scope.site,
    			remark:$scope.remark,
    			competence:$scope.seleCompetence,
    			parentId:(!$scope.seleCompany || $scope.seleCompetence != 4 || $scope.chkCmp !=2 ?
    							$rootScope.user.id : $scope.seleCompany)
    			}).success(function(data) {
			    	if(data.resultObj == "errorMsg"){
			    		$("#close-add-user-modal").click();
			    		swal(data.message, null, "error");
			        }else{
			        	//修改成功后
			        	$scope.closeAddModal();
			        	swal("新增成功", null, "success");
			            $scope.init();
			        }
			    	$scope.loadCtl.addUser = false;
        });
		
    };
    $scope.seleCompetenceChg = function(){
    	$http.get("service/getCompanySeleList").success(function(data) {
    		$scope.selCompanys = data.resultObj;

    	});
	}
    
    $scope.closeAddModal = function(){
		$scope.account="";
		$scope.password ="";
		$scope.company ="";
		$scope.contact="";
		$scope.phone="";
		$scope.fax="";
		$scope.email="";
		$scope.site="";
		$scope.remark="";
		$scope.seleCompetence="";
		$scope.seleCompany = null;
		$("#close-add-user-modal").click();
	}
    
    
    $scope.updatePwdById = function(){
    	$scope.loadCtl.upPWD = true;
		if($scope.newPwd != $scope.checkPwd){
			$scope.newPwd ="";
			$scope.checkPwd ="";
			swal("两次密码不一致，请重新输入！","error");
		}else{
			$scope.encordNewPwd=hex_md5($scope.newPwd);
			$http.post("service/updatePwdById",
					{uid:$scope.currUid,
					newPwd :$scope.encordNewPwd}).success(function(data) {
						if(data.resultObj == "errorMsg"){
				        	swal(data.message, null, "error");
				        }else{
				        	swal("修改成功", null, "success");
				        }
						$scope.loadCtl.upPWD = false;
			});

		}
		$scope.closePwdById();
	}

	$scope.closePwdById = function(){
		$scope.newPwd ="";
		$scope.checkPwd ="";
		$scope.encordNewPwd="";
		$("#updatePwdById").click();
	}
	
	$scope.delUser = function(uid){
		swal({   
            title: "是否确定删除该用户？",   
            text: "删除后数据将不能查看!",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#DD6B55",   
            confirmButtonText: "确定删除",   
            cancelButtonText: "取消", 
            closeOnConfirm: false,   
            closeOnCancel: false 
        }, function(isConfirm){   
            if (isConfirm) {     
            	$scope.delUserById(uid);
            }  else {     
                swal("操作取消", null, "error");   
            } 
        });
		
	}
	
	$scope.delUserById = function(uid){
		$scope.currDel = uid;
		$http.post("service/deleteUserById",
				{uid:$scope.currDel,}).success(function(data) {
				if(data.resultObj == "errorMsg"){
					swal(data.message, null, "error");
				}else{
					swal("删除成功", null, "success");
					$scope.init();
				}
			});
	}
	$scope.getUid = function(uid){
		$scope.currUid=uid;
		
	}
	
}

