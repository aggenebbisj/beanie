'use strict';


angular.module('breweryApp')
  .controller('MainCtrl', function ($scope, $rootScope) {

        $rootScope.serverUrl = "http://localhost:9000";

        $scope.updateUrl= function (url) {
            $rootScope.serverUrl = url;
        };

  });
