function outResources() {
    var $form = $('#resources-in');
    $form.modal();
    $("#resources-in-modal-title").html('出库');
    $("#resources-in-button").attr("name", "out");
}