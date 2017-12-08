/**
 * Created by valko on 30-Aug-16.
 */

function getCorrectAnswer(questionNumber) {
    var result = null;
    $.ajax({
        url: "/asker/evaluate",
        type: "GET",
        async: false,
        data: {'data': data[questionNumber].questionId},
        dataType: "text",
        success: function (data) {
            result = data.hashCode();
        },
        error: function () {
            debugger;
        }
    });
    return result;
}