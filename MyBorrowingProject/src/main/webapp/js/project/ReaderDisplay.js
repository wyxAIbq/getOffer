
$(function () {
    $("#jqGrid").Grid({
        url: '../cart/jqbooks',
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, hidden: true
        }, {
            label: 'Title', name: 'title', index: 'title',sortable: false, width: 80
        },  {
            label: 'Author', name: 'author', index: 'author',sortable: false,width: 80
        }]
    });

    $("#addlist").click(function() {

        var ids = $("#jqGrid").jqGrid('getGridParam', 'selarrrow');
        $.ajax({
            type:"post",
            url: "../cart/addcart",
            dateType:"json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(ids),
            success:function(data)
            {
                alert(data);
            },
        });
    });
});


