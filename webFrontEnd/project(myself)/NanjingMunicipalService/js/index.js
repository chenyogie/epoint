/**!
 * 南京市政服务-首页
 * author: chenyogie;
 * date: 20171216
 */

(function (win, $) {
    $.ajax({
        type: "get",
        url: "test/data.json",
        dataType: "json",
        success: function (data) {
            var result = data.data;
            for (var i = 0; i < 6; i++) {
                //console.log(result[i]);

                // html+='<td>'+result[i].number+'</td>';
                // html+='<td>'+result[i].applyer+'</td>';
                // html+='<td>'+result[i].department+'</td>';
                // html+='<td>'+result[i].project+'</td>';
                // html+='<td>'+result[i].date+'</td>';
                // html+='<td>'+result[i].state+'</td>';
                // html+='</tr>';
                var html = '<tr class="body-tr">';
                html += '<td class="office-table-td td-no">' + result[i].number + '</td>';
                html += '<td class="office-table-td td-unit">' + result[i].applyer + '</td>';
                html += '<td class="office-table-td td-department">' + result[i].department + '</td>';
                html += '<td class="office-table-td td-matter">' + result[i].project + '</td>';
                html += '<td class="office-table-td td-sign-time">' + result[i].date + '</td>';
                html += '<td class="office-table-td td-state">' + result[i].state + '</td>';
                html += '</tr>';

                $("#ajaxdata").append(html);
            }
            // console.log(data.total);
            // console.log(result.length);
            // console.log(result);
        }
    });


    $("#page").pagination({
        pageIndex: 0,//当前页数
        pageSize: 6,//每页显示数据量
        total:1000,//总数据量
        pageBtnCount: 6,//显示分页按钮数
        showInfo: true,
        prevBtnText:"上页",
        nextBtnText: "下页",
        //infoFormat: '1/'+Math.floor(1000/7),
        showJump: true,
        jumpBtnText:"GO",
        pageElementSort: ['$page', '$size', '$info', '$jump']
    });

    $("#page").on("pageClicked", function(event,data) {
        var index = data.pageIndex;// 当前页数
        var pageSize = data.pageSize;// 每页数据数
        $.ajax({
            type: "get",
            url: "test/data.json",
            dataType: "json",
            success:function(data){
                $("#ajaxdata tr:gt(0)").remove();
                var result = data.data;
                var start = index * pageSize;
                var end = start + pageSize - 1;
                for(;start<=end;start++){
                    var html = '<tr>';
                    html += '<td class="office-table-td td-no">' + result[start].number + '</td>';
                    html += '<td class="office-table-td td-unit">' + result[start].applyer + '</td>';
                    html += '<td class="office-table-td td-department">' + result[start].department + '</td>';
                    html += '<td class="office-table-td td-matter">' + result[start].project + '</td>';
                    html += '<td class="office-table-td td-sign-time">' + result[start].date + '</td>';
                    html += '<td class="office-table-td td-state">' + result[start].state + '</td>';
                    html += '</tr>';
                    $("#ajaxdata").append(html);
                }
            }
        });
    });

}(this, jQuery));