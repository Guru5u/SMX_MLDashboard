var sparkApp = angular.module('SparkLineApp', []);

sparkApp.directive("sparklinechart", function () {

    return {
        restrict: "E",
        scope: {
            data: "@"
        },
        compile: function (tElement, tAttrs, transclude) {
            tElement.replaceWith("<span>" + tAttrs.data + "</span>");
            return function (scope, element, attrs) {
                attrs.$observe("data", function (newValue) {
                    console.log("inside: " + newValue);
                    element.html(newValue);
                    element.sparkline('html', { type: 'line', width: '100%', height: '80px', barWidth: 21, barColor: 'blue' });
                });
            };
        }
    };
});