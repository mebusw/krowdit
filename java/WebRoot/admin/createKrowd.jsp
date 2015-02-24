<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tadosoft.krowdit.util.DateFormatter"%>
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

		<title>My JSP 'createKrowd' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script src="./js/util.js" type="text/javascript">
</script>
		<script src="./js/jquery.js" type="text/javascript">
</script>
		<script src="./js/jquery.form.js" type="text/javascript">
</script>
		<script src="./js/jquery.validate.pack.js" type="text/javascript">
</script>
		<script src="./js/datetimepicker_css.js" type="text/javascript">
</script>

		<script type="text/javascript">
$(document).ready(function() {
	/////
		jQuery.validator.addMethod("notEqual", function(value, element, param) {
			return this.optional(element) || value != $(param).val();
		}, "Please specify a different (non-default) value");

		$("#createKrowdForm").validate( {
			rules : {
				startTime : {
					required : true
				},
				homeTeamId : {
					required : true
				},
				awayTeamId : {
					required : true,
					notEqual : "#homeTeamId"
				}
			},
			messages : {
				regEmail : {
					startTime : "请输入您的电子邮件地址"
				}
			}
		});
		/////////
		$("#createKrowdForm").ajaxForm( {
			dataType : 'json',
			success : function(data) {
				switch (data.error) {
				case 0:
					alert("created");
					break;
				default:
					alert("failed, error=" + data.error);
				}
			}
		});

		//////////////
		$.getJSON("./servlet/ListKrowdTypes", {}, function(data) {
			switch (data.error) {
			case 0:
				$("#krowdTypeId").empty();
				$.each(data.krowdTypes, function(i, item) {
					$("#krowdTypeId").append(
							"<option value='{0}'>{1}</option>".Format(
									item.krowdTypeId, item.krowdTypeName));
				});
				break;
			default:
			}
		});

		/////////////////
		$.getJSON("./servlet/ListLocations", {
			lat : 10.0,
			lng : 10.0
		}, function(data) {
			switch (data.error) {
			case 0:
				$("#locationId").empty();
				$.each(data.locations, function(i, item) {
					$("#locationId").append(
							"<option value='{0}'>{1}</option>".Format(
									item.locationId, item.locationName));
				});
				$("#krowdTypeId").change();
				break;
			default:
			}
		});

		/////////////////
		$("#krowdTypeId").change(
				function() {
					$.getJSON("./servlet/ListTeams", {
						krowdTypeId : $("#krowdTypeId").val()
					}, function(data) {
						switch (data.error) {
						case 0:
							$("#homeTeamId").empty();
							$("#awayTeamId").empty();
							$.each(data.teams, function(i, item) {
								$("#homeTeamId").append(
										"<option value='{0}'>{1}</option>"
												.Format(item.teamId,
														item.teamName));
								$("#awayTeamId").append(
										"<option value='{0}'>{1}</option>"
												.Format(item.teamId,
														item.teamName));
							});

							break;
						default:
						}
					});
				});

	});</script>
	</head>

	<body>
		<h2>
			Create Krowdit
		</h2>
		<br>

		<form id="createKrowdForm" action="servlet/CreateKrowd" method="get">
			<label>
				location
			</label>
			<select name="locationId" id="locationId"></select>
			<br />
			<label>
				type
			</label>
			<select name="krowdTypeId" id="krowdTypeId"></select>
			<br />
			<label>
				home
			</label>
			<select name="homeTeamId" id="homeTeamId"></select>
			<br />
			<label>
				away
			</label>
			<select name="awayTeamId" id="awayTeamId"></select>
			<br />

			<label>
				creator
			</label>
			<select name="creatorId">
				<option value="1">
					KROWDIT_STAFF
				</option>
				<option value="2">
					UNOFFICIAL
				</option>
			</select>
			<br />
			<label>
				startTime
			</label>
			<input type="text" name="startTime" id="startTime" maxlength="25"
				size="25" value="" readonly="readonly" />
			<img src="images/cal.gif"
				onclick="javascript:NewCssCal('startTime', 'MMddyyyy', 'arrow', true, 24, true)"
				style="cursor: pointer" />
			<br />
			<input type="submit" />
		</form>

	</body>
</html>
