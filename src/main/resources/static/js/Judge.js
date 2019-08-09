
var appName="/JudgeByMonth/";
var factories = [];

/**
 * 请求后台查询数据
 */
function queryData(startDate, endDate) {
    var loadIndex = layer.load();

    $.post(appName + 'showData?startDate=' + formatDate(startDate) + '&endDate=' + formatDate(endDate)).then(function (data) {
        var stinkcount = 0;
        var arr = [];
        for(var i = 0; i< data.length;i++){
            if(data[i].maxTvocDensity === undefined){
                stinkcount++;
            }
        }
        tvocCount = data.length-stinkcount;
        for (var i = 0 ; i < stinkcount; i++) {
            arr.push({
                "id": i + 1,
                "name": getNameById(data[i].id),
                "recordCount": data[i].recordCount,
                "recordTvocCount": data[i+stinkcount].recordTvocCount,
                "densityCount": data[i].densityCount,
                "densityTvocCount": data[i+stinkcount].densityTvocCount,
                "maxDensity": data[i].maxDensity,
                "maxTvocDensity": data[i+stinkcount].maxTvocDensity,
                "ratio": parseInt(data[i].densityCount / data[i].recordCount * 100),
                "ratioTvoc": parseInt(data[i+stinkcount].densityTvocCount / data[i+stinkcount].recordTvocCount * 100),
            })
        }
        //关闭加载圈
        layer.close(loadIndex)

        $('#dataTable').DataTable({
            "bDestroy": true,
            "destroy": true,
            "searching": false,
            // 是否允许排序
            "ordering": true,
            // 初期排序列
            //"order": [[0,'asc'],[1,'desc']],
            // 是否显示情报 就是"当前显示1/100记录"这个信息
            "info": false,
            // 是否允许翻页，设成false，翻页按钮不显示
            "paging": false,
            // 水平滚动条
            "scrollX": false,
            // 垂直滚动条
            "scrollY": false,
            data: arr,
            columns: [
                { title: "序号", data: 'id' },
                { title: "点位名称", data: 'name' },
                { title: "最大值", data: 'maxDensity' },
                { title: "超上限数量", data: 'densityCount' },
                { title: "分析数量", data: 'recordCount' },
                { title: "超上限率（%）", data: 'ratio' },
                { title: "最大值", data: 'maxTvocDensity' },
                { title: "超上限数量", data: 'densityTvocCount' },
                { title: "分析数量", data: 'recordTvocCount' },
                { title: "超上限率（%）", data: 'ratioTvoc' }
            ]
        });
    })
}

function getNameById(factoryId) {
    for (var i = 0; i < factories.length; i++) {
        if (factories[i].id === factoryId) return factories[i].name;
    }
    return "";
}

function loadFactory() {
    $.post(appName + 'getFactory').then(function (data) {
        factories = data;
    })
}

$(function () {
    loadFactory()

    $('#dataTable tbody').on('click','tr td:nth-child(3)', function (e) {
        var name = $(this).text();
        alert(name);
    } );
})


