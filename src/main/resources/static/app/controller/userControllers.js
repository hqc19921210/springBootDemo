function userCtrl($scope, $http, $rootScope) {
	$http.get("service/getUsers").success(function(data) {
		$scope.users = data.resultObj;

	});
	
	$scope.chkCmp = $rootScope.user.competence;
	$scope.addUser = function() {
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
    			competence:$scope.competence
    			}).success(function(data) {
			    	console.info(data);
			    	if(data.resultObj == "errorMsg"){
			    		swal(data.message, null, "error");
			        }else{
			        	swal("新增成功", null, "success");
			        }
        });
		//修改成功后
        $scope.reloadUserList();
		$scope.closeAddModal();
    };
    
    $scope.closeAddModal = function(){
		$scope.account="";
		$scope.encordPwd ="";
		$scope.company ="";
		$scope.contact="";
		$scope.phone="";
		$scope.fax="";
		$scope.email="";
		$scope.site="";
		$scope.remark="";
		$scope.competence="";
		$("#close-add-user-modal").click();
	}
    
    
    $scope.updatePwdById = function(){
		if($scope.newPwd != $scope.checkPwd){
			$scope.newPwd ="";
			$scope.checkPwd ="";
			swal("两次密码不一致，请重新输入！","error");
		}else{
			$scope.encordNewPwd=hex_md5($scope.newPwd);
			$http.post("service/updatePwdById",
					{uid:$scope.currUid,
					newPwd :$scope.encordNewPwd}).success(function(data) {
						console.info(data);
						if(data.resultObj == "errorMsg"){
				        	swal(data.message, null, "error");
				        }else{
				        	swal("修改成功", null, "success");
				        }
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
					$scope.reloadUserList();
				}
			});
	}
	$scope.getUid = function(uid){
		$scope.currUid=uid;
		
	}
	
	$scope.reloadUserList = function(){
		$http.get("service/getUsers").success(function(data) {
			$scope.users = data.resultObj;
			
		});
	}
}

