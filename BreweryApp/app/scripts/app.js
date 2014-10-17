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
            .when('/monitor', {
                templateUrl: '../views/live.html',
                controller: 'ChatCtrl'
            })
            .when('/recipe', {
                templateUrl: '../views/recipe.html',
                controller: 'RestCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });


