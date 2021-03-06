// Requires jQuery from http://jquery.com/
// and jQuerySparklines from http://omnipotent.net/jquery.sparkline

// AngularJS directives for jquery sparkline
//angular.module('sparkline', []);
 
angular.module('ngSparkline', [])
    .directive('sparkLine', [function () {
        //'use strict';
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, elem, attrs, ngModel) {
 
                 var opts={};
                 //TODO: Use $eval to get the object
                opts.type = attrs.type || 'line';

                scope.$watch(attrs.ngModel, function () {
                	console.log("inside ngModel...");
                    render();
                });
                
                scope.$watch(attrs.opts, function(){
                  render();
                });
                
                var render = function () {
                    var model;
                    if(attrs.opts) angular.extend(opts, angular.fromJson(attrs.opts));
                    console.log(opts);
                    // Trim trailing comma if we are a string
                    angular.isString(ngModel.$viewValue) ? model = ngModel.$viewValue.replace(/(^,)|(,$)/g, "") : model = ngModel.$viewValue;
                    var data;
                    // Make sure we have an array of numbers
                    angular.isArray(model) ? data = model : data = model.split(',');
                    console.log("Inside of sparkline---> " + JSON.stringify(data));
                    $(elem).sparkline(data, opts);
                };
            }
        }
    }]);