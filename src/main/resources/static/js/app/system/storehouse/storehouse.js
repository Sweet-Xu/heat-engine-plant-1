$(function () {
    var settings = {
        id: 'storehouseId',
        code: 'storehouseId',
        url: ctx + 'storehouse/list',
        expandAll: true,
        expandColumn: "2",
        ajaxParams: {
            storehouseName: $(".storehouse-table-form").find("input[name='storehouseName']").val().trim()
        },
        columns: [{
            field: 'selectItem',
            checkbox: true
        },
            {
                field: 'storehouseName',
                title: '仓库名'
            }, {
                field: 'userName',
                title: '负责人'
            },{
                field: 'address',
                title: '地址'
            },{
                field: 'remarks',
                title: '备注'
            },{
                field: 'status',
                title: '状态',
                formatter: function (value, row, index) {
                    if (value === '1') return '<span class="badge badge-success">使用</span>';
                    if (value === '0') return '<span class="badge badge-warning">闲置</span>';
                }
            }
        ]
    };

    $MB.initTable('storehouseTable', settings);
});

function search() {
    $MB.refreshTable('storehouseTable');
}

function refresh() {
    $(".storehouse-table-form")[0].reset();
    $MB.refreshTable('storehouseTable');
}

function deleteStorehouses() {
    var selected = $("#storehouseTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的设备！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].storehouseId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的设备？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'storehouse/delete', { "ids": ids }, function(r) {
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
    $.post(ctx+"storehouse/excel",$(".storehouse-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportDeptCsv(){
    $.post(ctx+"storehouse/csv",$(".storehouse-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}