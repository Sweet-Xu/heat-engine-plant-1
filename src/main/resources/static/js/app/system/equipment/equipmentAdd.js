var validator;
var $equipmentAddForm = $("#equipment-add-form");
var $userSelect = $equipmentAddForm.find("select[name='userSelect']");
var $user = $equipmentAddForm.find("input[name='userId']");

$(function () {
    validateRule();
    initUser();

    $("#equipment-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $equipmentAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if($user.val().search(',') != -1)  {
                alert('只能选择一个负责人!');
                return;
            }
            if (name === "save") {
                $.post(ctx + "equipment/add", $equipmentAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "equipment/update", $equipmentAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#equipment-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#equipment-add-button").attr("name", "save");
    $("#equipment-add-modal-title").html('新增设备');
    validator.resetForm();
    $MB.closeAndRestModal("equipment-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $equipmentAddForm.validate({
        rules: {
            equipmentId: {
                required: false,
                digits: true,
                maxlength: 10
            },
            equipmentName: {
                required: true,
                maxlength: 20
            },
            model: {
                required: true,
                maxlength: 10
            },
            supplier: {
                required: true,
                maxlength: 20
            },
            describe: {
                required: true,
                maxlength: 100
            },
            userName: {
                required: false,
                maxlength: 10
            },
            remarks: {
                required: false,
                maxlength: 100
            },
            status: {
                required: true,
                digits: true,
                maxlength: 10
            }
        },
        messages: {
            equipmentName: {
                required: icon + "请输入设备名称",
                digits: icon + "请输入整数",
                maxlength: icon + "长度不能超过10个字符"
            },
            model: {
                required: icon + "请输入设备型号",
                maxlength: icon + "长度不能超过10个字符"
            },
            supplier: {
                required: icon + "请输入供应商",
                maxlength: icon + "长度不能超过20个字符"
            },
            describe: {
                required: icon + "请输入设备描述",
                maxlength: icon + "长度不能超过100个字符"
            },
            status: {
                required: icon + "请输入设备状态",
                maxlength: icon + "长度不能超过10个字符"
            }
        }
    });
}

function initUser() {
    $.post(ctx + "user/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].userId + "'>" + data[i].username + "</option>"
        }
        $userSelect.html("").append(option);
        var options = {
            size: '2',
            selectAllText: '所有角色',
            allSelected: '所有角色',
            width: '100%',
            onClose: function () {
                $user.val($userSelect.val());
                if($user.val().search(',') != -1)  {
                    alert('只能选择一个负责人!');
                    return;
                }
               else {
                    validator.element("input[name='user']");
                }

            }
        };

        $userSelect.multipleSelect(options);
    });
}
