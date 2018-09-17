function homeCtrl($scope, $http, $rootScope,$timeout) {
	$scope.cmp = !$rootScope.user ? true : ($rootScope.user.competence == 2 ? false : true) ;
	!function($) {
//	    "use strict";

	    var Dashboard = function() {
	        this.$body = $("body")
	        this.$realData = []
	    };
		$scope.sum=0;
	    $scope.max= 100;



	    //creates plot graph
	    Dashboard.prototype.createPlotGraph = function(selector, data1, data2, labels, colors, borderColor, bgColor) {

			//shows tooltip
			function showTooltip(x, y, contents) {
				$('<div id="tooltip" class="tooltipflot" style="font-size: 12px;color: #5aa;">' + contents + '</div>').css( {
					position: 'absolute',
					top: y - 25,
					left: x + 5
				}).appendTo("body").fadeIn(200);
			};


			var previousPoint = null;
			// 绑定提示事件
			$(selector).bind("plothover", function (event, pos, item) {
				if (item) {
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
						$("#tooltip").remove();
						var x = item.datapoint[0].toFixed(0);
						var y = item.datapoint[1].toFixed(0);

						var tip = x+"月雷击"+y+"次";
						showTooltip(item.pageX, item.pageY,tip);
					}
				}
				else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

	       $.plot($(selector),
	          [ { data: data1,
	            label: labels[0],
	            color: colors[0]
	          },
	          { data: data2,
	            label: labels[1],
	            color: colors[1]
	          }
	        ],
	        {
	            series: {
	               lines: {
	              show: true,
	              fill: true,
	              lineWidth: 2,
	              fillColor: {
	                colors: [ { opacity: 0.0 },
	                          { opacity: 0.0 }
	                        ]
	              }
	            },
	            points: {
	              show: true
	            },
	            shadowSize: 0
	            },
	            legend: {
	            position: 'nw'
	          },
	          grid: {
	            hoverable: true,
	            clickable: true,
	            borderColor: borderColor,
	            borderWidth: 0,
	            labelMargin: 10,
	            backgroundColor: bgColor
	          },
	          yaxis: {
	            min: 0,
	            max:  $scope.max,
	            color: 'rgba(0,0,0,0)'
	          },
	          xaxis: {
	            color: 'rgba(0,0,0,0)'
	          },
	          /*tooltip: true,
	          tooltipOpts: {
	              content: '%x月雷击 %y次',
	              shifts: {
	                  x: -60,
	                  y: 25
	              },
	              defaultTheme: false
	          }*/
	      });
	    };
	    //end plot graph

	    //creates Pie Chart
	    Dashboard.prototype.createPieGraph = function(selector, labels, datas, colors) {
	        var data = [{
	            label: labels[0],
	            data: datas[0]
	        }, {
	            label: labels[1],
	            data: datas[1]
	        }, {
	            label: labels[2],
	            data: datas[2]
	        }];
	        var options = {
	            series: {
	                pie: {
	                    show: true
	                }
	            },
	            legend : {
	              show : false
	            },
	            grid : {
	              hoverable : true,
	              clickable : true
	            },
	            colors : colors,
	            tooltip : true,
	            tooltipOpts : {
	              content : "%s, %p.0%"
	            }
	        };

	        $.plot($(selector), data, options);
	    };

	        //initializing various charts and components
	        Dashboard.prototype.init = function() {
				var this_=this;
				$http.post("/service/queryLightCountByYear" ).success(function(data) {
					console.info(data);
					var queryData = data.resultObj;
					var uploads = [];
					var downloads = [[1, 0], [2, 0], [3,0], [4, 0], [5, 0], [6, 0], [7, 0], [8, 0], [9, 0], [10, 0], [11,0], [12,0]];
					if(queryData){
						downloads =[];
						for(var i=0;i<12;i++){
							var mouth=i+1;
							var count=0;
							if(mouth>=10){
								mouth=mouth+"";
							}else{
								mouth="0"+mouth;
							}
							for(var j=0;j<queryData.length;j++){
								if(mouth == queryData[j].months){
									count= queryData[j].count;
									if( $scope.max<count){
										$scope.max=count;
									}
									$scope.sum=$scope.sum+count;
									break;
								}
							}
							downloads.push([i+1,count]);
					}

					}
					//var downloads = [[1, 5], [2, 12], [3,4], [4, 3], [5, 12], [6, 11], [7, 14], [8, 14], [9, 12], [10, 15], [11, 9], [12, 6]];
					var plabels = ["", "雷击次数"];
					var pcolors = ['#2b4049', '#2b9ac9'];
					var borderColor = '#fff';
					var bgColor = '#fff';
					this_.createPlotGraph("#website-stats", uploads, downloads, plabels, pcolors, borderColor, bgColor);
				});
			/*	var uploads = [];
				var downloads = [[1, 5], [2, 12], [3,4], [4, 3], [5, 12], [6, 11], [7, 14], [8, 14], [9, 12], [10, 15], [11, 9], [12, 6]];
				var plabels = ["", "雷击次数"];
				var pcolors = ['#2b4049', '#2b9ac9'];
				var borderColor = '#fff';
				var bgColor = '#fff';
				console.info(downloads);
				this_.createPlotGraph("#website-stats", uploads, downloads, plabels, pcolors, borderColor, bgColor);*/

	            //Pie graph data
	            var pielabels = ["故障","正常","下线"];
	            var datas = [32,119, 19];
	            var colors = ['#2b4049', '#2b9ac9', "#58c9c7"];
	            this.createPieGraph("#pie-chart #pie-chart-container", pielabels , datas, colors);

	        };

	    //init Dashboard
	    $.Dashboard = new Dashboard, $.Dashboard.Constructor = Dashboard;


	    
	}(window.jQuery),

	//initializing Dashboard
//	function($) {
//		
//		"use strict";
//		$.Dashboard.init(); 
//	}(window.jQuery);
	$timeout(function(){$.Dashboard.init(); }, 1000);
}