'use strict';
var test;
angular.module('breweryApp')
    .controller('MonitorCtrl', function ($scope, $rootScope) {
        $scope.reading = {
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
            websocket.onopen = function (evt) {
                $scope.readings = {
                    'capacity': 0,
                    'temperature': 0
                };
            };

            websocket.onmessage = function (evt) {
                var message = JSON.parse(evt.data);
                console.log('remko');
                console.log(message);
                test = message;
                console.log('pim2');
                console.log(message.ingredient.volume);
                $scope.readings = {
                    'capacity': $scope.readings['capacity'] + (message.ingredient.volume.value || 0),
                    'temperature': (message.kettle && message.kettle.value) || $scope.readings['temperature']
                };
            };

            websocket.onerror = function (evt) {
            };
        }
    });