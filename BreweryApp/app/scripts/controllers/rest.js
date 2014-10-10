'use strict';

angular.module('breweryApp')
    .controller('RestCtrl', function ($scope, restService) {
        console.log($scope);
        console.log(restService.fullName);


        function getUrlAsObject(url) {
            restService.get(url)
                .success(function (response) {
                    if (angular.isObject(response)) {
                        $scope.test = response;
                    }
                });
        }

        getUrlAsObject('http://localhost:9000');
        console.log($scope.test);


    });