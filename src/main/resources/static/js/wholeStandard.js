var factories = [];
$(function () {
    //初始显示
    getWholeStandardReportData('2019-07-07');

    $(".button-small").click(function () {
        getWholeStandardReportData($('#WeekPicker').val());
    })

})
function loadFactory() {
    $.post('getFactory').then(function (data) {
        factories = data;
    })
}

/**
 * 计算评价
 * @param value
 */
function calculateEvaluation(value) {
    if(value == 0 ){
        return '优';
    }
    if(value == 1){
        return '良';
    }
    if(value < 2){
        return '轻度污染';
    }
    if(value < 3){
        return '中度污染';
    }
    if(value < 4){
        return '重度污染';
    }

    return '严重污染';
}

function getWholeStandardReportData(date) {
    //当前时间后退7天为开始时间
    var startDate = new Date(date).getTime() - 7 * 24*60*60*1000

    var loadIndex = layer.load();
    $.post('showData?startDate=' + formatDate(new Date(startDate))  + '&endDate=' + date).then(function (data) {
        var arr = [];
        var counts = [2, 9, 5, 4, 18];
        for (var i = 0; i < data.length; i++) {
            arr.push({
                "days": data[i].days,
                "evaluation": calculateEvaluation(data[i].avg),
                "evaluation1": '在建',
                "evaluation2": '在建',
                "evaluation3": '在建'
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
                { title: "日期", data: 'days' },
                { title: "园区总体评价", data: 'evaluation' },
                { title: "01长征片区", data: 'evaluation' },
                { title: "02银邦片区(在建)", data: 'evaluation1' },
                { title: "03新合成片区(在建)", data: 'evaluation2' },
                { title: "04新天地片区(在建)", data: 'evaluation3' }
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
