layui.use('table', function () {
    var table = layui.table;

    table.render({
        elem: '#optional_stock'
        , url: '/v1/optional/stock/page'
        , title: '数据表'
        , cols: [[
            {field: 'id', title: 'ID', width: 80, fixed: 'left'}
            , {field: 'code', title: '编码', width: 120}
            , {field: 'name', title: '名称', width: 150, edit: 'text'}
            , {field: 'area', title: '类型', width: 80}
        ]]
        , page: true
        , response: {
            statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
        }
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": 200, //解析接口状态
                "msg": '', //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.list //解析数据列表
            };
        }
    });

    var $ = layui.$, active = {
        getCheckData: function () { //获取选中数据
            // console.info(table);
            // var checkStatus = table.checkStatus('optional_stock')
            //     ,data = checkStatus.data;
            // // layer.alert(JSON.stringify(data));
            // layer.alert(JSON.stringify(table.cache.optional_stock));
            $.ajax({
                url: '/v1/optional/stock/update/batch',
                type: "PUT",
                data: JSON.stringify(table.cache.optional_stock),
                headers: {
                    "Content-Type": "application/json",
                    "X-HTTP-Method-Override": "PUT" }, //PUT,DELETE
                success: function (msg) {
                    layer.alert('success');
                },
                error: function (xhr, textstatus, thrown) {
                    layer.alert('fail');
                }
            });
        }
    };

    $('.form-box .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});