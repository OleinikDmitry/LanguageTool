
var number = 1;

function goNext(size) {

    $.get('/test/unit/' + number, function (unit) {
        $("#ru_ver").text(unit.versionRu);
        $("#eng_ver").text(unit.versionEn);

        $("#eng_ver").hide();
        $("#visionBtn").show();

        $("#user_ver").focus();
        $("#user_ver").val('');

        $("#progressBar").attr("value", number);
        $("#current_test").text('[ ' + number + ' / ' + size + ' ]');

    });

    number++;

    if (number == size) {
        $("#nextBtn").hide();
    }
}

function showEng(mustVisible) {
    if (mustVisible) {
        $("#eng_ver").show();
        $("#visionBtn").hide();
    } else {
        $("#eng_ver").hide();
    }
}

function initView() {
    showEng(false);
    $("#user_ver").focus();
}

