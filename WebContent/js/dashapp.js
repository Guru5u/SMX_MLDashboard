
var app = angular.module('app',['ngRoute', 'ngCookies','ui.bootstrap','chart.js', 'nvd3', 'ngAnimate', 'ui.grid.cellNav' , 'ui.grid.validate','ngTouch', 'ui.grid', 'ui.grid.saveState', 'ui.grid.importer', 'ui.grid.exporter', 'ui.grid.pagination', 'ui.grid.selection', 'ui.grid.rowEdit', 'ui.grid.edit', 'ui.grid.selection', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pinning', 'ui.bootstrap', 'ui.grid.autoResize']);

//var app = angular.module('app', ['ui.bootstrap', 'chart.js', 'nvd3', 'ngAnimate', 'ngTouch', 'ui.grid', 'ui.grid.importer', 'ui.grid.exporter', 'ui.grid.rowEdit', 'ui.grid.edit', 'ui.grid.pagination']);

/******************************** login code *********************************/

app.config(function($routeProvider) {

    $routeProvider
      .when('/login', {
        controller: 'LoginController',
        templateUrl: 'login.html',
        hideMenus: true
      })

    .when('/', {
      controller: 'HomeController',
      templateUrl: 'dashmain.html'
    })

    .otherwise({
      redirectTo: '/login'
    });

  });
  
   app.run(function($rootScope, $location, $cookieStore, $http) {
 // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; 
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }
        });
   });



/******************************* login code *********************************/



app.filter('titlecase', function() {
    return function(s) {
    	//console.log("Inside of titlecase...");
        s = ( s === undefined || s === null ) ? '' : s;
        return s.toString().toLowerCase().replace( /\b([a-z])/g, function(ch) {
            return ch.toUpperCase();
        });
    };
});

app.filter('titleLookup', function() {	
	return function(key, scope) {
		console.log("Inside of titleLookup..." + key + ", titleData: " + scope.titleData);
		/*angular.forEach(scope.titleData, function(k, v) {
			console.log("lookup key=" + k + ", value=" + v);
			if (key.equals(k)) {
				return v;
			}
		});*/
		return key;
	};
 });

app.directive('focusOn', function() {
   return function(scope, elem, attr) {
      scope.$on(attr.focusOn, function(e) {
          elem[0].focus();
      });
   };
});

app.directive('ngElementReady', [function() {
    return {
        priority: Number.MIN_SAFE_INTEGER, // execute last, after all other directives if any.
        restrict: "A",
        link: function($scope, $element, $attributes) {
            $scope.$eval($attributes.ngElementReady); // execute the expression in the attribute.
        }
    };
  }]);

app.directive('errorCondition', ['$animate', function($animate) {
  return function(scope, elem, attr) {
    scope.$watch(attr.errorCondition, function(value) {
      if (value == true) {
    	  $animate.removeClass(elem, 'tile-stats front pulser');
    	  $animate.addClass(elem, 'tile-stats-error front pulser animated flash ');
      } else {
    	  $animate.removeClass(elem, 'tile-stats-error front pulser animated flash');
    	  $animate.addClass(elem, 'tile-stats front pulser');
      }
    });
  };
}]);

app.directive('errorConditionBack', ['$animate', function($animate) {
  return function(scope, elem, attr) {
    scope.$watch(attr.errorConditionBack, function(value) {
      if (value == true) {
    	  $animate.removeClass(elem, 'tile-stats-back back pulser');
    	  $animate.addClass(elem, 'tile-stats-error back pulser animated flash ');
      } else {
    	  $animate.removeClass(elem, 'tile-stats-error back pulser animated flash');
    	  $animate.addClass(elem, 'tile-stats-back back pulser');
      }
    });
  };
}]);

app.directive('animateOnChange', ['$animate', '$timeout', function($animate, $timeout) {
  return function(scope, elem, attr) {
    scope.$watch(attr.animateOnChange, function() {
      $animate.addClass(elem, 'heartbeat animated pulse image').then(function() {
        $timeout(function(){
          $animate.removeClass(elem, 'heartbeat animated pulse');
        }, 0);
      });
    });
  };
}]);

app.directive("sparklinechart", function () {

    return {
        restrict: "E",
        scope: {
            data: "@",
            trigger: "@",
            type: '@',
            width: '@',
            height: '@',
            barWidth: '=',	
            barColor: '@',
            options: '='
        },
        compile: function (tElement, tAttrs, transclude) {
            tElement.replaceWith("<span>" + tAttrs.data + "</span>");
            return function (scope, element, attrs) {
            	var options = {
            			type: scope.type,
                        width: scope.width,
                        height: scope.height,                        
                        barWidth: scope.barWidth,
                        barColor: scope.barColor,
                        //value: scope.value
                      };

                      if (scope.options) {
                          for (var key in scope.options) {
                              options[key] = scope.options[key];
                          }
                      }
                      
                attrs.$observe("data", function (newValue) {
                    //console.log("inside data: " + newValue);
                    element.html(newValue);
                    element.sparkline('html', options);
                    //element.sparkline('html', { type: 'bar', width: '100%', height: '80px', barWidth: 6, barColor: 'blue' });
                });
                
                attrs.$observe("trigger", function (newValue) {
                    console.log("inside trigger: " + newValue);
                    element.html("");
                    element.sparkline('html', options);
                    //element.sparkline('html', { type: 'bar', width: '100%', height: '80px', barWidth: 6, barColor: 'blue' });
                });
            };
        }
    };
});

app.directive('modal', function () {
    return {
      template: '<div class="modal fade" title="">' + 
          '<div class="modal-dialog" title="">' + 
            '<div class="modal-content">' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="false">&times;</button>' +
                '<h4 class="modal-title">'+
                '<span class="glyphicon glyphicon-star" aria-hidden="true"></span>' + 
                '  {{ title }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude></div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true) {
            $(element).modal('show');
          	scope.tt_isOpen = false;
          } else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });

app.directive('validShortname', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue) {
                // Any way to read the results of a "required" angular validator here?
                var isBlank = viewValue === '';
                var invalidChars = !isBlank && !/^[A-z0-9]+$/.test(viewValue);
                var invalidLen = !isBlank && !invalidChars && (viewValue.length < 5);
                var isValid = !isBlank && !invalidChars && !invalidLen;
                ctrl.$setValidity('isBlank', !isBlank);
                ctrl.$setValidity('invalidChars', !invalidChars);
                ctrl.$setValidity('invalidLen', !invalidLen);
                scope.shortnameGood = isValid;
            });
        }
    };
});

app.service('ajaxService', function($http) {
	this.getData = function(URL, ajaxMethod, ajaxParams) {
		var restURL = URL + ajaxParams;
		console.log("Inside ajaxService...");
		console.log("Connection using URL=[" + restURL + "], Method=[" + ajaxMethod + "]");
	    // $http() returns a $promise that we can add handlers with .then()
	    return $http({
	        method: ajaxMethod,
	        url: restURL,
	     });
	 };

	this.postData = function(URL, ajaxMethod, jsonData, ajaxParams) {
		var restURL = URL + ajaxParams;
		console.log("Inside ajaxService POST...");
		console.log("Connection using URL=[" + restURL + "], Method=[" + ajaxMethod + "]");
		
	    // $http() returns a $promise that we can add handlers with .then()
	    return $http({
	        method: ajaxMethod,
	        url: restURL,
	        headers: {'Content-Type': 'application/json'},
	        data: jsonData,
	     });
		
	};
});

app.directive('splitArray', function() {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel) {

            function fromUser(text) {
            	if (text != null)
            		return text.split("\n");
            }

            function toUser(array) {
            	if (array != null)
                return array.join("\n");
            }

            ngModel.$parsers.push(fromUser);
            ngModel.$formatters.push(toUser);
        }
    };
});
 
app.filter('mapGender', function() {
	  var genderHash = {
	    1: 'YES',
	    2: 'NO'
	  };

	  return function(input) {
	    if (!input){
	      return '';
	    } else {
	      return genderHash[input];
	    }
	  };
	})
	
app.filter('mapBank_status', function() {
	 var genderHash = {
	   'B': 'BANK',
	   'N': 'NON_BANK'
	 };

	 return function(input) {
	   if (!input) {
	     return '';
	   } else {
	     return genderHash[input];
	   }ok
	   
	 };
	});

