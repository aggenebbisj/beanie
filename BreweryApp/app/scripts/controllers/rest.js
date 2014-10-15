'use strict';

angular.module('breweryApp')
    .controller('RestCtrl', function ($scope, restService) {
        console.log($scope);
        console.log(restService.fullName);
        var serverUrl = 'http://localhost:9000';

        $scope.operations = {
            yeast: {
                'operation': 'add',
                'name': 'yeast',
                'unit': 'liter',
                'value': 0,
                activate: function (object) {
                    console.log(object);
                    if (object.value !== 0) {
                        sendUrlAsObject(serverUrl + '/' + 'ingredient/' + object.name + '/' + object.value + '/' + object.unit);
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
                        sendUrlAsObject(serverUrl + '/' + object.name + '/' + object.value + '/' + object.unit);
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
                        sendUrlAsObject(serverUrl + '/' + object.name + '/' + 'PT' + object.value + 'M'); //TODO fix to do this automatically
                    }
                }}
        };


        function getUrlAsObject(url) {
            restService.get(url)
                .success(function (response) {
                    if (angular.isObject(response)) {
                        console.log(response);
                    }
                });
        }

        function sendUrlAsObject(url) {
            restService.post(url)
                .success(function (response) {
                    if (angular.isObject(response)) {
                        console.log(response);
                    }
                });
        }

//        getUrlAsObject('http://localhost:9000');
//        console.log($scope.test);


    });