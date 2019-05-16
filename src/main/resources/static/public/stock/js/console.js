var url='/v1/optional/stock/stock/market';

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

function initScheduler() {
    var result;
    $.get(url, function(data,status){
        result = data;
        console.info(result);
        getSh(result.sh);
        getSz(result.sz);
        getCy(result.cy);
    });
}

window.setInterval('initScheduler()',5000);
