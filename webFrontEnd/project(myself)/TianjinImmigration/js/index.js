/*!
 *
 * author: chenyogie
 */

(function (win, $) {

    $.ajax({
        type: "get",
        url: "test/data.json",
        dataType: "json",
        success: function (data) {
            var result = data.items;
            for (var i=0;i<5;i++) {
                //新建节点，并拼接
                var newNode='<div  class="con-list-item">\n' +
                    '<div class="item-left">\n' +
                    '<div class="item-title">'+ result[i].title +'</div>\n' +
                    '<div class="item-txt">'+result[i].text+'</div>\n' +
                    '</div>\n' +
                    '<div class="item-right">\n' +
                    '<button class="con-btn">申请</button>\n' +
                    '<button class="con-btn">收藏</button>\n' +
                    '</div>\n' +
                    '</div>';
                 $("#ajaxdata").append(newNode);
            }
        }
    });

    //初始化插件
    $("#page").pagination({
        pageSize: 5,
        total: 1000
    });


    //分页
    $("#page").on(
        "pageClicked",
        function(event, data) {
            var index = data.pageIndex;// 当前页数
            var pageSize = data.pageSize;// 每页数据数
            $.ajax({
                type:"get",
                url:"test/data.json",
                dataType:"json",
                success:function(data){
                    $("#ajaxdata").empty();
                    var result = data.items;
                    var start = index * pageSize;
                    var end = start + pageSize - 1;
                    for(;start<=end;start++){
                        //新建节点，并拼接
                        var newNode='<div  class="con-list-item">\n' +
                            '<div class="item-left">\n' +
                            '<div class="item-title">'+ result[start].title +'</div>\n' +
                            '<div class="item-txt">'+result[start].text+'</div>\n' +
                            '</div>\n' +
                            '<div class="item-right">\n' +
                            '<button class="con-btn">申请</button>\n' +
                            '<button class="con-btn">收藏</button>\n' +
                            '</div>\n' +
                            '</div>';
                        $("#ajaxdata").append(newNode);
                    }
                }
            });
        });

    //下拉请求
    $(document).ready(function(){
        $('#selectid').change(function(){
            $.ajax({
                type: "get",
                url: "test/data.json",
                dataType: "json",
                success: function (data) {
                    $("#ajaxdata").empty();
                    var result = data.items;
                    for (var i=0;i<5;i++) {
                        //新建节点，并拼接
                        var newNode='<div  class="con-list-item">\n' +
                            '<div class="item-left">\n' +
                            '<div class="item-title">'+ result[i].title +'</div>\n' +
                            '<div class="item-txt">'+result[i].text+'</div>\n' +
                            '</div>\n' +
                            '<div class="item-right">\n' +
                            '<button class="con-btn">申请</button>\n' +
                            '<button class="con-btn">收藏</button>\n' +
                            '</div>\n' +
                            '</div>';
                        $("#ajaxdata").append(newNode);
                    }
                }
            });

        });
    });
}(this, jQuery));