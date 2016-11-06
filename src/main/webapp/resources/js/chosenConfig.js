/**
 * Created by valko on 11/2/2016.
 */
$(function () {
    $(".category-select").chosen({
        max_selected_options: "5",
        width: "25%"
    });

    $(".single-select").chosen({
        width: "10%",
        disable_search: "true"
    });
});