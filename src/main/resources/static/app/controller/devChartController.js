function devChartCtrl($scope, $http, $rootScope,$routeParams) {
// $scope.cmp = !$rootScope.user ? true : ($rootScope.user.competence == 2 ? false : true) ;
    $scope.devId=$routeParams.devId;
    $scope.logId=$routeParams.logId;

    $scope.typeData={
        "peakValue":["电流峰值","KA"],
        "effectiveValue":["电流有效值","KA"],
        "waveHeadTime":["波头时间","uS"],
        "halfPeakTime":["半峰值时间","uS"],
        "actionTime":["雷击作用时间","uS"],
        "energy":["雷击能量","KA.uS"],
    };

    $scope.type='peakValue';

    //时间组件
    $("#datepickerStrat"). datepicker().on('changeDate', function () {
        $scope.quereyData.start=$("#datepickerStrat").val();
    });
    $("#datepickerEnd"). datepicker().on('changeDate', function (e) {
        $scope.quereyData.end =$("#datepickerEnd").val();
    });

    $scope.quereyData={
        size:20, //每页数据量 defaultSize全局变量
        devEUI:$scope.devId,
        logId:$scope.logId
    };

    var Dashboard = function() {
        this.$body = $("body")
        this.$realData = []
    };
    $scope.sum=0;
    $scope.max=1;

    var pcolors = ['#2b4049', '#2b9ac9'];
    var plotUploads = [];
    $scope.queryData=[];
    $scope.plotDownloads = [[0,0]];
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
                        show: false
                    },
                    shadowSize: 0,
                    /*dataLabels:{
                        enabled:false
                    }*/
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
                    //min: 0,
                    //max: $scope.max,
                    color: 'rgba(0,0,0,0)',
                    tickFormatter: function (val, axis) {
                        return val+$scope.typeData[$scope.type][1]; //单位
                    },
                },
                xaxis: {
                    color: 'rgba(0,0,0,0)',
                    tickFormatter: function (val, axis) {
                        return formatDateTime(val);
                    },
                        labelWidth:5
                },
                tooltip: true,
                tooltipOpts: {
                    content: '%y (%x) ',
                    shifts: {
                        x: -60,
                        y: 25
                    },
                    defaultTheme: false
                }
            });
    },
        //end plot graph

        //initializing various charts and componentszuo
        Dashboard.prototype.init = function() {
            this.createPlotGraph("#website-stats2", plotUploads, $scope.plotDownloads, ["", $scope.typeData[$scope.type][0]], pcolors, borderColor, bgColor);
        },

        $.Dashboard = new Dashboard, $.Dashboard.Constructor = Dashboard;
    $scope.loading=false;
    $scope.init=function () {
        $scope.loading=true;
        $http.post("/service/queryLightChart" ,$scope.quereyData).success(function(data) {
            $scope.plotDownloads =[];
            $scope.queryData=[];
            if(data.success ){
                $scope.queryData = data.resultObj;
                $scope.changepType();
            }
            $.Dashboard.init();
            $scope.loading=false;
        });
    }

    $scope.changepType=function(){
        $scope.plotDownloads =[];
        var aaa =[];
        for(var j=0;j<$scope.queryData.length;j++){
            var valueText =$scope.queryData[j][$scope.type];
            var prox = $scope.typeData[$scope.type][1]; //单位
            var value= parseFloat(valueText.replace(prox,""));

            $scope.plotDownloads.push([Date.parse(new Date($scope.queryData[j].ligntningTime)),value]);
            aaa.push([$scope.queryData[j].ligntningTime,Date.parse(new Date($scope.queryData[j].ligntningTime)),value]);
        }
        $.Dashboard.init();
    }
    //初始化
    $scope.init();

    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        var time= y + '-' + m + '-' + d+' \n '+h+':'+minute+':'+second;
       // time =time.replace(" "," \n ");
        return time;
    };
}