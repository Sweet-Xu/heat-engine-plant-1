$(function () {
    var settings = {
        id: 'inOutStorehouseId',
        code: 'inOutStorehouseId',
        url: ctx + 'outStorehouse/list',
        expandAll: true,
        expandColumn: "2",
        ajaxParams: {
            resourcesName: $(".resources-table-form").find("input[name='resourcesName']").val().trim()
        },
        columns: [{
            field: 'selectItem',
            checkbox: true
        },
            {
                field: 'inOutStorehouseId',
                title: '出库编号'
            },
            {
                field: 'resourcesName',
                title: '物资名'
            }, {
                field: 'num',
                title: '数量'
            }, {
                field: 'time',
                title: '时间'
            }, {
                field: 'remarks',
                title: '剩余数量'
            }
        ]
    };

    $MB.initTable('resourcesTable', settings);
});

function search() {
    $MB.refreshTable('resourcesTable');
}

function refresh() {
    $(".resources-table-form")[0].reset();
    $MB.refreshTable('resourcesTable');
}

function deleteResources() {
    var selected = $("#resourcesTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的设备！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].resourcesId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的设备？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'resources/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportDeptExcel(){
    $.post(ctx+"resources/excel",$(".resources-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportDeptCsv(){
    $.post(ctx+"resources/csv",$(".resources-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}