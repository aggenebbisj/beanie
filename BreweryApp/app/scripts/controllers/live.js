'use strict';

angular.module('breweryApp')
    .controller('LiveCtrl', function ($scope, restService, $rootScope) {
        $scope.error = {
            value: false,
            text: ''
        };
        if (typeof $rootScope.serverUrl === 'undefined') {
            $rootScope.serverUrl = 'http://localhost:8080'; //TODO: has to be in a factory
            $rootScope.resourcePath = '/brewery/resources/kettle/';
        }

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
            water: {
                'index': 0,
                'operation': 'add',
                'name': 'water',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        var url = $rootScope.serverUrl + $rootScope.resourcePath + 'ingredients';
                        sendAsPostAndReport(url, restService.createIngredient(object));
                    }
                }},
            barley: {
                'index': 1,
                'operation': 'add',
                'name': 'barley',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        var url = $rootScope.serverUrl + $rootScope.resourcePath + 'ingredients';
                        sendAsPostAndReport(url, restService.createIngredient(object));
                    }
                }},
            hops: {
                'index': 2,
                'operation': 'add',
                'name': 'hops',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        var url = $rootScope.serverUrl + $rootScope.resourcePath + 'ingredients';
                        sendAsPostAndReport(url, restService.createIngredient(object));
                    }
                }},
            yeast: {
                'index': 3,
                'operation': 'add',
                'name': 'yeast',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        var url = $rootScope.serverUrl + $rootScope.resourcePath + 'ingredients';
                        sendAsPostAndReport(url, restService.createIngredient(object));
                    }
                }},
            temperature: {
                'index': 4,
                'operation': 'change',
                'unit': 'celsius',
                'name': 'temperature',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        var url = $rootScope.serverUrl + $rootScope.resourcePath + 'temperature';
                        sendAsPutAndReport(url, restService.createTemperature(object));
                    }
                }},
            waiting: {
                'index': 5,
                'operation': '',
                'unit': 'minutes',
                'name': 'wait',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        var url = $rootScope.serverUrl + $rootScope.resourcePath;
                        sendAsPostAndReport(url, restService.createWaitingMessage(object));
                    }
                }}
        };

        function sendAsPostAndReport(url, object) {
            restService.postWithData(url, object)
                .success(function (response) {
                    addToMessages(response, true);
                    console.log('success: ' + response);
                }).error(function (response) {
                    addToMessages(response, false);
                    console.log('failed:  ' + response);

                });
        }

        function sendAsPutAndReport(url, object) {
            restService.putWithData(url, object)
                .success(function (response) {
                    if (angular.isObject(response)) {
                        addToMessages(response, true);
                        console.log('success: ' + response);
                    }
                }).error(function (response) {
                    addToMessages(response, false);
                    console.log('failed:  ' + response);

                });
        }


    });