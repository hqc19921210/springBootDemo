function homeCtrl($scope, $http, $rootScope,$timeout) {
	$scope.cmp = !$rootScope.user ? true : ($rootScope.user.competence == 2 ? false : true) ;
	!function($) {
//	    "use strict";

	    var Dashboard = function() {
	        this.$body = $("body")
	        this.$realData = []
	    };

	    //creates plot graph
	    Dashboard.prototype.createPlotGraph = function(selector, data1, data2, labels, colors, borderColor, bgColor) {
	      //shows tooltip
	      function showTooltip(x, y, contents) {
	        $('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css( {
	          position: 'absolute',
	          top: y + 5,
	          left: x + 5
	        }).appendTo("body").fadeIn(200);
	      }

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
	            max: 15,
	            color: 'rgba(0,0,0,0)'
	          },
	          xaxis: {
	            color: 'rgba(0,0,0,0)'
	          },
	          tooltip: true,
	          tooltipOpts: {
	              content: '%x月雷击 %y次',
	              shifts: {
	                  x: -60,
	                  y: 25
	              },
	              defaultTheme: false
	          }
	      });
	    },
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
	    },

	    

	        //initializing various charts and components
	        Dashboard.prototype.init = function() {
	          //plot graph data
	          var uploads = [];
	          var downloads = [[1, 5], [2, 12], [3,4], [4, 3], [5, 12], [6, 11], [7, 14], [8, 14], [9, 12], [10, 15], [11, 9], [12, 6]];
	          var plabels = ["", "雷击次数"];
	          var pcolors = ['#2b4049', '#2b9ac9'];
	          var borderColor = '#fff';
	          var bgColor = '#fff';
	          this.createPlotGraph("#website-stats", uploads, downloads, plabels, pcolors, borderColor, bgColor);

	            //Pie graph data
	            var pielabels = ["故障","正常","下线"];
	            var datas = [32,119, 19];
	            var colors = ['#2b4049', '#2b9ac9', "#58c9c7"];
	            this.createPieGraph("#pie-chart #pie-chart-container", pielabels , datas, colors);

	        },

	    //init Dashboard
	    $.Dashboard = new Dashboard, $.Dashboard.Constructor = Dashboard
	    
	}(window.jQuery),

	//initializing Dashboard
//	function($) {
//		
//		"use strict";
//		$.Dashboard.init(); 
//	}(window.jQuery);
	$timeout(function(){$.Dashboard.init(); }, 1000);
}