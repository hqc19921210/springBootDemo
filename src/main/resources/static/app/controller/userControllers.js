function userCtrl($scope, $http) {
	$http.get("service/getUsers").success(function(data) {
		$scope.users = data.resultObj;

	});
	$scope.addUser = function() {
//        $http.post("service/login",{"userNo":$scope.userNo,"password":$scope.password}).success(function(data) {
//        	console.info(data);
//        });
		console.log($scope.addUserForm);
		console.log($scope.addUserForm.uID);
    };
}

