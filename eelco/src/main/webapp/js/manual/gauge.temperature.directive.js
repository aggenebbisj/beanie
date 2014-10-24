var manualBrewing = angular.module('breweryApp');

manualBrewing.directive('temperature', function () {

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
                redFrom: 90, redTo: 100,
                yellowFrom: 75, yellowTo: 90,
                minorTicks: 5
            };
            scope.$watch('data', function (v) {
                if (typeof v !== 'undefined') {
                    var data = google.visualization.arrayToDataTable(v);
                    chart.draw(data, options);
                }
            }, true);
        }};
});