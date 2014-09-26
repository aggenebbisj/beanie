'use strict';

/**
 * @ngdoc function
 * @name breweryApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the breweryApp
 */
angular.module('breweryAppApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
