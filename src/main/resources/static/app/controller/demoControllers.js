

//List Controller
function listCtrl($scope, $http,$rootScope) {
	/*$http.get("data/players.json").success(function(data) {
		$scope.players = data;
	});*/
}

//Edit Controller
function charCtrl($scope, $http, $routeParams, $location) {
	$scope.ID=$routeParams.ID;
	//creating bar chart
	var $barData  = [
		{ y: '2009', a: 100, b: 90 },
		{ y: '2010', a: 75,  b: 65 },
		{ y: '2011', a: 50,  b: 40 },
		{ y: '2012', a: 75,  b: 65 },
		{ y: '2013', a: 50,  b: 40 },
		{ y: '2014', a: 75,  b: 65 },
		{ y: '2015', a: 100, b: 90 }
	];
	$.MorrisCharts.createBarChart('morris-bar-example', $barData, 'y', ['a', 'b'], ['Series A', 'Series B'], ['#2b4049', '#2b9ac9']);


	var $data  = [
		{ y: '2009', a: 100, b: 90 },
		{ y: '2010', a: 75,  b: 65 },
		{ y: '2011', a: 50,  b: 40 },
		{ y: '2012', a: 75,  b: 65 },
		{ y: '2013', a: 50,  b: 40 },
		{ y: '2014', a: 75,  b: 65 },
		{ y: '2015', a: 100, b: 90 }
	];
	$.MorrisCharts.createLineChart('morris-line-example', $data, 'y', ['a', 'b'], ['Series A', 'Series B'], ['#2b4049', '#2b9ac9']);

	/*	//获取被编辑的球员信息
	 $http.get("data/players.json").success(function(data) {
	 var i = parseInt($routeParams.playerId)-1;
	 $scope.player = data[i];
	 });*/

	$http.post("service/test").then(function(resp) { //无论是否保存成功，都进行页面跳转
		console.log("Saved Successfully! Status: " + resp);
		//$location.path("#/player/list");
	}, function(resp) {
		console.log("Saved Failly! Status: ");
		//$location.path("#/player/list");
	});




}