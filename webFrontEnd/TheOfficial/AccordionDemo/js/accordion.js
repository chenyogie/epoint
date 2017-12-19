/*!
 * 手风琴demo
 * author: xiaolong
 * date: 20161112;
 */

(function(win) {
	// 手风琴外层容器对象，根据class选择器选择对应的dom元素
    var $accordionWrap = $(".accordion-wrap"); 
	
	// 根据索引生成序号 1 → "01"
    var getOrder = function(order) {
        if (order < 10) {
            order = '0' + order;
        }
        return order;
    };
	
	// 初始化时解析 手风琴。
    function parse() {
		// 先获取到手风琴的每一项item,通过属性选择器。
        var $accs = $accordionWrap.find('[role="accordion"]'); 
		
		// 遍历每一个item
        $accs.each(function(index, el) {
            var $head = $(el).find('[role="head"]'),
                title = $head.attr("title"),
                opened = $(el).attr("opened") === 'true' ? true : false;
            // console.log(opened);
			
			// 根据opened属性值 初始化每一项上是否加closed样式。closed样式控制每一项的显隐。
            if (!opened) {
                $(el).addClass('closed');
            }
			
			// 生成html标签包括3个节点元素，放入到div[role=head]里
            $head.append(generateHeadHtml(index, title)); 
        });
    }
	
	// 根据索引号，标题值生成3个html节点。
    function generateHeadHtml(index, title) {
        var arr = [];

        arr.push('<span class="order">' + getOrder(index + 1) + '</span>');
        arr.push('<h4 class="title">' + title + '</h4>');
        arr.push('<i class="icon-toggle"></i>');

        return arr.join('');

    }
	
	// 手风琴每一项的icon图标绑定点击事件，控制显隐，使用事件代理的方式。
    $accordionWrap.on('click', '.icon-toggle', function(event) {
        var $this = $(this);

        var $curAcc = $this.parents(".accordion-item");

        if ($curAcc.hasClass('closed')) {
            $curAcc.removeClass('closed');
        } else {
            $curAcc.addClass('closed');
        }
    });

    parse();
	
	// 对全局外部开放的三个方法
    win.accordion = {
        _accs: $accordionWrap.find('[role="accordion"]'),

        // 显示手风琴项
        showItem: function(index) {
            console.log(this);
            var $acc = this._accs.eq(index);

            if ($acc.length && $acc.hasClass('hidden')) {
                $acc.removeClass('hidden')
                this._updateOrders();
            }
        },

        // 隐藏手风琴项
        hideItem: function(index) {
            var $acc = this._accs.eq(index);

            if ($acc.length && !$acc.hasClass('hidden')) {
                $acc.addClass('hidden')
                this._updateOrders();
            }
        },

        // 设置手风琴标题
        setTitle: function(title, index) {
            var $acc = this._accs.eq(index);
            if ($acc.length) {
                var $header = $acc.find('[role="head"]').attr('title', title);

                $header.find('.title').html(title);
            }
        },

        // 显示|隐藏后需要更新下序号
        _updateOrders: function() {
            var order = 0;

            $.each(this._accs, function(i, acc) {
                var $acc = $(acc),
                    $order = $acc.find('.order');

                if (!$acc.hasClass('hidden')) {
                    $order.html(getOrder(++order));
                }
            });
        }
    };


})(this);
