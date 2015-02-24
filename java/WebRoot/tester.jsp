<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'post.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		This is Servlet Tester.
		<br>


		<form action="servlet/Login" method="post">
			<label>
				Login
			</label>
			<input type="text" name="userName" value="KrowditStaff" />
			<input type="text" name="pwd" value="123" />
			<select name="userType">
				<option value="1">
					KROWDIT
				</option>
				<option value="2">
					FACEBOOK
				</option>
			</select>
			<input type="submit" />
		</form>

		<form action="servlet/Signup" method="post">
			<label>
				Signup
			</label>
			<input type="text" name="userName" value="jacky" />
			<input type="text" name="pwd" value="123" />
			<input type="text" name="email" value="jacky@tadosoft.com" />
			<input type="submit" />
		</form>

        <form action="servlet/Logout" method="post">
            <label>
                Logout
            </label>
            <input type="text" name="uid" value="1" />
            <input type="submit" />
        </form>

		<form action="servlet/JoinKrowd" method="post">
			<label>
				JoinKrowd
			</label>
			<input type="text" name="uid" value="1" />
			<input type="text" name="teamId" value="2" />
			<input type="text" name="krowdId" value="1" />
			<input type="submit" />
		</form>

		<form action="servlet/ListKrowds" method="post">
			<label>
				ListKrowds
			</label>
			<input type="text" name="lat" value="10" />
			<input type="text" name="lng" value="10" />
			<input type="submit" />
		</form>

		<form action="servlet/RecoverPassword" method="post">
			<label>
				RecoverPassword
			</label>
			<input type="text" name="email" value="jacky@tadosoft.com" />
			<input type="submit" />
		</form>

		<form action="servlet/PostImage" method="post">
			<label>
				PostImage
			</label>
			<input type="text" name="xyz" value="abc" />
			<input type="submit" />
		</form>

		<form action="servlet/ListLocations" method="post">
			<label>
				ListLocations
			</label>
			<input type="text" name="lat" value="10" />
			<input type="text" name="lng" value="10" />
			<input type="submit" />
		</form>

		<form action="servlet/ListKrowdTypes" method="post">
			<label>
				ListKrowdTypes
			</label>
			<input type="submit" />
		</form>

		<form action="servlet/ListTeams" method="post">
			<label>
				ListTeams
			</label>
			<input type="text" name="krowdTypeId" value="1" />
			<input type="submit" />
		</form>

		<form action="servlet/CreateKrowd" method="post">
			<label>
				CreateKrowd
			</label>
			<input type="text" name="krowdTypeId" value="1" />typeId
			<input type="text" name="homeTeamId" value="1" />home
			<input type="text" name="awayTeamId" value="2" />away
			<input type="text" name="locationId" value="1" />locationId
			<select name="creatorId">
				<option value="1">
					KROWDIT_STAFF:1
				</option>
				<option value="2">
					SOMEONE:2
				</option>
			</select>
			<input type="text" name="startTime" value="07-31-2011 20:00:00" />
			<input type="submit" />
		</form>

		<form action="servlet/CreateLocation" method="post">
			<label>
				CreateLocation
			</label>
			<input type="text" name="lat" value="10" />
			<input type="text" name="lng" value="10" />
			<input type="text" name="name" value="NewOrlens" />
			<input type="text" name="typeId" value="1" />typeId
			<input type="text" name="creatorId" value="1" />creatorId
			<input type="submit" />
		</form>

	</body>
</html>
