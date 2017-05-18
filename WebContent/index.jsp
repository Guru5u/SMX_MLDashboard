<%@ page language="java"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html lang="en" ng-app="app">


<%
	/* 
	**  Using the Gentellela Admin template as a base to start...  All other elements have 
	**  been customized by me.  -- Amaury Valdes  
	** 
	**  https://github.com/kimlabs/gentelella
	**  
	*/
%>

<%
	String fullProtocol = request.getProtocol().toLowerCase();
	String protocol[] = fullProtocol.split("/");
	String baseUrl = protocol[0] + "://" + request.getHeader("Host");

	boolean isDebug = false;
	String debugParam = request.getParameter("debug");
	if (debugParam != null
			&& (debugParam.toLowerCase().equals("true")
					|| debugParam.toLowerCase().equals("yes") || debugParam
						.equals("1"))) {
		isDebug = true;
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->

<meta charset="utf-8">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>System Dashboard</title>

<!-- Bootstrap core CSS -->

<link href="css/dashboard.css" rel="stylesheet" />
<link href="css/bootstrap.css" rel="stylesheet">
<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"> -->

<link href="fonts/css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="css/angular-loading.css" />

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.5.1/jquery.nicescroll.min.js"></script>

<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>

<script
	src="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-csv/0.71/jquery.csv-0.71.min.js"></script>
  
<link
	href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/select/1.2.1/css/select.dataTables.min.css"
	rel="stylesheet">


<link data-require="font-awesome@*" data-semver="4.3.0" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />

<script src="js/angular-1.4.6/angular.js"></script>
<script src="js/angular-1.4.6/angular-touch.js"></script>
<script src="js/angular-1.4.6/angular-animate.js"></script>



<!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-animate.js"></script> -->
<script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
<script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
<script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>

<script src="http://ui-grid.info/release/ui-grid-unstable.js"></script>
<link rel="stylesheet"
	href="http://ui-grid.info/release/ui-grid-unstable.css" type="text/css">

<script src="js/lib/release/4.0.0/ui-grid.js"> </script>
<link href="js/lib/release/4.0.0/ui-grid.css" rel="stylesheet">

<!-- <script src="js/ui-bootstrap-tpls-0.13.0.min.js"></script> -->

<script src="js/bootstrap.js"></script>
<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script> 

<!-- angular-chart includes -->
<script src="js/chartjs/chart.js"></script>
<script src="js/angular-chart/angular-chart.css"></script>
<script src="js/angular-chart/angular-chart.js"></script>

<!-- angular-nvd3 includes -->
<link rel="stylesheet" href="js/angular-nvd3/nv.d3.min.css" />
<script src="js/angular-nvd3/d3.min.js"></script>
<script src="js/angular-nvd3/nv.d3.min.js"></script>
<script src="js/angular-nvd3/angular-nvd3.min.js"></script>

<!-- angular-justgage includes -->
<script src="js/justgage/raphael-2.1.4.min.js"></script>
<script src="js/justgage/justgage-1.1.0.js"></script>
<script src="js/justgage/angular-gage.js"></script>

<!-- Custom styling plus plugins -->
<link href="css/custom.css" rel="stylesheet">
<script src="js/application.js"></script>
<script src="js/authService.js"></script>
<script src="js/dashapp.js"></script>
<!-- UI Grid -->


<!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
					<script src="js/html5shiv.min.js"></script>
          <script src="js/respond.min.js"></script>
        <![endif]-->

</head>


<body class="nav-md" ng-controller="MainCtrl">

<div ng-view></div>
		<div id="custom_notifications" class="custom-notifications dsp_none">
			<ul class="list-unstyled notifications clearfix"
				data-tabbed_notifications="notif-group">
			</ul>
			<div class="clearfix"></div>
			<div id="notif-group" class="tabbed_notifications"></div>
		</div>

		<!-- PNotify -->

		<script src="js/custom.js"></script>

		<script type="text/javascript">        
       function createAlert() {
    	   console.log("Inside createAlert()...");
    	   new PNotify({
               title: 'Log File Alert',
               text: 'Log File Alert... System has detected a log file error that needs your attention...',
               type: 'error',
               hide: false
           });
       }
       
</script>
		<div ng-element-ready="setDefaults('<%=isDebug%>', '<%=baseUrl%>')"></div>
</body>

</html>