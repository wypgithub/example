var factories = [];
function loadFactory() {
    $.post('getFactory').then(function (data) {
        factories = data;
        // 强制显示表格
        getFactoryReportData(true);
    })
}

function getFactoryReportData(init) {
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    if (init) {
        startDate = "2019-07-01";
        endDate = "2019-08-01";
    }
    $.post('showData?startDate=' + startDate + '&endDate=' + endDate).then(function (data) {
        var arr = [];
        var counts = [2, 9, 5, 4, 18];
        for (var i = 0; i < data.length; i++) {
            arr.push({
                "id": i + 1,
                "name": getNameById(data[i].factoryId),
                "count": counts[i],
                "day100": data[i].day100,
                "day95": data[i].day95,
                "day90": data[i].day90,
                "day89": data[i].day89

            })
        }

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
                { title: "编号", data: 'id' },
                { title: "企业名称", data: 'name' },
                { title: "监测点数量", data: 'count' },
                { title: "优（天）", data: 'day100' },
                { title: "良（天）", data: 'day95' },
                { title: "合格（天）", data: 'day90' },
                { title: "差（天）",data:'day89'}
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

function startup() {
    loadFactory();
}

startup();
