$(function () {
    $(".btn").on("click", function () {

        var permissions = $.map($(this).parent("form").find("input[type=checkbox]:checked"), function (element, index) {
            return $(element).val();
        });

        var query = "permission=" + permissions.join("&permission=");
        var role = $($(this).parent("form").children("input[name=role]")).val();

        $.ajax({
            method: "PUT",
            url: `${CONTEXT_PATH}api/roles/${role}?${query}`,
        }).done(function (data) {
            alert("更新しました。再ログイン後に反映されます。");
        }).fail(function (e) {
            alert("更新に失敗しました");
        });
    });
}());