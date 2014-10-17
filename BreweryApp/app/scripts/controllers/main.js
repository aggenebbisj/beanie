'use strict';


angular.module('breweryApp')
  .controller('MainCtrl', function ($scope, $rootScope) {

        $rootScope.serverUrl = "http://localhost:8080";

        $scope.updateUrl= function (url) {
            $rootScope.serverUrl = url;
        };

  });
