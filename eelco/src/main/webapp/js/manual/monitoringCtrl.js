angular.module('breweryApp').controller('ManualBrewingMonitorCtrl', function ($scope, $rootScope, refreshService, websocketService) {
    'use strict';
    
    $scope.readings = [
        ['Label', 'Value'],
        ['Temperature', 0],
        ['Volume', 0]
    ];

    websocketService.onopen(function (event) {
        var endpoint = $rootScope.resourcePath + 'brewer/kettle';
        refreshService.refreshReadings(endpoint, function (data) {
            $scope.readings[1][1] = data.temperature && data.temperature.value || 0;
            $scope.readings[2][1] = data.ingredients && sumVolumes(data.ingredients) || 0;
            
            function sumVolumes(ingredients) {
                return ingredients.reduce(function (a, b) {
                    return (a.volume && a.volume.value || a) + b.volume.value;
                }, 0);
            }
        });
    });

    websocketService.onmessage(function (event) {
        console.log(event);
        var evt = JSON.parse(event.data);
        switch (evt.event) {
            case 'ingredient added':
                $scope.readings[2][1] += evt.ingredient.volume.value;
                break;
            case 'temperature changed':
                $scope.readings[1][1] = evt.temperature.value;
                break;
            case 'kettle emptied':
                $scope.readings[2][1] = 0;
                break;
            default:
                console.log('default' + evt);
        }
        $scope.$apply();
    });
});
