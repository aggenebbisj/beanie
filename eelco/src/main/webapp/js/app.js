'use strict';
angular.module('breweryApp', [])
        .run(function ($rootScope) {
            $rootScope.serverUrl = 'localhost:8080/eelco/';
            $rootScope.resourcePath = 'http://' + $rootScope.serverUrl + 'resources/';
            $rootScope.wsUri = 'ws://' + $rootScope.serverUrl + 'sockets/';
        });


google.load('visualization', '1', {packages: ['gauge']});