app.directive('onReadFile', function ($parse) {
	return {
		restrict: 'A',
		scope: false,
		link: function(scope, element, attrs) {
            var fn = $parse(attrs.onReadFile);
            
			element.on('change', function(onChangeEvent) {
				var reader = new FileReader();
                
				reader.onload = function(onLoadEvent) {
					scope.$apply(function() {
						fn(scope, {$fileContent:onLoadEvent.target.result});
					});
				};

				reader.readAsText((onChangeEvent.srcElement || onChangeEvent.target).files[0]);
			});
		}
	};
});


/**************************************** Login Controller *********************************/

var LoginController = function($scope, $rootScope, $location, AuthenticationService) {
    
    // Reset the login status before we start
    AuthenticationService.ClearCredentials();

    $scope.login = function (username, password) {
        $scope.dataLoading = true;
        AuthenticationService.Login(username, password, function(response) {
            if(response.success) {
                AuthenticationService.SetCredentials(username, password);
                $location.path('/');
            } else {
                $scope.error = response.message;
                $scope.dataLoading = false;
            }
        });
    };    
};    

app.controller("LoginController", LoginController);
/***************************************  Login Controller ********************************/



/*******************************************Controller *****************************************/
app.controller('MainCtrl', ['$scope', '$http', '$interval', '$q', '$timeout', 'uiGridExporterService', 'uiGridExporterConstants', '$log', 'ajaxService', function($scope, $http, $interval, $q, $timeout, $uiGridExporterService, $uiGridExporterConstants, $log, $ajaxService) {

	
  /***************************************************************************************************************/
 	$scope.gridOptions1 = {};
	$scope.gridOptions2 = {};
	$scope.gridOptions3 = {};
	$scope.gridOptions4 = {};
	$scope.gridOptions5 = {};
	$scope.gridOptions6 = {};
	$scope.gridOptions7 = {};
	$scope.gridOptions8 = {};
	
	
	
	//$scope.data = [];
	$scope.data1 = [];
	  $scope.gridOptions1 = {
	    enableGridMenu: true,
	    importerDataAddCallback: function(grid1, newObjects) {
	      $scope.data1 = $scope.data1.concat(newObjects);
	    },
	    onRegisterApi: function(gridApi1) {
	      $scope.gridApi1 = gridApi1;
	      gridApi1.edit.on.afterCellEdit($scope, function(rowEntity, columnDefs1, newValue, oldValue) {
	          //Do your REST call here via $hhtp.get or $http.post
	          //This alert just shows which info about the edit is available
	          //alert('Column: ' + columnDefs3.I_KEY );
	        });
	      gridApi1.rowEdit.on.saveRow($scope, $scope.saveRow1);
	    },
	    data: 'data1'
	  };

	  $scope.saveRow1 = function(rowEntity) {
	    // create a fake promise - normally you'd use the promise returned by $http or $resource
	    var promise = $q.defer();
	    $scope.gridApi1.rowEdit.setSavePromise(rowEntity, promise.promise);

	   // console.log(" rowEntity.I_KEY "+rowEntity.I_KEY  + " === "+rowEntity.I_KEY.length);
	    // fake a delay of 3 seconds whilst the save occurs, return error if gender is "male"
	    $interval(function() {
		      if (rowEntity.Gender === 'male') {
		    	  alert( 'Ikey cannot be empty');
		        promise.reject();
		      } else {
		        promise.resolve();
		      }
		    }, 3000, 1);
	  };
	  //ikey | add1 | add2 | add3 | add4
	  
	  var columnDefs1 = [
	    { name: 'ProductInfo', displayName: 'ProductInfo', enableColumnMenu: false, enableCellEdit: false, validators: {notNull: true} },
	    { name: 'Category' ,displayName: 'Category',enableColumnMenu: false}
	  ];
	  
	  $scope.gridOptions1.columnDefs = columnDefs1;
	  
	  
	  
	  
	  $scope.data2 = [];
	  $scope.gridOptions2 = {
	    enableGridMenu: true,
	    importerDataAddCallback: function(grid2, newObjects) {
	      $scope.data2 = $scope.data2.concat(newObjects);
	    },
	    onRegisterApi: function(gridApi2) {
	      $scope.gridApi2 = gridApi2;
	      gridApi2.edit.on.afterCellEdit($scope, function(rowEntity, columnDefs2, newValue, oldValue) {
	          //Do your REST call here via $hhtp.get or $http.post
	          //This alert just shows which info about the edit is available
	          //alert('Column: ' + columnDefs3.I_KEY );
	        });
	      gridApi2.rowEdit.on.saveRow($scope, $scope.saveRow2);
	    },
	    data: 'data2'
	  };

	  $scope.saveRow2 = function(rowEntity) {
	    // create a fake promise - normally you'd use the promise returned by $http or $resource
	    var promise = $q.defer();
	    $scope.gridApi2.rowEdit.setSavePromise(rowEntity, promise.promise);

	   // console.log(" rowEntity.I_KEY "+rowEntity.I_KEY  + " === "+rowEntity.I_KEY.length);
	    // fake a delay of 3 seconds whilst the save occurs, return error if gender is "male"
	    $interval(function() {
		      if (rowEntity.Gender === 'male') {
		    	  alert( 'Ikey cannot be empty');
		        promise.reject();
		      } else {
		        promise.resolve();
		      }
		    }, 3000, 1);
	  };
	  //ikey | add1 | add2 | add3 | add4
	  
	  var columnDefs2 = [
	     { name: 'ProductInfo', displayName: 'ProductInfo', enableColumnMenu: false, enableCellEdit: false, validators: {notNull: true} },
	     { name: 'Category' ,displayName: 'Category',enableColumnMenu: false}
	  ];
	  
	  $scope.gridOptions2.columnDefs = columnDefs2;
	  
	  /******************************************************/
	  
	  
	  /*$scope.gridOptions2 = {
			    saveFocus: false,
			    saveScroll: true,
			    enableGridMenu: true,
			    onRegisterApi: function(gridApi) {
			      $scope.gridApi1 = gridApi;
			    }
			  };
	  
	  var columnDefs1 = [
	        		    { name: 'ProductInfo', displayName: 'ProductInfo', enableColumnMenu: false },
	        		    { name: 'Category' ,displayName: 'Category', enableColumnMenu: false },
	        		  ];
	  $scope.gridOptions1.columnDefs = columnDefs1;*/

	  /*$scope.export = function() {
	    var grid1 = $scope.gridApi.grid;
	    var rowTypes = uiGridExporterConstants.ALL
	    var collTypes = uiGridExporterConstants.ALL
	    uiGridExporterService.csvExport(grid1, rowTypes, colTypes)
	  };*/

	  /****************Guru*********************/
	  $scope.data3 = [];
	  $scope.gridOptions3 = {
	    enableGridMenu: true,
	    importerDataAddCallback: function(grid3, newObjects) {
	      $scope.data3 = $scope.data3.concat(newObjects);
	    },
	    onRegisterApi: function(gridApi3) {
	      $scope.gridApi3 = gridApi3;
	      gridApi3.edit.on.afterCellEdit($scope, function(rowEntity, columnDefs3, newValue, oldValue) {
	          //Do your REST call here via $hhtp.get or $http.post
	          //This alert just shows which info about the edit is available
	          //alert('Column: ' + columnDefs3.I_KEY );
	        });
	      gridApi3.rowEdit.on.saveRow($scope, $scope.saveRow3);
	    },
	    data: 'data3'
	  };

	  $scope.saveRow3 = function(rowEntity) {
	    // create a fake promise - normally you'd use the promise returned by $http or $resource
	    var promise = $q.defer();
	    $scope.gridApi3.rowEdit.setSavePromise(rowEntity, promise.promise);

	   // console.log(" rowEntity.I_KEY "+rowEntity.I_KEY  + " === "+rowEntity.I_KEY.length);
	    // fake a delay of 3 seconds whilst the save occurs, return error if gender is "male"
	    $interval(function() {
		      if (rowEntity.Gender === 'male') {
		    	  alert( 'Ikey cannot be empty');
		        promise.reject();
		      } else {
		        promise.resolve();
		      }
		    }, 3000, 1);
	  };
	  //ikey | add1 | add2 | add3 | add4
	  
	  var columnDefs3 = [
	    { name: 'I_KEY', displayName: 'I_KEY', enableColumnMenu: false, enableCellEdit: false, type: 'number', width: '10%', validators: {notNull: true} },
	    { name: 'ADDR_1' ,displayName: 'ADDR_1', enableColumnMenu: false },
	    { name: 'ADDR_2' ,displayName: 'ADDR_2', enableColumnMenu: false },
	    /*{ name: 'ADDR_3' ,displayName: 'ADDR_3', enableColumnMenu: false},
	    { name: 'ADDR_4' ,displayName: 'ADDR_4',enableColumnMenu: false}*/
	  ];
	  
	  $scope.gridOptions3.columnDefs = columnDefs3;
	  
	  
	  
	  /**************************Grid 4 ****************/
	  $scope.data4 = [];
	  $scope.gridOptions4 = {
       enableGridMenu: true,
       importerDataAddCallback: function(grid4, newObjects) {
 	      $scope.data4 = $scope.data4.concat(newObjects);
 	     angular.forEach($scope.data4,function(row){
 			
 			console.log("row "+row);
 			  row.getFullAddr = function(){
 			    return this.Addr_1 + ',' + this.Addr_2 + ',' + this.Addr_3 + ',' + this.Addr_4;
 			  }
 			});
 	    },
 	    onRegisterApi: function(gridApi4) {
 	      $scope.gridApi4 = gridApi4;
 	      
 	     gridApi4.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {

	          console.log('colDef.name : ' + colDef.name);
	          
	          
	          
	          if (colDef.name === 'ISO_COUNTRY_CD' && ( newValue !== oldValue || !(_isContains(ISOCtryCode, newValue)))) {
                 
                  if(_isContains(ISOCtryCode, newValue)){
    	        		//alert("country code exists");   
    	        		 $scope.$apply();
    	          }
    	          else{
    	        	 alert("Enter Valid ISO Country code");
    	        	 rowEntity.ISO_COUNTRY_CD = oldValue;
    	          }  
               }
	          
	          /*if (colDef.name === 'BANK_ID' && validation.isNotEmpty(newValue)) {
	        	  
	        	  console.log(' newValue.length '+newValue.length);
	        	  
	        	  if (validation.isNumber(newValue) && (newValue.length != 8 )){
	        		  
	        		  alert("Bank Id must be of 9 numbers");
	        	  }
	        	  
				if (!(validation.isNumber(newValue)) && (newValue.length != 5 )){
					        		  
					  alert("Bank Id must be of 6 characters");
					}
	        	  
                }*/
	          
					if (colDef.name === 'BANK_STATUS' && validation.isNotEmpty(newValue)) {
						        	  
						        	  console.log(' newValue '+newValue);
						        	  
						        	  if (newValue == 'B'){
						        		  
						        		  alert("Please enter valid Country code and Bank Id");
						        	  }
						        	  
									/*if (!(validation.isNumber(newValue)) && (newValue.length != 5 )){
										        		  
										  alert("Bank Id must be of 6 characters");
										}*/
						        	  
					                }
	         
 	        });
 	      
 	      gridApi4.rowEdit.on.saveRow($scope, $scope.saveRow4);
 	    },
 	    data: 'data4'
	  };
	  
	  
	  
	  var validation = {

			    isNotEmpty:function (str) {
			        var pattern =/\S+/;
			        return pattern.test(str);  // returns a boolean
			    },
			    isNumber:function(str) {
			        var pattern = /^\d+$/;  // /^-?(\d+\.?\d*)$|(\d*\.?\d+)$/;
			        return pattern.test(str);  // returns a boolean
			    },
			    isSame:function(str1,str2){
			        return str1 === str2;
			    }
			};  
	  
	  
	  $scope.saveRow4 = function(rowEntity) {
		    // create a fake promise - normally you'd use the promise returned by $http or $resource
		    var promise = $q.defer();
		    $scope.gridApi4.rowEdit.setSavePromise(rowEntity, promise.promise);
		    // fake a delay of 3 seconds whilst the save occurs, return error if gender is "male"
		    $interval(function() {
			      if (rowEntity.Gender === 'male') {
			    	  alert( 'Ikey cannot be empty');
			        promise.reject();
			      } else {
			        promise.resolve();
			      }
			    }, 3000, 1);
		  };
	  
	  
	//i_key,Addr_1,Addr_2,Addr_3,Addr_4,BANK_STATUS,ISO_COUNTRY_CD,BANK_ID
	  var columnDefs4Output = [
	      { name: 'i_key', displayName: 'i_key', enableColumnMenu: false, enableCellEdit: false, type: 'number',  validators: {notNull: true} },
	      { name: 'Addr_1', displayName: 'Addr_1',visible: false, enableColumnMenu: false},
	      { name: 'Addr_2', displayName: 'Addr_2', visible: false, enableColumnMenu: false},
	      { name: 'Addr_3', displayName: 'Addr_3',visible: false, enableColumnMenu: false},
	      { name: 'Addr_4', displayName: 'Addr_4',visible: false, enableColumnMenu: false},
	      { name: 'getFullAddr()',displayName: 'ADDRESS' ,enableColumnMenu: false},
	      
	      { name: 'BANK_STATUS',
		         displayName: 'BANK_STATUS',
		         editableCellTemplate: 'ui-grid/dropdownEditor',
		         //width: '20%',
		         cellFilter: 'mapBank_status',
		         editDropdownValueLabel: 'BANK_STATUS',
		         editDropdownOptionsArray: [{
		           id: 'B',
		           BANK_STATUS: 'BANK'
		         }, {
		           id: 'N',
		           BANK_STATUS: 'NON_BANK'
		         }]
		       },
	      
	      
	      { name: 'ISO_COUNTRY_CD' , displayName: 'ISO_COUNTRY_CD', enableColumnMenu: false,  textalign:'center', validators: {notNull: true}},
	      { name: 'BANK_ID',displayName: 'BANK_ID' ,enableColumnMenu: false, validators: {notNull: true}, cellTemplate: 'ui-grid/cellTitleValidator'},
	      /*{ name: 'RECORD_TYPE', displayName: 'RECORD_TYPE', enableColumnMenu: false, editableCellTemplate: 'ui-grid/dropdownEditor', width: '20%',
	          cellFilter: 'mapGender', editDropdownValueLabel: 'RECORD_TYPE', editDropdownOptionsArray: [
	          { id: 1, RECORD_TYPE: 'YES' },
	          { id: 2, RECORD_TYPE: 'NO' }
	        ] },*/
	    ];
	    $scope.gridOptions4.columnDefs = columnDefs4Output;
	    
	    //add1 | add2 | add3 | add4 

	    
	    function _isContains(json, value) {
	    	let contains = false;
	      Object.keys(json).some(key => {
	    		contains = typeof json[key] === 'object' ? _isContains(json[key], value) : json[key] === value;
	    	  return contains;
	    	});
	      return contains;
	     }

	   // var ISOCtryCode = {"animals": [{name:"SG"}, {name:"AG"}]};
	    

	    
	    
	  
 /*********************Grid 4********************/	  
	    
	    
	    /*********************************Grid 7*******************************/
		  $scope.data7 = [];
		  $scope.gridOptions7 = {
		    enableGridMenu: true,
		    importerDataAddCallback: function(grid7, newObjects) {
		    	console.log(" grid7 "+ grid7.data);
		    	console.log(" newObjects "+ JSON.stringify(newObjects));
		      $scope.data7 = $scope.data7.concat(newObjects);
		    },
		    onRegisterApi: function(gridApi7) {
		      $scope.gridApi7 = gridApi7;
		      gridApi7.edit.on.afterCellEdit($scope, function(rowEntity, columnDefs7, newValue, oldValue) {
		          //Do your REST call here via $hhtp.get or $http.post
		          //This alert just shows which info about the edit is available
		          //alert('Column: ' + columnDefs3.I_KEY );
		        });
		      gridApi7.rowEdit.on.saveRow($scope, $scope.saveRow7);
		    },
		    data: 'data7'
		  };

		  $scope.saveRow7 = function(rowEntity) {
		    // create a fake promise - normally you'd use the promise returned by $http or $resource
		    var promise = $q.defer();
		    $scope.gridApi7.rowEdit.setSavePromise(rowEntity, promise.promise);

		   // console.log(" rowEntity.I_KEY "+rowEntity.I_KEY  + " === "+rowEntity.I_KEY.length);
		    // fake a delay of 3 seconds whilst the save occurs, return error if gender is "male"
		    $interval(function() {
			      if (rowEntity.Gender === 'male') {
			    	  alert( 'Ikey cannot be empty');
			        promise.reject();
			      } else {
			        promise.resolve();
			      }
			    }, 3000, 1);
		  };
		  //ID|MERCHANT_NAME|STREET_NAME|CITY|STATE|ZIP|COUNTRY|BUSINESS_CATEGORY
		  
		  var columnDefs7 = [
		    { name: 'ID', displayName: 'ID', enableColumnMenu: false, enableCellEdit: false, type: 'number', width: '10%', validators: {notNull: true} },
		    { name: 'MERCHANT_NAME' ,displayName: 'MERCHANT_NAME', enableColumnMenu: false },
		    { name: 'STREET_NAME' ,displayName: 'STREET_NAME', enableColumnMenu: false },
		    { name: 'CITY' ,displayName: 'CITY', enableColumnMenu: false},
		    { name: 'STATE' ,displayName: 'STATE',enableColumnMenu: false},
		    { name: 'ZIP' ,displayName: 'ZIP', enableColumnMenu: false },
		    { name: 'COUNTRY' ,displayName: 'COUNTRY', enableColumnMenu: false},
		    { name: 'BUSINESS_CATEGORY' ,displayName: 'BUSINESS_CATEGORY',enableColumnMenu: false}
		  ];
		  
		  $scope.gridOptions7.columnDefs = columnDefs7;
		  
		  
		  $scope.uploadGrid7 = function($fileContent){
		       $scope.content = $fileContent;
		       var data = $scope.content;
		       console.log('$scope.content '+data);
		       jsonData = csv2JSON(data , "|")
		   	 $scope.data7 = jsonData;
		   	 $scope.gridApi7.core.refresh();
		   };
		  
		  /*********************************Grid 7*******************************/	    
		  
		  
		  
		  /*********************************Grid 8*******************************/
		  $scope.data8 = [];
		  $scope.gridOptions8 = {
		    enableGridMenu: true,
		    importerDataAddCallback: function(grid8, newObjects) {
		      $scope.data8 = $scope.data7.concat(newObjects);
		    },
		    onRegisterApi: function(gridApi8) {
		      $scope.gridApi8 = gridApi8;
		      gridApi8.edit.on.afterCellEdit($scope, function(rowEntity, columnDefs8, newValue, oldValue) {
		          //Do your REST call here via $hhtp.get or $http.post
		          //This alert just shows which info about the edit is available
		          //alert('Column: ' + columnDefs3.I_KEY );
		        });
		      gridApi8.rowEdit.on.saveRow($scope, $scope.saveRow8);
		    },
		    data: 'data8'
		  };

		  $scope.saveRow8 = function(rowEntity) {
		    // create a fake promise - normally you'd use the promise returned by $http or $resource
		    var promise = $q.defer();
		    $scope.gridApi8.rowEdit.setSavePromise(rowEntity, promise.promise);

		   // console.log(" rowEntity.I_KEY "+rowEntity.I_KEY  + " === "+rowEntity.I_KEY.length);
		    // fake a delay of 3 seconds whilst the save occurs, return error if gender is "male"
		    $interval(function() {
			      if (rowEntity.Gender === 'male') {
			    	  alert( 'Ikey cannot be empty');
			        promise.reject();
			      } else {
			        promise.resolve();
			      }
			    }, 3000, 1);
		  };

		  //SYSIX_MERCHANT_NAME|SYSIX_STREET|SYSIX_CITY|SYSIX_STATE|SYSIX_ZIP|SYSIX_CTRY|SYS_SYNTHETIC_MERCHANT_ID|SYSIX_HIT_IND|INPUT_STATUS
		  //|GOOGLE_STATUS|PROCESS_STATUS|ADDRESS_STATUS|SYSIX_ID
		  
		  var columnDefs8 = [
		    { name: 'SYSIX_MERCHANT_NAME', displayName: 'SYSIX_MERCHANT_NAME', enableColumnMenu: false, enableCellEdit: false, type: 'number', width: '10%', validators: {notNull: true} },
		    { name: 'SYSIX_STREET' ,displayName: 'SYSIX_STREET', enableColumnMenu: false },
		    { name: 'SYSIX_CITY' ,displayName: 'SYSIX_CITY', enableColumnMenu: false },
		    { name: 'SYSIX_STATE' ,displayName: 'SYSIX_STATE', enableColumnMenu: false},
		    { name: 'SYSIX_ZIP' ,displayName: 'SYSIX_ZIP',enableColumnMenu: false},
		    { name: 'SYSIX_CTRY' ,displayName: 'SYSIX_CTRY', enableColumnMenu: false },
		    { name: 'SYS_SYNTHETIC_MERCHANT_ID' ,displayName: 'SYS_SYNTHETIC_MERCHANT_ID', enableColumnMenu: false},
		    { name: 'SYSIX_HIT_IND' ,displayName: 'SYSIX_HIT_IND',enableColumnMenu: false},
		    { name: 'INPUT_STATUS' ,displayName: 'INPUT_STATUS',enableColumnMenu: false},
		    { name: 'GOOGLE_STATUS' ,displayName: 'GOOGLE_STATUS', enableColumnMenu: false },
		    { name: 'PROCESS_STATUS' ,displayName: 'PROCESS_STATUS', enableColumnMenu: false},
		    { name: 'ADDRESS_STATUS' ,displayName: 'ADDRESS_STATUS',enableColumnMenu: false},
		    { name: 'SYSIX_ID' ,displayName: 'SYSIX_ID',enableColumnMenu: false}
		  ];
		  
		  $scope.gridOptions8.columnDefs = columnDefs8;
		  
		  $scope.uploadGrid8 = function($fileContent){
		       $scope.content = $fileContent;
		       var data = $scope.content;
		       console.log('$scope.content '+data);
		       jsonData = csv2JSON(data , "|")
		   	 $scope.data8 = jsonData;
		   	 $scope.gridApi8.core.refresh();
		   };
		  
		  /*********************************Grid 7*******************************/
	  	  
  
/******************Add , Delete , Reset For all Grids********************************/
	//Add Grid 
	  $scope.addDataGrid = function() {
			var n = $scope.data.length + 1;
			$scope.data.push({
			"I_KEY" : "",
			"ADDR_1" : "",
			"ADDR_2" : "",
			"ADDR_3" : "",
			"ADDR_4" : ""
				});
			};
			
	$scope.deleteSelectedGrid = function() {
				angular.forEach($scope.gridApi.selection
						.getSelectedRows(), function(data,
						index) {
					$scope.data.splice($scope.data.lastIndexOf(data), 1);	});
			}
			
			$scope.clearGrid = function() {
				$scope.data.length = 0;
				$scope.gridOptions = {}
			}
			
			//Add Grid1
			$scope.addDataGrid1 = function() {
				var n = $scope.data1.length + 1;
				$scope.data1.push({
				"I_KEY" : "",
				"ADDR_1" : "",
				"ADDR_2" : "",
				"ADDR_3" : "",
				"ADDR_4" : ""
					});
				};
				
		$scope.deleteSelectedGrid1 = function() {
					angular.forEach($scope.gridApi1.selection
							.getSelectedRows(), function(data,
							index) {
						$scope.data1.splice($scope.data1.lastIndexOf(data), 1);	});
				}
				
				
				
		
				//Add Grid3
				$scope.addDataGrid3 = function() {
					var n = $scope.data3.length + 1;
					$scope.data3.push({
					"I_KEY" : "",
					"ADDR_1" : "",
					"ADDR_2" : "",
					"ADDR_3" : "",
					"ADDR_4" : ""
						});
					};
					
			$scope.deleteSelectedGrid3 = function() {
						angular.forEach($scope.gridApi3.selection
								.getSelectedRows(), function(data,
								index) {
							$scope.data3.splice($scope.data3.lastIndexOf(data), 1);	});
					}
					
					$scope.clearGrid3 = function() {
						$scope.data3.length = 0;
					}
				
				
		//Grid4	
		// i_key,Addr_1,Addr_2,Addr_3,Addr_4,BANK_STATUS,ISO_COUNTRY_CD,BANK_ID
		$scope.addDataGrid4 = function() {
		var n = $scope.data4.length + 1;
		$scope.data4.push({
		"i_key" : "",
		"Addr_1" : "",
		"Addr_2" : "",
		"Addr_3" : "",
		"Addr_4" : "",
		"BANK_STATUS" : "",
		"ISO_COUNTRY_CD" : "",
		"BANK_ID" : ""
			});
		};
		
		$scope.deleteSelectedGrid4 = function() {
		angular.forEach($scope.gridApi4.selection.getSelectedRows(), function(data,index) {
				$scope.data4.splice($scope.data4.lastIndexOf(data), 1);	
			});
		}
		
		$scope.clearGrid4 = function() {
			$scope.data4.length = 0;
		}
		
		$scope.clearGrid1 = function() {
			$scope.data1.length = 0;
		}
		
		$scope.clearGrid2 = function() {
			$scope.data2.length = 0;
		}
		
/******************Add , Delete , Reset For all Grids ********************************/
		
		$scope.runLoading = false ;
		
		$scope.counter = 0;
		$scope.max = 0;
		$scope.msg='';
		$scope.pBarLoading3 = true;
		$scope.pBarLoading4 = true;
		
		
		$scope.trainCatClassifier = function(){
	    	console.log( ' in run classifier ');
	    	$scope.runLoading = true;
	    	
    		   var selectedData =$scope.gridApi1.selection.getSelectedRows();
    		   console.log( selectedData.length);
    		   
    		   if ($scope.gridApi1.selection.getSelectedRows().length > 0){
    			   //$scope.runClasHide = false;
    			   $scope.pBarLoading3 = false;
    			   
    			var header = 'ProductInfo,Category\r\n';
   			   	var csv = convertToCSV(angular.toJson(selectedData) , header);
   		   	
   			   	var totalRec = $scope.gridApi1.selection.getSelectedRows().length;
   			   	
   			   	console.log(" $scope.gridApi.selection.getSelectedRows().length "+$scope.gridApi1.selection.getSelectedRows().length);
   			   	increment(totalRec);
		   	
			   	//loadProgressBar(totalRec);
   			   //	$scope.counter = "Clasification Started...";
   				 var res = $http.post('http://localhost:8081/SMX_MLDashboard/rest/trainCatClassifier', csv);
   			   	res.success(function(data, status, headers, config) {
   			   		console.log(' createRunClasFile data  '+data);
   			   		$scope.counter = "Clasification File Created...";
   						
   				});
   				res.error(function(data, status, headers, config) {
   					console.log( "failure message: " + JSON.stringify({data: data}));
   				});

    		   }
    		   else{    			   
    			   alert("select atleast 1 record to run the classifier");
    		   }
	    }
		
		
		$scope.testCatClassifier = function(){
	    	console.log( ' in testCatClassifier classifier ');
	    	$scope.runLoading = true;
	    	
    		   var selectedData =$scope.gridApi1.selection.getSelectedRows();
    		   console.log( selectedData.length);
    		   
    		   if ($scope.gridApi1.selection.getSelectedRows().length > 0){
    			   //$scope.runClasHide = false;
    			   $scope.pBarLoading3 = false;
    			   
    			var header = 'ProductInfo,Category\r\n';
   			   	var csv = convertToCSV(angular.toJson(selectedData) , header);
   		   	
   			   	var totalRec = $scope.gridApi1.selection.getSelectedRows().length;
   			   	
   			   	console.log(" $scope.gridApi1.selection.getSelectedRows().length "+$scope.gridApi1.selection.getSelectedRows().length);
   			   	increment(totalRec);
		   	
			   	//loadProgressBar(totalRec);
   			   //	$scope.counter = "Clasification Started...";
   				 var res = $http.post('http://localhost:8081/SMX_MLDashboard/rest/testCatClassifier', csv);
   			   	res.success(function(data, status, headers, config) {
   			   		console.log(' testCatClassifier data  '+data);
   			   		$scope.counter = "Clasification File Created...";
   					var res = $http.get('http://localhost:8081/SMX_MLDashboard/rest/downloadCatClasCsv');
   					res.success(function(data, status, headers, config) {
   						console.log(" classfication Done!!!!   "+data);
   						$scope.dynamic = 100;
   						$scope.percentage = '%';
   			            $scope.msg='Table Loading .......';
   			            $scope.counter = '';
   			    	    $scope.max = '';
   			    	    $scope.pBarLoading3 = true;
   						
 
   					        var csvString = data;
   					        var data  = csv2JSON(csvString);
   							       /* angular.forEach(data, function(row){
   							        console.log(' row === '+JSON.stringify(row));
   							        row.getFullAddr = function() {
   						            return this.Addr_1 + ',' + this.Addr_2 +','+this.Addr_3 + ',' + this.Addr_4;
   						          }
   						        });*/
   				         	$scope.data2 = data;
   				         	$scope.Loading = false;
   				         $scope.runClasHide = true;
   						
   					});
   					res.error(function(data, status, headers, config) {
   						console.log( "failure message: " + JSON.stringify({data: data}));
   					});	
   				});
   				res.error(function(data, status, headers, config) {
   					console.log( "failure message: " + JSON.stringify({data: data}));
   				});//console.log(" $scope.gridApi1.selection.getSelectedRows().length "+$scope.gridApi1.selection.getSelectedRows().length);

    		   }
    		   else{
    			   
    			   alert("select atleast 1 record to run the classifier");
    		   }
    		   $scope.runClasHide = true;
	    }
		
		
		
	    $scope.runClassifier = function(){
	    	console.log( ' in run classifier ');
	    	$scope.runLoading = true;
	    	
    		   var selectedData =$scope.gridApi3.selection.getSelectedRows();
    		   console.log( selectedData.length);
    		   
    		   if ($scope.gridApi3.selection.getSelectedRows().length > 0){
    			   //$scope.runClasHide = false;
    			   $scope.pBarLoading3 = false;
    			   
    			var header = 'i_key,Addr_1,Addr_2,Addr_3,Addr_4\r\n';
   			   	var csv = convertToCSV(angular.toJson(selectedData) , header);
   		   	
   			   	var totalRec = $scope.gridApi3.selection.getSelectedRows().length;
   			   	
   			   	console.log(" $scope.gridApi3.selection.getSelectedRows().length "+$scope.gridApi3.selection.getSelectedRows().length);
   			   	increment(totalRec);
		   	
			   	//loadProgressBar(totalRec);
   			   //	$scope.counter = "Clasification Started...";
   				 var res = $http.post('http://localhost:8081/SMX_MLDashboard/rest/createRunClasFile', csv);
   			   	res.success(function(data, status, headers, config) {
   			   		console.log(' createRunClasFile data  '+data);
   			   		$scope.counter = "Clasification File Created...";
   					var res = $http.get('http://localhost:8081/SMX_MLDashboard/rest/downloadWireMDMCsv');
   					res.success(function(data, status, headers, config) {
   						console.log(" classfication Done!!!!   "+data);
   						$scope.dynamic = 100;
   						$scope.percentage = '%';
   			            $scope.msg='Table Loading .......';
   			            $scope.counter = '';
   			    	    $scope.max = '';
   			    	    $scope.pBarLoading3 = true;
   						
 
   					        var csvString = data;
   					        var data  = csv2JSON(csvString)
   							        angular.forEach(data, function(row){
   							        console.log(' row === '+JSON.stringify(row));
   							        row.getFullAddr = function() {
   						            return this.Addr_1 + ',' + this.Addr_2 +','+this.Addr_3 + ',' + this.Addr_4;
   						          }
   						        });
   				         	$scope.data4 = data;
   				         	$scope.Loading = false;
   				         $scope.runClasHide = true;
   						
   					});
   					res.error(function(data, status, headers, config) {
   						console.log( "failure message: " + JSON.stringify({data: data}));
   					});	
   				});
   				res.error(function(data, status, headers, config) {
   					console.log( "failure message: " + JSON.stringify({data: data}));
   				});console.log(" $scope.gridApi3.selection.getSelectedRows().length "+$scope.gridApi3.selection.getSelectedRows().length);

    		   }
    		   else{
    			   
    			   alert("select atleast 1 record to run the classifier");
    		   }
    		   $scope.runClasHide = true;
	    }
	    
	    $scope.updateLoading = false ;
	    
	    
	    $scope.updateClassifier = function(){
	    	
	    	var selectedData =$scope.gridApi4.selection.getSelectedRows();
 		   console.log( selectedData.length);
 		   
 		   if ($scope.gridApi4.selection.getSelectedRows().length > 0){
 			  var totalRec = $scope.gridApi4.selection.getSelectedRows().length;
 			  $scope.updateLoading = true;
 			 $scope.pBarLoading4 = false;
 			 increment(totalRec);
 			   
 			   console.log(" angular.toJson(fieldData) selectedData "+ angular.toJson(selectedData));
 			   var header = 'i_key,Addr_1,Addr_2,Addr_3,Addr_4,BANK_STATUS,ISO_COUNTRY_CD,BANK_ID\r\n';
			   	var csv = convertToCSV(angular.toJson(selectedData) , header);
			   	console.log("json 2 csv converter ====  "+csv)
			   	
			   	var totalRec = $scope.gridApi4.selection.getSelectedRows().length;
		   	
			   	//loadProgressBar(totalRec);
			   	//$scope.counter = "Clasification Started...";
				 var res = $http.post('http://localhost:8081/SMX_MLDashboard/rest/updateBankClassifier1', csv);
			   	res.success(function(data, status, headers, config) {	
			   		alert(" Bank Classifier Updated Successfully");
			   		console.log(' Update Clas File data  '+data);
			   			$scope.dynamic = 100;
						$scope.percentage = '%';
			            $scope.msg='Table Loading .......';
			            $scope.counter = '';
			    	    $scope.max = '';
			    	    $scope.pBarLoading4 = true;
			   		
			   		$scope.updateLoading = false;
				});
				res.error(function(data, status, headers, config) {
					console.log( "failure message: " + JSON.stringify({data: data}));
					$scope.updateLoading = false;
				});
				
 		   }
 		   else{
 			   
 			   alert("select atleast 1 record to update the classifier");
 		   }
 		   
 		  scope.updateLoading = false;
	    }

		   increment = function(totalRec){
			   console.log( ' increment  started');
			   console.log(" totalRec "+totalRec);
		       var types = ['info']
		       
		       if($scope.counter < totalRec ){
		       	$scope.counter++;
			       $scope.dynamic = $scope.counter / totalRec * 100;
			       $scope.msg= $scope.dynamic;
			       $scope.percentage = '%';
			       $scope.type = types[$scope.counter % 4];
		           $timeout(increment, 1000);
	            }
		       
		       /*if($scope.counter == $scope.max ){
		       	$scope.percentage = '';
		            $scope.msg='Classification complete ......';
		            $timeout(increment, 2000);
		            $scope.msg='Table Loading .......';
		            $scope.counter = '';
		    	    $scope.max = '';
		    	   $scope.pBarLoading = true;
					$scope.Loading = false;	    	    
	           }*/
		   }
	    
	    $scope.Move2Update = function() {
			angular.forEach($scope.gridApi4.selection.getSelectedRows(), function(data,index) {
				console.log("data "+data);
				$scope.data5.push(data)
			});
		};
		
		
		$scope.addRow2Grid = function () {
	    	var csvData = $scope.ocrawlInTextarea;
	    	var jsonData = csv2JSON(csvData)
	    	angular.forEach(jsonData, function(jsonData,index) {
				console.log("data in"+jsonData);
				console.log(" json Stringfy Data in" +JSON.stringify(jsonData));
				$scope.data.push(jsonData)
			});
	    	
	    };
		
		
		$scope.addRow2Grid1 = function () {
	    	var csvData = $scope.ocrawlOutTextarea;
	    	var jsonData = csv2JSON(csvData)
	    	angular.forEach(jsonData, function(jsonData,index) {
				console.log("data in"+jsonData);
				console.log(" json Stringfy Data in" +JSON.stringify(jsonData));
				$scope.data1.push(jsonData)
			});
	    	
	    };
	    
	    $scope.addRow2Grid3 = function () {
	    	var csvData = $scope.wmdmTextarea;
	    	var jsonData = csv2JSON(csvData)
	    	angular.forEach(jsonData, function(jsonData,index) {
				console.log("data in"+jsonData);
				console.log(" json Stringfy Data in" +JSON.stringify(jsonData));
				$scope.data3.push(jsonData)
			});
	    	
	    };
	    
	    $scope.addRow2Grid4 = function () {
	    	var csvData = $scope.wmdmUpdateTextarea;
	    	var data = csv2JSON(csvData)

	        angular.forEach(data, function(row){
	        var jsonRow = JSON.stringify(row);
	        console.log(' jsonRow === '+row);
	        row.getFullAddr = function() {
	        	console.log("Address" + row.Addr_1 + ',' + this.Addr_2 +','+this.Addr_3 + ',' + this.Addr_4);
            return this.Addr_1 + ',' + this.Addr_2 +','+this.Addr_3 + ',' + this.Addr_4;
          }
	        $scope.data4 = data;
        });
	    };
	    
	    $scope.addRow2Grid3 = function () {
	    	var csvData = $scope.wmdmTextarea;
	    	var jsonData = csv2JSON(csvData)
	    	angular.forEach(jsonData, function(jsonData,index) {
				console.log("data in"+jsonData);
				console.log(" json Stringfy Data in" +JSON.stringify(jsonData));
				$scope.data3.push(jsonData)
			});
	    	
	    };

  /*********************************************************************************************************/
	    
	function convertToCSV(objArray , header) {
	       var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
	       var str = header;
	       for (var i = 0; i < array.length; i++) {
	       	
	       	
	           var line = '';
	           for (var index in array[i]) {
	               if (line != '') line += ','
	               	console.log("array[i][index] "+array[i][index]);
	               line += array[i][index];
	           }
	           str += line + '\r\n';
	       }
	       return str;
	   }


	    
/*loadProgressBar = function(maxCount){ 
	 // $scope.max = 10;
	   	$scope.max = maxCount;
	   	
	   	console.log( " $scope.max "+$scope.max); 
	   	 $scope.counter = 0;
	   	 $scope.limit= "/";
	   	 $scope.Loading = true;

	   	 var promise1 = $timeout(3000);
	   	 
	   	 var promise3 = $timeout(30000);
	   	 
	   	 promise1.then(function() {
	   	 var mytimeout = $timeout($scope.onTimeout,1000);
	   	    promise3.then(function(){
	   	    	$scope.Loading = false;
	   	    	$scope.stop;
	   	 });
	   	 });
	  } 


 $scope.onTimeout = function(){
	       if($scope.counter < $scope.max)
	       {
	           $scope.runLoading = true;
	           //initialtimeout = $timeout($scope.onTimeout,100000);
	           mytimeout = $timeout($scope.onTimeout,1000);
	           $scope.counter++;
	       }
	       var promise2 = $timeout(3000);

	       if($scope.counter == $scope.max){
	        // $scope.counter = "Clasification Complete...";
	        $scope.max = "";
	        $scope.limit  = "";
	         promise2.then(function(){
	         $scope.counter = "Table loading ......";
	   });
	       }
	   }
	   
	   $scope.stop = function(){
	       $timeout.cancel(mytimeout);
	    }*/
	    
	    
 /***********************************************************************************************************/
	
	
  $scope.heartbeat = {};
  $scope.heartbeat.count = 0;
  $scope.heartbeat.error = false;
  
  $scope.dashboard = {};
  $scope.dashboard.today_heading = "Tweet Impressions"
  $scope.dashboard.today_count = 0;
  $scope.dashboard.today_avg = 0;
  $scope.dashboard.today_avg_subheading = "Daily Average tweets";
  $scope.dashboard.onboarded_heading = "Clicks";
  $scope.dashboard.onboarded_count = 0;
  $scope.dashboard.onboarded_subheading = "Work in Progress";
  $scope.dashboard.signups_heading = "Retweets";
  $scope.dashboard.signups_count = 0;
  $scope.dashboard.signups_subheading = "Work in Progress";
  
  $scope.tabIndex = {};
  $scope.tabIndex.current = 1;
  $scope.tabIndex.CPU = 1;
  $scope.tabIndex.MEMORY = 2;
  $scope.tabIndex.DISK = 3;
  $scope.tabIndex.IO = 4;
  $scope.tabIndex.OS = 5;
  
  $scope.operating_system = {};
  $scope.java_jvm = {};
  $scope.network_details = {};
  $scope.disk_details ={};
  $scope.disk_drive = {};
  
  $scope.network_interface = {};
  $scope.network_interface_stats = {};
  
  $scope.disk_details.customSectors = [
                          {
                              color: "#18FF0D",
                              lo: 0,
                              hi: 50
                          },
                          {
                              color: "#FF9B0C",
                              lo: 51,
                              hi: 75
                          },
                          {
                              color: "#FF320A",
                              lo: 76,
                              hi: 100
                          }
                      ];
  
  $scope.disk_details.options = {
      chart: {
          type: 'pieChart',
          width: 300,
          height: 300,
          x: function(d){return d.key;},
          y: function(d){return d.y;},
          showLabels: true,
          transitionDuration: 500,
          labelThreshold: 0.01,
          legend: {
              margin: {
                  top: 5,
                  right: 35,
                  bottom: 5,
                  left: 0
              }
          },
          yAxis: {
              tickFormat: function(d){
                  return d3.format(',.0f')(d);
              }
          }
      }
  };
  
  $scope.cpu = {};
  $scope.cpu.system = 100;
  $scope.cpu.user = 100;
  $scope.cpu.idle = 100;
  $scope.cpu.nice = 100;
  $scope.cpu.wait = 100;  

  $scope.procs = {};
  $scope.procs.historical = {};
  $scope.procs.data = [];
  $scope.procs.backup = [];
  $scope.procs.data.actual = [];
  
  $scope.memory = {};
  $scope.memory.historical = {};
  
  $scope.io = {};
  
  $scope.memory.precision= 'kilobytes';
  $scope.memory.scale = 1024;
  $scope.memory.scale_precision = "Kb";
  
  $scope.memory.stack = {};
  $scope.memory.stack.backup = [];
  $scope.memory.stack.actual = [];
  $scope.memory.stack.data = [];
  
  $scope.procs.options = {
          chart: {
              type: 'lineChart',
              height: 400,
              margin : {
                  top: 20,
                  right: 20,
                  bottom: 60,
                  left: 40
              },
              x: function(d){return d[0];},
              y: function(d){return d[1];},
              useVoronoi: false,
              clipEdge: true,
              transitionDuration: 0,
              useInteractiveGuideline: true,
              xAxis: {
                  showMaxMin: false,
                  tickFormat: function(d) {
                      return d3.time.format('%H:%M:%S')(new Date(d))
                  }
              },
              yAxis: {
                  tickFormat: function(d){
                      return d3.format(',.0f')(d);
                  }
              }
          }
      };
  
  $scope.labels = [];
  $scope.data = [];  
  $scope.series = ['Memory (total)', 'Memory (used)', 'Memory (free)'];
  $scope.colors = ['#79B8E8', '#F58F9B', '#ECD30C'];
  
  $scope.setTabIndex = function(idx) {
	  $scope.tabIndex.current = idx;
	  console.log("Inside setTabIndex..: " + $scope.tabIndex.current);
	  
	  if($scope.tabIndex.current == "1"){
		  
		  $timeout(function() {

		      $scope.gridApi1.core.handleWindowResize();
		      $scope.gridApi2.core.handleWindowResize();
		    }, 0);
	  }
	  
	  if($scope.tabIndex.current == "2"){

		    $timeout(function() {

		        $scope.gridApi3.core.handleWindowResize();
		        $scope.gridApi4.core.handleWindowResize();
		      }, 0);
		  
	  }
  }
  
  $scope.animateHeartbeat = function() {
	  $scope.heartbeats = $scope.heartbeats + 1;
  };
  
  $scope.startSpin = function(key) {
  	usSpinnerService.spin(key);
  };
  
  $scope.stopSpin = function(key) {
  	usSpinnerService.stop(key);
  };
 
  $scope.setTitleCase = function(input) {
  	if (input != null ) {
  		return input.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
  	}
  };

  $scope.setLowerCase = function(input) {
  	if (input != null ) {
 		return input.replace(/\w\S*/g, function(txt){return txt.toLowerCase();});
  	}
  };

  $scope.setUpperCase = function(input) {
  	if (input != null ) {
 		return input.replace(/\w\S*/g, function(txt){return txt.toUpperCase();});
  	}
  };
  
  $scope.split = function(input, index) {
	if (input != null ) {  
		var array = input.split(':'); 
		return array[index];
	}
  };

  $scope.SplitCommas = function(input) {
  if (input != null ) {  
	  var str = input.replace(/,/g, '<br>');
	  return str;
	} 
  };
  
  $scope.currentTimeMillis = function(ts) {
	var date = new Date().getTime();
	return date;
  };


  

  


  $scope.processOSDetails = function () {
    console.log("Inside processOSDetails " + $scope.osDetailsUrl);

	function onSuccess(response) {
		console.log("+++++processOSDetails SUCCESS++++++");
		if ($scope.debugFlag == 'true') {
			//console.log("Inside processOSDetails response..." + JSON.stringify(response.data));
		} else {
			//console.log("Inside processOSDetails response...(XML response is being skipped, debug=false)");
		}
		$scope.operating_system.os_description = response.data.os_description;
		$scope.operating_system.os_architecture = response.data.os_architecture;
		$scope.operating_system.os_patch_level = response.data.os_patch_level;
		$scope.operating_system.os_vendor = response.data.os_vendor;
		$scope.operating_system.os_name = response.data.os_name;
		$scope.operating_system.os_version = response.data.os_version;
		$scope.operating_system.os_code_name = response.data.os_code_name;
		$scope.operating_system.os_data_model = response.data.os_data_model;
		
		$scope.java_jvm.jvm_vendor = response.data.jvm_vendor;
		$scope.java_jvm.jvm_version = response.data.jvm_version;
		$scope.java_jvm.jvm_home = response.data.jvm_home;
	};
		
	function onError(response) {
		//console.log("Inside processOSDetails error condition...");
	};  
	
	//----MAKE AJAX REQUEST CALL to GET DATA----
	ajaxService.getData($scope.osDetailsUrl, 'GET', '').then(onSuccess, onError);  
  };
  
 

  $scope.processStatistics = function () {
    console.log("Inside processStatistics " + $scope.statisticsUrl);

	function onSuccess(response) {
		console.log("+++++processStatistics SUCCESS++++++");
		if ($scope.debugFlag == 'true') {
			//console.log("Inside processStatistics response..." + JSON.stringify(response.data));
		} else {
			//console.log("Inside processStatistics response...(XML response is being skipped, debug=false)");
		}
		if (response.data) {
			$scope.dashboard.today_heading = response.data.today_heading;
			$scope.dashboard.today_count = response.data.today_count;
			$scope.dashboard.today_avg = response.data.today_avg;
			$scope.dashboard.today_avg_subheading = response.data.today_avg_subheading;
			$scope.dashboard.onboarded_heading = response.data.onboarded_heading;
			$scope.dashboard.onboarded_count = response.data.onboarded_count;
			$scope.dashboard.onboarded_subheading = response.data.onboarded_subheading;
			$scope.dashboard.signups_heading = response.data.signups_heading;
			$scope.dashboard.signups_count = response.data.signups_count;
			$scope.dashboard.signups_subheading = response.data.signups_subheading;
		}
	};
		
	function onError(response) {
		console.log("Inside processStatistics error condition...");
	};  
	
	//----MAKE AJAX REQUEST CALL to GET DATA----
	ajaxService.getData($scope.statisticsUrl, 'GET', '').then(onSuccess, onError);  
  };
	  
  
  
  $scope.setDefaults = function(debugFlag, baseUrl) {
	  
	  console.log(" baseUrl  "+baseUrl);
	  $scope.osDetailsUrl 					= baseUrl + "/MyDashboard/rest/osdetails";
	  $scope.statisticsUrl 					= baseUrl + "/MyDashboard/rest/statistics";
	  
	  $scope.wordcountUrl 					= baseUrl + "/MyDashboard/rest/wordcount";
	  $scope.debugFlag 						= debugFlag;
	  
	  console.log("Setting Defaults");
	  console.log("statisticsUrl.................: " + $scope.statisticsUrl);
	  console.log("osDetailsUrl..................: " + $scope.osDetailsUrl);
	  
	 // $interval($scope.processStatistics, 5000);
	  $scope.processOSDetails();
  };
  
  
  /*************************Utility functions**************/
 
  function csv2JSON(csv) {
	  
	  
	  console.log(" csv "+csv);
	  
	    var array = CSVToArray(csv);
	    
	    console.log(" array "+array);
	    var objArray = [];
	    for (var i = 1; i < array.length; i++) {
	        objArray[i - 1] = {};
	        for (var k = 0; k < array[0].length && k < array[i].length; k++) {
	            var key = array[0][k];
	            objArray[i - 1][key] = array[i][k]
	        }
	    }

	    //var json = JSON.stringify(objArray);
	    //var str = json.replace(/},/g, "},\r\n");

	    return objArray;
	}
  
  
  function CSVToArray(strData, strDelimiter) {
	    // Check to see if the delimiter is defined. If not,
	    // then default to comma.
	    strDelimiter = (strDelimiter || ",");
	    // Create a regular expression to parse the CSV values.
	    var objPattern = new RegExp((
	    // Delimiters.
	    "(\\" + strDelimiter + "|\\r?\\n|\\r|^)" +
	    // Quoted fields.
	    "(?:\"([^\"]*(?:\"\"[^\"]*)*)\"|" +
	    // Standard fields.
	    "([^\"\\" + strDelimiter + "\\r\\n]*))"), "gi");
	    // Create an array to hold our data. Give the array
	    // a default empty first row.
	    var arrData = [[]];
	    // Create an array to hold our individual pattern
	    // matching groups.
	    var arrMatches = null;
	    // Keep looping over the regular expression matches
	    // until we can no longer find a match.
	    while (arrMatches = objPattern.exec(strData)) {
	        // Get the delimiter that was found.
	        var strMatchedDelimiter = arrMatches[1];
	        // Check to see if the given delimiter has a length
	        // (is not the start of string) and if it matches
	        // field delimiter. If id does not, then we know
	        // that this delimiter is a row delimiter.
	        if (strMatchedDelimiter.length && (strMatchedDelimiter != strDelimiter)) {
	            // Since we have reached a new row of data,
	            // add an empty row to our data array.
	            arrData.push([]);
	        }
	        // Now that we have our delimiter out of the way,
	        // let's check to see which kind of value we
	        // captured (quoted or unquoted).
	        if (arrMatches[2]) {
	            // We found a quoted value. When we capture
	            // this value, unescape any double quotes.
	            var strMatchedValue = arrMatches[2].replace(
	            new RegExp("\"\"", "g"), "\"");
	        } else {
	            // We found a non-quoted value.
	            var strMatchedValue = arrMatches[3];
	        }
	        // Now that we have our value string, let's add
	        // it to the data array.
	        arrData[arrData.length - 1].push(strMatchedValue);
	    }
	    // Return the parsed data.
	    return (arrData);
	};
  
  
  
  
  var ISOCtryCode =   [
   {
     "COUNTRY_CD": "CG"
   },
   {
     "COUNTRY_CD": "PR"
   },
   {
     "COUNTRY_CD": "RU"
   },
   {
     "COUNTRY_CD": "GB"
   },
   {
     "COUNTRY_CD": "CA"
   },
   {
     "COUNTRY_CD": "IM"
   },
   {
     "COUNTRY_CD": "PK"
   },
   {
     "COUNTRY_CD": "KR"
   },
   {
     "COUNTRY_CD": "JP"
   },
   {
     "COUNTRY_CD": "AE"
   },
   {
     "COUNTRY_CD": "NZ"
   },
   {
     "COUNTRY_CD": "BR"
   },
   {
     "COUNTRY_CD": "SG"
   },
   {
     "COUNTRY_CD": "MY"
   },
   {
     "COUNTRY_CD": "IT"
   },
   {
     "COUNTRY_CD": "MX"
   },
   {
     "COUNTRY_CD": "HK"
   },
   {
     "COUNTRY_CD": "TT"
   },
   {
     "COUNTRY_CD": "VG"
   },
   {
     "COUNTRY_CD": "TW"
   },
   {
     "COUNTRY_CD": "BO"
   },
   {
     "COUNTRY_CD": "DE"
   },
   {
     "COUNTRY_CD": "TH"
   },
   {
     "COUNTRY_CD": "PG"
   },
   {
     "COUNTRY_CD": "LB"
   },
   {
     "COUNTRY_CD": "CL"
   },
   {
     "COUNTRY_CD": "VN"
   },
   {
     "COUNTRY_CD": "ZA"
   },
   {
     "COUNTRY_CD": "UY"
   },
   {
     "COUNTRY_CD": "ID"
   },
   {
     "COUNTRY_CD": "LK"
   },
   {
     "COUNTRY_CD": "UA"
   },
   {
     "COUNTRY_CD": "TR"
   },
   {
     "COUNTRY_CD": "FR"
   },
   {
     "COUNTRY_CD": "BB"
   },
   {
     "COUNTRY_CD": "IE"
   },
   {
     "COUNTRY_CD": "PH"
   },
   {
     "COUNTRY_CD": "BE"
   },
   {
     "COUNTRY_CD": "AU"
   },
   {
     "COUNTRY_CD": "HU"
   },
   {
     "COUNTRY_CD": "FI"
   },
   {
     "COUNTRY_CD": "SA"
   },
   {
     "COUNTRY_CD": "ES"
   },
   {
     "COUNTRY_CD": "BY"
   },
   {
     "COUNTRY_CD": "MV"
   },
   {
     "COUNTRY_CD": "MD"
   },
   {
     "COUNTRY_CD": "JO"
   },
   {
     "COUNTRY_CD": "SY"
   },
   {
     "COUNTRY_CD": "NG"
   },
   {
     "COUNTRY_CD": "FJ"
   },
   {
     "COUNTRY_CD": "SE"
   },
   {
     "COUNTRY_CD": "NO"
   },
   {
     "COUNTRY_CD": "HR"
   },
   {
     "COUNTRY_CD": "IL"
   },
   {
     "COUNTRY_CD": "GR"
   },
   {
     "COUNTRY_CD": "TO"
   },
   {
     "COUNTRY_CD": "EC"
   },
   {
     "COUNTRY_CD": "PL"
   },
   {
     "COUNTRY_CD": "MO"
   },
   {
     "COUNTRY_CD": "PE"
   },
   {
     "COUNTRY_CD": "AW"
   },
   {
     "COUNTRY_CD": "GH"
   },
   {
     "COUNTRY_CD": "BA"
   },
   {
     "COUNTRY_CD": "GG"
   },
   {
     "COUNTRY_CD": "SK"
   },
   {
     "COUNTRY_CD": "WS"
   },
   {
     "COUNTRY_CD": "TG"
   },
   {
     "COUNTRY_CD": "VE"
   },
   {
     "COUNTRY_CD": "PT"
   },
   {
     "COUNTRY_CD": "AT"
   },
   {
     "COUNTRY_CD": "DK"
   },
   {
     "COUNTRY_CD": "RO"
   },
   {
     "COUNTRY_CD": "LI"
   },
   {
     "COUNTRY_CD": "NC"
   },
   {
     "COUNTRY_CD": "BG"
   },
   {
     "COUNTRY_CD": "TN"
   },
   {
     "COUNTRY_CD": "KG"
   },
   {
     "COUNTRY_CD": "SV"
   },
   {
     "COUNTRY_CD": "CK"
   },
   {
     "COUNTRY_CD": "SI"
   },
   {
     "COUNTRY_CD": "BH"
   },
   {
     "COUNTRY_CD": "CD"
   },
   {
     "COUNTRY_CD": "SC"
   },
   {
     "COUNTRY_CD": "SN"
   },
   {
     "COUNTRY_CD": "EG"
   },
   {
     "COUNTRY_CD": "GE"
   },
   {
     "COUNTRY_CD": "NP"
   },
   {
     "COUNTRY_CD": "SX"
   },
   {
     "COUNTRY_CD": "DO"
   },
   {
     "COUNTRY_CD": "LU"
   },
   {
     "COUNTRY_CD": "IQ"
   },
   {
     "COUNTRY_CD": "HN"
   },
   {
     "COUNTRY_CD": "MT"
   },
   {
     "COUNTRY_CD": "BD"
   },
   {
     "COUNTRY_CD": "BS"
   },
   {
     "COUNTRY_CD": "LY"
   },
   {
     "COUNTRY_CD": "US"
   },
   {
     "COUNTRY_CD": "IN"
   },
   {
     "COUNTRY_CD": "KY"
   },
   {
     "COUNTRY_CD": "CO"
   },
   {
     "COUNTRY_CD": "KZ"
   },
   {
     "COUNTRY_CD": "AR"
   },
   {
     "COUNTRY_CD": "MU"
   },
   {
     "COUNTRY_CD": "CY"
   },
   {
     "COUNTRY_CD": "LV"
   },
   {
     "COUNTRY_CD": "EE"
   },
   {
     "COUNTRY_CD": "LT"
   },
   {
     "COUNTRY_CD": "PA"
   },
   {
     "COUNTRY_CD": "AD"
   },
   {
     "COUNTRY_CD": "CH"
   },
   {
     "COUNTRY_CD": "CN"
   },
   {
     "COUNTRY_CD": "NL"
   },
   {
     "COUNTRY_CD": "AM"
   },
   {
     "COUNTRY_CD": "CZ"
   },
   {
     "COUNTRY_CD": "CW"
   },
   {
     "COUNTRY_CD": "KW"
   },
   {
     "COUNTRY_CD": "CR"
   },
   {
     "COUNTRY_CD": "KH"
   },
   {
     "COUNTRY_CD": "GT"
   },
   {
     "COUNTRY_CD": "KE"
   },
   {
     "COUNTRY_CD": "MG"
   },
   {
     "COUNTRY_CD": "TZ"
   },
   {
     "COUNTRY_CD": "OM"
   },
   {
     "COUNTRY_CD": "BM"
   },
   {
     "COUNTRY_CD": "AF"
   },
   {
     "COUNTRY_CD": "PF"
   },
   {
     "COUNTRY_CD": "QA"
   },
   {
     "COUNTRY_CD": "HT"
   },
   {
     "COUNTRY_CD": "TC"
   },
   {
     "COUNTRY_CD": "JE"
   }
 ]
  
  
  
  
   
}]);


