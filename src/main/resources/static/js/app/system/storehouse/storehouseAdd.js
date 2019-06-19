var validator;
var $storehouseAddForm = $("#storehouse-add-form");
var $userSelect = $storehouseAddForm.find("select[name='userSelect']");
var $user = $storehouseAddForm.find("input[name='userId']");

$(function () {
    validateRule();
    initUser();

    $("#storehouse-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $storehouseAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if($user.val().search(',') != -1)  {
                alert('只能选择一个负责人!');
                return;
            }
            if (name === "save") {
                $.post(ctx + "storehouse/add", $storehouseAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "storehouse/update", $storehouseAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#storehouse-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#storehouse-add-button").attr("name", "save");
    $("#storehouse-add-modal-title").html('新增设备');
    validator.resetForm();
    $MB.closeAndRestModal("storehouse-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $storehouseAddForm.validate({
        rules: {
            storehouseId: {
                required: false,
                digits: true,
                maxlength: 10
            },
            storehouseName: {
                required: true,
                maxlength: 20
            },
            userName: {
                required: false,
                maxlength: 20
            },
            address: {
                required: true,
                maxlength: 30
            },
            userId: {
                required: true,
                maxlength: 20
            }
        },
        messages: {
            storehouseName: {
                required: icon + "请输入仓库名",
                maxlength: icon + "长度不能超过20个字符"
            },
            userName: {
                required: icon + "请输入负责人",
                maxlength: icon + "长度不能超过20个字符"
            },
            address: {
                required: icon + "请输入地址",
                maxlength: icon + "长度不能超过30个字符"
            },
            userId: {
                required: icon + "请输入所在仓库"
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
            selectAllText: '所有用户',
            allSelected: '所有用户',
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
