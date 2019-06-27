(function ($) {

    /*********************************对jQuery类型原型扩展********************************************/
    jQuery.extend($.prototype, {  //jQuery.extend() 函数用于将一个或多个对象的内容合并到目标对象
        Grid: function (options) { //这里的option就是具体每个jqGrid的url、colModel属性
            //分页Id
            var pager = this.attr('id') + 'Pager';//这里的id就是jqGrid，pager变为jqGridPager
            this.after('<div id="' + pager + '"></div>');//after在被选元素之后插入内容

            this.defaults = {
                width: 1000,
                styleUI: 'Bootstrap', //设置jqgrid的全局样式为bootstrap样式
                datatype: "json",
                viewrecords: true,//定义是否要显示总记录数
                height: 200,
                rowNum: 5,
                rowList: [5, 10, 20],//下拉框选择显示每页记录数
                rownumbers: true,//如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
                rownumWidth: 25,//上边的最左边一列宽度为25
                autowidth: true,
                multiselect: true,//定义是否可以多选

                jsonReader: {                //jqgrid里面jsonreader 怎样解析json 数据
                    root: "page.list",//数据模型
                    page: "page.currPage",//当前页码
                    total: "page.totalPage",//总页数
                    records: "page.totalCount"    //总记录数
                },
                prmNames: {//Default valuesprmNames: {page:“page”,rows:“rows”, sort: “sidx”,order: “sord”, search:“_search”, nd:“nd”, npage:null} 当参数为null时不会被发到服务器端
                    page: "page", //设置初始页码
                    rows: "limit",
                    order: "order"
                },
                pager: "#" + pager
            };
            var param = $.extend(this.defaults, options);
            this.jqGrid(param);
        }
    });
})(jQuery);