
$(function () {
    $("#jqGrid").Grid({
        url: '../book/jqbooks',
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, hidden: true
        }, {
            label: 'Title', name: 'title', index: 'title',sortable: false, width: 80
        },  {
            label: 'Author', name: 'author', index: 'author',sortable: false,width: 80
        }]
    });

    $("#addlistbtn").click(function () {
        var ids = getSelectedRows("#jqGrid");
        $.ajax({
            type:"post",
            url: "../cart/cart",
            dateType:"json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(ids),
            success:function()
            {
                alert("加入成功");
            },
        });
        }
    );


    $("#form_getlist").submit(function () {
            var href = "http://localhost:8081/MyBorrowingProject/cart/cart/";
            $("#form_getlist").attr("action", href).submit();
        }
    )

});

