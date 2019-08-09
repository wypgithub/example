
function formatDate(date) {
    var year = date.getFullYear()
    var month = date.getMonth()
    var day = date.getDate()
    if(month>0 && month<10){
        month="0"+month;
    }
    if(day>0 && day<10){
        day="0"+day;
    }
    return year+"-"+month+"-"+day;
}
