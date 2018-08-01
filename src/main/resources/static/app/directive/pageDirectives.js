demoApp.directive("pageInfo", function() {
	return {
		restrict: 'EA',
		replace: true,
		scope: {
			pages: '=', //总页数
			curpage:'=', //当前页
			change: '&', //点击事件
		},
		template:
		' <div class="col-md-12 col-sm-12 col-xs-12">'
		+'<div class="" style="text-align: center">'
		+'<ul class="pagination m-b-5">'
		+'<li ng-repeat="obj in pageArr">'
		+'<a ng-if="obj.show" ng-click="changePage(obj.num)"  ng-class="{true:\'page-put\'}[curpage==obj.num]" ng-bind="obj.num"></a>'
		+'<a ng-if="obj.hide" >...</a>'
		+'</li>'
		+'</ul>'
		+'</div>'
		+'</div>',
		controller: function ($scope,$attrs) {

			/*  监听最大页数，如果页数变化，重新生成页数数组
			 * */
			var watch = $scope.$watch('pages', function (newValue, oldValue, scope) {
//				console.info("newValue:"+newValue);
				$scope.pageFunc();
			});


			$scope.pageFunc=function () {
//				console.info($scope.pages);
//				console.info($scope.curpage);

				var indexes = [{num:0}];
				for(var i=1;i<=$scope.pages;i++){
					var obj={num:i};
					indexes.push(obj);
				}
				for(var i=1;i<=$scope.pages;i++){
					if(i == 1 || i==$scope.pages){
						indexes[i].show=true;
					}
					else if(i >= $scope.curpage-2 && i<= $scope.curpage+2){
						indexes[i].show=true;
					}else if(i== $scope.curpage-3 || i== $scope.curpage+3){
						indexes[i].hide=true;
					}
				}
				if($scope.curpage == 1  ){
					var len =$scope.pages>=5?5:$scope.pages;
					for(var j=1;j<=len;j++) {
						indexes[j].show = true;
						indexes[j].hide=false;
					}
					if($scope.pages>=6){
						indexes[$scope.pages-1].show = false;
						indexes[$scope.pages-1].hide=true;
					}
				}else if($scope.curpage ==$scope.pages){
					var len =$scope.pages>=5?5:$scope.pages;
					for(var j=$scope.pages;j>$scope.pages-len;j--) {
						indexes[j].show = true;
						indexes[j].hide = false;
					}
					if($scope.pages>=6){
						indexes[2].show = false;
						indexes[2].hide=true;
					}
				}else if($scope.curpage ==2  && $scope.pages >=6){
					indexes[5].show = true;
					indexes[5].hide=false;
					indexes[6].show = false;
					indexes[6].hide=true;
					indexes[$scope.pages].show = true;
					indexes[$scope.pages].hide=false;
				}else if($scope.curpage +1 ==$scope.pages && $scope.pages >=6){
					indexes[$scope.pages-4].show = true;
					indexes[$scope.pages-4].hide=false;
					indexes[$scope.pages-5].show = false;
					indexes[$scope.pages-5].hide=true;
					indexes[1].show = true;
					indexes[1].hide=false;
				}
				$scope.pageArr=indexes;
//				console.info(indexes);
			}



			//翻页
			$scope.changePage=function(page){
				if($scope.curpage==page){
					return;
				}
				$scope.curpage=page;
				$scope.pageFunc();
				if ($attrs.change) {
					 $scope.change({page: $scope.curpage});
				}
			}

			$scope.pageFunc();
		}
	}
});