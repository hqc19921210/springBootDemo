demoApp.directive("btnLoading", function() {
	return {
		restrict:'A',
		scope: true,
		link:function(scope,element,attr){
			scope.prevText=element.text();
			scope.$watch(function(){
				return scope.$eval(attr.btnLoading);
			},function(value){
				if(angular.isDefined(value)){
					//element.toggleClass('disabled',value);
					value?element.attr('disabled',true):element.removeAttr('disabled');
					element.text((value?"加载中...":scope.prevText));
				}
			});
		}
    }
});