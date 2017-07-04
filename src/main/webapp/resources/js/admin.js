
$(document).ready(function() {
    $("#grid").kendoGrid({
        dataSource: {
            type: "json",
            transport: {
                read: {url : "/langUnits", type : "POST"},
                update: {url : "/langUnits/update", type : "POST"},
                destroy: {url : "/langUnits/destroy", type : "POST"},
                create: {url : "/langUnits/create", type : "POST"}
            },
            pageSize: 20,
            schema: {
                model: { id: "id" }
            }
        },
        height: 700,
//                groupable: true,
        sortable: true,
        pageable: {
            refresh: true,
            pageSizes: true,
            buttonCount: 5
        },
        toolbar: ["create"],
        columns: [{
            field: "versionRu",
            title: "Русская версия"
//                    width: 240
        }, {
            field: "versionEn",
            title: "English version"
        },
            { command: ["edit", "destroy"], width: "200px"}] ,
        editable: {
            mode: "popup",
            window: {
                title: "Enter the unit"
                //animation: false
//                        width: "600px",
//                        height: "300px"
            }
        }

    });
});

function generateArea() {
    return '<textarea id="batchArea" class="form-control" placeholder="english version = russian version" rows="15" cols="50"></textarea>';
}

function addBatch() {
    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_DEFAULT,
        size: BootstrapDialog.SIZE_LARGE,
        title: 'Lang units',
        message: generateArea(),
        buttons: [{
            label: 'Save',
            cssClass: 'btn-primary',
            hotkey: 13, // Enter.
            action: function(dialog) {
                $.post( "/langUnits/addBatch", {batchStr : $("#batchArea").val()}, function(data) {
                    if (data.status == "OK") {
                        showSuccessDialog();
                    } else {
                        showErrorDialog(data.message);
                    }

                });
                dialog.close();

            }
        }]
    });
}

function showSuccessDialog() {
    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_DEFAULT,
        title: 'Add batch result',
        message: 'Batch of lang units has been added successfully!',
        buttons: [{
            label: 'Close',
            action: function(dialogRef){
                dialogRef.close();
            }
        }]
    });
}

function showErrorDialog(message) {
    BootstrapDialog.show({
        type : BootstrapDialog.TYPE_DANGER,
        title: 'Error',
        message: message,
        buttons: [{
            label: 'Close',
            action: function(dialogRef){
                dialogRef.close();
            }
        }]
    });

}

