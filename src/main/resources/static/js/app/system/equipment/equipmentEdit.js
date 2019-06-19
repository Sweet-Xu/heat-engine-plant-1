function updateEquipment() {
    var selected = $("#equipmentTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的设备！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个设备！');
        return;
    }
    var equipmentId = selected[0].equipmentId;
    $.post(ctx + "equipment/getEquipment", {"equipmentId": equipmentId}, function (r) {
        if (r.code === 0) {
            var $form = $('#equipment-add');
            $form.modal();
            var equipment = r.msg;
            $("#equipment-add-modal-title").html('修改设备');
            $form.find("input[name='equipmentName']").val(equipment.equipmentName);
            $form.find("input[name='oldEquipmentName']").val(equipment.equipmentName);
            $form.find("input[name='equipmentId']").val(equipment.equipmentId);
            $form.find("input[name='model']").val(equipment.model);
            $form.find("input[name='supplier']").val(equipment.supplier);
            $form.find("input[name='describe']").val(equipment.describe);
            $form.find("input[name='userName']").val(equipment.userName);
            var userArr = [];
            userArr.push(equipment.userId);
            $form.find("select[name='userSelect']").multipleSelect('setSelects', userArr);
            $form.find("input[name='userId']").val($form.find("select[name='userSelect']").val());
            $form.find("input[name='remarks']").val(equipment.remarks);
            $("input:radio[value='" + equipment.status + "']").prop("checked", true);
            $("#equipment-add-button").attr("name", "update");

        } else {
            $MB.n_danger(r.msg);
        }
    });
}