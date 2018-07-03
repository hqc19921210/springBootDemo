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
	}).otherwise({
		templateUrl: "app/module/demoList.html",
		controller: listCtrl
	});
}]);