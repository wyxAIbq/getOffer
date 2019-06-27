
$(function () {
    $("#jqGrid").Grid({
        url: 'http://localhost:8081/MyBorrowingProject/cart/getcart',
        colModel: [{
            label: 'id', name: 'bookId', index: 'bookId', key: true, hidden: true
        }, {
            label: 'Title', name: 'bookTitle', index: 'bookTitle',sortable: false, width: 80
        },  {
            label: 'Author', name: 'bookAuthor', index: 'bookAuthor',sortable: false,width: 80
        },{
            label: 'ReaderId', name: 'readerId', index: 'readerId',sortable: false,width: 80
        },{
            label: 'ReaderUsername', name: 'readerUsername', index: 'readerUsername',sortable: false,width: 80
        },{
            label: 'ReaderName', name: 'readerName', index: 'readerName',sortable: false,width: 80
        },{
            label: 'Count', name: 'count', index: 'count',sortable: true,width: 80
        }]

    });

});
    var vm = new Vue({
        el: '#app',
        data: {
            value: '',
            code : '',
        },
        methods: {
            clearBookCart : function () {
                var href = "http://localhost:8081/MyBorrowingProject/cart/cart";
                $("#form_delete").attr("action", href).submit();
            },
            deleteBook : function () {
                var bookIds = getSelectedRows("#jqGrid");
                $.ajax({
                    type:"POST",
                    url: "http://localhost:8081/MyBorrowingProject/cart/deletecart",
                    dateType:"json",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(bookIds),
                    success:function() {
                        location.reload();
                    },
                });
            },
            update : function(){
                var rowData = getSelectedRowData("#jqGrid");
                var count = rowData.count;
                vm.value = count;
                if(vm.value){
                    layer.open({
                        title: '更改图书数量',
                        type: 1,
                        skin: 'layui-layer-rim', //加上边框
                        area: ['220px', '140px'], //宽高
                        content: $("#update_count")
                    });
                }
            },
            add: function () {
                vm.value++;
            },
            minus: function () {
                if(vm.value > 1){
                    vm.value--;
                }
            },
            submitCount :function(){
                var bookId = getSelectedRow("#jqGrid");
                $.ajax({
                    type:"POST",
                    url: "http://localhost:8081/MyBorrowingProject/cart/cart/"+bookId,
                    dateType:"json",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(vm.value),
                    success:function() {
                        location.reload();
                    },
                });
            },
            submitBookCart :function () {
                var bookIds = getSelectedRows("#jqGrid");
               //通过form的hidden传递bookIds
                var href = "http://localhost:8081/MyBorrowingProject/order/order";
                $("#bookIds").val(bookIds);
                if(bookIds){
                    $("#form_submit").attr("action", href).submit();
                }
            }
        }
    });

