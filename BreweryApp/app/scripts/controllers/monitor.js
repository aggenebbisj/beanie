'use strict';

angular.module('breweryApp')
    .controller('MonitorCtrl', function ($scope, $rootScope) {
        $scope.reading = {
            'capacity': 0,
            'gauge2': 0,
            'gauge3': 0,
            'gauge4': 0
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
                    'gauge2': 0,
                    'gauge3': 0,
                    'gauge4': 0
                };
                // writeToScreen("CONNECTED to " + wsUri);
            };

            websocket.onmessage = function (evt) {
                // writeToScreen("RECEIVED: " + evt.data);
                var message = JSON.parse(evt.data);
                console.log('remko');
                console.log(message);
                $scope.readings = {
                    'capacity': $scope.readings['capacity'] + message.name.volume.value || 0,
                    'gauge2': '30',
                    'gauge3': '100',
                    'gauge4': '0'
                };
            };

            websocket.onerror = function (evt) {
                // writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            };
        }
    });