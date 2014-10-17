'use strict';

angular.module('breweryApp')
    .controller('ChatCtrl', function ($scope, restService, $rootScope) {
        $scope.error = {
            value: false,
            text: ''
        };
        if (typeof $rootScope.serverUrl === 'undefined') {
            $rootScope.serverUrl = 'http://localhost:8080'
        }

        $scope.readings = {
            'gauge1': '50',
            'gauge2': '30',
            'gauge3': '100',
            'gauge4': '0'
        };


        $scope.messages = [];

        function addToMessages(text, isSuccessful) {
            var time = new Date();
            var message = {
                'time': time.toLocaleString(),
                'message': text,
                'success': isSuccessful
            };
            $scope.messages.push(message);
        }

        $scope.operations = {
            yeast: {
                'operation': 'add',
                'name': 'yeast',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        sendAsPostAndReport($rootScope.serverUrl + '/' + 'ingredient/' + object.name + '/' + object.value + '/' + object.unit);
                    }
                }},
            temperature: {
                'operation': 'change',
                'unit': 'celsius',
                'name': 'temperature',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        sendAsPostAndReport($scope.serverUrl + '/' + object.name + '/' + object.value + '/' + object.unit);
                    }

                }},
            waiting: {
                'operation': '',
                'unit': 'minutes',
                'name': 'wait',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        sendAsPostAndReport($scope.serverUrl + '/' + object.name + '/' + 'PT' + object.value + 'M'); //TODO fix to do this automatically
                    }
                }}
        };

        function sendAsPostAndReport(url) {
            restService.post(url)
                .success(function (response) {
                    if (angular.isObject(response)) {
                        addToMessages(response, true);
                        console.log("success: " + response);
                    }
                }).error(function (response) {
                    addToMessages(response, false);
                    console.log("failed:  " + response);

                });
        }

       /* var wsUri = 'ws://' + document.location.host
            + document.location.pathname.substr(0, document.location.pathname.indexOf('/faces'))
            + '/websocket';
        console.log(wsUri);
        var websocket = new WebSocket(wsUri);

        var username;
        websocket.onopen = function (evt) {
            onOpen(evt);
        };
        websocket.onmessage = function (evt) {
            onMessage(evt);
        };
        websocket.onerror = function (evt) {
            onError(evt);
        };
        websocket.onclose = function (evt) {
            onClose(evt);
        };
        var output = document.getElementById('output');
        var textField = document.getElementById('textField');
        var users = document.getElementById('users');
        var chatlog = document.getElementById('chatlog');

        function join() {
            username = textField.value;
            websocket.send(username + ' joined');
        }

        function send_message() {
            websocket.send(username + ': ' + textField.value);
        }

        function onOpen() {
            writeToScreen('CONNECTED');
        }

        function onClose() {
            writeToScreen('DISCONNECTED');
        }

        function onMessage(evt) {
            writeToScreen('RECEIVED: ' + evt.data);
            if (evt.data.indexOf('joined') !== -1) {
                users.innerHTML += evt.data.substring(0, evt.data.indexOf(' joined')) + '\n';
            } else {
                chatlog.innerHTML += evt.data + '\n';
            }
        }

        function onError(evt) {
            $scope.error.value = true;
            $scope.error.text = evt;
        }

        function disconnect() {
            websocket.close();
        }

        function writeToScreen(message) {
            $scope.error.value = true;
            $scope.error.text = message;
        }*/


    });