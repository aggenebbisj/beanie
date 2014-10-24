var manualBrewing = angular.module('breweryApp');
    'use strict';

manualBrewing.controller('ManualBrewingMonitorCtrl', function ($scope, $rootScope, refreshService) {
    $rootScope.brewer = {};
    $rootScope.brewer.readings = {
        'capacity': 0,
        'temperature': 0,
        'locked': 0
    };

    $scope.manualKettleReadings = [
        ['Label', 'Value'],
        ['Temperature', 0],
        ['Volume', 0]
    ];

    refreshService.refreshReadings($rootScope.resourcePath + 'brewer/kettle');

    connect();

    function connect() {
        var websocket = new WebSocket($rootScope.wsUri + 'monitor');
        websocket.onmessage = function (evtJson) {
            console.log(evtJson.data);
            var evt = JSON.parse(evtJson.data);
            switch (evt.event) {
                case 'ingredient added':
                    $rootScope.brewer.readings.capacity += evt.ingredient.volume.value;
                    break;
                case 'temperature changed':
                    $rootScope.brewer.readings.temperature = evt.temperature.value;
                    break;
                case 'kettle emptied':
                    $rootScope.brewer.readings.capacity = 0;
                    break;
                default:
                    console.log('default' + evt);

            }
            updateReadings();
            $('#messages').append("RECEIVED: " + evtJson.data + '<br/>');
        };
    }

    function updateReadings() {
        $scope.manualKettleReadings[1][1] = $rootScope.brewer.readings.temperature;
        $scope.manualKettleReadings[2][1] = $rootScope.brewer.readings.capacity;
        $scope.$apply();
        $rootScope.$apply();
    }
});
