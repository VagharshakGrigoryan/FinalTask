<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="../css/tooplate-chilling-cafe.css" rel="stylesheet" type="text/css"/>
<head>
    <title>Title</title>
</head>
<body>
<%List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>
<a href="${pageContext.request.contextPath}/logout">Logout</a>

<div style="text-align: center ">All Tasks:<br>
    <table border="1">
        <tr>
            <th>name</th>
            <th>description</th>
            <th>deadline</th>
            <th>status</th>
            <th>user</th>
            <th>Update Status</th>
                <%
                for (Task task : tasks) {%>
        <tr>
            <td><%=task.getName()%>
            </td>
            <td><%=task.getDescription()%>
            </td>
            <td><%=task.getDeadline()%>
            </td>
            <td><%=task.getTaskStatus().name()%>
            </td>
            <td><%=task.getUser().getName() + " " + task.getUser().getSurname()%>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/changeTaskStatus" method="post">
                    <label>
                        <input type="hidden" name="taskId" value="<%=task.getId()%>">
                        <select name="status">
                            <option value="NEW"> NEW</option>
                            <option value="IN_PROGRESS">IN_PROGRES</option>
                            <option value="FINISHED">FINISHED</option>
                        </select><input type="submit" value="ok">
                    </label>
                </form>

            </td>
        </tr>
        <% }

        %>

    </table>

</div>
</body>
</html>
