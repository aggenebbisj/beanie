'use strict';

/**
 * @ngdoc overview
 * @name breweryApp
 * @description
 * # breweryApp
 *
 * Main module of the application.
 */
angular
    .module('breweryApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ngJustGage'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/chat', {
                templateUrl: 'views/chat.html',
                controller: 'ChatCtrl'
            })
            .when('/rest', {
                templateUrl: 'views/rest.html',
                controller: 'RestCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });


