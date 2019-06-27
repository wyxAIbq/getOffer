window.alert = function (msg, callback) {
    // parent.layer.alert 弹出在iframe外的页面。
    layer.alert(msg, function (index) {
        layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
};

//获取选中的数据
function getSelectedRowData(gridId) {
    let id = getSelectedRow(gridId);
    return $(gridId).jqGrid('getRowData', id);
}

//选择一条记录
function getSelectedRow(gridId) {
    var grid = $(gridId);
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        iview.Message.error("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        iview.Message.error("只能选择一条记录");
        return;
    }

    return selectedIDs[0];
};

//选择多条记录
function getSelectedRows(gridId) {
    var grid = $(gridId);
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        iview.Message.error("请至少选择一本图书");
        return;
    }
    return grid.getGridParam("selarrrow");
};

