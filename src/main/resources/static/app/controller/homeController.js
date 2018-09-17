function homeCtrl($scope, $http, $rootScope,$timeout) {
	$scope.cmp = !$rootScope.user ? true : ($rootScope.user.competence == 2 ? false : true) ;
	var Dashboard = function() {
        this.$body = $("body")
        this.$realData = []
    };
    $scope.sum=0;
    $scope.max= 100;
    var plabels = ["", "雷击次数"];
    var pcolors = ['#2b4049', '#2b9ac9'];
    var plotUploads = [];
    var plotDownloads = [[1, 0], [2, 0], [3,0], [4, 0], [5, 0], [6, 0], [7, 0], [8, 0], [9, 0], [10, 0], [11,0], [12,0]];
    var borderColor = '#fff';
    var bgColor = '#fff';
    var pielabels = ["GPRS","LORA","NBIOT"];
    var pieColors = ['#2b4049', '#2b9ac9', "#58c9c7"];
	var pieDatas = [0,0,0];
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
            max: $scope.max,
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
              content : function(label, xval, yval, flotItem){
            	  return label+"设备:"+yval+"台";
              }
//            	  content : "%s, %p.0%"
            }
        };

        $.plot($(selector), data, options);
    },

    

        //initializing various charts and components
        Dashboard.prototype.init = function() {
    	
          //plot graph data
          this.createPlotGraph("#website-stats", plotUploads, plotDownloads, plabels, pcolors, borderColor, bgColor);

            //Pie graph data
            this.createPieGraph("#pie-chart #pie-chart-container", pielabels , pieDatas, pieColors);

        },

    //init Dashboard
    $.Dashboard = new Dashboard, $.Dashboard.Constructor = Dashboard;
    $http.post("/service/queryLightCountByYear" ).success(function(data) {
		console.info(data);
		var queryData = data.resultObj;
		if(queryData){
			plotDownloads =[];
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
				plotDownloads.push([i+1,count]);
			}

		}
		$http.get("service/getHomePie").success(function(data) {
			pieDatas = data.resultObj.pieMap;
			$scope.home=data.resultObj;
			$.Dashboard.init();
		});

	});
//    console.log(datas);
//	$timeout(function(){$.Dashboard.init(); }, 1000);
}