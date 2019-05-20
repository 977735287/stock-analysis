var url='/v1/optional/stock/market';
var minute_url='/v1/optional/stock/min/data';

// 上证
function getSh(sh_elements) {
    // var sh_elements = hq_str_s_sh000001.split(",");
    var sh_name = sh_elements[0];
    var sh_current_price = parseFloat(sh_elements[1]).toFixed(2);
    var sh_ups_downs = parseFloat(sh_elements[2]).toFixed(2);
    var sh_current_percent;
    var sh_current_ups_downs;
    if (sh_ups_downs >= 0)  {
        sh_current_ups_downs = '+' + sh_ups_downs;
        sh_current_percent = '+' + sh_elements[3] + '%';
    }else {
        sh_current_ups_downs = '-' + sh_ups_downs;
        sh_current_percent = '-' + sh_elements[3] + '%';
    }
    var sh_current_volume = sh_elements[4];
    var sh_current_turnover = sh_elements[5];
// sh_current_turnover = (parseFloat(sh_current_turnover)/10000).toFixed(2);
    document.getElementById('sh_name').innerHTML = sh_name;
    document.getElementById('sh_current_price').innerHTML = sh_current_price;
    document.getElementById('sh_current_ups_downs').innerHTML = sh_current_ups_downs;
    document.getElementById('sh_current_percent').innerHTML = sh_current_percent;
    document.getElementById('sh_current_volume').innerHTML = sh_current_volume;
    document.getElementById('sh_current_turnover').innerHTML = sh_current_turnover;
}


// 深证
function getSz(sz_elements) {
    // var sz_elements = hq_str_s_sz399001.split(",");
    var sz_name = sz_elements[0];
    var sz_current_price = parseFloat(sz_elements[1]).toFixed(2);
    var sz_ups_downs = parseFloat(sz_elements[2]).toFixed(2);
    var sz_current_percent;
    var sz_current_ups_downs;
    if (sz_ups_downs >= 0)  {
        sz_current_ups_downs = '+' + sz_ups_downs;
        sz_current_percent = '+' + sz_elements[3] + '%';
    }else {
        sz_current_ups_downs = '-' + sz_ups_downs;
        sz_current_percent = '-' + sz_elements[3] + '%';
    }
    var sz_current_volume = sz_elements[4];
    var sz_current_turnover = sz_elements[5];
// sz_current_turnover = (parseFloat(sz_current_turnover)/10000).toFixed(2);
    document.getElementById('sz_name').innerHTML = sz_name;
    document.getElementById('sz_current_price').innerHTML = sz_current_price;
    document.getElementById('sz_current_ups_downs').innerHTML = sz_current_ups_downs;
    document.getElementById('sz_current_percent').innerHTML = sz_current_percent;
    document.getElementById('sz_current_volume').innerHTML = sz_current_volume;
    document.getElementById('sz_current_turnover').innerHTML = sz_current_turnover;
}


// 创业
function getCy(cy_elements) {
    // var cy_elements = hq_str_s_sz399006.split(",");
    var cy_name = cy_elements[0];
    var cy_current_price = parseFloat(cy_elements[1]).toFixed(2);
    var cy_ups_downs = parseFloat(cy_elements[2]).toFixed(2);
    var cy_current_percent;
    var cy_current_ups_downs;
    if (cy_ups_downs >= 0)  {
        cy_current_ups_downs = '+' + cy_ups_downs;
        cy_current_percent = '+' + cy_elements[3] + '%';
    }else {
        cy_current_ups_downs = '-' + cy_ups_downs;
        cy_current_percent = '-' + cy_elements[3] + '%';
    }
    var cy_current_volume = cy_elements[4];
    var cy_current_turnover = cy_elements[5];
// cy_current_turnover = (parseFloat(cy_current_turnover)/10000).toFixed(2);
    document.getElementById('cy_name').innerHTML = cy_name;
    document.getElementById('cy_current_price').innerHTML = cy_current_price;
    document.getElementById('cy_current_ups_downs').innerHTML = cy_current_ups_downs;
    document.getElementById('cy_current_percent').innerHTML = cy_current_percent;
    document.getElementById('cy_current_volume').innerHTML = cy_current_volume;
    document.getElementById('cy_current_turnover').innerHTML = cy_current_turnover;
}
$.get(url, function(data,status){
    getSh(data.sh);
    getSz(data.sz);
    getCy(data.cy);
});

function initScheduler() {
    var result;
    $.get(url, function(data,status){
        result = data;
        getSh(result.sh);
        getSz(result.sz);
        getCy(result.cy);
    });
}

window.setInterval('initScheduler()',5000);

