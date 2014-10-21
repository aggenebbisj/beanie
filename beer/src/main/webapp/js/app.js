'use strict';

angular.module('breweryApp', ['ngRoute'])
        .config(function ($routeProvider) {
            $routeProvider
                    .when('/', {
                        templateUrl: 'views/main.html',
                        controller: 'MainCtrl'
                    })
                    .when('/manualbrewing', {
                        templateUrl: 'views/manualbrewing.html',
                        controller: 'ManualBrewingCtrl'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
        })
        .run(function ($rootScope) {
            $rootScope.serverUrl = 'localhost:8080/beer/';
            $rootScope.resourcePath = 'http://' + $rootScope.serverUrl + 'resources/';
            $rootScope.wsUri = 'ws://' + $rootScope.serverUrl + 'sockets/';
        });



