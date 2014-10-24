var manualBrewing = angular.module('breweryApp');

manualBrewing.directive('volume', function () {

    return {
        restrict: 'E',
        replace: true,
        transclude: true,
        scope: {data: '='
        },
        template: '<div class="gauge"></div>',
        link: function (scope, element, attrs) {
            var chart = new google.visualization.Gauge(element[0]);
            var options = {
                width: 400, height: 200,
                redFrom: 900, redTo: 1000,
                yellowFrom: 750, yellowTo: 900,
                minorTicks: 5, max: 1000, animation: {
                    duration: 500,
                    easing: 'linear',
                },
            };
            scope.$watch('data', function (v) {
                if (typeof v !== 'undefined') {
                    var data = google.visualization.arrayToDataTable(v);
                    chart.draw(data, options);
                }
            }, true);
        }};
});