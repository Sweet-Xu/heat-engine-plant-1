function updateResources() {
    var selected = $("#resourcesTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的物资！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一条数据！');
        return;
    }
    var resourcesId = selected[0].resourcesId;
    $.post(ctx + "resources/getResources", {"resourcesId": resourcesId}, function (r) {
        if (r.code === 0) {
            var $form = $('#resources-add');
            $form.modal();
            var resources = r.msg;
            $("#resources-add-modal-title").html('修改物资');
            $form.find("input[name='resourcesNo']").val(resources.resourcesNo);
            $form.find("input[name='oldResourcesNo']").val(resources.resourcesNo);
            $form.find("input[name='resourcesId']").val(resources.resourcesId);
            $form.find("input[name='resourcesName']").val(resources.resourcesName);
            $form.find("input[name='type']").val(resources.type);
            $form.find("input[name='price']").val(resources.price);
            $form.find("input[name='weight']").val(resources.weight);
            $form.find("input[name='price']").val(resources.price);
            $form.find("input[name='weight']").val(resources.weight);
            $form.find("input[name='storehouseName']").val(resources.storehouseName);
            var storehouseArr = [];
            storehouseArr.push(resources.storehouseId);
            $form.find("select[name='storehouseSelect']").multipleSelect('setSelects', storehouseArr);
            $form.find("input[name='storehouseId']").val($form.find("select[name='storehouseSelect']").val());
            $form.find("input[name='remarks']").val(resources.remarks);
            var $status = $form.find("input[name='status']");
            if (resources.status === '1') {
                $status.prop("checked", true);
                $status.parent().next().html('使用');
            } else {
                $status.prop("checked", false);
                $status.parent().next().html('闲置');
            }
            $("#resources-add-button").attr("name", "update");

        } else {
            $MB.n_danger(r.msg);
        }
    });
}