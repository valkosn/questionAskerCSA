<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Asker</title>
    <link href="/qa/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/lib/DataTables-1.10.12/media/css/jquery.dataTables.min.css" rel="stylesheet"
          type="text/css">
    <link href="/qa/resources/lib/DataTables-1.10.12/media/css/dataTables.bootstrap.min.css" rel="stylesheet"
          type="text/css">

    <script src="/qa/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="/qa/resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/qa/resources/lib/DataTables-1.10.12/media/js/jquery.dataTables.min.js"
            type="text/javascript"></script>
    <script src="/qa/resources/lib/DataTables-1.10.12/media/js/dataTables.bootstrap.min.js"
            type="text/javascript"></script>

</head>

<header>
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/qa/start">Home</a></li>
        <li role="presentation"><a href="/qa/config/questionManager/">Question Manager</a></li>
        <li role="presentation" class="active"><a href="/qa/config/categoryManager/">Category Manager</a></li>
    </ul>
</header>

<body>
<div id="addContainer" class="form-horizontal col-lg-3">
    <div class="input-group">
        <input id="new_category" class="form-control" name="newCategory" placeholder="Category" type="text">
        <span class="input-group-btn">
        <button id="add_button" class="btn btn-primary" name="addButton" type="button" onclick="addNewCat()">
            Add new category
        </button>
    </span>
    </div>
</div>

<div class="container">
    <div class="row">
        <table id="categoryTable" class="table table-striped" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="cat">
                <tr>
                    <td>${cat.key}</td>
                    <td>${cat.value}</td>
                    <td>
                        <button class="btn btn-danger" type="button" onclick="rmCat(${cat.key})">
                            Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    function addNewCat() {
        $.ajax({
            url: "/qa/config/categoryManager",
            type: "POST",
            data: {'data': $("#new_category").val()},
            dataType: "text",
            success: function () {
                location.reload(true);
            },
            error: function (xhr, resp, text) {
                console.log(xhr, resp, text);
                debugger;
            }
        });
    }

    function rmCat(catID) {
        $.ajax({
            url: "/qa/config/categoryManager/" + catID,
            type: "DELETE",
            success: function () {
                location.reload(true);
            },
            error: function (xhr, resp, text) {
                console.log(xhr, resp, text);
                debugger;
            }
        });
    }

    $(document).ready(function () {
        $('#categoryTable').DataTable({
        });
    });
</script>
</body>
</html>
