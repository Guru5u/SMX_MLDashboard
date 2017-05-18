angular.module('ngCanvGauge', [])
  .directive('canvGauge', ['$timeout', function ($timeout) {
    return {
      restrict: 'EA',
      scope: {
        id: '@',
        class: '@',
        width: '=',
        height: '=',        
        glow: '=',
        title: '@',
        units: '@',
        value: '@',
        strokeTicks: '=',
        highlights: '=',        
        options: '='
      },
      template: '<canvas id="{{id}}"></canvas>',
      link: function (scope, element, attrs) {
        $timeout(function () {
          var options = {
        	renderTo: scope.id,
            width: scope.width,
            height: scope.height,
            glow: scope.glow,
            title: scope.title,
            units: scope.units,
            //value: scope.value
          };

          if (scope.options) {
              for (var key in scope.options) {
                  options[key] = scope.options[key];
              }
          }

          //console.log("Inside ng-canvgauge... " + JSON.stringify(options));
          var graph = new Gauge(options);
          graph.draw();
          
          scope.$watch('value', function (updatedValue) {
            if (updatedValue !== undefined) {
              graph.setValue(scope.value, updatedValue);
            }
          }, true);

        });
      }
    };
  }]);