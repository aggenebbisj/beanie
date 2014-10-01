'use strict';

/**
 * @ngdoc function
 * @name breweryApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the breweryApp
 */
angular.module('breweryApp')
    .controller('HeaderController', function ($scope, $location) {
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };
    });