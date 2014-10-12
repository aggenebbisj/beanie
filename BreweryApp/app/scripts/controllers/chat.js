'use strict';

angular.module('breweryApp')
    .controller('ChatCtrl', function ($scope) {
        $scope.error = {
            value: false,
            text: ''
        };

        $scope.readings = {
            'gauge1': 40
        };



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