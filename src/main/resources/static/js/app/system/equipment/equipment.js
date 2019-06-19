$(function () {
    var settings = {
        id: 'equipmentId',
        code: 'equipmentId',
        url: ctx + 'equipment/list',
        expandAll: true,
        expandColumn: "2",
        ajaxParams: {
            equipmentName: $(".equipment-table-form").find("input[name='equipmentName']").val().trim()
        },
        columns: [{
            field: 'selectItem',
            checkbox: true
        },
            {
                field: 'equipmentName',
                title: '设备名称'
            }, {
                field: 'model',
                title: '设备类型'
            }, {
                field: 'supplier',
                title: '供应商'
            }, {
                field: 'describe',
                title: '描述'
            }, {
                field: 'userName',
                title: '负责人'
            },{
                field: 'remarks',
                title: '备注'
            },{
                field: 'status',
                title: '状态',
                formatter: function (value, row, index) {
                    if (value === '1') return '<span class="badge badge-success">使用</span>';
                    if (value === '0') return '<span class="badge badge-warning">维修</span>';
                    if (value === '2') return '<span class="badge badge-danger">报废</span>';
                }
            }
        ]
    };

    $MB.initTable('equipmentTable', settings);
});

function search() {
    $MB.refreshTable('equipmentTable');
}

function refresh() {
    $(".equipment-table-form")[0].reset();
    $MB.refreshTable('equipmentTable');
}

function deleteEquipments() {
    var selected = $("#equipmentTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的设备！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].equipmentId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的设备？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'equipment/delete', { "ids": ids }, function(r) {
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
    $.post(ctx+"equipment/excel",$(".equipment-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportDeptCsv(){
    $.post(ctx+"equipment/csv",$(".equipment-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}