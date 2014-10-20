'use strict';
var test = [];
angular.module('breweryApp')
    .controller('MonitorCtrl', function ($scope, $rootScope) {
        $scope.readings = {
            'capacity': 0,
            'temperature': 0
        }

        $scope.error = {
            value: false,
            text: ''
        };
        
        if (typeof $rootScope.serverUrl === 'undefined') {
            $rootScope.serverUrl = 'http://localhost:8080'
        }

        var contextRoot = window.location.pathname.split('/')[1];
        var wsUri = "ws://" + document.location.host + "/" + contextRoot + "/brewer";
        
        connect();

        function connect() {
            var websocket = new WebSocket(wsUri);
            websocket.onopen = function (evt) {};

            websocket.onmessage = function (evt) {
                console.log("evt" + evt);
                var message = JSON.parse(evt.data);
                console.log("message: " + message);
                test.push(message);

                if (message.temperature) {
                    console.log('pim: ' + message.temperature.value);
                    $scope.readings['temperature'] = message.temperature.value;
                    $scope.$apply();
                } else if (message.ingredient) {
                    $scope.readings['capacity'] += message.ingredient.volume.value;
                }
            };

            websocket.onerror = function (evt) {
            };
        }
    });