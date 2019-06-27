
$(function () {
    var bookIds = $("#bookIds").text(); //把字符串变为数组
    var b1 = bookIds.substring(bookIds.indexOf("[")+1,bookIds.indexOf("]"));
    var b2 = JSON.stringify(b1.split(','));
    $("#jqGrid").Grid({
        url: 'http://localhost:8081/MyBorrowingProject/order/getorder',
        postData:{bookIds : b2},
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
        bookIds: '',
        formItem: {
            expressBox: '1'
        }
    },
    methods: {
        submitOrder :function () {
            vm.bookIds = $("#jqGrid").jqGrid('getDataIDs');
            $.ajax({
                type:"POST",
                url: "http://localhost:8081/MyBorrowingProject/order/submit",
                dateType:"json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(vm.formItem.expressBox),
                success:function() {
                    location.reload();
                },
            });
        },
    }
});