var validator;
var $resourcesAddForm = $("#resources-add-form");
var $storehouseSelect = $resourcesAddForm.find("select[name='storehouseSelect']");
var $storehouse = $resourcesAddForm.find("input[name='storehouseId']");

$(function () {
    validateRule();
    initStorehouse();

    $("#resources-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $resourcesAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if($storehouse.val().search(',') != -1)  {
                alert('只能选择一个负责人!');
                return;
            }
            if (name === "save") {
                $.post(ctx + "resources/add", $resourcesAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "resources/update", $resourcesAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#resources-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#resources-add-button").attr("name", "save");
    $("#resources-add-modal-title").html('新增物资');
    validator.resetForm();
    $MB.closeAndRestModal("resources-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $resourcesAddForm.validate({
        rules: {
            resourcesId: {
                required: false,
                digits: true,
                maxlength: 10
            },
            resourcesNo: {
                required: true,
                maxlength: 20
            },
            resourcesName: {
                required: true,
                maxlength: 20
            },
            type: {
                required: true,
                maxlength: 20
            },
            storehouseName: {
                required: true,
                maxlength: 20
            },
            storehouseId: {
                required: true,
                maxlength: 20
            }
        },
        messages: {
            resourcesNo: {
                required: icon + "请输入物资编号",
                maxlength: icon + "长度不能超过20个字符"
            },
            resourcesName: {
                required: icon + "请输入物资名称",
                maxlength: icon + "长度不能超过20个字符"
            },
            type: {
                required: icon + "请输入类型",
                maxlength: icon + "长度不能超过20个字符"
            },
            storehouseName: {
                required: icon + "请输入所在仓库",
                maxlength: icon + "长度不能超过20个字符"
            },
            storehouseId: {
                required: icon + "请输入所在仓库"
            }
        }
    });
}

function initStorehouse() {
    $.post(ctx + "storehouse/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].storehouseId + "'>" + data[i].storehouseName + "</option>"
        }
        $storehouseSelect.html("").append(option);
        var options = {
            selectAllText: '所有仓库',
            allSelected: '所有仓库',
            width: '100%',
            onClose: function () {
                $storehouse.val($storehouseSelect.val());
                if($storehouse.val().search(',') != -1)  {
                    alert('只能选择一个仓库!');
                    return;
                }
                else {
                    validator.element("input[name='storehouse']");
                }

            }
        };

        $storehouseSelect.multipleSelect(options);
    });
}
