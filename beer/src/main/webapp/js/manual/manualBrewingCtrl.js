var manualBrewing = angular.module('breweryApp');

manualBrewing.controller('ManualBrewingCtrl', function ($scope, $rootScope, restService, refreshService) {
    'use strict';
    var ingredientsResourceUrl = $rootScope.resourcePath + 'brewer/ingredients';
    var kettleResourceUrl = $rootScope.resourcePath + 'brewer/kettle';

    $scope.operations = {
        flush: {
            activate: function () {
                restService.del(ingredientsResourceUrl);
                refreshService.refreshReadings(kettleResourceUrl);
            }
        },
        ingredient: {
            activate: function (type, newValue) {
                if (newValue !== 0) {
                    var ingredient = restService.createIngredient(type, newValue, 'liter');
                    restService.postWithData(ingredientsResourceUrl, ingredient);
                }
            }
        },
        temperature: {
            activate: function (newValue) {
                var temperature = restService.createTemperature(newValue, 'celsius');
                restService.putWithData(kettleResourceUrl + '/temperature', temperature);
            }
        }
    };

});

manualBrewing.directive('gauge', function () {

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
                if (typeof v != 'undefined') {
                    var data = google.visualization.arrayToDataTable(v);
                    chart.draw(data, options);
                }
            }, true);
        }}
});


google.setOnLoadCallback(function () {
    angular.bootstrap(document.body, ['breweryApp']);
});
google.load('visualization', '1', {packages: ['gauge']});