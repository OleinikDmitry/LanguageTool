var multiselect;
$(document).ready(function() {
    multiselect = $("#ms").kendoMultiSelect({
        autoClose: false,
        placeholder: "Select expressions...",
        dataTextField: "text",
        dataValueField: "value",
        autoBind: false,
        maxSelectedItems: 50,
        //autoWidth: true,
        dataSource: {
            type: "json",
            serverFiltering: true,
            transport: {
                read: {
                    url: "/langUnits/kendoData",
                    type : "POST"
                }
            }
        }
    }).data("kendoMultiSelect");

    $.get("/langUnits/attach", function(data) {
        multiselect.value(data);
    });


});

function save() {
    $.post('/langUnits/attach', {unitIds : multiselect.value()}, function(result) {
        if (result.status == "OK") {
            BootstrapDialog.show({
                type : BootstrapDialog.TYPE_DEFAULT,
                title: 'Attach units',
                message: 'Lang units has been attached successfully!',
                buttons: [{
                    label: 'Close',
                    action: function(dialogRef){
                        dialogRef.close();
                    }
                }]
            });
        }
    });
}
