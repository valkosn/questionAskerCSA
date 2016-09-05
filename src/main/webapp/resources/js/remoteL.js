/**
 * Created by valko on 30-Aug-16.
 */

function getCorrectAnswer(questionNumber) {
    var result = null;
    $.ajax({
        url: "/qa/evaluate",
        type: "POST",
        async: false,
        data: {'data': data[questionNumber].id},
        dataType: "text",
        success: function (resData) {
            result = resData.hashCode();
        },
        error: function () {
            debugger;
        }
    });
    return result;
}