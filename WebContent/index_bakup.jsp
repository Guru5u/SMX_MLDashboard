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

	<div class="container body">
		<div class="main_container">

			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.jsp" class="site_title"> <span
							class="image_icon"><img src="images/dashboardIcon.png"
								alt="logo" /></span> <span>MLDashboard</span>
						</a>
					</div>
					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">

						<div class="menu_section">
							<h3>&nbsp;</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-home"></i> Home <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="index.jsp">ML Dashboard</a></li>
									</ul></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle" data-toggle="tooltip" data-placement="right"
								title="Toggle Sidebar"> <i class="fa fa-bars"></i>
							</a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="test.html"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <span class=" fa fa-angle-down"></span>
							</a>
								<ul role="menu"
									class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
									<li><a href="javascript:;"> Profile</a></li>
									<li><a href="javascript:;"> <span>Settings</span> <span
											class="badge bg-red pull-right">30%</span>
									</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a href="javascript:;"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>

							<!-- <li role="presentation" class="dropdown">
										<a href="javascript:;" class="dropdown-toggle info-number"
											data-toggle="dropdown" aria-expanded="false"> 
											<i class="fa fa-bell"></i> 
											<span class="badge bg-red">6</span> 
										</a>
										<ul id="menu1"
											class="dropdown-menu list-unstyled msg_list animated fadeInDown"
											role="menu">
											<li><a> <span class="image"> <img
														src="images/users/amaury.png" alt="Profile Image" /> </span> <span>
														<span>Qts</span> <span class="time">7 mins ago</span>
												</span> <span class="message"> Dashboard has been running without
												any problems for several weeks... </span> </a></li>
											<li><a> <span class="image"> <img
														src="images/users/amaury.png" alt="Profile Image" /> </span> <span>
														<span>Qts</span> <span class="time">39 mins ago</span>
												</span> <span class="message"> Noticed some some inconsistencies in the
												data.  Will mention to Management... </span> </a></li>
											<li><a> <span class="image"> <img
														src="images/users/amaury.png" alt="Profile Image" /> </span> <span>
														<span>Qts</span> <span class="time">3 hours ago</span>
												</span> <span class="message"> We are planning on releasing the final
												version of the dashboard next week... </span> </a></li>
											<li><a> <span class="image"> <img
														src="images/users/amaury.png" alt="Profile Image" /> </span> <span>
														<span>Qts</span> <span class="time">2 days ago</span>
												</span> <span class="message"> Still working on some last minute changes
												that I think will add to the WOW factor... </span> </a></li>
											<li>
												<div class="text-center">
													<a> <strong>See All Alerts</strong> <i
														class="fa fa-angle-right"></i> </a>
												</div></li>
										</ul>
									</li> -->
							<!-- <li><a id="bolt" data-toggle="tooltip" data-placement="bottom" title="Simulate Log File Error" href="javascript:createAlert();" class="dropdown-toggle info-number"><i class="fa fa-bolt"></i></a></li>
									<li><a id="bomb" data-toggle="tooltip" data-placement="bottom" title="Simulate Server Down" ng-click="causeHeartbeatError()" class="dropdown-toggle info-number"><i class="fa fa-bomb"></i></a></li>
									<li><a id="speaker" data-toggle="tooltip" data-placement="bottom" title="Mute Alarm" ng-click="muteHeartbeatError()" class="dropdown-toggle info-number">
										<i ng-show="!alert_sound.mute" class="glyphicon glyphicon-volume-up"></i>
										<i ng-show="alert_sound.mute" class="glyphicon glyphicon-volume-off"></i>
										</a>
									</li> -->
						</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->


			<!-- page content -->
			<div class="right_col" role="main">

				<br />
				<div class="">

					<div class="container">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#cpu_tab"
								ng-click="setTabIndex(tabIndex.CPU)"><i class="fa fa-laptop"></i>
									 CATEGORY CLASSIFIER</a></li>
							<li><a data-toggle="tab" href="#memory_tab"
								ng-click="setTabIndex(tabIndex.MEMORY)"><i
									class="fa fa-ticket"></i> BANK CLASSIFIER</a></li>
							<li><a data-toggle="tab" href="#disk_tab"
								ng-click="setTabIndex(tabIndex.DISK)"><i
									class="fa fa-database"></i>PARTY UNIFICATION</a></li>
							<!-- <li><a data-toggle="tab" href="#network_tab" ng-click="setTabIndex(tabIndex.IO)"><i class="fa fa-sitemap"></i> Tab 4</a></li> -->
							<li><a data-toggle="tab" href="#os_tab"
								ng-click="setTabIndex(tabIndex.OS)"><i class="fa fa-windows"></i>
									IDGP</a></li>
						</ul>

						<div class="tab-content">
							
							<!-- Ocrawl Details Tab -->
							<div id="cpu_tab" class="tab-pane fade in active">
								<div class="row">
									<div class="col-md-12">
										<div class="x_panel">
											<div class="x_title">
												<!-- <h2>
													CATEGORY CLASSIFIER<small></small>
												</h2> -->
												<ul class="nav navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>
													<li><a class="close-link"><i class="fa fa-close"></i></a>
													</li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<!-- <div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="ocrawlInTextarea" cols="100" rows="3" ng-model="ocrawlInTextarea" ng-init="ocrawlInTextarea = 'SX_ID,NAME,ALT_NAME,PHONE,STREET,CITY,STATE,ZIP,ZIP_EXT,SX_NAICS,NAICS_DESCR,SOURCE'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div> -->
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
													<button type="button" class="btn btn-primary" ng-click="addRow2Grid1()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid1()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid1()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid1()">Clear Grid</button>
														
														<div id="grid1" ui-grid="gridOptions1" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize></div>
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="trainCatClassifier()">														
															 <span data-ng-hide="ocrawlRunLoad"> Train Classifier </span>
															 
															 <span data-ng-show="ocrawlRunLoad"> OCRAWL Running...
															 <i class="fa fa-spinner fa-spin"></i>
															 </span>
														</button>
														<button class="btn btn-primary" ng-click="testCatClassifier()">														
															 <span data-ng-hide="ocrawlRunLoad"> Test Classifier </span>
															 
															 <span data-ng-show="ocrawlRunLoad"> OCRAWL Running...
															 <i class="fa fa-spinner fa-spin"></i>
															 </span>
														</button>
													</div>
												</div>
												
												
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="ocrawlOutTextarea" cols="100" rows="1" ng-model="ocrawlOutTextarea" ng-init="ocrawlOutTextarea = 'i_key,Addr_1,Addr_2,Addr_3,Addr_4,BANK_STATUS,ISO_COUNTRY_CD,BANK_ID'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div>
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
														<button type="button" class="btn btn-primary" ng-click="addRow2Grid2()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid2()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid2()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid2()">Clear Grid</button>
														
														 <div id="grid2" ui-grid="gridOptions2" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize>															
														</div> 
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="updateOcrawl()">
														 <span data-ng-hide="ocrawlUpdateLoad"> Update Classifier </span>
															 
															 <span data-ng-show="ocrawlUpdateLoad"> OCRAWL Updating...
															 <i class="fa fa-spinner fa-spin"></i>
															 </span>
														</button>
													</div>
												</div>
											</div> 
											</div>
										</div>
									</div>
							</div>
					<!-- Ocrawl Details Tab -->

					<!-- BANK CLASSIFIER Details Tab -->
							<div id="memory_tab" class="row tab-pane fade">
								<div class="row">
									<div class="col-md-12">
										<div class="x_panel">
											<div class="x_title">
												<h2>
													BANK CLASSIFIER<small></small>
												</h2>
												<ul class="nav navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>
													<li><a class="close-link"><i class="fa fa-close"></i></a>
													</li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="wmdmTextarea" cols="100" rows="3" ng-model="wmdmTextarea" ng-init="wmdmTextarea = 'I_KEY,ADD_1,ADD_2,ADD_3,ADD_4'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div>
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
													<button type="button" class="btn btn-primary" ng-click="addRow2Grid3()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid3()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid3()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid3()">Clear Grid</button>
														
														<div id="grid3" ui-grid="gridOptions3" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize></div>
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="runClassifier()">													
															  <span> Run Classifier </span>
															</button>
													</div>
													<!-- <div class="col-md-12 text-left" style="overflow: hidden;"> 
							       						<progressbar  data-ng-show="Loading" value="counter">
									                 			{{counter}} {{limit}} {{max}}</progressbar> 
									                 </div> -->
									                 
									                 <div class="col-md-12 text-left" style="overflow: hidden;">
										                 <div class="progress" ng-hide= "pBarLoading3">
	    														<div  class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:{{dynamic}}%">
	      															{{msg | number:0}} {{percentage}}
	    														</div>
	  													</div> 
		              								</div>
		              								</div>
												</div>	
											</div>
												
												
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="wmdmUpdateTextarea" cols="100" rows="1" ng-model="wmdmUpdateTextarea" ng-init="wmdmUpdateTextarea = 'i_key,Addr_1,Addr_2,Addr_3,Addr_4,BANK_STATUS,ISO_COUNTRY_CD,BANK_ID'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div>
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
														<button type="button" class="btn btn-primary" ng-click="addRow2Grid4()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid4()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid4()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid4()">Clear Grid</button>
														
														<div id="grid4" ui-grid="gridOptions4" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize>
															
														</div>
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="updateClassifier()"> Update Classifier
														</button>
													</div>
													<!-- <div class="col-md-12 text-left" style="overflow: hidden;"> 
							       						<progressbar  data-ng-show="updateLoading" value="counter">
									                 			{{updtCounter}} {{updtLimit}} {{updtMax}}</progressbar> 
									                 </div> -->
									                 
									                 <div class="col-md-12 text-left" style="overflow: hidden;">
										                 <div class="progress" ng-hide= "pBarLoading4">
	    														<div  class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:{{dynamic}}%">
	      															{{msg | number:0}} {{percentage}}
	    														</div>
	  													</div> 
		              								</div>
												</div>
											</div> 
									</div>
							</div>
					<!-- BANK CLASSIFIER Details Tab -->


					<!-- PARTY UNIFICATION Details Tab -->
							<div id="disk_tab" class="row tab-pane fade">
								<div class="row">
									<div class="col-md-12">
										<div class="x_panel">
											<div class="x_title">
												<h2>
													PARTY UNIFICATION<small></small>
												</h2>
												<ul class="nav navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>
													<li><a class="close-link"><i class="fa fa-close"></i></a>
													</li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="partyTextarea" cols="100" rows="3" ng-model="partyTextarea" ng-init="partyTextarea = 'I_KEY,ADD_1,ADD_2,ADD_3,ADD_4'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div>
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
													<button type="button" class="btn btn-primary" ng-click="addRow2Grid5()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid5()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid5()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid5()">Clear Grid</button>
														
														<div id="grid5" ui-grid="gridOptions5" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize></div>
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="runPartyClassifier()">														
															 <span data-ng-hide="runPartyLoading"> Run Party Unification  </span>
															 
															 <span data-ng-show="runPartyLoading"> Unification Running...
															 <i class="fa fa-spinner fa-spin"></i>
															 </span>
														</button>
													</div>
												</div>
												
												
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="partyUpdateTextarea" cols="100" rows="1" ng-model="partyUpdateTextarea" ng-init="partyUpdateTextarea = 'i_key,Addr_1,Addr_2,Addr_3,Addr_4,BANK_STATUS,ISO_COUNTRY_CD,BANK_ID'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div>
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
														<button type="button" class="btn btn-primary" ng-click="addRow2Grid6()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid6()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid6()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid6()">Clear Grid</button>
														
														<div id="grid6" ui-grid="gridOptions6" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize>
															

															
														</div>
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="updateClassifier()">
														 <span data-ng-hide="upLoading"> Update Party Unification </span>
															 
															 <span data-ng-show="upLoading"> Party Unification Updating...
															 <i class="fa fa-spinner fa-spin"></i>
															 </span>
														</button>
													</div>
												</div>
											</div> 
											</div>
										</div>
									</div>
							</div>
					<!-- PARTY UNIFICATION details Tab -->

					<!-- IDGP Details Tab-->
							<div id="os_tab" class="row tab-pane fade">
								<div class="row">
									<div class="col-md-12">
										<div class="x_panel">
											<div class="x_title">
												<h2>
													IDGP<small></small>
												</h2>
												<ul class="nav navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>
													<li><a class="close-link"><i class="fa fa-close"></i></a>
													</li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="idgpTextarea" cols="100" rows="3" ng-model="idgpTextarea" ng-init="idgpTextarea = 'ID|MERCHANT_NAME|STREET_NAME|CITY|STATE|ZIP|COUNTRY|BUSINESS_CATEGORY'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div>
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
													<button type="button" class="btn btn-primary" ng-click="addRow2Grid7()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid7()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid7()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid7()">Clear Grid</button>
														
														<input type="file" on-read-file="uploadGrid7($fileContent)" />
														<div id="grid7" ui-grid="gridOptions7" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize></div>
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="runClassifier()">														
															 <span data-ng-hide="idgpLoading"> Run IDGP </span>
															 
															 <span data-ng-show="idgpLoading"> IDGP Running...
															 <i class="fa fa-spinner fa-spin"></i>
															 </span>
														</button>
													</div>
												</div>
												
												
												<div class="row"
													style="border-bottom: 1px solid #E0E0E0; padding-bottom: 5px; margin-bottom: 5px;">
													<div  class="row" style="padding-bottom: 5px; margin-bottom: 5px;">
														<div class="col-md-12 text-left" style="overflow: hidden;">
													   		<textarea class ="text-left" id="prtyUpdateTextarea" cols="100" rows="1" ng-model="prtyUpdateTextarea" ng-init="prtyUpdateTextarea = 'i_key,Addr_1,Addr_2,Addr_3,Addr_4,BANK_STATUS,ISO_COUNTRY_CD,BANK_ID,RECORD_TYPE'" style="width: 98%; height: 100px; resize: none;"></textarea>
															
														</div>
													</div>	
													<div class="col-md-12 text-right" style="overflow: hidden;">
														<button type="button" class="btn btn-primary" ng-click="addRow2Grid8()">Add To Row</button>
														<button type="button" class="btn btn-primary" ng-click="addDataGrid8()">Add Data</button>
														<button type="button" class="btn btn-primary" ng-click="deleteSelectedGrid8()">Delete Selected</button>
														<button type="button" class="btn btn-primary" ng-click="clearGrid8()">Clear Grid</button>
														
														<input type="file" on-read-file="uploadGrid8($fileContent)" />
														<div id="grid8" ui-grid="gridOptions8" ui-grid-importer
															class="grid" ui-grid-edit ui-grid-row-edit
															ui-grid-selection ui-grid-exporter ui-grid-pagination ui-grid-auto-resize>
															

															
														</div>
														<p></p>
													</div>
													<div class="col-xs-6 text-right">
														<button class="btn btn-primary" ng-click="updateIdgpClassifier()">
														 <span data-ng-hide="idgpUpLoading"> Update IDGP </span>
															 
															 <span data-ng-show="idgpUpLoading"> IDGP Updating...
															 <i class="fa fa-spinner fa-spin"></i>
															 </span>
														</button>
													</div>
												</div>
											</div> 
											</div>
										</div>
									</div>
							</div>
					<!-- IDGP Details Tab -->
						</div>
						<!-- tab content -->
					</div>
					<!-- container content -->

					<!-- footer content -->
					<footer>
						<div class="">
							<p class="pull-right">
								ML Dashboard -- SMX | <span class="image_icon_footer"><img
									src="images/dashboardIcon.png" alt="logo" /></span> systematrix.com
							</p>
						</div>
						<div class="clearfix"></div>
					</footer>
					<!-- /footer content -->

				</div>
				<!-- /page content -->
			</div>
		</div>
</div>
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