var testSize;

function generateGenSelectElement() {
    var select =
        '<select id="genericSelect" class="form-control "> ' +
        '<option value="five">5 units</option> ' +
        '<option value="ten">10 units</option>' +
        '<option value="twenty">20 units</option>' +
        '</select>';

    return select;
}

function generateCustomSelectElement() {
    var select =
        '<select id="genericSelect" class="form-control "> ' +
        '<option value="five">5 units</option> '

    if (testSize >= 10) select += '<option value="ten">10 units</option>';
    if (testSize >= 20) select += '<option value="ten">20 units</option>';
    select += '</select>';

    return select;
}

function createGenericTest() {

    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_DEFAULT,
        size: BootstrapDialog.SIZE_SMALL,
        title: 'Create generic test',
        message: generateGenSelectElement(),
        buttons: [{
            label: 'Create',
            cssClass: 'btn-primary',
            hotkey: 13, // Enter.
            action: function(dialog) {
                var testsize = $("#genericSelect").val();
                window.location.href = "/test/generic?size=" + testsize ;
                dialog.close();
            }
        }]
    });
}

function createCustomTest() {
    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_DEFAULT,
        size: BootstrapDialog.SIZE_SMALL,
        title: 'Create custom test',
        message: generateCustomSelectElement(),
        buttons: [{
            label: 'Create',
            cssClass: 'btn-primary',
            hotkey: 13, // Enter.
            action: function(dialog) {
                var testsize = $("#genericSelect").val();
                window.location.href = "/test/custom?size=" + testsize ;
                dialog.close();
            }
        }]
    });
}

$( document ).ready(function() {
    // $("#showCustomTestDialogBtn").prop("disabled",true);
    $("#showCustomTestDialogBtn").hide();
    $.get('/setsize', function(result) {
        if (result.status == "OK") {
            testSize = parseInt(result.message);
            if (testSize >= 5) {
                // $("#showCustomTestDialogBtn").prop("disabled",false);
                $("#showCustomTestDialogBtn").show();
            }
        }
    });
});
