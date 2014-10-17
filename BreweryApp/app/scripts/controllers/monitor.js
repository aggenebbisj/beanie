'use strict';

angular.module('breweryApp')
    .controller('MonitorCtrl', function ($scope, $rootScope) {
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
    });