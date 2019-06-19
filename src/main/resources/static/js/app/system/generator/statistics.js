var theme_color = $MB.getThemeColor(theme);

$(document).ready(function() {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    $("#statistics").highcharts({
        chart: {
            type: "spline",
            animation: Highcharts.svg,
            marginRight: 0,
            events: {
                load: function() {
                    var series = this.series[0];
                    /*rediskeysSizeInterval = setInterval(function() {
                            $.getJSON(ctx + "generator/statistics", function(data) {
                                var x = data.create_time,
                                    y = data.generationCapacity * 1024 / 1024
                                temperature = data.temperature;
                                $("#temperature1").val(temperature+"°c")
                                if(temperature > 100) {
                                    $("#warning1").css("display", "inline");
                                }
                                else {
                                    $("#warning1").css("display", "none");
                                }
                                series.addPoint([ x, y ], true, true);
                            });
                    }, 3e3);*/
                }
            }
        },
        title: {
            text: "发电量统计",
            style: {
                "font-size": "1.2rem"
            }
        },
        xAxis: {
            type: "datetime",
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: "度"
            },
            plotLines: [ {
                value: 0,
                width: 1,
                color: "#808080"
            } ]
        },
        plotOptions: {
            spline: {
                color: theme_color
            }
        },
        credits: {
            enabled: false
        },
        tooltip: {
            formatter: function() {
                return "<b>" + this.series.name + "</b><br/>" + Highcharts.dateFormat("%Y-%m-%d %H:%M:%S", this.x) + "<br/>" + Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [ {
            name: "发电量统计（度）",
            data: function() {
                var data = [], time = new Date().getTime(), i;
                for (i = -19; i <= 0; i++) {
                    data.push({
                        x: time + i * 1e3,
                        y: Math.random() * (1e3 - 800) + 800
                    });
                }
                return data;
            }()
        } ]
    });

});