function getMinuteData() {
    var hour = 9;
    var minute = 30;
    var data = [];
    var index = 0;
    while (hour <= 11){
        while (minute < 60 && hour < 11){
            data[index++] = (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
            minute ++;
        }
        while (minute < 30 && hour == 11){
            data[index++] = (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
            minute ++;
        }
        if (minute == 30 && hour == 11) {
            data[index++] = "11:30/13:00";
        }
        if (minute == 60) {
            minute = 0;
        }
        hour ++;
    }
    hour = 13;
    minute = 1;
    while (hour <= 15){
        while (minute < 60 && hour < 15){
            data[index++] = hour + ":" + (minute < 10 ? "0" + minute : minute);
            minute ++;
        }
        if (minute == 60) {
            minute = 0;
        }
        if (hour == 15) {
            data[index++] = "15:00"
        }
        hour ++;
    }
    return data;
}

var minPrice = 0;
var maxPrice = 0;
var minPercent = 0;
var maxPercent = 0;

function getStockData(x_data, code, type) {
    $.get(minute_url + "/" + type + "/" + code, function(result,status){
        var yc = parseFloat(result.info.yc);
        var h = parseFloat(result.info.h);
        var l = parseFloat(result.info.l);
        var highChange;
        var lowChange = yc - l;
        if (h > yc) {
            highChange = h - yc;
        } else {
            highChange = yc - h;
        }
        if (l > yc) {
            lowChange = l - yc;
        } else {
            highChange = yc - l;
        }
        var y_interval = 0;
        var y_interva2 = 0;
        if (highChange > lowChange) {
            maxPrice = h;
            minPrice = (yc - highChange).toFixed(2);
            y_interval = highChange / 3;
            maxPercent = highChange / yc;
            minPercent = - maxPercent;
        } else {
            maxPrice = (yc + lowChange).toFixed(2);
            minPrice = l;
            y_interval = lowChange / 3;
            maxPercent = lowChange / yc;
            minPercent = - maxPercent;
        }
        y_interva2 = maxPercent / 3;
        var data1 = [];
        var data2 = [];
        for (var i = 0; i < result.data.length; i++) {
            data1[i] = parseFloat(result.data[i].split(",")[1]).toFixed(2);
            data2[i] = (parseFloat(result.data[i].split(",")[1]) - yc) / yc;
        }
        settingMinuteData(x_data, data1, data2, y_interval, y_interva2, yc);
    });
}

function settingMinuteData(x_data, y_data1, y_data2, y_interval1, y_interval2, yc) {
    console.info(y_data2);
    var kLineChart = echarts.init(document.getElementById('k_line_main'));
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer:{
                show: true,
                type : 'cross',
                lineStyle: {
                    type : 'dashed',
                    width : 1
                }
            },
            formatter: function (params) {
                var res = "价格：" + params[0].value;
                res += "<br/>涨幅：" + (params[1].value * 100).toFixed(2) + "%";
                return res;
            }
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : true,
                axisLabel:{
                    interval:29,
                },
                axisTick: {show:false},
                splitLine: {show:false},
                axisLine:{show:false},
                data : x_data
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitNumber: 5,
                minInterval:0,
                interval:y_interval1,
                min: minPrice,
                max: maxPrice,
                axisTick:{       //y轴刻度线
                    show:false
                },
                axisLabel: {
                    formatter: function (v) {
                        return v.toFixed(2);
                    }
                },
                axisPointer:{
                    show: true,
                    label:{
                        show: false, //不显示指示器的文本标签
                    }
                }
            },
            {
                type : 'value',
                splitNumber: 5,
                minInterval:0,
                interval:y_interval2,
                min: minPercent,
                max: maxPercent,
                axisTick:{       //y轴刻度线
                    show:false
                },
                axisLabel: {
                    formatter: function (v) {
                        var rtn = (v * 100).toFixed(2);
                        if (rtn == "-0.00") {
                            rtn = " 0.00";
                            v = 0.00;
                        }
                        if (v > 0) {
                            rtn = " " + rtn;
                        }
                        return rtn + "%";
                    }
                },
                axisPointer:{
                    show: true,
                    label:{
                        show: false, //不显示指示器的文本标签
                    }
                }
            }
        ],
        series : [
            {
                name:'价格',
                type:'line',
                yAxisIndex: 0,
                symbol: 'none',
                data:y_data1,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor : '#1e90ff'
                        }
                    }
                },
                markPoint : {
                    symbol: 'emptyPin',
                    itemStyle : {
                        normal : {
                            color:'#1e90ff',
                            label : {
                                show:true,
                                position:'top',
                                formatter: function (param) {
                                    return param.value.toFixed(2);
                                }
                            }
                        }
                    },
                    data : [
                        {type : 'max', name: '当日最高', symbolSize:5},
                        {type : 'min', name: '当日最低', symbolSize:5}
                    ]
                },
                markLine : {
                    symbol : 'none',
                    itemStyle : {
                        normal : {
                            color:'#1e90ff',
                            label : {
                                show:false,
                                formatter: function (param) {
                                    return param.value.toFixed(2);
                                }
                            }
                        }
                    },
                    data : [
                        {yAxis:yc}
                    ]
                }
            },
            {
                name:'涨幅',
                type:'line',
                yAxisIndex: 1,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor : '#1e90ff'
                        }
                    }
                },
                data:y_data2
            }
        ]
    };
// 使用刚指定的配置项和数据显示图表。
    kLineChart.setOption(option);
}
getStockData(getMinuteData(), "000001", "sh");
var timer = window.setInterval('settingMinuteLine("000001", "sh")',6000);
function settingMinuteLine(code, type) {
    clearInterval(timer);
    getStockData(getMinuteData(), code, type);
    timer = window.setInterval('settingMinuteLine(code, type)',6000);
}
