<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Asker</title>
    <link href="/asker/resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/asker/resources/lib/DataTables-1.10.12/media/css/jquery.dataTables.min.css" rel="stylesheet"
          type="text/css">
    <link href="/asker/resources/lib/DataTables-1.10.12/media/css/dataTables.bootstrap.min.css" rel="stylesheet"
          type="text/css">
    <link href="/asker/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Bangers" rel="stylesheet">


    <script src="/asker/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="/asker/resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/asker/resources/lib/DataTables-1.10.12/media/js/jquery.dataTables.min.js"
            type="text/javascript"></script>
    <script src="/asker/resources/lib/DataTables-1.10.12/media/js/dataTables.bootstrap.min.js"
            type="text/javascript"></script>

</head>

<body>

<header>
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/asker/start">Home</a></li>
        <li role="presentation"><a href="/asker/config/questionManager/">Question Manager</a></li>
        <li role="presentation" class="active"><a href="/asker/config/categoryManager/">Category Manager</a></li>
        <li role="presentation"><a id="addNewCategoryButton" data-toggle="modal" data-target="#categoryModal">Add new category</a></li>
    </ul>
</header>

<div class="modal fade" id="categoryModal" tabindex="-1" role="dialog" aria-labelledby="categoryModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title text-center" id="categoryModalLabel">Add new category</h4>
            </div>

            <div class="modal-body" id="categoryModalHolder">
                <!-- Question holder -->
                <input id="new_category" class="form-control" name="newCategory" placeholder="Category" type="text">
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Close
                </button>

                <button type="button" class="btn btn-success" onclick="addNewCat()">
                    Save changes
                </button>
            </div>
        </div>
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

<footer class="footer">
    <div class="container">
        <hr>
        <p class="pull-right">Design by Rebok inc 2016</p>
    </div>
</footer>

<script>
    function addNewCat() {
        $.ajax({
            url: "/asker/config/categoryManager",
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
            url: "/asker/config/categoryManager/" + catID,
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
