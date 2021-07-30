<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Manager</title>
</head>
<body>
<%List<User> users = (List<User>) request.getAttribute("users");%>
<%List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<div style="width: 800px ">
    <div style="width: 50%; float: left">
        Ad User<br>
        <form action="${pageContext.request.contextPath}/userRegister" method="post">
            <br>
            name:<label><br>
            <input type="text" name="name" placeholder="name">
        </label> <br>
            surname:<label><br>
            <input type="text" name="surname" placeholder="surname">
        </label><br>
            email: <label><br>
            <input type="text" name="email" placeholder="email">
        </label><br>
            password: <label><br>
            <input type="password" name="password" placeholder="password"></label>
            <label><br>
                <select name="type">
                    <option value="USER"> USER</option>
                    <option value="MANAGER"> MANAGER</option>
                </select>
            </label><br>
            <input type="submit" value="Register"> <br>
        </form>
        <div style="text-align: center ">All Users:<br>
            <table border="1">
                <tr>
                    <th>name</th>
                    <th>surname</th>
                    <th>email</th>
                    <th>type</th>
                        <%
                for (User user : users) {%>
                <tr>
                    <td><%=user.getName()%>
                    </td>
                    <td><%=user.getSurname()%>
                    </td>
                    <td><%=user.getEmail()%>
                    </td>
                    <td><%=user.getUserType().name()%>
                    </td>
                </tr>
                <% }

                %>

            </table>
        </div>

    </div>




    <div style="width: 50%; float: right ">
        Ad Task<br>
        <form action="${pageContext.request.contextPath}/adTask" method="post"><br>
            name:<label><br>
                <input type="text" name="name" placeholder="name">
            </label><br>
            description:<label><br>
                <textarea name="description" placeholder="description"></textarea>
            </label><br>
            deadline:<label><br>
                <input type="datetime-local" name="deadline">
            </label><br>
            <label>
                <select name="status">
                    <option value="NEW"> NEW</option>
                    <option value="IN_PROGRESS">IN_PROGRES</option>
                    <option value="FINISHED">FINISHED</option>
                </select>
            </label><br>
            <label>
                <select name="user_id">
                    <%
                        for (User user : users) {%>
                    <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
                    </option>
                    <% }

                    %>
                </select>
            </label><br>

            <input type="submit" value="add task">
        </form>
        <div style="text-align: center ">All Tasks:<br>
            <table border="1">
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>deadline</th>
                    <th>status</th>
                    <th>user</th>
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
                </tr>
                <% }

                %>

            </table>

        </div>
    </div>
</div>


</body>
</html>
