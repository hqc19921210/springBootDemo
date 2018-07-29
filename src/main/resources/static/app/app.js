//定义主模块并注入依赖
var demoApp =angular.module("demoApp", ["ngRoute"]);

//路由配置
	demoApp.config(["$routeProvider","$httpProvider", function($routeProvider,$httpProvider) {
		$httpProvider.interceptors.push(HttpInterceptor);
		$routeProvider

	// 	.when("/player/list", {
	// 	templateUrl: "tmpl/player/list.html",
	// 	controller: playerListCtrl
	// })
	// 	.when("/player/view/:playerId/:playerName", {
	// 	templateUrl: "tmpl/player/view.html",
	// 	controller: playerViewCtrl
	// }).when("/player/add", {
	// 	templateUrl: "tmpl/player/add.html",
	// 	controller: playerAddCtrl
	// })
	.when("/module/demoChar/:ID", {
		templateUrl: "app/module/demoChar.html",
		controller: charCtrl
	}).when("/module/demoCharTest", {
		templateUrl: "app/module/demoCharTest.html",
		controller: demoCharTestCtrl
	}).when("/module/userList", {
		templateUrl: "app/module/userList.html",
		controller: userCtrl
	}).when("/module/imageChar", {
			templateUrl: "app/module/imageChar.html",
			controller: imageCharCtrl
	}).when("/module/equView", {
		templateUrl: "app/module/equipmentView.html",
		controller: equCtrl
	}).when("/module/lightLog", {
			templateUrl: "app/module/lightLog.html",
			controller: lightLogCtrl
		}).otherwise({
		templateUrl: "app/module/home.html",
		//controller: listCtrl
	});
}]);