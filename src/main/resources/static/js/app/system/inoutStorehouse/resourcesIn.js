var validator1;
var $resourcesInForm = $("#resources-in-form");
var $resourcesSelect = $resourcesInForm.find("select[name='resourcesSelect']");
var $resources = $resourcesInForm.find("input[name='resourcesId']");

$(function () {
    validateRule();
    initResources();

    $("#resources-in .btn-save").click(function () {
        var name = $(this).attr("name");
        validator1 = $resourcesInForm.validate();
        var flag = validator1.form();
        if (flag) {
            if($resources.val().search(',') != -1)  {
                alert('只能选择一种物资!');
                return;
            }
            var $form = $('#resources-in');
            var resourcesId = $form.find("input[name='resourcesId']").val();
            var num = $form.find("input[name='num']").val();
            var remarks = $form.find("input[name='remarks']").val();
            if (name === "in") {
                $.post(ctx + "resources/in", {"resourcesId": resourcesId,"num":num,"remarks":remarks},function (r) {
                    if (r.code === 0) {
                        closeModal1();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "out") {
                $.post(ctx + "resources/out", {"resourcesId": resourcesId,"num":num,"remarks":remarks},function (r) {
                    if (r.code === 0) {
                        closeModal1();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#resources-in .btn-close").click(function () {
        closeModal1();
    });

});

function closeModal1() {
    $("#resources-in-button").attr("name", "in");
    $("#resources-in-modal-title").html('入库');
    validator1.resetForm();
    $MB.closeAndRestModal("resources-in");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator1 = $resourcesInForm.validate({
        rules: {
            resourcesId: {
                required: true,
                maxlength: 10
            },
            num: {
                required: true,
                maxlength: 20
            }
        },
        messages: {
            resourcesId: {
                required: icon + "请输入物资名",
                maxlength: icon + "长度不能超过20个字符"
            },
            num: {
                required: icon + "请输入入库数量",
                maxlength: icon + "长度不能超过20个字符"
            }
        }
    });
}

function initResources() {
    $.post(ctx + "resources/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].resourcesId + "'>" + data[i].resourcesName + "</option>"
        }
        $resourcesSelect.html("").append(option);
        var options = {
            selectAllText: '所有物资',
            allSelected: '所有物资',
            width: '100%',
            onClose: function () {
                $resources.val($resourcesSelect.val());
                if($resources.val().search(',') != -1)  {
                    alert('只能选择一个物资!');
                    return;
                }
                else {
                    validator1.element("input[name='resources']");
                }

            }
        };

        $resourcesSelect.multipleSelect(options);
    });
}
