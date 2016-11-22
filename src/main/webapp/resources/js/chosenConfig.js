/**
 * Created by valko on 11/2/2016.
 */
$(function () {
    $("#categories").chosen({
        max_selected_options: "5",
        width: "auto"

});

    $(".single-select").chosen({
        width: "10%",
        disable_search: "true"
    });
});