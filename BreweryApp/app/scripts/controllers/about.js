'use strict';

/**
 * @ngdoc function
 * @name breweryApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the breweryApp
 */
angular.module('breweryAppApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });