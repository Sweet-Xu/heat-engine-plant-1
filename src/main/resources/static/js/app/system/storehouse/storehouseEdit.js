function updateStorehouse() {
    var selected = $("#storehouseTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的设备！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个设备！');
        return;
    }
    var storehouseId = selected[0].storehouseId;
    $.post(ctx + "storehouse/getStorehouse", {"storehouseId": storehouseId}, function (r) {
        if (r.code === 0) {
            var $form = $('#storehouse-add');
            $form.modal();
            var storehouse = r.msg;
            $("#storehouse-add-modal-title").html('修改仓库');
            $form.find("input[name='storehouseName']").val(storehouse.storehouseName);
            $form.find("input[name='oldStorehouseName']").val(storehouse.storehouseName);
            $form.find("input[name='storehouseId']").val(storehouse.storehouseId);
            $form.find("input[name='address']").val(storehouse.address);
            $form.find("input[name='userName']").val(storehouse.userName);
            var roleArr = [];
            roleArr.push(storehouse.userId);
            $form.find("select[name='userSelect']").multipleSelect('setSelects', roleArr);
            $form.find("input[name='userId']").val($form.find("select[name='userSelect']").val());
            $form.find("input[name='remarks']").val(storehouse.remarks);
            var $status = $form.find("input[name='status']");
            if (storehouse.status === '1') {
                $status.prop("checked", true);
                $status.parent().next().html('可用');
            } else {
                $status.prop("checked", false);
                $status.parent().next().html('禁用');
            }
            $("#storehouse-add-button").attr("name", "update");

        } else {
            $MB.n_danger(r.msg);
        }
    });
}